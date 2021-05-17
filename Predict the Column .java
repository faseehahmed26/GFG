class Solution
{
    int columnWithMaxZeros(int arr[][], int N)
    {
        // code here 
        int r=0;
        int rc=0;
        for(int i=0;i<N;i++){
            int count=0;
            for(int j=0;j<N;j++){
                if(arr[j][i]==0)
                count++;
            }
            if(count>rc){
                r=i;
                rc=count;
            }
        }
        return r;
    }
}