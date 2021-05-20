//Back-end complete function Template for Java


import java.io.*;
import java.util.*;


/** 
// Driver class
class k sorted array{
    
    // Driver code
	public static void main (String[] args) throws IOException{
		// Taking input using buffered reader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testcases = Integer.parseInt(br.readLine());
		// looping through all testcases
		while(testcases-- > 0){
		    int n=Integer.parseInt(br.readLine());
		    String line1 = br.readLine();
		    String[] a1 = line1.trim().split("\\s+");
		    int a[]=new int[n];
		    for(int i=0;i<n;i++)
		    {
		        a[i]=Integer.parseInt(a1[i]);
		    }
		    int k=Integer.parseInt(br.readLine());
		    Solution ob=new Solution();
		    String ans=ob.isKSortedArray(a,n,k);
		    System.out.println(ans);
		    
		}
	}
}  
*/
// } Driver Code Ends

import java.util.Arrays; 
  
class Solution
{ 
    // Method to check whether the given array is 
    // a 'k' sorted array or not 
     String isKSortedArray(int arr[], int n, int k) 
    { 
        // auxiliary array 'aux' 
        int aux[] = new int[n]; 
           
        // copy elements of 'arr' to 'aux' 
        for (int i = 0; i<n; i++) 
            aux[i] = arr[i]; 
           
        // sort 'aux'     
        Arrays.sort(aux); 
           
        // for every element of 'arr' at index 'i',  
        // find its index 'j' in 'aux' 
        for (int i = 0; i<n; i++) 
        { 
            // index of arr[i] in sorted array 'aux' 
            int j = Arrays.binarySearch(aux,arr[i]); 
               
            // if abs(i-j) > k, then that element is 
            // not at-most k distance away from its  
            // target position. Thus,  'arr' is not a  
            // k sorted array  
            if (Math.abs(i - j) > k) 
                return "No"; 
        } 
           
        // 'arr' is a k sorted array 
        return "Yes";     
    }
}