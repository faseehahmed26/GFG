class Solution
{
	// Function to find the peak element
	// arr[]: input array
	// n: size of array a[]
	static int findpeakEle(int[] arr,int l,int r,int n){
	    int mid=l+(r-l)/2;
	    if((mid==0 || arr[mid-1]<=arr[mid] )&& (mid==n-1 ||arr[mid+1] <= arr[mid]))
	    return mid;
	    
	    else if(mid>0 && arr[mid-1]>arr[mid])
	    return findpeakEle(arr,l,(mid-1),n);
	    
	    else 
	    return findpeakEle(arr,(mid+1),r,n);
	}
	public int peakElement(int[] arr,int n)
    {
       //add code here.
       
       return findpeakEle(arr,0,n-1,n);
    }
}