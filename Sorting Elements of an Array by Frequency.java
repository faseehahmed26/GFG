
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


//User function Template for Java
class Element
{
    int val;
    int key;
    
    Element(int x,int y)
    {
        key = x;
        val = y;
    }
}

class Solution
{
    //Function to sort the array according to frequency of elements.
    static ArrayList<Integer> sortByFreq(int arr[], int n)
    {
        HashMap<Integer,Integer> map = new HashMap<>();
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        for(int i : arr)
        {
            if(map.containsKey(i))
            map.put(i,map.get(i)+1);
            else
            map.put(i,1);
        }
        
        Element el[] = new Element[map.size()];
        int c = 0;
        
        for(Map.Entry<Integer,Integer> e : map.entrySet())
        {
            el[c] = new Element(e.getKey(),e.getValue());
            c++;
        }
        
        Arrays.sort(el,new Comparator<Element>(){
            
           public int compare(Element e1,Element e2)
           {
               if(e1.val>e2.val)
               return 1;
               else if(e1.val<e2.val)
               return -1;
               else 
               {
                   if(e1.key<e2.key)
                   return 1;
                   else
                   return -1;
               }
           }
        });
        
        for(int i = el.length-1;i>=0;i--)
        {
            while(el[i].val>0)
            {
                res.add(el[i].key);
                el[i].val--;
            }
        }
        
        return res;
    }
}