class Solution{
    //Function to find the days of buying and selling stock for max profit.
    ArrayList<ArrayList<Integer> > stockBuySell(int A[], int n) {
        // code here
        
    
        int count =0;
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<n;i++){
            int j= i;
            count=0;
            while(j<n-1 && A[j+1]>=A[j] && A[i]!=A[j+1])
            {
                count++;
                j++;
            }
            if(count>0 && j<n && A[i]<A[j]){
                ArrayList<Integer> arr= new ArrayList<Integer>();
                arr.add(i);
                arr.add(j);
                res.add(arr);
                i=j;
            }
        }
        return res;
    }
}
