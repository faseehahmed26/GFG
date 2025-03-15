import os
import re
import json
import pandas as pd
import numpy as np
from typing import List, Dict, Tuple, Any, Optional
from pathlib import Path
import logging
import time
from tqdm import tqdm
import nltk
from nltk.tokenize import sent_tokenize, TextTilingTokenizer
from nltk.corpus import stopwords

# Document parsing
import fitz  # PyMuPDF for better PDF handling
import docx

# Embedding and retrieval
from sentence_transformers import SentenceTransformer
import faiss

# LLM for analysis
from transformers import AutoModelForCausalLM, AutoTokenizer
import torch

# Setup logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
    handlers=[logging.StreamHandler()]
)
logger = logging.getLogger('DataSharingAnalyzer')

class DocumentParser:
    """Handles parsing of PDF and DOC/DOCX files to extract text and tables."""
    
    def __init__(self):
        logger.info("Initializing document parser...")
    
    def parse_document(self, file_path: str) -> Dict[str, Any]:
        """
        Parse document based on file extension and extract content
        
        Args:
            file_path: Path to the document file
            
        Returns:
            Dictionary containing extracted text, tables, and metadata
        """
        file_path = Path(file_path)
        if not file_path.exists():
            raise FileNotFoundError(f"File not found: {file_path}")
        
        file_extension = file_path.suffix.lower()
        
        if file_extension == '.pdf':
            return self._parse_pdf(file_path)
        elif file_extension in ['.doc', '.docx']:
            return self._parse_docx(file_path)
        else:
            raise ValueError(f"Unsupported file format: {file_extension}")
    
    def _parse_pdf(self, file_path: Path) -> Dict[str, Any]:
        """Parse PDF file using PyMuPDF for better extraction quality."""
        try:
            logger.info(f"Parsing PDF file with PyMuPDF: {file_path}")
            
            # Open the PDF document
            doc = fitz.open(str(file_path))
            pages_text = []
            tables = []
            
            # Process each page
            for page_num, page in enumerate(doc, 1):
                # Extract text with better layout preservation
                text = page.get_text("text")  # Simple text mode
                
                # Clean up the text a bit
                text = re.sub(r'\s+', ' ', text)
                
                pages_text.append({
                    "page_num": page_num,
                    "text": text.strip()
                })
                
                # Table detection and extraction
                try:
                    tables_on_page = page.find_tables()
                    
                    for i, table in enumerate(tables_on_page):
                        # Convert table to structured data
                        rows = []
                        for cells in table.extract():
                            row_data = {}
                            for j, cell_text in enumerate(cells):
                                row_data[f"col_{j}"] = cell_text
                            rows.append(row_data)
                        
                        tables.append({
                            "table_id": len(tables) + 1,
                            "page_num": page_num,
                            "content": rows
                        })
                except Exception as e:
                    logger.warning(f"Error extracting tables from page {page_num}: {e}")
            
            # Close the document
            doc.close()
            
            logger.info(f"Successfully extracted {len(pages_text)} pages and {len(tables)} tables from PDF")
            
            return {
                "file_name": file_path.name,
                "file_type": "pdf",
                "pages": pages_text,
                "tables": tables
            }
            
        except Exception as e:
            logger.error(f"Error parsing PDF file {file_path}: {e}")
            raise
    
    def _parse_docx(self, file_path: Path) -> Dict[str, Any]:
        """Parse DOCX file to extract text and tables with better structure preservation."""
        try:
            logger.info(f"Parsing DOCX file: {file_path}")
            doc = docx.Document(file_path)
            
            # Track paragraphs with their styles
            structured_paragraphs = []
            
            # Extract text by paragraphs, preserving styles for better section detection
            for para in doc.paragraphs:
                if para.text.strip():
                    # Get paragraph style name (helps identify headings)
                    style_name = para.style.name if para.style else "Normal"
                    
                    structured_paragraphs.append({
                        "text": para.text.strip(),
                        "style": style_name,
                        # Check if it's likely a heading
                        "is_heading": style_name.startswith('Heading') or 
                                     (len(para.text) < 100 and para.text.isupper())
                    })
            
            # Extract tables with better column naming
            tables = []
            for i, table in enumerate(doc.tables):
                table_data = []
                
                # Check if first row might be headers
                has_header = False
                if table.rows and len(table.rows) > 1:
                    # Heuristic: first row is likely header if it's all non-empty and formatted differently
                    first_row = table.rows[0]
                    has_header = all(cell.text.strip() for cell in first_row.cells)
                
                # Extract headers if present
                headers = []
                if has_header:
                    for cell in table.rows[0].cells:
                        headers.append(cell.text.strip() or f"Column_{len(headers)+1}")
                
                # Process rows
                start_row = 1 if has_header else 0
                for row_idx, row in enumerate(table.rows[start_row:], start=start_row):
                    row_data = {}
                    for j, cell in enumerate(row.cells):
                        # Use header name if available, otherwise use column index
                        column_name = headers[j] if has_header and j < len(headers) else f"col_{j}"
                        row_data[column_name] = cell.text.strip()
                    table_data.append(row_data)
                
                tables.append({
                    "table_id": i + 1,
                    "content": table_data,
                    "has_header": has_header,
                    "headers": headers if has_header else []
                })
            
            # Create pages from paragraphs (approximate, as DOCX doesn't have native page info)
            # Group by headings and significant breaks
            pages_text = []
            current_page = []
            page_count = 1
            
            for para in structured_paragraphs:
                # Start a new page on major headings or after accumulating substantial content
                if (para["is_heading"] and para["style"].startswith("Heading 1")) or \
                   (len(current_page) > 10 and para["is_heading"]):
                    if current_page:
                        combined_text = " ".join([p["text"] for p in current_page])
                        pages_text.append({
                            "page_num": page_count,
                            "text": combined_text
                        })
                        page_count += 1
                        current_page = []
                
                current_page.append(para)
            
            # Add the last page if it's not empty
            if current_page:
                combined_text = " ".join([p["text"] for p in current_page])
                pages_text.append({
                    "page_num": page_count,
                    "text": combined_text
                })
            
            return {
                "file_name": file_path.name,
                "file_type": "docx",
                "pages": pages_text,
                "tables": tables,
                "paragraphs": structured_paragraphs  # Keep detailed paragraph info for better processing
            }
            
        except Exception as e:
            logger.error(f"Error parsing DOCX file {file_path}: {e}")
            raise

