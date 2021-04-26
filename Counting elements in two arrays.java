class Solution
{   
    static int binary_Srch(int arr[],int l,int r,int s){
        while(l<=r){
            int mid=(l+r)/2;
            if(arr[mid]<=s)
                l=mid+1;
            else
                r=mid-1;
            
        }
        return r;
    }
    public static ArrayList<Integer> countEleLessThanOrEqual(int arr1[], int arr2[], int m, int n)
    {
       // add your code here
       ArrayList<Integer> a=new ArrayList<Integer>();
       Arrays.sort(arr2);
       for(int i=0;i<m;i++){
           int index=binary_Srch(arr2,0,n-1,arr1[i]);
           a.add(index+1);
           
           
       }
       return a;
    }
}