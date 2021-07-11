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
            int n = Integer.parseInt(br.readLine().trim());
            int[] nums = new int[n];
            String[] S = br.readLine().trim().split(" ");
            for(int i = 0; i < n; i++)
                nums[i]  =Integer.parseInt(S[i]);
            int k = Integer.parseInt(br.readLine().trim());
            Solution obj = new Solution();
            System.out.println(obj.solveWordWrap(nums, k));
        }
    }
}
// } Driver Code Ends


class Solution
{
    public int solveWordWrap (int[] nums, int k)
    {
        // Code here 
        int n=nums.length;
        int i, j;

        // Variable to store 
        // number of characters 
        // in given line.
        int currlen;
    
        // Variable to store 
        // possible minimum 
        // cost of line.
        int cost;
    
        // DP table in which 
        // dp[i] represents 
        // cost of line starting
        // with word arr[i].
        int dp[] = new int[n];
    
        // Array in which ans[i]
        // store index of last 
        // word in line starting 
        // with word arr[i].
        int ans[] = new int[n];
    
        // If only one word is present 
        // then only one line is required. 
        // Cost of last line is zero. 
        // Hence cost of this line is zero. 
        // Ending point is also n-1 as 
        // single word is present.
        dp[n - 1] = 0;
        ans[n - 1] = n - 1;
    
        // Make each word first 
        // word of line by iterating 
        // over each index in arr.
        for (i = n - 2; i >= 0; i--) 
        {
            currlen = -1;
            dp[i] = Integer.MAX_VALUE;
    
            // Keep on adding words in 
            // current line by iterating 
            // from starting word upto 
            // last word in arr.
            for (j = i; j < n; j++) 
            {
    
                // Update number of characters
                // in current line. arr[j] is
                // number of characters in
                // current word and 1
                // represents space character
                // between two words.
                currlen += (nums[j] + 1);
    
                // If limit of characters
                // is violated then no more
                // words can be added to
                // current line.
                if (currlen > k)
                    break;
    
                // If current word that is
                // added to line is last
                // word of arr then current
                // line is last line. Cost of
                // last line is 0. Else cost
                // is square of extra spaces
                // plus cost of putting line
                // breaks in rest of words
                // from j+1 to n-1.
                if (j == n - 1)
                    cost = 0;
                else
                    cost = (k - currlen) * 
                           (k - currlen) +
                                dp[j + 1];
    
                // Check if this arrangement
                // gives minimum cost for
                // line starting with word 
                // arr[i].
                if (cost < dp[i]) 
                {
                    dp[i] = cost;
                    ans[i] = j;
                }
            }
        }
        
        return dp[0];
        
    }
}