class TextProcessor:
    """Processes extracted text and splits it into manageable chunks."""
    
    def __init__(self, chunk_size: int = 500, chunk_overlap: int = 100):
        """
        Initialize the text processor
        
        Args:
            chunk_size: Target size of text chunks in characters
            chunk_overlap: Number of characters to overlap between chunks
        """
        self.chunk_size = chunk_size
        self.chunk_overlap = chunk_overlap
        logger.info(f"Initializing text processor with chunk size {chunk_size} and overlap {chunk_overlap}")
    
    def process_document(self, doc_content: Dict[str, Any]) -> List[Dict[str, Any]]:
        """
        Process document content and split into chunks
        
        Args:
            doc_content: Document content from parser
            
        Returns:
            List of chunks with metadata
        """
        logger.info("Processing document text and creating chunks...")
        chunks = []
        
        # Process text from pages
        for page in doc_content["pages"]:
            page_num = page["page_num"]
            text = page["text"]
            
            # Clean text
            cleaned_text = self._clean_text(text)
            
            # Split into paragraphs first
            paragraphs = self._split_into_paragraphs(cleaned_text)
            
            # Create chunks from paragraphs
            page_chunks = self._create_chunks_from_paragraphs(
                paragraphs, 
                page_num
            )
            
            chunks.extend(page_chunks)
        
        # Add tables as chunks
        for table in doc_content["tables"]:
            table_id = table["table_id"]
            table_text = self._table_to_text(table["content"])
            
            chunks.append({
                "chunk_id": f"table_{table_id}",
                "text": table_text,
                "page_num": "Unknown",  # Tables in PDFs don't always have page info
                "source": "table",
                "is_table": True,
                "context": table_text[:200] + "..." if len(table_text) > 200 else table_text
            })
        
        logger.info(f"Created {len(chunks)} chunks from document")
        return chunks
    
    def _clean_text(self, text: str) -> str:
        """Clean extracted text by removing artifacts and normalizing whitespace."""
        # Replace multiple whitespace with single space
        text = re.sub(r'\s+', ' ', text)
        
        # Remove common header/footer patterns
        text = re.sub(r'Page \d+ of \d+', '', text)
        
        # Remove unnecessary Unicode characters
        text = re.sub(r'[\x00-\x08\x0B\x0C\x0E-\x1F\x7F-\x9F]', '', text)
        
        return text.strip()
    
    
def split_into_paragraphs(text: str) -> List[str]:
    """Split text into paragraphs using a simpler approach with NLTK."""
    # Download NLTK data if needed
    try:
        nltk.data.find('tokenizers/punkt')
    except LookupError:
        nltk.download('punkt', quiet=True)
    
    # Split by paragraph breaks
    paragraphs = re.split(r'\n\s*\n', text)
    
    result = []
    for para in paragraphs:
        # Skip empty paragraphs
        if not para.strip():
            continue
            
        # Check for section headers
        header_match = re.search(r'^([A-Z][A-Z\s]+:|\d+\.\d+\s+[A-Z][\w\s]+)', para.strip())
        if header_match:
            # If it starts with a header, split it
            header = header_match.group(0)
            content = para[header_match.end():].strip()
            
            if header:
                result.append(header)
            if content:
                result.append(content)
        else:
            # Regular paragraph
            result.append(para.strip())
    
    # Filter out very short paragraphs
    return [p for p in result if len(p) > 10]
    def _create_chunks_from_paragraphs(self, paragraphs: List[str], page_num: int) -> List[Dict[str, Any]]:
        """Create overlapping chunks from paragraphs."""
        chunks = []
        current_chunk = ""
        current_paragraphs = []
        chunk_id = 1
        
        for para in paragraphs:
            # If adding this paragraph exceeds chunk size, save current chunk and start new one
            if len(current_chunk) + len(para) > self.chunk_size and current_chunk:
                chunk_text = current_chunk.strip()
                
                # Get context (first and last paragraph)
                context = (current_paragraphs[0][:100] + "..." if len(current_paragraphs[0]) > 100 else current_paragraphs[0])
                if len(current_paragraphs) > 1:
                    context += " [...] " + (current_paragraphs[-1][:100] + "..." if len(current_paragraphs[-1]) > 100 else current_paragraphs[-1])
                
                chunks.append({
                    "chunk_id": f"page_{page_num}_chunk_{chunk_id}",
                    "text": chunk_text,
                    "page_num": page_num,
                    "source": "text",
                    "is_table": False,
                    "context": context
                })
                
                # Start new chunk with overlap
                overlap_point = max(0, len(current_chunk) - self.chunk_overlap)
                current_chunk = current_chunk[overlap_point:] + " " + para
                current_paragraphs = [current_paragraphs[-1], para] if current_paragraphs else [para]
                chunk_id += 1
            else:
                current_chunk += " " + para
                current_paragraphs.append(para)
        
        # Add the last chunk if not empty
        if current_chunk.strip():
            chunk_text = current_chunk.strip()
            
            # Get context (first and last paragraph)
            context = (current_paragraphs[0][:100] + "..." if len(current_paragraphs[0]) > 100 else current_paragraphs[0])
            if len(current_paragraphs) > 1:
                context += " [...] " + (current_paragraphs[-1][:100] + "..." if len(current_paragraphs[-1]) > 100 else current_paragraphs[-1])
            
            chunks.append({
                "chunk_id": f"page_{page_num}_chunk_{chunk_id}",
                "text": chunk_text,
                "page_num": page_num,
                "source": "text",
                "is_table": False,
                "context": context
            })
        
        return chunks
    
    def _table_to_text(self, table_content: List[Dict[str, Any]]) -> str:
        """Convert table dictionary to text representation."""
        text = "TABLE CONTENT:\n"
        
        if not table_content:
            return text + "Empty table"
        
        # Get all keys from the first row
        keys = list(table_content[0].keys())
        
        # Create header
        header = " | ".join(keys)
        text += header + "\n"
        text += "-" * len(header) + "\n"
        
        # Add rows
        for row in table_content:
            row_text = " | ".join(str(row.get(k, "")) for k in keys)
            text += row_text + "\n"
        
        return text

