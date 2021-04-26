class GfG
{
	
	int strstr(String s, String x) {
	    
	    // if target string is empty
        if (x.length() == 0){
            return 0;
        } 
        
        // brute force solution to iterate over the string
        // and check if characters matches or not,
        for (int i = 0; i < s.length(); i ++){ 
            if (i + x.length() > s.length()){
                return -1;
            }
            
            for (int j = 0; j < x.length(); j ++){
                
                // if characters doesn't matches, then continue till
                // you traversed whole string
                if ( s.charAt(i + j) == x.charAt(j) ){
                    if (j == (x.length() -1)){
                        return i;
                    }
                }
                else{
                    break;
                }
             }
        }
        
        // else if string is found, then return the starting index
        // of the match
        return -1;
    }
}
