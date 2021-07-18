class Solution {
    public int triangleNumber(int[] nums) {
        int a=0;
        if(nums.length<3) return a;
        Arrays.sort(nums);
        for(int i=2;i<nums.length;i++){
            int l=0, r=i-1;
            while(l<r){
                if(nums[l]+nums[r]>nums[i]){
                    a+=r-l;
                    r--;
                }
                else 
                    l++;
            }
        }
        return a;
    }
}