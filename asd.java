import os
import re
import pandas as pd
import numpy as np
from tqdm import tqdm
import fitz  # PyMuPDF
import docx
import time
from sentence_transformers import SentenceTransformer
from sklearn.metrics.pairwise import cosine_similarity

# Constants
SIMILARITY_THRESHOLD = 0.65  # Threshold for determining restrictions

print("Starting document analysis script...")

# 1. Document parsing functions - keeping this simple and efficient
def extract_text_from_pdf(pdf_path):
    """Extract text from PDF file."""
    try:
        doc = fitz.open(pdf_path)
        text = ""
        for page in doc:
            text += page.get_text()
        doc.close()
        return text
    except Exception as e:
        print(f"Error extracting text from PDF {pdf_path}: {e}")
        return ""

def extract_text_from_docx(docx_path):
    """Extract text from DOCX file."""
    try:
        doc = docx.Document(docx_path)
        text = ""
        for para in doc.paragraphs:
            text += para.text + "\n"
        doc.close()
        return text
    except Exception as e:
        print(f"Error extracting text from DOCX {docx_path}: {e}")
        return ""

def extract_text_from_file(file_path):
    """Extract text from file based on extension."""
    ext = os.path.splitext(file_path)[1].lower()
    
    if ext == '.pdf':
        return extract_text_from_pdf(file_path)
    elif ext == '.docx':
        return extract_text_from_docx(file_path)
    else:
        print(f"Unsupported file format: {ext}")
        return ""

def split_into_paragraphs(text):
    """Split text into paragraphs, handling various document formats."""
    # Split by various paragraph markers
    paragraphs = []
    
    # First try splitting by double newlines (most common paragraph separator)
    initial_paragraphs = [p.strip() for p in re.split(r'\n\s*\n', text) if p.strip()]
    
    # Process each paragraph - further split if too long
    for para in initial_paragraphs:
        if len(para) > 1000:  # If paragraph is very long
            # Split by single newlines
            subparas = [sp.strip() for sp in para.split('\n') if sp.strip()]
            paragraphs.extend(subparas)
        else:
            paragraphs.append(para)
    
    return paragraphs

# 2. Keyword-based filtering for efficient candidate selection
def restriction_keyword_filter(paragraph):
    """
    Check if paragraph likely contains data sharing restrictions.
    This is a fast pre-filter to identify candidate paragraphs.
    """
    # Keywords specifically related to data sharing restrictions
    # Focusing on legal/clinical trial terminology
    restriction_keywords = [
        'not allowed', 'not permit', 'prohibit', 'restrict', 'forbidden',
        'confidential', 'proprietary', 'not share', 'not be shared',
        'shall not', 'must not', 'cannot be', 'not authorized',
        'exclusive', 'solely', 'only for', 'limited to', 
        'not to be used', 'not for', 'may not be', 'not available'
    ]
    
    # Data-related keywords
    data_keywords = [
        'data', 'information', 'dataset', 'record', 'result',
        'trial', 'study', 'research', 'finding', 'patient'
    ]
    
    # First, check if paragraph contains any data-related terms
    has_data_term = any(term in paragraph.lower() for term in data_keywords)
    if not has_data_term:
        return False
    
    # Then check for restriction terms
    has_restriction_term = any(term in paragraph.lower() for term in restriction_keywords)
    
    return has_restriction_term

# 3. Embedding model for semantic similarity
def load_embedding_model():
    """Load the most appropriate embedding model for legal/clinical text."""
    print("Loading embedding model...")
    try:
        # Use a model that works well with legal text while still being efficient
        # MPNet is a good compromise between performance and efficiency
        model = SentenceTransformer('all-mpnet-base-v2')
        return model
    except Exception as e:
        print(f"Error loading MPNet model: {e}")
        try:
            # Fall back to lighter model if needed
            print("Falling back to MiniLM model...")
            model = SentenceTransformer('all-MiniLM-L6-v2')
            return model
        except Exception as e2:
            print(f"Error loading fallback model: {e2}")
            return None

# 4. Define clear restriction templates for comparison
def get_restriction_templates():
    """
    Define clear templates of data sharing restrictions.
    These are the definitive examples we'll compare against.
    """
    return [
        # Explicit prohibition statements
        "Data from this study cannot be shared for other research purposes.",
        "Sharing of this data for secondary research is not permitted.",
        "The study data may not be used for any other research initiatives.",
        "This clinical trial data shall not be shared with external researchers.",
        "It is prohibited to use this data for secondary analyses.",
        "Patient data from this trial is not to be shared for other purposes.",
        "Data sharing for secondary research is not allowed under this protocol.",
        "The data is restricted to this study only and cannot be reused.",
        "Research data must not be distributed for other studies.",
        "Information collected in this trial is not available for other uses.",
        
        # Legal/ICF specific language
        "The data is proprietary and confidential and shall not be shared.",
        "All study information is exclusively for the purposes described herein.",
        "Patient records are confidential and not for secondary use.",
        "This data belongs exclusively to the sponsor and cannot be repurposed.",
        "Trial data is protected by confidentiality obligations and not for sharing."
    ]

