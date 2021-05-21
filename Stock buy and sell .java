class Solution {
    class Interval {
        int buy, sell;
    }   public void stockBuySell(int[] price, int n) {
        int count = 0; // count of solution pairs

        // solution array
        Interval[] sol = new Interval[(n / 2) + 1];
        // Traverse through given price array
        int i = 0;
        while (i < n - 1) {
            // Find Local Minima. Note that the limit is (n-2) as we are
            // comparing present element to the next element.
            while ((i < n - 1) && (price[i + 1] <= price[i])) i++;

            // If we reached the end, break as no further solution possible
            if (i == n - 1) break;

            // Store the index of minima
            Interval inv = new Interval();
            inv.buy = i++;

            // Find Local Maxima.  Note that the limit is (n-1) as we are
            // comparing to previous element
            while ((i < n && i > 0) && (price[i] >= price[i - 1])) i++;

            // Store the index of maxima
            inv.sell = i - 1;
            sol[count] = inv;
            // Increment count of buy/sell pairs
            count++;
        }

        // print solution
        if (count == 0)
            System.out.print("No Profit");
        else {
            for (int j = 0; j < count; j++)
                System.out.print("(" + sol[j].buy + " " + sol[j].sell + ") ");
        }
        System.out.println();
    }
}

/**
 * // Initial Template for Java

import java.io.*;
import java.util.*;

class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine().trim());

        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            int[] price = new int[n];
            String[] str = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                price[i] = Integer.parseInt(str[i]);
            }
            Solution sln = new Solution();
            sln.stockBuySell(price, n);
        }
    }
}// } Driver Code Ends

 * class Solution 
{
    public void stockBuySell(int[] price, int n) {
        // code here
        if(n==1)
        return;
        boolean exchange=false;
        int b=0,s=0;
        int i=0;
        while(i<n-1){
            while((i<n-1) && (price[i+1]<=price[i])){
                i++;
            }
            if(i==n-1)
            break;
            
            b=i++;
        
            while((i<n) && (price[i]>=price[i-1])){
                i++;
            }
            s=i-1;    
            
            System.out.print("(" +b+" "+s+")"+" ");
            exchange=true;
            
            
        }
        if(exchange==false){
            System.out.print("No Profit");
        }
        System.out.println();
        return;
        
        
    }
    
}
 */