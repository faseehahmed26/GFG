// { Driver Code Starts
//Initial Template for Java

import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while(T-->0)
        {
            String str = br.readLine().trim();
            Solution ob = new Solution();
            int ans = ob.LongestRepeatingSubsequence(str);
            System.out.println(ans);
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution
{
    public int lcs(String str,int i,int j,int[][] dp){
        if(i>=0 && j>=0){
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        if(i!=j && str.charAt(i)==str.charAt(j)){
            return dp[i][j]=1+lcs(str,i-1,j-1,dp);
        }
        return dp[i][j]=Math.max(lcs(str,i-1,j,dp),lcs(str,i,j-1,dp));
        }
        return 0;
    }
    public int LongestRepeatingSubsequence(String str)
    {
        int len=str.length();
        int[][] dp=new int[len][len];
        for(int[] x:dp){Arrays.fill(x,-1);}
        return lcs(str,len-1,len-1,dp);
    }
}