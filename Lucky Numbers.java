
class Solution
{
    // Complete the function
    // n: Input n
    // Return True if the given number is a lucky number else return False
    public static boolean isCheck(int n,int counter){
        if(counter<=n){
            if(n%counter==0) return false;
            n=n-n/counter;
            counter++;
            return isCheck(n,counter);
        }
        
        else 
            return true;
    }
    public static boolean isLucky(int n)
    {
    
    return isCheck(n,2);
    }
}