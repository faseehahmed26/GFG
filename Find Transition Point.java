class GfG {
    int transitionPoint(int arr[], int n) {
        // code here
        int l=0,r=n-1;
        while(l<=r){
            int m=(l+r)/2;
            if(arr[m]==1  &&(m-1<0 || arr[m-1]==0)){
                return m;
            }
            else if(arr[m]==1)
            r=m-1;
            else
            l=m+1;
        }
        return -1;
    }
}