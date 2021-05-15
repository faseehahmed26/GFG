class Solution
{
    int maxDistance(int arr[], int n)
    {
	// Your code here'
	int maxD=0;
	Map<Integer,List<Integer>> posit=new HashMap<>();
	for(int i=0;i<n;i++){
            if(posit.containsKey(arr[i]))
            {
                posit.get(arr[i]).add(i);
                maxD=Math.max(i-posit.get(arr[i]).get(0),maxD);
            }
	   
	        else
	         posit.put(arr[i],new ArrayList<>(Arrays.asList(i)));
	    }
	    return maxD;
    }
}