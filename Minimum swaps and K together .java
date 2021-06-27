//Initial Template for Java

//Initial Template for Java


/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;


class Array {
    
    // Driver code
	public static void main (String[] args) throws IOException{
		// Taking input using buffered reader
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testcases = Integer.parseInt(br.readLine());
		
		// looping through all testcases
		while(testcases-- > 0){
		    String line = br.readLine();
		    String[] element = line.trim().split("\\s+");
		    int sizeOfArray = Integer.parseInt(element[0]);
		     
		    int arr [] = new int[sizeOfArray];
		    
		    line = br.readLine();
		    String[] elements = line.trim().split("\\s+");
		    for(int i = 0;i<sizeOfArray;i++){
		        arr[i] = Integer.parseInt(elements[i]);
		    }
		    int K = Integer.parseInt(br.readLine());
		    
		    Complete obj = new Complete();
		    int ans = obj.minSwap(arr, sizeOfArray, K);
		    System.out.println(ans);
		}
	}
}
// } Driver Code Ends


//User function Template for Java



class Complete{
    
   
    // Function for finding maximum and value pair
    public static int minSwap (int arr[], int n, int k) {
        //Complete the function
        int cnt=0;//window size
        for(int i=0;i<n;i++){
            if(arr[i]<=k)
            cnt++;//counts <=k
        }
        //if all are <=k || all are >k then return zero
        if(cnt==0 && cnt==n)
        return 0;
        
        int ans=n;
        int c=0;
        //creating 1st window
        for(int j=0;j<cnt;j++){
            if(arr[j]>k)
            c++;//count numbers greater than k in 1st window
        }
        ans=Math.min(ans,c);
        int l=0,r=cnt-1;//initial window size
        while(r<n-1){
            if(arr[l]>k)//excluding left most
            c--;
            l++;
            r++;
            if(arr[r]>k)//including right most
            c++;
            
            ans=Math.min(ans,c);
        }
        return ans;
    }
}