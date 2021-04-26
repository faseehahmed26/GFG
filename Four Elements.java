class Compute
{   
    boolean find2No(int[] A,int s,int e, int X){
        while(s!=e){
            if(A[s]+A[e]==X){
                return true;
            }
            if(A[s]+A[e]>X){
                e--;
            }
            else
            s++;
        }
        return false;
    }
    boolean find4Numbers(int A[], int n, int X) 
    {
        Arrays.sort(A);
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                if(j+2<n && find2No(A,j+1,n-1,X-A[i]-A[j])){
                    return true;
                }
            }
        }
        return false;
        
    }
}