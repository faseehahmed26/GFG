class Solution {
    int getPairsCount(int[] arr, int n, int k) {
        // code here
        int count=0;
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<n;i++){
            if(map.containsKey(k-arr[i])){
               count+= map.get(k-arr[i]);
                
            }
            map.put(arr[i],map.getOrDefault(arr[i],0)+1);
        }
        return count;
        
        
    }
}