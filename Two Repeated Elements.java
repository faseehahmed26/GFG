
class Solution
{
    //Function to find two repeated elements.
    public Pair twoRepeated(int arr[], int N)
    {
        // Your code here
       boolean first = false;
       Pair res = new Pair();
    
        for(int p = 0; p < N+2 ; p++)
        {
            if(arr[Math.abs(arr[p])] > 0)
            {
                arr[Math.abs(arr[p])] = -arr[Math.abs(arr[p])];
            }
            else
            {
                if(first==false)
                {
                    res.first = Math.abs(arr[p]);
                    first = true;
                }
                else
                    res.second = Math.abs(arr[p]);
                
                }
            }
    return res;
    
    }
    
}