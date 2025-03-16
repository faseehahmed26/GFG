def setup_phi2_ggml():
    """Set up Phi-2 using GGML via CTransformers for optimal CPU performance."""
    from ctransformers import AutoModelForCausalLM
    
    # Load the model in GGML format with 4-bit quantization
    model = AutoModelForCausalLM.from_pretrained(
        "TheBloke/phi-2-GGML",
        model_file="phi-2.q4_0.gguf",  # 4-bit quantized version
        model_type="phi",
        context_length=2048,  # Adjust based on your needs
        gpu_layers=0  # Force CPU-only
    )
    
    return model

def analyze_with_phi2_ggml(chunk_text, model):
    """Analyze text chunk using Phi-2 GGML to extract data sharing restrictions."""
    prompt = f"""
    Task: Identify if the following text contains restrictions on data sharing for research,
    and extract the EXACT text fragment that describes these restrictions.
    
    Text: {chunk_text}
    
    Output JSON:
    {{
        "has_restrictions": true or false,
        "restriction_type": "PROHIBITED" or "CONDITIONAL" or "NONE",
        "confidence": value between 0-100,
        "extracted_text": "exact text from document describing the restriction"
    }}
    """
    
    # Generate with controlled parameters
    response = model(
        prompt,
        max_new_tokens=150,
        temperature=0.1,
        top_p=0.9,
        repetition_penalty=1.2,
    )
    
    # Extract generated text
    generated_text = response.strip()
    
    # Extract JSON from response
    import re
    import json
    
    json_match = re.search(r'(\{.*\})', generated_text, re.DOTALL)
    if json_match:
        try:
            analysis = json.loads(json_match.group(1))
            return analysis
        except:
            # Fallback if JSON parsing fails
            pass
    
    # Simple fallback extraction if JSON parsing fails
    has_restrictions = "true" in generated_text.lower() and "has_restrictions" in generated_text.lower()
    
    return {
        "has_restrictions": has_restrictions,
        "restriction_type": "POTENTIAL" if has_restrictions else "NONE",
        "confidence": 60 if has_restrictions else 0,
        "extracted_text": generated_text[:200] if has_restrictions else ""
    }
