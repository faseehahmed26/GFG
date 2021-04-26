class Solution {
    // Function to find maximum product subarray
    long maxProduct(int[] arr, int n) {
        // code here
        long mmax=arr[0];
        long mmin=arr[0];
        long omax=arr[0];
        for(int i=1;i<n;i++){
            if(arr[i]<0){
                long temp=mmax;
                mmax=mmin;
                mmin=temp;
            }
            mmax=Math.max(arr[i],mmax*arr[i]);
            mmin=Math.min(arr[i],mmin*arr[i]);
            omax=Math.max(omax,mmax);
        }
        return omax;
    }
}