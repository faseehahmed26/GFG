class Solution {
    public int findExtra(int a[], int b[], int n) {
        // add code here.
        if(a[n-2]==b[n-2])
            {
                return n-1;
            }
        if(a[0]!=b[0])
            {
                return 0;
            }
        int i=n;
        int l=0,r=n-1;
        int mid=-1;
        while(l<=r){
          mid=(l+r)/2;
          if(b[mid]==a[mid]){
              l=mid+1;
          }
          else if (b[mid]!=a[mid] && a[mid-1]==b[mid-1]){
              
               return mid;
          }
          else if(b[mid]!=a[mid] && a[mid-1]!=b[mid-1]){
                r=mid-1;
          }
        }
        return mid;
    }
}