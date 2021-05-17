
class Solution
{
    //Function to find maximum number of consecutive steps 
    //to gain an increase in altitude with each step.
    static int maxStep(int A[], int N)
    {
        // Your code here
        int c=0;
        int mc=0;
        for(int i=1;i<N;i++){
            if(A[i]>A[i-1]){
                c++;
                mc=Math.max(mc,c);
                
            }
            else {
                c=0;
            }
        }
        
        return mc;
    }
    
}