class Solution {
    boolean checkTriplet(int[] arr, int n) {
        // code here
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int x = arr[i] * arr[i], y = arr[j] * arr[j], z = arr[k] * arr[k];
 
                    if (x == y + z || y == x + z || z == x + y)
                        return true;
                }
            }
        }
        return false;
	    
	
    }
}