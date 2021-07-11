// Initial Template for Java

import java.io.*;
import java.util.*;

class GFG{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            int N = Integer.parseInt(in.readLine());
            String a[] = in.readLine().trim().split("\\s+");
            int arr[] = new int[N];
            for(int i = 0;i < N;i++)
                arr[i] = Integer.parseInt(a[i]);
            
            Solution ob = new Solution();
            List<Integer> ans = new ArrayList<Integer>();
            ans = ob.nextPermutation(N, arr);
            StringBuilder out = new StringBuilder();
            for(int i = 0;i < N;i++)
                out.append(ans.get(i) + " ");
            System.out.println(out);
        }
    }
}// } Driver Code Ends


// User function Template for Java

class Solution{
    void swap(int i, int j, int arr[]){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    
    List<Integer> nextPermutation(int N, int arr[]){
        // code here
        int ind = 0;
        int v[] = arr.clone();
        for(int i = N-2;i >= 0;i--){
            if(v[i] < v[i+1]){
                ind = i;
                break;
            }
        }
        for(int i = N-1;i > ind;i--){
            if(v[i] > v[ind]){
                swap(i, ind, v);
                ind++;
                break;
            }
        }
        for(int i = 0;i < (N-ind)/2;i++)
            swap(i + ind, N - i - 1, v);
        List<Integer> li = new ArrayList<>();
        for(int x : v) li.add(x);
        return li;
    }
}