class EmbeddingGenerator:
    """Generates embeddings for text chunks using a sentence transformer model."""
    
    def __init__(self, model_name: str = "all-MiniLM-L6-v2"):
        """
        Initialize the embedding generator with a lightweight model
        
        Args:
            model_name: Name of the sentence-transformers model to use
        """
        logger.info(f"Loading embedding model: {model_name}")
        self.model = SentenceTransformer(model_name)
        self.embedding_dim = self.model.get_sentence_embedding_dimension()
        logger.info(f"Embedding dimension: {self.embedding_dim}")
    
    def generate_embeddings(self, chunks: List[Dict[str, Any]]) -> List[Dict[str, Any]]:
        """
        Generate embeddings for all chunks
        
        Args:
            chunks: List of text chunks
            
        Returns:
            Chunks with added embedding vectors
        """
        logger.info(f"Generating embeddings for {len(chunks)} chunks...")
        
        # Extract text from chunks
        texts = [chunk["text"] for chunk in chunks]
        
        # Generate embeddings in batches to avoid memory issues
        batch_size = 32
        embeddings = []
        
        for i in tqdm(range(0, len(texts), batch_size), desc="Generating embeddings"):
            batch_texts = texts[i:i+batch_size]
            batch_embeddings = self.model.encode(batch_texts)
            embeddings.extend(batch_embeddings)
        
        # Add embeddings to chunks
        for i, chunk in enumerate(chunks):
            chunk["embedding"] = embeddings[i]
        
        return chunks
    
    def generate_query_embeddings(self, queries: List[str]) -> np.ndarray:
        """
        Generate embeddings for search queries
        
        Args:
            queries: List of query strings
            
        Returns:
            Array of query embeddings
        """
        logger.info(f"Generating query embeddings for {len(queries)} queries")
        query_embeddings = self.model.encode(queries)
        return query_embeddings

class RetrievalSystem:
    """
    Implements a vector retrieval system to find relevant chunks
    related to data sharing restrictions.
    """
    
    def __init__(self, embedding_dim: int):
        """
        Initialize the retrieval system
        
        Args:
            embedding_dim: Dimension of the embeddings
        """
        logger.info(f"Initializing retrieval system with {embedding_dim} dimensions")
        self.index = faiss.IndexFlatIP(embedding_dim)  # Inner product for cosine similarity
        self.chunks = []
    
    def add_chunks(self, chunks: List[Dict[str, Any]]):
        """
        Add chunks to the retrieval index
        
        Args:
            chunks: List of chunks with embeddings
        """
        embeddings = np.array([chunk["embedding"] for chunk in chunks]).astype(np.float32)
        self.index.add(embeddings)
        self.chunks = chunks
        logger.info(f"Added {len(chunks)} chunks to retrieval index")
    
    def search(self, query_embeddings: np.ndarray, k: int = 10, threshold: float = 0.5) -> List[Dict[str, Any]]:
        """
        Search for relevant chunks using query embeddings
        
        Args:
            query_embeddings: Embeddings of the search queries
            k: Number of results to return per query
            threshold: Minimum similarity score threshold
            
        Returns:
            List of relevant chunks with similarity scores
        """
        logger.info(f"Searching for top {k} relevant chunks")
        
        # Ensure query_embeddings is 2D
        if len(query_embeddings.shape) == 1:
            query_embeddings = query_embeddings.reshape(1, -1)
        
        # Normalize vectors for cosine similarity
        faiss.normalize_L2(query_embeddings)
        
        # Search index
        scores, indices = self.index.search(query_embeddings.astype(np.float32), k=k)
        
        # Collect results above threshold
        results = []
        seen_chunks = set()  # To avoid duplicates
        
        for query_idx, (query_scores, query_indices) in enumerate(zip(scores, indices)):
            for score, idx in zip(query_scores, query_indices):
                if idx < 0 or idx >= len(self.chunks):  # Skip invalid indices
                    continue
                    
                if score < threshold:  # Skip results below threshold
                    continue
                
                chunk = self.chunks[idx]
                chunk_id = chunk["chunk_id"]
                
                if chunk_id in seen_chunks:  # Skip duplicates
                    continue
                
                seen_chunks.add(chunk_id)
                
                results.append({
                    **chunk,
                    "similarity_score": float(score)
                })
        
        # Sort by similarity score (descending)
        results.sort(key=lambda x: x["similarity_score"], reverse=True)
        
        logger.info(f"Found {len(results)} relevant chunks above threshold {threshold}")
        return results

