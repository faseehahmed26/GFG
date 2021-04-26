class Solution
{
    //Function to return list containing first n fibonacci numbers.
    public static void fibRec(long fib[] ,int n){
        if(n>=fib.length){
            return ;
        }
        fib[n]=fib[n-1]+fib[n-2];
        fibRec(fib,n+1);
    }
    public static long[] printFibb(int n) 
    {
        //Your code here
        
        long l[]=new long[n];
        l[0]=1;
        if(n==1)
        return new long[] {l[0]};
        l[1]=1;
        if(n==2){
            return new long[] {l[0],l[1]};  
        }
        fibRec(l,2);
        return l;
    }
}