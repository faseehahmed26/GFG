class Solution{
    static ArrayList<Integer> subarraySum(int[] arr, int n, int s) {
        
        // Your code 
        ArrayList<Integer> l=new ArrayList<Integer>();
        int st,sum;
        st=0;
        sum=arr[0];
        for(int i=1;i<=n;i++){
        while(sum>s && st<i-1){
            sum-=arr[st];
            st++;
        }
        if(sum==s){
            l.add(st+1);
            l.add(i);
            return l;
            }
        
        if(i<n)
        sum+=arr[i];
        }
        l.add(-1);
        return l;
        
        
    }
}