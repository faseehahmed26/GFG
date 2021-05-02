
class Solution
{
    ArrayList<Integer> commonElements(int A[], int B[], int C[], int n1, int n2, int n3) 
    {
        // code here 
        int i=0,j=0,k=0;
        ArrayList<Integer> a=new ArrayList<Integer>();
        while(i<A.length && j<B.length && k<C.length){
            if(A[i]==B[j] && B[j]==C[k]){
                if(!a.contains(A[i])){
                a.add(A[i]);
                }
                i++;
                j++;
                k++;
            }
            else if(A[i]<B[j])
            i++;
            else if(B[j]<C[k])
            j++;
            else
            k++;
        }
        return a;
        
    }
}