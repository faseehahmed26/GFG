
class Solution
{
    //Function to find the first position with different bits.
    public static int posOfRightMostDiffBit(int m, int n)
    {
            
        // Your code here    
        return (int)(Math.log((m^n)&-(m^n))/Math.log(2))+1;
            
    }
}
