//Initial Template for Java

import java.util.*;
import java.io.*;
import java.lang.*;
import java.util.Map.Entry;


class Driverclass 
{
	public static void main(String[] args) throws NumberFormatException, IOException
	{
		BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(sc.readLine());
	    while(n != 0)
		{
			int size = Integer.parseInt(sc.readLine());
			int arr[] = new int[size];
			String[] temp = sc.readLine().trim().split("\\s+");
			
			for(int i = 0; i < size; i++)
			    arr[i] = Integer.parseInt(temp[i]);
			    
			 ArrayList<Integer> ans = new ArrayList<Integer>();
			 ans = new Solution().sortByFreq(arr, size);
			 for(int i=0;i<ans.size();i++)
			    System.out.print(ans.get(i)+" ");
		    System.out.println();
			n--;
		}
	}
}

// } Driver Code Ends

class comparator implements Comparator<Map.Entry<Integer,Integer>>
{
    //Function used in sorting list elements first according to frequency
    //and if it is same for both values, then smaller number comes first.
	public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) 
	{
		if(o1.getValue() > o2.getValue())
			return -1;
		else if(o1.getValue() == o2.getValue()){
			if(o2.getKey() < o1.getKey())
			    return 1;
			else
			    return -1;
		    
		}
		else if(o1.getValue() < o2.getValue())
			return 1;
		
		return Integer.MIN_VALUE;
	}
	
}
class Solution
{
    //Function to sort the array according to frequency of elements.
    static ArrayList<Integer> sortByFreq(int arr[], int n)
    {
        //using map to keep count of the elements.
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        
        //storing the frequency of each element in map.
		for(int i = 0; i < n; i++)
		{
				int num = arr[i];
				
				if(map.containsKey(num))
				{
					map.put(num, map.get(num) + 1);
				}
				else
				{
					map.put(num, 0);
				}
		}
		
		//copying the elements and their count from map to list.	
		List<Map.Entry<Integer,Integer>> set = new ArrayList<Map.Entry<Integer,Integer>> (map.entrySet());
		
		//sorting the list according to frequency.
		Collections.sort(set,new comparator());
		ArrayList <Integer> ans = new ArrayList<Integer>();
		
		for(int i = 0;i<map.size();i++)
		{
			int count = set.get(i).getValue();
			//storing elements as many times as their count in output list.
			while(count >= 0)
			{
					ans.add(set.get(i).getKey());
					count -- ;
			}
		}
		//returning the output.
        return ans;
    }
}