class Solution{
    
    /*  Function to check if two strings are rotated
    *   str1, str2: input strings
    */
    public static boolean isRotated(String str1, String str2){
        
        // Your code here
        int l1=str1.length();
        if(l1!=str2.length())
        return false;
        if(l1==1)
            return str1.equals(str2);
        return (str1.substring(2)+str1.substring(0,2)).equals(str2) || (str2.substring(2)+str2.substring(0,2)).equals(str1);
    
        
    }
    
}