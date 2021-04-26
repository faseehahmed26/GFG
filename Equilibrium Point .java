

class Solution {

    
    // a: input array
    // n: size of array
    // Function to find equilibrium point in the array.
    public static int equilibriumPoint(long arr[], int n) {

        // Your code here
        int lsum=0;
        long s=0;
        for(int i=0;i<n;i++){
            s+=arr[i];
        }
        for(int i=0;i<n;i++){
            s=s-arr[i];
            if(lsum==s)
            return i+1;
            else 
            lsum+=arr[i];
            
        }
        return -1;
    }
}
