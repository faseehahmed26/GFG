class Solution {
    
    int binarysearch(int arr[], int n, int k){
        // code here
        int l=0,r=n-1,mid;
        while(l<=r){
            mid=(l+r)/2;
            if(arr[mid]==k)
            return mid;
            else if(arr[mid]>k)
            r=mid-1;
            else
            l=mid+1;
        }
        return -1;

    }
}