import java.util.*;
class Sorting
{
	static void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
    
    // Driver program
    public static void main(String args[])
    {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while(T>0)
		{
			int n = sc.nextInt();
			int arr[] = new int[n];
			for(int i=0;i<n;i++)
				arr[i] = sc.nextInt();

			
			new Solution().quickSort(arr,0,n-1);
			printArray(arr);
		    T--;
		}
} }// } Driver Code Ends


class Solution
{
    //Function to sort an array using quick sort algorithm.
    static void quickSort(int arr[], int low, int high)
    {
        if (low < high)
        {
            
            //pi is partitioning index, arr[pi] is now at right place.
            int pi = partition(arr, low, high);
            //Separately sorting elements before and after partitioning index.
            quickSort(arr, low, pi-1);
            quickSort(arr, pi+1, high);
        }
    }
    
    //Function that takes last element as pivot, places the pivot element at 
    //its correct position in sorted array, and places all smaller elements
    //to left of pivot and all greater elements to right of pivot.
    static int partition(int arr[], int low, int high)
    {
        //Picking the pivot.
        int pivot = arr[high];
        //Index of smaller element and indicates the right position of 
        //pivot found so far.
        int i = (low-1); 
        for (int j=low; j<=high-1; j++)
        {
            //If current element is smaller than or equal to pivot we increment
            //the value of i and swap the values at ith and jth index.
            if (arr[j] <= pivot)
            {
                i++;
                //Swapping of values at ith and jth index.
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        //At last, swapping of value at ith and the last index which was
        //selected as pivot.
        int temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        
        //returning the partitioning index. 
        return i+1;
    } }