class Solution{

    //Function to swap two elements in the array.    
    public static void swap(int arr[], int x, int y){
        //Use of a temporary variable to swap the elements.
        int tmp = arr[x];
        arr[x] = arr[y];
        arr[y] = tmp;
    }
    //Function to sort the array into a wave-like array.
    public static void convertToWave(int arr[], int n){
        
        //Iterating over the array. 
        for(int i=0;i<n-1;i=i+2){
            //Swapping two neighbouring elements at a time.
		    swap(arr, i, i+1);
		}
        return;
    }
    
}
/**class Solution{

    
    // arr: input array
    // n: size of the array
    //Function to sort the array into a wave-like array.
    public static void convertToWave(int arr[], int n){
        
        // Your code here
        
        int i=1;
        while(i<n){
            if(arr[i-1]<=arr[i]){
                int temp=arr[i-1];
                arr[i-1]=arr[i];
                arr[i]=temp;
                i=i+2;
            }
        }
        
    }
    
} */