import java.util.*;
import java.lang.Math;

class Sorting
{
	
	static void printArray(int arr[],int size)
	{
		int i;
		for(i=0;i<size;i++)
		System.out.print(arr[i]+" ");
	    System.out.println();
	}
	
	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);
		int t= sc.nextInt();
		while(t>0)
		{
			int n = sc.nextInt();
			int a[] = new int[n];
		
			for(int i=0;i<n;i++)
			a[i]= sc.nextInt();
			
			 new Solution().insertionSort(a,n);
			 printArray(a,n);
			
		t--;
		}
		
	}
}// } Driver Code Ends

class Solution
{
	static void insert(int arr[],int i)
	{
		int key = arr[i];
		int j = i-1;
		
		 //We run a loop from j and keep shifting the array elements
         //towards right till the element at jth index is greater
         //than element at ith index(key).
		while(j>=0 && arr[j]>key)
		{
		    //Shifting of array elements.
			arr[j+1] = arr[j];
			j=j-1;
		}
		//Then we just insert the current element(key) at (j+1)th index.
		arr[j+1]=key;
	}
	
	//Function to sort the array using insertion sort algorithm.
	public void insertionSort(int arr[],int n)
		{
			int i;
			for(i=1;i<n;i++)
		    	//Calling of insert function.
			    insert(arr,i);
		}
	
}