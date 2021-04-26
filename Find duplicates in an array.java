class Solution {
    public static ArrayList<Integer> duplicates(int arr[], int n) {
        // code here
        ArrayList<Integer> t=new ArrayList<Integer>();
        for(int i=0;i<n;i++){
            int j=arr[i]%n;
            arr[j]+=n;
        }
        int c=0;
        for(int i=0;i<n;i++){
            if(arr[i]/n>1){
                t.add(i);
                c++;
                
            }
        }
        if(c==0){
            t.add(-1);
        }
        return t;
        
    }
}