class DataSharingAnalyzer:
    """
    Analyzes retrieved chunks to identify data sharing restrictions
    using only LLM analysis of top relevant chunks.
    """
    
    def __init__(self):
        """Initialize the LLM-based data sharing analyzer"""
        logger.info("Initializing LLM-based data sharing analyzer")
        
        # Load the Microsoft Phi-2 model which is lightweight enough for CPU
        logger.info("Loading Phi-2 model for analysis...")
        try:
            # Choose Phi-2 which is efficient for CPU usage
            self.model_name = "microsoft/phi-2"
            self.tokenizer = AutoTokenizer.from_pretrained(self.model_name)
            
            # Load in 4-bit to reduce memory usage (essential for CPU)
            self.model = AutoModelForCausalLM.from_pretrained(
                self.model_name,
                device_map="auto",  # Will use CPU by default if no GPU
                load_in_4bit=True,  # Reduces memory footprint to ~2-3GB
            )
            logger.info("Phi-2 model loaded successfully")
        except Exception as e:
            logger.error(f"Failed to load LLM: {e}")
            raise
    
    def analyze_chunks(self, relevant_chunks: List[Dict[str, Any]], max_chunks: int = 20) -> List[Dict[str, Any]]:
        """
        Analyze the top N most relevant chunks using the LLM
        
        Args:
            relevant_chunks: List of relevant chunks from retrieval system
            max_chunks: Maximum number of chunks to analyze with LLM
            
        Returns:
            List of analyzed chunks with restriction details and confidence scores
        """
        # Limit to top chunks to avoid excessive processing time
        top_chunks = relevant_chunks[:max_chunks]
        logger.info(f"Analyzing top {len(top_chunks)} relevant chunks with Phi-2")
        
        results = []
        
        # Process each chunk with the LLM
        for chunk in tqdm(top_chunks, desc="Analyzing with LLM"):
            analysis = self._analyze_with_llm(chunk)
            
            if analysis["has_restrictions"]:
                results.append({
                    "paragraph": chunk["text"],
                    "confidence": analysis["confidence"],
                    "page": chunk["page_num"],
                    "restriction_type": analysis["restriction_type"],
                    "context": chunk["context"]
                })
        
        # Sort by confidence (descending)
        results.sort(key=lambda x: x["confidence"], reverse=True)
        
        logger.info(f"Found {len(results)} chunks with data sharing restrictions")
        return results
    
    def _analyze_with_llm(self, chunk: Dict[str, Any]) -> Dict[str, Any]:
        """Analyze a chunk using the Phi-2 LLM."""
        try:
            # Create a clear, focused prompt for the LLM
            prompt = f"""
            You are an expert in analyzing research documents for data sharing policies.
            
            Analyze the following text and determine if it contains restrictions against sharing data 
            for secondary research, other clinical trials, or other research purposes.
            
            Text: {chunk['text']}
            
            Answer the following questions in JSON format:
            1. has_restrictions: true if text contains ANY restriction on sharing data, false otherwise
            2. restriction_type: One of "PROHIBITED" (complete ban), "CONDITIONAL" (allowed with conditions), or "NONE"
            3. confidence: A number from 0-100 representing how confident you are in your assessment
            
            JSON:
            """
            
            # Tokenize the prompt
            inputs = self.tokenizer(prompt, return_tensors="pt")
            
            # Generate response with parameters optimized for factual accuracy
            with torch.no_grad():
                outputs = self.model.generate(
                    inputs.input_ids,
                    max_new_tokens=150,
                    temperature=0.1,  # Low temperature for more deterministic output
                    top_p=0.9,
                    repetition_penalty=1.2,
                    num_return_sequences=1,
                )
            
            # Decode the response and extract the part after the prompt
            response = self.tokenizer.decode(outputs[0], skip_special_tokens=True)
            response = response.replace(prompt, "").strip()
            
            # Extract JSON from the response using regex
            json_match = re.search(r'(\{.*\})', response, re.DOTALL)
            if json_match:
                try:
                    analysis = json.loads(json_match.group(1))
                    return {
                        "has_restrictions": analysis.get("has_restrictions", False),
                        "restriction_type": analysis.get("restriction_type", "NONE"),
                        "confidence": analysis.get("confidence", 0)
                    }
                except json.JSONDecodeError:
                    logger.warning("Failed to parse JSON from LLM response")
            
            # If we couldn't extract valid JSON, use a conservative default
            logger.warning("Could not extract valid JSON from LLM response, using default values")
            return {
                "has_restrictions": False,
                "restriction_type": "NONE",
                "confidence": 0
            }
            
        except Exception as e:
            logger.error(f"Error in LLM analysis: {e}")
            # Return conservative default values
            return {
                "has_restrictions": False,
                "restriction_type": "NONE",
                "confidence": 0
            }

class DataSharingDetector:
    """
    Main class that orchestrates the end-to-end process of detecting 
    data sharing restrictions in documents using a simplified LLM-only approach.
    """
    
    def __init__(
        self,
        chunk_size: int = 500,
        chunk_overlap: int = 100,
        search_threshold: float = 0.45,  # Slightly lower threshold to capture more potential matches
        embedding_model: str = "all-MiniLM-L6-v2",
        top_k: int = 20  # We'll analyze only the top 20 most relevant paragraphs
    ):
        """
        Initialize the data sharing detector with simplified LLM-only approach
        
        Args:
            chunk_size: Size of text chunks
            chunk_overlap: Overlap between chunks
            search_threshold: Similarity threshold for retrieval
            embedding_model: Name of the embedding model to use
            top_k: Number of top results to retrieve and analyze with LLM
        """
        self.chunk_size = chunk_size
        self.chunk_overlap = chunk_overlap
        self.search_threshold = search_threshold
        self.embedding_model = embedding_model
        self.top_k = top_k
        
        # More focused data sharing restriction queries specifically about not allowing secondary use
        self.queries = [
            "Data cannot be shared for secondary research or clinical trials",
            "Data sharing for other clinical trials is prohibited",
            "Restrictions on sharing data for future studies or secondary research",
            "Data must not be used for purposes beyond this study",
            "Secondary use of data is not permitted or is prohibited",
            "Data is exclusively for the current research only, not for sharing",
            "Sharing of this data for other research is forbidden",
            "Data cannot be transferred to other researchers for different studies",
            "Prohibition on using this data for other clinical trials",
            "Data must not be repurposed for secondary analysis"
        ]
        
        # Initialize components
        self.parser = DocumentParser()
        self.text_processor = TextProcessor(chunk_size=chunk_size, chunk_overlap=chunk_overlap)
        self.embedding_generator = EmbeddingGenerator(model_name=embedding_model)
        
        # Retrieval system will be initialized after we know the embedding dimension
        self.embedding_dim = self.embedding_generator.embedding_dim
        self.retrieval_system = RetrievalSystem(self.embedding_dim)
        
        # LLM-based Analyzer 
        self.analyzer = DataSharingAnalyzer()
        
        logger.info("Data sharing detector initialized successfully")
    
    def process_document(self, file_path: str) -> List[Dict[str, Any]]:
        """
        Process a document to detect data sharing restrictions
        
        Args:
            file_path: Path to the document file
            
        Returns:
            List of detected data sharing restrictions
        """
        try:
            start_time = time.time()
            logger.info(f"Processing document: {file_path}")
            
            # Step 1: Parse the document
            doc_content = self.parser.parse_document(file_path)
            logger.info(f"Document parsed: {len(doc_content['pages'])} pages, {len(doc_content['tables'])} tables")
            
            # Step 2: Process text and create chunks
            chunks = self.text_processor.process_document(doc_content)
            
            # Step 3: Generate embeddings for chunks
            chunks_with_embeddings = self.embedding_generator.generate_embeddings(chunks)
            
            # Step 4: Add chunks to retrieval system
            self.retrieval_system.add_chunks(chunks_with_embeddings)
            
            # Step 5: Generate query embeddings
            query_embeddings = self.embedding_generator.generate_query_embeddings(self.queries)
            
            # Step 6: Retrieve relevant chunks
            relevant_chunks = self.retrieval_system.search(
                query_embeddings,
                k=self.top_k,
                threshold=self.search_threshold
            )
            
            # Step 7: Analyze chunks for data sharing restrictions
            results = self.analyzer.analyze_chunks(relevant_chunks)
            
            elapsed_time = time.time() - start_time
            logger.info(f"Document processed in {elapsed_time:.2f} seconds")
            logger.info(f"Found {len(results)} potential data sharing restrictions")
            
            return results
            
        except Exception as e:
            logger.error(f"Error processing document: {e}")
            raise
    
    def save_results_to_excel(self, results: List[Dict[str, Any]], output_path: str):
        """
        Save analysis results to Excel file
        
        Args:
            results: List of detected data sharing restrictions
            output_path: Path to save the Excel file
        """
        try:
            logger.info(f"Saving results to Excel: {output_path}")
            
            # Create DataFrame from results
            df = pd.DataFrame(results)
            
            # Format confidence as percentage
            df["confidence"] = df["confidence"].apply(lambda x: f"{x:.1f}%")
            
            # Save to Excel
            df.to_excel(output_path, index=False)
            
            logger.info(f"Results saved successfully: {len(results)} entries")
            
        except Exception as e:
            logger.error(f"Error saving results to Excel: {e}")
            raise

