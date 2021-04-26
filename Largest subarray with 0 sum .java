class GfG
{
    int maxLen(int arr[], int n)
    {
        // Your code here
        int maxL=0,prefSum=0;
        Map<Integer,Integer> sub =new HashMap<>();
        
        for(int i=0;i<n;i++){
            prefSum+=arr[i];
            if(prefSum==0)
                maxL=i+1;
            Integer index=sub.get(prefSum);
            if(index==null)
                sub.put(prefSum,i);
            else
                maxL=Math.max(maxL,i-index);
        }
        return maxL;
    }
}