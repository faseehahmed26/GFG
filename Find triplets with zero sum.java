public class Solution {

    public boolean findTriplets(int arr[], int n){
        Array.sort(arr);
        for(int i=0;i<=n-2;i++){
            int j=i+1;
            int k=n-1;
            while(j<k){
                int sum=arr[i]+arr[j]+arr[k];
                if(sum==0) return true;
                else if (sum>0) k--;
                else if (sum<0) j--;
            }
        }
        return false;

    }
    
}
/*Example 1:

Input: n = 5, arr[] = {0, -1, 2, -3, 1}
Output: true
Explanation: 0, -1 and 1 forms a triplet
with sum equal to 0.
Example 2:

Input: n = 3, arr[] = {1, 2, 3}
Output: false
Explanation: No triplet with zero sum exists. 
*/