# 5. Core analysis function
def analyze_for_data_sharing_restrictions(file_path, output_file):
    """
    Analyze a document to find data sharing restrictions using a simple, efficient approach.
    Default assumption: data sharing is allowed unless we find clear evidence otherwise.
    """
    print(f"\nAnalyzing {os.path.basename(file_path)} for data sharing restrictions...")
    start_time = time.time()
    
    # Extract text
    text = extract_text_from_file(file_path)
    if not text:
        print(f"Failed to extract text from {file_path}")
        return "ERROR", []
    
    # Split into paragraphs
    paragraphs = split_into_paragraphs(text)
    total_paragraphs = len(paragraphs)
    print(f"Document contains {total_paragraphs} paragraphs")
    
    # Filter candidate paragraphs using keywords (fast pre-filtering)
    candidates = []
    for para in paragraphs:
        if restriction_keyword_filter(para):
            candidates.append(para)
    
    print(f"Identified {len(candidates)} candidate paragraphs for detailed analysis")
    if len(candidates) == 0:
        print("No relevant paragraphs found - data sharing appears to be allowed")
        
        # Save empty results
        pd.DataFrame({
            'filename': [os.path.basename(file_path)],
            'document_status': ['ALLOWED'],
            'rationale': ['No data sharing restrictions found'],
            'paragraph': ['N/A'],
            'similarity_score': [0]
        }).to_excel(output_file, index=False)
        
        return "ALLOWED", []
    
    # Load embedding model
    model = load_embedding_model()
    if model is None:
        print("Failed to load embedding model")
        return "ERROR", []
    
    # Get restriction templates
    templates = get_restriction_templates()
    template_embeddings = model.encode(templates)
    
    # Process each candidate paragraph
    restriction_findings = []
    for para in tqdm(candidates, desc="Analyzing candidates"):
        # Get paragraph embedding
        para_embedding = model.encode([para])[0]
        
        # Calculate similarity to each template
        similarities = cosine_similarity([para_embedding], template_embeddings)[0]
        
        # Get maximum similarity score
        max_similarity = np.max(similarities)
        max_index = np.argmax(similarities)
        
        # If similarity exceeds threshold, consider it a restriction
        if max_similarity >= SIMILARITY_THRESHOLD:
            restriction_findings.append({
                'paragraph': para,
                'similarity_score': max_similarity,
                'matched_template': templates[max_index]
            })
    
    # Make determination
    if restriction_findings:
        status = "RESTRICTED"
        print(f"\n⚠️ ALERT: DATA SHARING IS RESTRICTED ⚠️")
        print(f"Found {len(restriction_findings)} paragraphs indicating data sharing restrictions")
        
        # Show top 3 restriction findings
        print("\nKey restriction paragraphs:")
        for i, finding in enumerate(sorted(restriction_findings, 
                                          key=lambda x: x['similarity_score'], 
                                          reverse=True)[:3]):
            print(f"{i+1}. Score: {finding['similarity_score']:.2f} - {finding['paragraph'][:150]}...")
    else:
        status = "ALLOWED"
        print("\n✅ No data sharing restrictions found - data sharing appears to be ALLOWED")
    
    # Save results to Excel
    results = []
    for finding in restriction_findings:
        results.append({
            'filename': os.path.basename(file_path),
            'document_status': status,
            'rationale': 'Found explicit data sharing restrictions' if status == 'RESTRICTED' else 'No restrictions found',
            'paragraph': finding['paragraph'],
            'similarity_score': round(finding['similarity_score'], 3),
            'matched_template': finding['matched_template']
        })
    
    # If no restrictions found, add a single summary row
    if not results:
        results.append({
            'filename': os.path.basename(file_path),
            'document_status': status,
            'rationale': 'No data sharing restrictions found',
            'paragraph': 'N/A',
            'similarity_score': 0,
            'matched_template': 'N/A'
        })
    
    # Save to Excel
    df = pd.DataFrame(results)
    df.to_excel(output_file, index=False)
    
    elapsed_time = time.time() - start_time
    print(f"\nAnalysis completed in {elapsed_time:.2f} seconds")
    print(f"Results saved to {output_file}")
    
    return status, restriction_findings

# Script variables - set these before running
if __name__ == "__main__":
    # Configure these variables
    input_file = "path/to/your/document.pdf"  # Path to document
    output_file = "data_sharing_analysis.xlsx"  # Name of output Excel file
    
    print(f"Starting analysis of document: {input_file}")
    status, findings = analyze_for_data_sharing_restrictions(input_file, output_file)
    
    # Final determination
    print(f"\nFINAL DETERMINATION: {status}")
    if status == "RESTRICTED":
        print("⚠️ This document contains restrictions on data sharing for secondary research ⚠️")
    elif status == "ALLOWED":
        print("✅ This document does not restrict data sharing for secondary research")
    else:
        print("❌ Error analyzing document")
