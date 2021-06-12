//Initial Template for Java

import java.math.*;
import java.util.*;
import java.io.*;

class Driverclass
{
    // Driver Code
    static class FastReader{ 
        BufferedReader br; 
        StringTokenizer st; 
  
        public FastReader(){ 
            br = new BufferedReader(new InputStreamReader(System.in)); 
        } 
  
        String next(){ 
            while (st == null || !st.hasMoreElements()){ 
                try{ st = new StringTokenizer(br.readLine()); } catch (IOException  e){ e.printStackTrace(); } 
            } 
            return st.nextToken(); 
        } 
  
        String nextLine(){ 
            String str = ""; 
            try{ str = br.readLine(); } catch (IOException e) { e.printStackTrace(); } 
            return str; 
        } 

        Integer nextInt(){
            return Integer.parseInt(next());
        }
    }
    
	public static void main(String args[])
	{
		FastReader sc = new FastReader();
		PrintWriter out = new PrintWriter(System.out);
		int t = sc.nextInt();
		
		while(t>0)
		{
			int n = sc.nextInt();
			int a[] = new int[n];
			
			for(int i=0; i<n; i++)
				a[i] = sc.nextInt();
		    out.println(new Solution().findLongestConseqSubseq(a, n));
		    t--;
		}
		out.flush();
	}
}// } Driver Code Ends


//User function Template for Java

class Solution
{   
    //Function to return length of longest subsequence of consecutive integers.
	static int findLongestConseqSubseq(int a[], int n)
	{
	    //using a HashSet to store elements.
		HashSet<Integer> hs = new HashSet<Integer>();
		int ans = 0;
		
		//inserting all the array elements in HashSet.
		for(int i=0; i<n; i++)
			hs.add(a[i]);
		
		//checking each possible sequence from the start. 
		for(int i=0; i<n; i++)
		{
		    //if current element is starting element of a sequence then only 
            //we try to find out the length of sequence. 
			if(!hs.contains(a[i]-1))
			{
				int j = a[i];
				//then we keep checking whether the next consecutive elements
                //are present in HashSet and we keep incrementing the counter. 
				while(hs.contains(j))
				{
					j++;
				}
				
				//storing the maximum count.
				if(ans<j-a[i])
					ans=j-a[i];
			}
		}
		//returning the length of longest subsequence.
		return ans;
	}
}
