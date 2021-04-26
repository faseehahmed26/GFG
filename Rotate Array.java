class Solution
{
    //Function to rotate an array by d elements in counter-clockwise direction. 
    static void rev(int arr[],int i,int j){
        int t=0;
        while(i<j){
            t=arr[i];
            arr[i]=arr[j];
            arr[j]=t;
            i++;
            j--;
        }
    }
    static void rotateArr(int arr[], int d, int n)
    {
        // add your code here
       
         rev(arr,0,d-1);
        rev(arr,d,n-1);
        rev(arr,0,n-1);
        
    }
}