def main():
    """Main function to demonstrate usage."""
    
    # Get inputs from user
    file_path = input("Enter path to document file: ")
    output_path = input("Enter path to save results (Excel): ")
    
    # Set default output path if not provided
    if not output_path:
        output_path = "data_sharing_restrictions.xlsx"
    
    # Print notice about requirements and improved PDF processing
    print("\nNote: This system uses:")
    print("- PyMuPDF for high-quality PDF extraction (much better than pdfminer)")
    print("- Improved DOCX parsing with structure preservation")
    print("- Phi-2 model which requires ~2-3GB of RAM")
    print("- Total RAM usage: ~7GB for large documents (68K tokens)")
    print("- Processing time: ~4-11 minutes depending on document size\n")
    
    # Initialize detector with simplified approach
    detector = DataSharingDetector(
        chunk_size=500,
        chunk_overlap=100,
        search_threshold=0.45,
        top_k=20  # Will only analyze top 20 chunks with LLM
    )
    
    # Process document
    results = detector.process_document(file_path)
    
    # Save results to Excel
    detector.save_results_to_excel(results, output_path)
    
    print(f"Analysis complete. Found {len(results)} potential data sharing restrictions.")
    print(f"Results saved to: {output_path}")

if __name__ == "__main__":
    main()
, sentence) and len(current_group) >= 3) or len(current_group) >= 4:
                            result.append(" ".join(current_group))
                            current_group = []
                    if current_group:
                        result.append(" ".join(current_group))
            else:
                # For shorter text, keep as a single paragraph
                result.append(para.strip())
                
        # Check for section headers and split on them
        final_result = []
        for para in result:
            # Look for section header patterns and split
            header_matches = re.finditer(r'([A-Z][A-Z\s]+:|\d+\.\d+\s+[A-Z][\w\s]+)', para)
            last_end = 0
            for match in header_matches:
                if match.start() > last_end:
                    final_result.append(para[last_end:match.start()].strip())
                final_result.append(para[match.start():match.end()].strip())
                last_end = match.end()
            if last_end < len(para):
                final_result.append(para[last_end:].strip())
        
        # Remove empty paragraphs and very short ones that are likely noise
        return [p for p in final_result if p.strip() and len(p.strip()) > 10]
    
    def _create_chunks_from_paragraphs(self, paragraphs: List[str], page_num: int) -> List[Dict[str, Any]]:
        """Create overlapping chunks from paragraphs."""
        chunks = []
        current_chunk = ""
        current_paragraphs = []
        chunk_id = 1
        
        for para in paragraphs:
            # If adding this paragraph exceeds chunk size, save current chunk and start new one
            if len(current_chunk) + len(para) > self.chunk_size and current_chunk:
                chunk_text = current_chunk.strip()
                
                # Get context (first and last paragraph)
                context = (current_paragraphs[0][:100] + "..." if len(current_paragraphs[0]) > 100 else current_paragraphs[0])
                if len(current_paragraphs) > 1:
                    context += " [...] " + (current_paragraphs[-1][:100] + "..." if len(current_paragraphs[-1]) > 100 else current_paragraphs[-1])
                
                chunks.append({
                    "chunk_id": f"page_{page_num}_chunk_{chunk_id}",
                    "text": chunk_text,
                    "page_num": page_num,
                    "source": "text",
                    "is_table": False,
                    "context": context
                })
                
                # Start new chunk with overlap
                overlap_point = max(0, len(current_chunk) - self.chunk_overlap)
                current_chunk = current_chunk[overlap_point:] + " " + para
                current_paragraphs = [current_paragraphs[-1], para] if current_paragraphs else [para]
                chunk_id += 1
            else:
                current_chunk += " " + para
                current_paragraphs.append(para)
        
        # Add the last chunk if not empty
        if current_chunk.strip():
            chunk_text = current_chunk.strip()
            
            # Get context (first and last paragraph)
            context = (current_paragraphs[0][:100] + "..." if len(current_paragraphs[0]) > 100 else current_paragraphs[0])
            if len(current_paragraphs) > 1:
                context += " [...] " + (current_paragraphs[-1][:100] + "..." if len(current_paragraphs[-1]) > 100 else current_paragraphs[-1])
            
            chunks.append({
                "chunk_id": f"page_{page_num}_chunk_{chunk_id}",
                "text": chunk_text,
                "page_num": page_num,
                "source": "text",
                "is_table": False,
                "context": context
            })
        
        return chunks
    
    def _table_to_text(self, table_content: List[Dict[str, Any]]) -> str:
        """Convert table dictionary to text representation."""
        text = "TABLE CONTENT:\n"
        
        if not table_content:
            return text + "Empty table"
        
        # Get all keys from the first row
        keys = list(table_content[0].keys())
        
        # Create header
        header = " | ".join(keys)
        text += header + "\n"
        text += "-" * len(header) + "\n"
        
        # Add rows
        for row in table_content:
            row_text = " | ".join(str(row.get(k, "")) for k in keys)
            text += row_text + "\n"
        
        return text

