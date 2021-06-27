//Initial Template for Java

import java.util.*;
import java.io.*;
import java.lang.*;

class Main
{
    public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim()); //Inputting the testcases
		while(t-->0){
		    String inputLine[] = br.readLine().trim().split(" ");
		    int n = Integer.parseInt(inputLine[0]);
		    int X = Integer.parseInt(inputLine[1]);
		    int A[] = new int[n];
		    inputLine = br.readLine().trim().split(" ");
		    for(int i=0; i<n; i++){
		        A[i] = Integer.parseInt(inputLine[i]);
		    }
		    Solution ob=new Solution();
		    boolean ans = ob.find3Numbers(A, n, X);
		    System.out.println(ans?1:0);
		}
	}
}// } Driver Code Ends

//Back-end complete function Template for Java

class Solution
{
    //Function to find if there exists a triplet in the 
    //array A[] which sums up to X.
    public static boolean find3Numbers(int A[], int n, int X) { 
        int l, r;
        //Sorting the elements.
        Arrays.sort(A);
  
        //Traversing the array elements.
        for (int i = 0; i < n - 2; i++) { 
  
            //Taking two pointers. One at element after ith index and another
            //at the last index.
            l = i + 1; 
            r = n - 1; 
            while (l < r) { 
                //If sum of elements at indexes i, l and r is equal 
                //to required number, we return true.
                if (A[i] + A[l] + A[r] == X) { 
                    
                    return true; 
                } 
                //Else if the sum is less than required number, it means we need
                //to increase the sum so we increase the left pointer l.
                else if (A[i] + A[l] + A[r] < X) 
                    l++; 
                //Else the sum is more than required number and we need to
                //decrease the sum so we decrease the right pointer r.
                else
                    r--; 
            } 
        } 
  
        //returning false if no triplet sum equal to required number is present.
        return false; 
    }
}