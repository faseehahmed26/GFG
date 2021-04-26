class Solution
{
    /*You are required to complete this method
    * ar1 : 1st array
    * ar2 : 2nd array
    */
    int maxPathSum(int ar1[], int ar2[])
    {
        // Your code here
        
        int sum1,sum2,res,i,j;
        i=j=sum1=sum2=res=0;
        while(i<ar1.length && j<ar2.length){
            if(ar1[i]==ar2[j]){
                res+=Math.max(sum1,sum2)+ar1[i];
                sum1=sum2=0;
                ++i;++j;
            }
            else if(ar1[i]<ar2[j])
            sum1+=ar1[i++];
            else
            sum2+=ar2[j++];
        }
        while(i<ar1.length){
            sum1+=ar1[i++];
        }
        while(j<ar2.length){
            sum2+=ar2[j++];
            
        }
        return res +Math.max(sum1,sum2);
    }
}