class EmbeddingGenerator:
    """Generates embeddings for text chunks using a sentence transformer model."""
    
    def __init__(self, model_name: str = "all-MiniLM-L6-v2"):
        """
        Initialize the embedding generator with a lightweight model
        
        Args:
            model_name: Name of the sentence-transformers model to use
        """
        logger.info(f"Loading embedding model: {model_name}")
        self.model = SentenceTransformer(model_name)
        self.embedding_dim = self.model.get_sentence_embedding_dimension()
        logger.info(f"Embedding dimension: {self.embedding_dim}")
    
    def generate_embeddings(self, chunks: List[Dict[str, Any]]) -> List[Dict[str, Any]]:
        """
        Generate embeddings for all chunks
        
        Args:
            chunks: List of text chunks
            
        Returns:
            Chunks with added embedding vectors
        """
        logger.info(f"Generating embeddings for {len(chunks)} chunks...")
        
        # Extract text from chunks
        texts = [chunk["text"] for chunk in chunks]
        
        # Generate embeddings in batches to avoid memory issues
        batch_size = 32
        embeddings = []
        
        for i in tqdm(range(0, len(texts), batch_size), desc="Generating embeddings"):
            batch_texts = texts[i:i+batch_size]
            batch_embeddings = self.model.encode(batch_texts)
            embeddings.extend(batch_embeddings)
        
        # Add embeddings to chunks
        for i, chunk in enumerate(chunks):
            chunk["embedding"] = embeddings[i]
        
        return chunks
    
    def generate_query_embeddings(self, queries: List[str]) -> np.ndarray:
        """
        Generate embeddings for search queries
        
        Args:
            queries: List of query strings
            
        Returns:
            Array of query embeddings
        """
        logger.info(f"Generating query embeddings for {len(queries)} queries")
        query_embeddings = self.model.encode(queries)
        return query_embeddings

class RetrievalSystem:
    """
    Implements a vector retrieval system to find relevant chunks
    related to data sharing restrictions.
    """
    
    def __init__(self, embedding_dim: int):
        """
        Initialize the retrieval system
        
        Args:
            embedding_dim: Dimension of the embeddings
        """
        logger.info(f"Initializing retrieval system with {embedding_dim} dimensions")
        self.index = faiss.IndexFlatIP(embedding_dim)  # Inner product for cosine similarity
        self.chunks = []
    
    def add_chunks(self, chunks: List[Dict[str, Any]]):
        """
        Add chunks to the retrieval index
        
        Args:
            chunks: List of chunks with embeddings
        """
        embeddings = np.array([chunk["embedding"] for chunk in chunks]).astype(np.float32)
        self.index.add(embeddings)
        self.chunks = chunks
        logger.info(f"Added {len(chunks)} chunks to retrieval index")
    
    def search(self, query_embeddings: np.ndarray, k: int = 10, threshold: float = 0.5) -> List[Dict[str, Any]]:
        """
        Search for relevant chunks using query embeddings
        
        Args:
            query_embeddings: Embeddings of the search queries
            k: Number of results to return per query
            threshold: Minimum similarity score threshold
            
        Returns:
            List of relevant chunks with similarity scores
        """
        logger.info(f"Searching for top {k} relevant chunks")
        
        # Ensure query_embeddings is 2D
        if len(query_embeddings.shape) == 1:
            query_embeddings = query_embeddings.reshape(1, -1)
        
        # Normalize vectors for cosine similarity
        faiss.normalize_L2(query_embeddings)
        
        # Search index
        scores, indices = self.index.search(query_embeddings.astype(np.float32), k=k)
        
        # Collect results above threshold
        results = []
        seen_chunks = set()  # To avoid duplicates
        
        for query_idx, (query_scores, query_indices) in enumerate(zip(scores, indices)):
            for score, idx in zip(query_scores, query_indices):
                if idx < 0 or idx >= len(self.chunks):  # Skip invalid indices
                    continue
                    
                if score < threshold:  # Skip results below threshold
                    continue
                
                chunk = self.chunks[idx]
                chunk_id = chunk["chunk_id"]
                
                if chunk_id in seen_chunks:  # Skip duplicates
                    continue
                
                seen_chunks.add(chunk_id)
                
                results.append({
                    **chunk,
                    "similarity_score": float(score)
                })
        
        # Sort by similarity score (descending)
        results.sort(key=lambda x: x["similarity_score"], reverse=True)
        
        logger.info(f"Found {len(results)} relevant chunks above threshold {threshold}")
        return results

