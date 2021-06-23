/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;

class Driverclass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            int k = sc.nextInt();

            System.out.println(new Solution().countOccurence(arr, n, k));
        }
    }
}
// } Driver Code Ends




class Solution 
{
    //Function to find all elements in array that appear more than n/k times.
    public int countOccurence(int[] arr, int n, int k) 
    {
        // your code here,return the answer
        int c=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i:arr){
             map.put(i,map.getOrDefault(i,0)+1);
        }
        for(int ans:map.keySet()){
            if(map.get(ans)>n/k)
            c++;
        }
        
        return c;
    }
}
