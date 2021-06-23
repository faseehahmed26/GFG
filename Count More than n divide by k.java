/*package whatever //do not write package name here */

import java.io.*;
import java.util.*;

class Driverclass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            int k = sc.nextInt();

            System.out.println(new Solution().countOccurence(arr, n, k));
        }
    }
}
// } Driver Code Ends



//class to store an element and its current count.
class eleCount 
{
    int e; // Element
    int c; // Count
    eleCount(int a, int b) {
        this.e = a;
        this.c = b;
    }
}

class Solution 
{
    //Function to find all elements in array that appear more than n/k times.
    static int countOccurence(int arr[], int n, int k)
    {
        int count = 0;
        if (k < 2) return 0;
        
        //creating a temporary array (contains element and count) of size k-1. 
        //initializing count of all elements as 0.
        eleCount temp[] = new eleCount[k];
        for (int i = 0; i < k; i++) 
            temp[i] = new eleCount(0, 0);

        //iterating over the array.
        for (int i = 0; i < n; i++)
        {
            int j = 0;
            //if arr[i] is already present in the element-count array temp[], 
            //then we increment its count.
            for (j = 0; j < k; j++) 
            {
                if (temp[j].e == arr[i]) {
                    temp[j].c += 1;
                    break;
                }
            }
            //if arr[i] is not present in temp[]
            if (j == k)
            {
                int l = 0;
                //if there is position available in temp[] then we place arr[i] 
                //in the first available position and set its count as 1.
                for (l = 0; l < k; l++) 
                {
                    if (temp[l].c == 0) {

                        temp[l].e = arr[i];
                        temp[l].c = 1;
                        break;
                    }
                }
                //if all the position in the temp[] are filled then we
                //decrease count of every element by 1.
                if (l == k)
                    for (l = 0; l < k; l++) 
                        temp[l].c -= 1;
            }
        }

        //checking actual counts of potential candidates in temp[].
        for (int i = 0; i < k; i++) 
        {
            int ac = 0; 
            //calculating actual count of elements.
            for (int j = 0; j < n; j++)
                if (arr[j] == temp[i].e) ac++;

            //if actual count is more than n/k then we increment the answer.
            if (ac > n / k) 
                count++;
        }
        return count;
    }
}