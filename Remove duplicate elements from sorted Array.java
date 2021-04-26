class Solution {
    int remove_duplicate(int A[],int N){
        // code here
        int t=0;
        for(int i=1;i<N;++i){
            if (A[i]!=A[t]){
              A[++t]=A[i];
              
            }
        }
        return t+1;
    }
}