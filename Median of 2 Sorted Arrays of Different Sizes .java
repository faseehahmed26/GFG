
class GFG 
{ 
    static double medianOfArrays(int n, int m, int a[], int b[]) 
    {
        // Your Code Here
        int j=0;
        int i=n-1;
        while(i>=0 && j<m){
            if(a[i]>=b[j]){
                int temp=a[i];
                a[i]=b[j];
                b[j]=temp;
            }
            j++;i--;
        }
        
        Arrays.sort(a);
        Arrays.sort(b);
       
        if((m+n)%2 != 0){
            int mid=((m+n)/2)+1;
            if(mid<=n)
            return (double)a[mid-1];
            else
            return (double)b[mid-n-1];
            
        }
        else{
            int mid=(m+n)/2;
            if(mid<n)
            return (double)(a[mid-1]+a[mid])/2;
            else if(mid==n)
            return (double)(a[n-1]+b[0])/2;
            else
            return (double)(b[mid-n-1]+b[mid-n])/2;
        }
    }
}