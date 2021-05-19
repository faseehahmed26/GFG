class Solution {
    int findKRotation(int arr[], int n) {
        return countRotations(arr, 0, n - 1);
    }

    int countRotations(int arr[], int low, int high) {
        // This condition is needed to handle
        // the case when array is not rotated
        // at all
        if (high < low) return 0;

        // If there is only one element left
        if (high == low) return low;

        // Find mid
        // /*(low + high)/2;*/
        int mid = low + (high - low) / 2;

        // Check if element (mid+1) is minimum
        // element. Consider the cases like
        // {3, 4, 5, 1, 2}
        if (mid < high && arr[mid + 1] < arr[mid]) return (mid + 1);

        // Check if mid itself is minimum element
        if (mid > low && arr[mid] < arr[mid - 1]) return mid;

        // Decide whether we need to go to left
        // half or right half
        if (arr[high] > arr[mid]) return countRotations(arr, low, mid - 1);

        return countRotations(arr, mid + 1, high);
    }
}
/**
 * // Initial Template for Java

import java.io.*;
import java.util.*;

class GFG {
    // Driver code
    public static void main(String[] args) throws Exception {
        BufferedReader br =
            new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine().trim());
        while (t-- > 0) {
            int n = Integer.parseInt(br.readLine().trim());
            String[] str = br.readLine().trim().split(" ");
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = Integer.parseInt(str[i]);
            }

            int ans = new Solution().findKRotation(a, n);
            System.out.println(ans);
        }
    }
}
// } Driver Code Ends


// User function Template for Java

class Solution {
    int findKRotation(int arr[], int n) {
        // code here
        
        int s=0,e=n-1;
	    int m,nex,p;
	    while(s<=e){
	        m = s + (e-s)/2;
	        nex = (m+1)%n;
	        p = (m+n-1)%n;
	        if(arr[m]<= arr[nex] && arr[m]<=arr[p])
	            return m;
	        else if(arr[m] > arr[e])
	            s = m+1;
	        else if(arr[m] < arr[e])
	            e = m-1;
	    }
        return 0;
    }
}
 */