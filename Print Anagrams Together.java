//Initial Template for Java

/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;

class GFG {
    public static void main (String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        
	    int t=Integer.parseInt(br.readLine().trim());
	    while(t > 0)
	    {
	        int n= Integer.parseInt(br.readLine().trim());
	        String x = br.readLine().trim();
	        String string_list[] = x.split(" ",n);
	        
	        Solution T = new  Solution();
	        
	        List <List<String>> ans = T.Anagrams(string_list);
	        
	        Collections.sort(ans, new Comparator<List<String>>(){
            public int compare(List<String> l1, List<String> l2) {
                    String s1 =  l1.get(0);
                    String s2 = l2.get(0);
                    
                    return s1.compareTo(s2);
                }
            });
	        
	        for(int i=0;i<ans.size();i++)
	        {
	            for(int j=0;j<ans.get(i).size();j++)
	            {
	                System.out.print(ans.get(i).get(j) + " ");
	            }
	            System.out.println();
	        }
	       
	       
            t--;
	    }
	}
    
}
// } Driver Code Ends


//User function Template for Java

//Back-end complete function Template for Java


class Solution {
    public List<List<String>> Anagrams(String[] string_list) {
        
        HashMap <String, List<String>> mp = new HashMap<>();
        List<List<String>> ans = new ArrayList <List<String>>();
        
        for(String i: string_list)
        {
            char ch[] = i.toCharArray();
            Arrays.sort(ch);
            // Convert the string to its lexicographically 
            // least representation, 
            // e.g. cat->act, act->act, tac->act 
            String s = String.valueOf(ch);
            
            // Check if that representation has 
            // occurred already
            if(mp.containsKey(s))
            {
                // If occurred add the original string to the map
                mp.get(s).add(i);
            }
            
            // If not present
            else
            {
                // Create a new list
                List<String> li = new ArrayList<>();
                // Add the original string
                li.add(i);
                ans.add(li);
                // Insert into the map the string as key 
                // and the new list as value
                mp.put(s,li);
            }
        }
        
        return ans;
        
    }
}