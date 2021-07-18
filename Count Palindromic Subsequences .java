import java.util.*;
class GFG
{
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		sc.nextLine();
		while(t>0)
		{
			String str = sc.nextLine();
			//System.out.println(str.length());
			Solution ob  = new Solution();
			System.out.println(ob.countPS(str));
		t--;
		}
	}
}// } Driver Code Ends


/*You are required to complete below method */

class Solution
{   
    long countPS(String str)
    {
        // Your code heref
        int n = str.length();
        int mod = 1000000007;
        long[][] opt = new long[n][n];
        for (int gap = 0; gap < n; gap++) {
        for (int i = 0; i < n - gap; i++) {
        int j=i+gap;
        if(i==j){
            opt[i][j]=1;
        } else if (str.charAt(i) == str.charAt(j)) {
        opt[i][j] = (opt[i + 1][j] + opt[i][j - 1] + 1) % mod;
        } else {
        opt[i][j] = (opt[i + 1][j] + (opt[i][j - 1] - opt[i + 1][j - 1])) % mod;
        if (opt[i][j] < 0) opt[i][j] += mod;
        }
        }
        }
        return opt[0][n - 1];
                
    }
}