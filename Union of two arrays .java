
class Solution{
    public static int doUnion(int a[], int n, int b[], int m) 
    {
        //Your code 
        if(n<0||m<0)
        return -1;
        HashSet<Integer> set =new HashSet<Integer>();
        for(int i=0;i<Math.max(n,m);i++){
                if(i<n){
                    set.add(a[i]);
                }
                if(i<m){
                    set.add(b[i]);
                }
                
        }
        return set.size();
        
    }
}