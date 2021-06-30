//Initial Template for Java

import java.util.*;
class KthSmallestElement{
	public static void main(String[] args){
		Scanner sc=new Scanner(System.in);
		int t=Integer.parseInt(sc.next());
		while(t>0)
		{
			int n=Integer.parseInt(sc.next());
			int[][] a=new int[n][n];
			for(int i=0;i<n;i++)
				for(int j=0;j<n;j++)
    				a[i][j]=Integer.parseInt(sc.next());
    		
			int k=Integer.parseInt(sc.next());
		    Solution ob = new Solution();
		    System.out.println(ob.kthSmallest(a,n,k));
		    
		    t--;
		}
	}
}
// } Driver Code Ends


//User function Template for Java

/*
class Solution
{
	public static int kthSmallest(int[][]mat,int n,int k)
	{
        //code here.
        ArrayList<Integer> pq=new ArrayList<Integer>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                pq.add(mat[i][j]);
            }
        }
        Collections.sort(pq);
        Iterator<Integer> itr=pq.iterator();
        int l=0;
        while(itr.hasNext()){
            l++;
            if(l==k){
                return itr.next();
            }
            itr.next();
        }
        return 0;
        
    }
}*/

class Solution
{ 
	
// A structure to store entry of heap. 
// The entry contains value from 2D array, 
// row and column numbers of the value 
static class HeapNode 
{ 
	
	// Value to be stored 
	int val; 
	
	// Row number of value in 2D array 
	int r; 
	
	// Column number of value in 2D array 
	int c; 
	
	HeapNode(int val, int r, int c) 
	{ 
		this.val = val; 
		this.c = c; 
		this.r = r; 
	} 
} 

// A utility function to swap two HeapNode items. 
static void swap(int i, int min, HeapNode[] arr) 
{ 
	HeapNode temp = arr[i]; 
	arr[i] = arr[min]; 
	arr[min] = temp; 
} 

// A utility function to minheapify the node 
// harr[i] of a heap stored in harr[] 
static void minHeapify(HeapNode harr[], 
					int i, int heap_size) 
{ 
	int l = 2 * i + 1; 
	int r = 2 * i + 2; 
	int min = i; 
	
	if (l < heap_size && 
		harr[l].val < harr[i].val) 
	{ 
		min = l; 
	} 
	if (r < heap_size && 
		harr[r].val < harr[min].val) 
	{ 
		min = r; 
	} 
	
	if (min != i) 
	{ 
		swap(i, min, harr); 
		minHeapify(harr, min, heap_size); 
	} 
} 

// A utility function to convert 
// harr[] to a max heap 
static void buildHeap(HeapNode harr[], int n) 
{ 
	int i = (n - 1) / 2; 
	while (i >= 0) 
	{ 
		minHeapify(harr, i, n); 
		i--; 
	} 
} 

// This function returns kth smallest 
// element in a 2D array mat[][] 
public static int kthSmallest(int[][] mat, int n, int k) 
{ 
	
	// k must be greater than 0 and 
	// smaller than n*n 
	if (k <= 0 || k > n * n) 
	{ 
		return Integer.MAX_VALUE; 
	} 
	
	// Create a min heap of elements 
	// from first row of 2D array 
	HeapNode harr[] = new HeapNode[n]; 
	
	for(int i = 0; i < n; i++) 
	{ 
		harr[i] = new HeapNode(mat[0][i], 0, i); 
	} 
	buildHeap(harr, n); 
	
	HeapNode hr = new HeapNode(0, 0, 0); 
	
	for(int i = 0; i < k; i++) 
	{ 
		
		// Get current heap root 
		hr = harr[0]; 
		
		// Get next value from column of root's 
		// value. If the value stored at root was 
		// last value in its column, then assign 
		// INFINITE as next value 
		int nextVal = hr.r < n - 1 ? 
					mat[hr.r + 1][hr.c] : 
					Integer.MAX_VALUE; 
						
		// Update heap root with next value 
		harr[0] = new HeapNode(nextVal, 
							hr.r + 1, hr.c); 
								
		// Heapify root 
		minHeapify(harr, 0, n); 
	} 
	
	// Return the value at last extracted root 
	return hr.val; 
}
}
