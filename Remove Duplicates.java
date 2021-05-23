
import java.io.*;
import java.util.*;

class GFG {
    public static void main(String args[]) throws IOException {
        BufferedReader read =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while (t-- > 0) {
            String s = read.readLine();
            
            Solution ob = new Solution();
            String result = ob.removeDups(s);
            
            System.out.println(result);
        }
    }
}// } Driver Code Ends


//User function Template for Java

class Solution {
    String removeDups(String S) {
        // code here
        String str="";
        LinkedHashMap<Character,Integer> hmap=new LinkedHashMap<>();
        for(int i=0;i<S.length();i++){
            if(hmap.containsKey(S.charAt(i))){
                int val=hmap.get(S.charAt(i));
                hmap.put(S.charAt(i),val+1);
            }
            else
            hmap.put(S.charAt(i),1);
        }
        for(Character ans:hmap.keySet()){
            str+=String.valueOf(ans);
        }
        return str;
        
        
    }
}