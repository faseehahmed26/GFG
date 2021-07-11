//Initial Template for Java

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
            System.out.println(ob.lps(s));
        }
    }
}// } Driver Code Ends


//User function Template for Java

class Solution {
    int lps(String str) {
		int[] A = new int[str.length()];
	    int len = 0, i = 1;
	    while(i < str.length())
	    {
	        if(str.charAt(i) == str.charAt(len))
	        {
	            A[i] = len+1;
	            len++;
	            i++;
	        }
	        else
	        {
	            if(len==0)
	                i++;
	            else
	                len = A[len-1];
	            
	        }
	    }

	    return A[str.length()-1];
	}
}