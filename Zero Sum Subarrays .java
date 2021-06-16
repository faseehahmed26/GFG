//Initial Template for Java

/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;
class GFG {
	public static void main (String[] args) {
		Scanner sc=new Scanner(System.in);
		int t=sc.nextInt();
		sc.nextLine();
		while(t-->0)
		{
		    int n;
		    n=sc.nextInt();
		    
		    long arr[]=new long[n];
	
		    
		    for(int i=0;i<n;i++)
		    {
		        arr[i]=sc.nextLong();
		    }
            Solution ob = new Solution();
		    System.out.println(ob.findSubarray(arr,n));
		}
		
	}
}
// } Driver Code Ends


//User function Template for Java
//Back-end complete function Template for Java

class Solution{
    //Function to count subarrays with sum equal to 0.
public static long findSubarray(long[] arr ,long n) 
{
     long sum=0, counter=0; 
     //using Hashmap to store the prefix sum which has appeared already.
     HashMap<Long, Long>mp=new HashMap<>();
    
        //iterating over the array.
        for(int i=0;i<n;i++)
        {
            //storing prefix sum.
            sum+=arr[i];
            
            //if prefix sum is zero that means we get a subarray with sum=0.
            if(sum==0)
            {
                //incrementing the counter.
                counter++;
            }
            
            //if prefix sum is already present in Hashmap then it is repeated 
            //which means there is a subarray whose summation is 0.
            if(mp.containsKey(sum)) 
            {
               //we add the value at prefix sum in Hashmap to counter.
               counter+=mp.get(sum);
            }
            
            //incrementing the count of prefix sum obtained in Hashmap.
            if(mp.containsKey(sum))
            {
                long k=mp.get(sum);
                k++;
                mp.put(sum,k);
                
            }
            else
             mp.put(sum,1L);
        }
       
       //returning the counter.
       return counter ;
}
}