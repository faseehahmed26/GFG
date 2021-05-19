class Solution
{
    public int findPeakElement(List<Integer> a)
    {
        // Code here
        
        int l=0,h=a.size()-1,mid;
        
        while(l!=h){
        mid = (l+h)/2;
        if(a.get(mid)>a.get(mid+1)){
        h = mid;continue;
        }
        else{
        l=mid+1;continue;
        }
        }
        return a.get(h);
    }
}