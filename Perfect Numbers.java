class Solution {
    static int isPerfectNumber(Long N) {
        // code here
        int flag=0;
        if(N==1)
        return flag;
        
        int sum=1;
        for(int i=2;i<Math.sqrt(N);i++){
            if(N%i==0){
                sum+=i;
                sum+=N/i;
            }
        }
        if(sum==N)
        flag=1;
        return flag;
        
    }
};