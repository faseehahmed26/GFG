
class GfG
{
    public static Long product(Long arr[], Long mod, int n)
    {
        //yout code here
        if(n==1) return arr[0];
        return arr[n-1]*product(arr,mod,n-1)%mod;
    }
}
