class Solution {
    //Function to reverse every sub-array group of size k.
    void reverseInGroups(ArrayList<Integer> arr, int n, int k) {
        // code here
        for(int i=0;i<n;i+=k){
            int s=i;
            int l=Math.min(i+k-1,n-1);
            while(s<l){
                Collections.swap(arr,s,l);
                s++;
                l--;
            }
        }
    }
}