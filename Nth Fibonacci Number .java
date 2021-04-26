
//User function Template for Java
class Solution {
    static long nthFibonacci(long n){
        // code here
        int num=(int)n;
        long d[]=new long[num+1];
        d[0]=0;
        d[1]=1;
        for(int i=2;i<num+1;i++){
            d[i]=( d[(i-1)]+d[(i-2)])%1000000007;
            
        } 
        return d[num];
    }
}   