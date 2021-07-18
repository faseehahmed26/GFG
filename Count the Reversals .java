//Initial Template for Java

import java.io.*;
import java.util.*;
class GfG
{
    public static void main (String[] args)
    {
        
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        
        while(t-- > 0)
        {
            String s = sc.next ();
    		System.out.println (new Sol().countRev (s));
        }
        
    }
}
// Contributed By: Pranay Bansal
// } Driver Code Ends


//User function Template for Java

class Sol
{
    int countRev (String s)
    {
        // if length is odd, we can't balance
        if (s.length () % 2 == 1) return -1;
        
        // number of opening and closing braces
        int op = 0, cl = 0;
    
        int res = 0; // ans
        for (int i = 0; i < s.length (); ++i)
        {
            if (s.charAt (i) == '{') op++;
            else cl++;
        
            // if cl exceeds op, then we balance the brackets till that point
            if (cl > op)
            {
                res += cl - op;
                int temp = cl;
                cl = op;
                op = temp;
            }
        }
        
        
        // finally, the we reverse the half of brakcets that form the unbalance
        // to balance the unbalanced pairs
        res += (Math.abs (cl - op)) / 2;
        return res;
    }
}

