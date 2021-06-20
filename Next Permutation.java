class Solution {
    public void swap(int[] nums,int i,int j){
        int temp=nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
 public void nextPermutation(int[] nums) {
        int n=nums.length;
        int s=-1;
        for(int i=n-1;i>0;i--){
            if(nums[i]>nums[i-1]){
                s=i-1;
                break;
                
            }
         }   
        
        if(s>=0){
            for(int i=n-1;i>=s;i--){
                if(nums[i]>nums[s]){
                    swap(nums,i,s);
                    break;
                }
            }
        
        reverse(nums,s+1);
        return;
        }
    reverse(nums,0);
 }
       public void reverse(int[] nums,int j){
        for(int i=nums.length-1;i>j;i--){
            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
            j++;
        }
    }
    
}