class DataSharingAnalyzer:
    """
    Analyzes retrieved chunks to identify data sharing restrictions
    and assigns confidence scores.
    """
    
    def __init__(self, use_llm: bool = False):
        """
        Initialize the data sharing analyzer
        
        Args:
            use_llm: Whether to use LLM for analysis (uses rule-based method if False)
        """
        self.use_llm = use_llm
        logger.info(f"Initializing data sharing analyzer (use_llm={use_llm})")
        
        if use_llm:
            logger.info("Loading lightweight LLM for analysis...")
            try:
                # Choose a lightweight model suitable for CPU (Phi-2)
                self.model_name = "microsoft/phi-2"
                self.tokenizer = AutoTokenizer.from_pretrained(self.model_name)
                
                # Load in 4-bit to reduce memory usage
                self.model = AutoModelForCausalLM.from_pretrained(
                    self.model_name,
                    device_map="auto",
                    torch_dtype=torch.float16,  # If GPU available
                    load_in_4bit=True,  # For CPU efficiency
                )
                logger.info("LLM loaded successfully")
            except Exception as e:
                logger.warning(f"Failed to load LLM: {e}. Falling back to rule-based analysis.")
                self.use_llm = False
        
        # Initialize patterns for rule-based analysis
        self._init_patterns()
    
    def _init_patterns(self):
        """Initialize regex patterns for rule-based analysis."""
        # Patterns indicating prohibition of data sharing
        self.prohibition_patterns = [
            r"data\s+(?:sharing|use)\s+(?:is|are)\s+(?:not|prohibited|forbidden|disallowed)",
            r"(?:not|no)\s+(?:allowed|permitted)\s+to\s+(?:share|use|transfer)\s+(?:the|this|study)?\s*data",
            r"data\s+(?:may|can|shall|must|will)\s+not\s+be\s+(?:shared|used|transferred|disclosed)",
            r"(?:prohibit|restrict|limit|forbid)\s+(?:the|any|all)\s+(?:sharing|use|transfer)\s+of\s+(?:the|this|study)?\s*data",
            r"data\s+(?:is|are)\s+exclusive(?:ly)?\s+for\s+(?:this|the|current)\s+(?:study|trial|research|use)",
            r"secondary\s+(?:use|usage|research|analysis)\s+(?:is|are)\s+(?:not|never)\s+(?:permitted|allowed)",
            r"(?:cannot|may\s+not|must\s+not|shall\s+not)\s+be\s+used\s+for\s+(?:secondary|other|future|additional)\s+(?:research|trials|studies|purposes)"
        ]
        
        # Patterns indicating conditional data sharing
        self.conditional_patterns = [
            r"data\s+(?:may|can|could|might|shall)\s+be\s+(?:shared|used|transferred)\s+(?:only|if|when|subject\s+to|conditional)",
            r"(?:approval|permission|consent|authorization)\s+(?:is|must\s+be|will\s+be|shall\s+be)\s+(?:required|necessary|needed|obtained)",
            r"(?:prior|written|explicit|specific)\s+(?:approval|permission|consent|authorization)",
            r"subject\s+to\s+(?:the|a|an)\s+(?:agreement|approval|authorization|permission)",
            r"(?:ethics|IRB|institutional\s+review\s+board)\s+(?:approval|review|permission)",
            r"data\s+(?:transfer|sharing|use)\s+agreement\s+(?:is|must\s+be|will\s+be|shall\s+be)\s+(?:required|necessary|needed|in\s+place)"
        ]
    
    def analyze_chunks(self, relevant_chunks: List[Dict[str, Any]]) -> List[Dict[str, Any]]:
        """
        Analyze chunks to identify data sharing restrictions
        
        Args:
            relevant_chunks: List of relevant chunks from retrieval system
            
        Returns:
            List of analyzed chunks with restriction details and confidence scores
        """
        logger.info(f"Analyzing {len(relevant_chunks)} relevant chunks for data sharing restrictions")
        
        results = []
        
        for chunk in relevant_chunks:
            if self.use_llm and hasattr(self, 'model'):
                analysis = self._analyze_with_llm(chunk)
            else:
                analysis = self._analyze_with_rules(chunk)
            
            if analysis["has_restrictions"]:
                results.append({
                    "paragraph": chunk["text"],
                    "confidence": analysis["confidence"],
                    "page": chunk["page_num"],
                    "restriction_type": analysis["restriction_type"],
                    "context": chunk["context"]
                })
        
        # Sort by confidence (descending)
        results.sort(key=lambda x: x["confidence"], reverse=True)
        
        logger.info(f"Found {len(results)} chunks with data sharing restrictions")
        return results
    
    def _analyze_with_rules(self, chunk: Dict[str, Any]) -> Dict[str, Any]:
        """Analyze chunk using rule-based patterns."""
        text = chunk["text"].lower()
        
        # Check for prohibition patterns
        prohibition_matches = [
            re.search(pattern, text, re.IGNORECASE) 
            for pattern in self.prohibition_patterns
        ]
        prohibition_matches = [m for m in prohibition_matches if m]
        
        # Check for conditional patterns
        conditional_matches = [
            re.search(pattern, text, re.IGNORECASE) 
            for pattern in self.conditional_patterns
        ]
        conditional_matches = [m for m in conditional_matches if m]
        
        # Determine restriction type and confidence
        if prohibition_matches:
            restriction_type = "PROHIBITED"
            # More matches = higher confidence
            confidence = min(95, 70 + 5 * len(prohibition_matches))
            has_restrictions = True
        elif conditional_matches:
            restriction_type = "CONDITIONAL"
            confidence = min(90, 60 + 5 * len(conditional_matches))
            has_restrictions = True
        else:
            # Check for potential restrictions using keyword density
            keywords = [
                "confidential", "proprietary", "restrict", "limit", 
                "exclusive", "only", "sole", "disclosure"
            ]
            
            keyword_count = sum(1 for kw in keywords if kw in text)
            
            if keyword_count >= 3 and any(term in text for term in ["data", "information", "results"]):
                restriction_type = "POTENTIAL"
                confidence = min(60, 40 + 5 * keyword_count)
                has_restrictions = True
            else:
                restriction_type = "NONE"
                confidence = 0
                has_restrictions = False
        
        return {
            "has_restrictions": has_restrictions,
            "restriction_type": restriction_type,
            "confidence": confidence
        }
    
    def _analyze_with_llm(self, chunk: Dict[str, Any]) -> Dict[str, Any]:
        """Analyze chunk using LLM-based analysis."""
        try:
            # Create prompt for the LLM
            prompt = f"""
            Analyze the following text from a research document and determine if it contains 
            restrictions on sharing data for secondary research or clinical trials.
            
            Text: {chunk['text']}
            
            Respond with a JSON object with these fields:
            1. has_restrictions: true or false
            2. restriction_type: "PROHIBITED" (cannot share at all), "CONDITIONAL" (can share with conditions), "POTENTIAL" (might have restrictions), or "NONE"
            3. confidence: number from 0-100 representing confidence in the assessment
            
            JSON:
            """
            
            inputs = self.tokenizer(prompt, return_tensors="pt")
            
            with torch.no_grad():
                outputs = self.model.generate(
                    inputs.input_ids,
                    max_new_tokens=100,
                    temperature=0.1,
                    top_p=0.9,
                    top_k=40,
                    repetition_penalty=1.1,
                    num_return_sequences=1,
                )
            
            response = self.tokenizer.decode(outputs[0], skip_special_tokens=True)
            response = response.replace(prompt, "").strip()
            
            # Extract JSON from response
            json_match = re.search(r'(\{.*\})', response, re.DOTALL)
            if json_match:
                analysis = json.loads(json_match.group(1))
                return {
                    "has_restrictions": analysis.get("has_restrictions", False),
                    "restriction_type": analysis.get("restriction_type", "NONE"),
                    "confidence": analysis.get("confidence", 0)
                }
            
            # Fallback to rule-based if LLM failed
            logger.warning("LLM returned invalid JSON response, falling back to rule-based analysis")
            return self._analyze_with_rules(chunk)
            
        except Exception as e:
            logger.warning(f"Error in LLM analysis: {e}, falling back to rule-based analysis")
            return self._analyze_with_rules(chunk)

