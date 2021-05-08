class Solution {
    ArrayList<Integer> find3Numbers(ArrayList<Integer> arr, int n) {
        // add code here.
        ArrayList<Integer> l=new ArrayList<Integer>();
          l.add(Integer.MAX_VALUE); l.add(Integer.MAX_VALUE);
        for(int i=1;i<n;i++){
            if(arr.get(i-1)<arr.get(i) &&arr.get(i)<l.get(1)){
                l.clear();
                l.add(arr.get(i-1));
                l.add(arr.get(i));
                
            }
            if(arr.get(i)>l.get(1)){
                l.add(arr.get(i));
                return l;
            }
        }
        l.clear();
        return l;
    }
}

