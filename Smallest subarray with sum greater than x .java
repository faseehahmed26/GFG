//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;

class GFG {
	public static void main(String[] args) throws IOException
	{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while(t-->0)
        {
            StringTokenizer stt = new StringTokenizer(br.readLine());
            
            int n = Integer.parseInt(stt.nextToken());
            int m = Integer.parseInt(stt.nextToken());
            // int n = Integer.parseInt(br.readLine().trim());
            int a[] = new int[n];
            String inputLine[] = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(inputLine[i]);
            }
            
            Solution obj = new Solution();
            System.out.println(obj.sb(a, n, m));
        }
	}
}

// } Driver Code Ends


//User function Template for Java


class Solution {

    public static int sb(int a[], int n, int x) {
        // Your code goes here 
        int sum=0;
        int len=Integer.MAX_VALUE;
        int l=0;
        for(int i=0;i<n;i++){
            sum+=a[i];
            while(sum>x && l<=i){
                sum=sum-a[l];
                len=Math.min(len,i-l+1);
                l++;
            }       
            
        }
        return len;
    }
}