class DataSharingDetector:
    """
    Main class that orchestrates the end-to-end process of detecting 
    data sharing restrictions in documents.
    """
    
    def __init__(
        self,
        use_llm: bool = False,
        chunk_size: int = 500,
        chunk_overlap: int = 100,
        search_threshold: float = 0.5,
        embedding_model: str = "all-MiniLM-L6-v2",
        top_k: int = 20
    ):
        """
        Initialize the data sharing detector
        
        Args:
            use_llm: Whether to use LLM for analysis
            chunk_size: Size of text chunks
            chunk_overlap: Overlap between chunks
            search_threshold: Similarity threshold for retrieval
            embedding_model: Name of the embedding model to use
            top_k: Number of top results to retrieve
        """
        self.use_llm = use_llm
        self.chunk_size = chunk_size
        self.chunk_overlap = chunk_overlap
        self.search_threshold = search_threshold
        self.embedding_model = embedding_model
        self.top_k = top_k
        
        # Data sharing restriction queries
        self.queries = [
            "Data cannot be shared for secondary research",
            "Data sharing for other clinical trials is prohibited",
            "Restrictions on sharing data for future studies",
            "Data may not be used for purposes beyond this study",
            "Secondary use of data is not permitted",
            "Data is exclusively for the current research only",
            "Data sharing agreement is required for secondary use",
            "Confidentiality restrictions prevent data sharing",
            "Data cannot be transferred to other researchers",
            "Authorization needed for data sharing"
        ]
        
        # Initialize components
        self.parser = DocumentParser()
        self.text_processor = TextProcessor(chunk_size=chunk_size, chunk_overlap=chunk_overlap)
        self.embedding_generator = EmbeddingGenerator(model_name=embedding_model)
        
        # Retrieval system will be initialized after we know the embedding dimension
        self.embedding_dim = self.embedding_generator.embedding_dim
        self.retrieval_system = RetrievalSystem(self.embedding_dim)
        
        # Analyzer
        self.analyzer = DataSharingAnalyzer(use_llm=use_llm)
        
        logger.info("Data sharing detector initialized successfully")
    
    def process_document(self, file_path: str) -> List[Dict[str, Any]]:
        """
        Process a document to detect data sharing restrictions
        
        Args:
            file_path: Path to the document file
            
        Returns:
            List of detected data sharing restrictions
        """
        try:
            start_time = time.time()
            logger.info(f"Processing document: {file_path}")
            
            # Step 1: Parse the document
            doc_content = self.parser.parse_document(file_path)
            logger.info(f"Document parsed: {len(doc_content['pages'])} pages, {len(doc_content['tables'])} tables")
            
            # Step 2: Process text and create chunks
            chunks = self.text_processor.process_document(doc_content)
            
            # Step 3: Generate embeddings for chunks
            chunks_with_embeddings = self.embedding_generator.generate_embeddings(chunks)
            
            # Step 4: Add chunks to retrieval system
            self.retrieval_system.add_chunks(chunks_with_embeddings)
            
            # Step 5: Generate query embeddings
            query_embeddings = self.embedding_generator.generate_query_embeddings(self.queries)
            
            # Step 6: Retrieve relevant chunks
            relevant_chunks = self.retrieval_system.search(
                query_embeddings,
                k=self.top_k,
                threshold=self.search_threshold
            )
            
            # Step 7: Analyze chunks for data sharing restrictions
            results = self.analyzer.analyze_chunks(relevant_chunks)
            
            elapsed_time = time.time() - start_time
            logger.info(f"Document processed in {elapsed_time:.2f} seconds")
            logger.info(f"Found {len(results)} potential data sharing restrictions")
            
            return results
            
        except Exception as e:
            logger.error(f"Error processing document: {e}")
            raise
    
    def save_results_to_excel(self, results: List[Dict[str, Any]], output_path: str):
        """
        Save analysis results to Excel file
        
        Args:
            results: List of detected data sharing restrictions
            output_path: Path to save the Excel file
        """
        try:
            logger.info(f"Saving results to Excel: {output_path}")
            
            # Create DataFrame from results
            df = pd.DataFrame(results)
            
            # Format confidence as percentage
            df["confidence"] = df["confidence"].apply(lambda x: f"{x:.1f}%")
            
            # Save to Excel
            df.to_excel(output_path, index=False)
            
            logger.info(f"Results saved successfully: {len(results)} entries")
            
        except Exception as e:
            logger.error(f"Error saving results to Excel: {e}")
            raise

def main():
    """Main function to demonstrate usage."""
    
    # Get inputs from user
    file_path = input("Enter path to document file: ")
    output_path = input("Enter path to save results (Excel): ")
    use_llm = input("Use LLM for analysis? (y/n): ").lower() == 'y'
    
    # Set default output path if not provided
    if not output_path:
        output_path = "data_sharing_restrictions.xlsx"
    
    # Initialize detector
    detector = DataSharingDetector(
        use_llm=use_llm,
        chunk_size=500,
        chunk_overlap=100,
        search_threshold=0.5,
        top_k=20
    )
    
    # Process document
    results = detector.process_document(file_path)
    
    # Save results to Excel
    detector.save_results_to_excel(results, output_path)
    
    print(f"Analysis complete. Found {len(results)} potential data sharing restrictions.")
    print(f"Results saved to: {output_path}")

if __name__ == "__main__":
    main()
