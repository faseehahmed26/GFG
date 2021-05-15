/**class Solution
{
    //Function to find position of first set bit in the given number.
    public static int getFirstSetBit(int n){
            
        // Your code here
        return (int)((Math.log10(n^(n-1))/Math.log10(2))+1);
            
    }
}
*/

class Solution
{
    //Function to find position of first set bit in the given number.
    public static int getFirstSetBit(int n)
    {
        if(n==0)
            return 0;
            
        //doing AND operation of n and -n. n and -n will have similar
        //bits only till the first set bit starting from the right  
        //and differnt bits after the first set bit.Then we take 
        //log2 of the result to find the position.
        
        //we add 1 to obtained value so that count starts from 1 instead of 0.
        
        return (int)(Math.log(n&-n)/Math.log(2.0)) + 1;
    }
}