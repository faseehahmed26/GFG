
class Solution {
    int minDist(int a[], int n, int x, int y) {
        // code here
        ArrayList<Integer> x1=new ArrayList<Integer>();
        ArrayList<Integer> y1=new ArrayList<Integer>();
        int min=n;
        for(int i=0;i<n;i++){
            if(a[i]==x)
                x1.add(i);
            if(a[i]==y)
                y1.add(i);
            
        }
        if(x1.size()==0|| y1.size()==0){
            return -1;
        }
            for(int i: x1){
                for(int j: y1){
                    if(Math.abs(i-j)<min){
                        min=Math.abs(i-j);
                    }
                }
            }
        return min;
        
    }
}