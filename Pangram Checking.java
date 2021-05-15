class Solution
{
    //Function to check if a string is Pangram or not.
    public static boolean checkPangram (String str)
    {
        //using a hash table to mark the characters present in the string.
        Boolean[] mark = new Boolean[26];
        for (int i = 0; i < 26; i++)
            mark[i] = false;
	    
	    //iterating over the string.
	    for (int i = 0; i < str.length(); i++)
	    {
	       //marking index of current character as true in hash table.
	        
	       //if we get uppercase character, we subtract 'A' from it
           //to get its index (in terms of 0 to 25).
	       if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z')
	            mark[str.charAt(i) - 'A'] = true;
	            
	       //if we get lowercase character, we subtract 'a' from it
           //to get its index (in terms of 0 to 25).
	       else if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') 
	            mark[str.charAt(i) - 'a'] = true;
	            
	    }
	    
	    //returning false if any letter of alphabet is unmarked.
	    for (int i = 0; i < 26; i++)
	        if (!mark[i])
	            return false;
	    
	    //if all letters of alphabet are present then returning true.        
	    return true;
    }
}
/**
 
class Solution
{
    //Function to check if a string is Pangram or not.
    public static boolean checkPangram  (String str)
    {
        // your code here
        str=str.toLowerCase();
        int i=0;
        for(i=0;i<26;i++){
        if(str.indexOf((char)i+97)==-1)
        break;
        }
        return i==26;
    }
}
 */