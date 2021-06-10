import java.io.*;
import java.lang.*;
import java.util.*;

class Item {
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
}

class GfG {
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine().trim());
		while(t-->0){
            String inputLine[] = br.readLine().trim().split(" ");
            int n = Integer.parseInt(inputLine[0]);
            int w = Integer.parseInt(inputLine[1]);
            Item[] arr = new Item[n];
            inputLine = br.readLine().trim().split(" ");
            for(int i=0, k=0; i<n; i++){
                arr[i] = new Item(Integer.parseInt(inputLine[k++]), Integer.parseInt(inputLine[k++]));
            }
            System.out.println(String.format("%.2f", new Solution().fractionalKnapsack(w, arr, n)));
        }
    }
}// } Driver Code Ends


/*
class Item {
    int value, weight;
    Item(int x, int y){
        this.value = x;
        this.weight = y;
    }
}
*/

  
class itemComparator implements Comparator<Item>
{
    //comparison function to sort items according to value/weight ratio.
    public int compare(Item a, Item b)
    {
        double r1 = (double)a.value / a.weight;
        double r2 = (double)b.value / b.weight;
        if(r1 > r2){
            return -1;
        } else if(r1 < r2) {
            return 1;
        }
        return 0;
    }
}

class Solution
{
    //Function to get the maximum total value in the knapsack.
    double fractionalKnapsack(int W, Item arr[], int n) 
    {
        //sorting items on basis of value/weight ratio.
        Arrays.sort(arr, new itemComparator());
        
        //taking variable for current weight in knapsack.
        int curWeight = 0;
        double finalvalue = 0.0;
        
        //iterating over all the items.
        for (int i = 0; i < n; i++) 
        {
            //if currweight + item's weight is less than or equal to W,
            //then we add the item's value to final value.
            if (curWeight + arr[i].weight <= W) 
            {
                curWeight += arr[i].weight;
                finalvalue += arr[i].value;
            } 
            //else we take the fraction of the that item's value 
            //based on the remaining weight in knapsack.
            else 
            {
                int remain = W - curWeight;
                finalvalue += arr[i].value * ((double) remain / arr[i].weight);
                break;
            }
        }
         //returning final value.
        return finalvalue;
    }
}

