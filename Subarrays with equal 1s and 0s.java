class Solution
{
    //Function to count subarrays with 1s and 0s.
    static int countSubarrWithEqualZeroAndOne(int arr[], int n)
    {
        // add your code here
        int ans=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        
        map.put(0,1);
        int sum=0;
        for(int val: arr){
            if(val==0)
            sum+=-1;
            else
            sum+=1;
            if(map.containsKey(sum)){
                ans+=map.get(sum);
                map.put(sum,map.get(sum)+1);
            }
            else
            map.put(sum,1);
        }
        return ans;
    }
}



import java.util.*;
import java.lang.*;
import java.lang.*;
import java.io.*;
class Driverclass
 {
	public static void main (String[] args) {
	   
	   Scanner in = new Scanner(System.in);
	   int t= in.nextInt();
	   while(t-->0){
	       
	       int n = in.nextInt();
	       int [] arr= new int[n];
	       for(int i=0;i<n;i++) {
	           arr[i] = in.nextInt();
	       }
	       System.out.println(new Solution().countSubarrWithEqualZeroAndOne(arr, n));
	   }
	 }
}

// } Driver Code Ends


//User function Template for Java



//Back-end complete function Template for Java

class Solution
{
    //Function to count subarrays with 1s and 0s.
    static int countSubarrWithEqualZeroAndOne(int arr[], int n)
    {
        //using a map to store frequency of values obtained for prefix sum.
        Map<Integer,Integer> um = new HashMap<>(); 
        
        int curr_sum = 0; 
      
        //iterating over the array.
        for (int i = 0; i < n; i++) { 
            
            //calculating prefix sum by considering 0 as -1 and adding 1 if 
    	    //value of arr[i] is 1 or adding -1 if it is 0.
            curr_sum += (arr[i] == 0) ? -1 : arr[i]; 
            
            //updating the value in map.
            um.put(curr_sum, um.get(curr_sum)==null?1:um.get(curr_sum)+1); 
        } 
      
        int count = 0; 
          
        //iterating over the map.
        for (Map.Entry<Integer,Integer> itr : um.entrySet()) 
        { 
            //if value of any element in map is more than 1, then we 
    	    //update the count of subarrays.
    	    //count=(value*(value-1))/2 where value is value of key in map.
            if (itr.getValue() > 1) 
                count += ((itr.getValue()* (itr.getValue()- 1)) / 2); 
        } 
        
        //adding frequency of sum 0 in the map to the final result.
        if (um.containsKey(0)) 
            count += um.get(0); 
      
        //returning the count of subarrays.
        return count; 
    }
}



