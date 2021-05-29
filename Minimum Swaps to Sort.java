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
            for(int i = 0; i < n; i++){
                nums[i] = Integer.parseInt(S[i]);
            }
            Solution obj = new Solution();
            int ans = obj.minSwaps(nums);
            System.out.println(ans);
       }
    }
}// } Driver Code Ends


// Back-end Complete function Template for JAVA

class Solution
{
    //Function to find the minimum number of swaps required to sort the array.
    public int minSwaps(int nums[])
    {
        int n = nums.length;
        
        //creating a list of pairs where first element of pair is array 
        //element and second element is its position.
        ArrayList<ArrayList<Integer>> cur = new ArrayList<>();
        
        //iterating over the array elements.
        for(int i=0; i<n; i++)
        {
            //storing the elements and their position as pair in the list.
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(nums[i]);
            temp.add(i);
            cur.add(temp);
        }
        
        //sorting the list.
        cur.sort(new Comparator<ArrayList<Integer>> ()
        {
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2)
            {
                return o1.get(0).compareTo(o2.get(0));
            }
        });
        
        //using boolean list to mark visited elements and currently 
        //marking all the nodes as false.
        boolean[] vis = new boolean[n];
        Arrays.fill(vis, false);
        
        int ans = 0;
        
        for (int i = 0; i < n; i++)
        {
            //if element is already visited or it is already at
		    //correct position, we continue the loop.
            if (vis[i] || cur.get(i).get(1) == i)
                continue;
            else
            {
                int cycle_size = 0;
                int j = i;
                
                //while element is not visited, we find out the number of nodes 
                //in this cycle and keep incrementing cycle size.
                while (!vis[j])
                {
                    vis[j] = true;
                    j = cur.get(j).get(1);
                    cycle_size++;
                }
                //updating number of swaps required.
                ans += Math.max(0,cycle_size - 1);
            }
        }
        return ans;
    }
}