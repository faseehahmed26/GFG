class Solution{
    
    // Function to find number of bits needed to be flipped to convert A to B
    public static int countBitsFlip(int a, int b){
        
        // Your code here
        int c=0;
        int n=a^b;
        
        while(n>0){
        n=n&(n-1);
        c++;
        }
        return c;
        
    }
    
    
}
/*Input: A = 10, B = 20
Output: 4
Explanation:
A  = 01010
B  = 10100
As we can see, the bits of A that need 
to be flipped are 01010. If we flip 
these bits, we get 10100, which is B.
*/