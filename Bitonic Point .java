class Solution{
    public int findMaximumUtil(int arr[], int low, int high) {

        /* Base Case: Only one element is present in arr[low..high]*/
        if (low == high) return arr[low];

        /* If there are two elements and first is greater then
            the first element is maximum */
        if ((high == low + 1) && arr[low] >= arr[high]) return arr[low];

        /* If there are two elements and second is greater then
            the second element is maximum */
        if ((high == low + 1) && arr[low] < arr[high]) return arr[high];

        int mid = (low + high) / 2; /*low + (high - low)/2;*/

        /* If we reach a point where arr[mid] is greater than both of
            its adjacent elements arr[mid-1] and arr[mid+1], then arr[mid]
            is the maximum element*/
        if (arr[mid] > arr[mid + 1] && arr[mid] > arr[mid - 1]) return arr[mid];

        /* If arr[mid] is greater than the next
            element and smaller than the previous
            element then maximum lies on left side of mid */
        if (arr[mid] > arr[mid + 1] && arr[mid] < arr[mid - 1])
            return findMaximumUtil(arr, low, mid - 1);

        // when arr[mid] is greater than arr[mid-1]
        // and smaller than arr[mid+1]
        else
            return findMaximumUtil(arr, mid + 1, high);
    }
    public int findMaximum(int arr[], int n) {
        return findMaximumUtil(arr, 0, n - 1);
    }
};
/**
 * 
import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine().trim());
        while (tc-- > 0) {
            String[] inputLine;
            int n = Integer.parseInt(br.readLine().trim());
            int[] arr = new int[n];
            inputLine = br.readLine().trim().split(" ");
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(inputLine[i]);
            }

            int ans = new Solution().findMaximum(arr, n);
            System.out.println(ans);
        }
    }
}
// } Driver Code Ends


//User function Template for Java



class Solution {
    int findMaximum(int[] arr, int n) {
        // code here
        if(arr[1]>arr[0] && arr[n-1]>arr[n-2])
        return arr[n-1];
        int l=0;
        int r=n-1;
        while(l<r){
            int m=(l+r)/2;
            if(arr[m]>arr[m-1] && arr[m]>arr[m+1])
            return arr[m];
            else if(arr[m]<arr[m-1]&&arr[m]>arr[m+1])
            r=m; 
            else if(arr[m]>arr[m-1]&&arr[m]<arr[m+1])
            l=m;
            
        }
        return -1;
    }
}
 */