import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
	public static void main (String[] args) {
		//code
			//code
			
		//taking input using class Scanner
		Scanner scan = new Scanner(System.in);
		
		//taking total number of testcases
		int t = scan.nextInt();
		while(t>0)
		{
		    //taking total count of elements
		    int n = scan.nextInt() ;
		    
		    //Declaring and Initializing an array of size n
		    int arr[] = new int[n];
		    
		    //adding elements to the array
		    for(int i = 0; i<n;i++)
		    {
		        arr[i] = scan.nextInt();
		    }
		    
		    t--;
		    
		     //calling the method findSum
		     //and print "YES" or "NO" based on the result
		     System.out.println(new Solution().findsum(arr,n)==true?"Yes":"No");
		}
	}
	
	
}// } Driver Code Ends


class Solution{
    //Function to check whether there is a subarray present with 0-sum or not.
    static boolean findsum(int arr[], int n)
    {
            //using Hashmap to store the prefix sum which has appeared already.
            HashMap<Integer, Integer> hM =  
                            new HashMap<Integer, Integer>(); 
              
            int sum = 0;      
              
            //iterating over the array. 
            for (int i = 0; i < arr.length; i++) 
            {  
                //storing prefix sum.
                sum += arr[i]; 
                  
                //if prefix sum is 0 or if it is already present in Hashmap 
                //then it is repeated which means there is a subarray whose 
                //summation is 0, so we return true.
                if (sum == 0 || hM.get(sum) != null)                          
                    return true; 
                  
                //storing every prefix sum obtained in Hashmap.
                hM.put(sum, i); 
            }  
            //returning false if we don't get any subarray with 0 sum.
            return false;
    }
}