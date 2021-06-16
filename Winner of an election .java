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
            int n = sc.nextInt();
            String arr[] = new String[n];
            
            for(int i = 0; i < n; i++)
                arr[i] = sc.next();
            
            Solution obj = new Solution();    
            String result[] = obj.winner(arr, n);
            System.out.println(result[0] + " " + result[1]);
            
           
        }
        
    }
}
// } Driver Code Ends


//User function Template for Java
class Solution
{
    //Function to print the name of candidate that received maximum votes.
    public static String[] winner(String arr[], int n)
    {
        //using map to store count of votes for each name.
        HashMap<String, Integer> mp = new HashMap<>();
        
        //storing the frequency of names in the map.
        for(int i = 0; i < n; i++)
        {
            String key = arr[i];
            if(mp.containsKey(key) == true)
            {
                int freq = mp.get(key);
                freq++;
                mp.put(key, freq);
            }else
            {
                mp.put(key, 1);
            }
        }
        
        int maxx = 0; 
        String answer = ""; 
        
        //iterating through the map to find the name with highest frequency.
        for (Map.Entry<String,Integer> entry : mp.entrySet()) 
        { 
            String key  = entry.getKey(); 
            Integer val = entry.getValue();
            
            //updating answer whenever we get any name with frequency 
            //greater than frequency of name stored previously.
            if (val > maxx) 
            { 
                maxx = val; 
                answer = key; 
            } 
            //if frequency of current name is same as the previously 
            //stored name then we compare both names and store 
            //lexicographically smaller name.
            else if (val == maxx && 
                answer.compareTo(key) > 0) 
                answer = key; 
        } 
        
        //storing name with highest votes and the number of votes in a list.
        String result[] = {answer,maxx+""};
        
        //returning the list.
        return result;
    }
}
