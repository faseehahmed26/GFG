
//User function Template for Java

class Solution{
    //Function to return the position of the first repeating element.
    public static int firstRepeated(int []arr, int n) 
    {
        //Your code here
        HashSet<Integer> s=new HashSet<Integer>();
        int m=-1;
        for(int i=n-1;i>=0;i--){
            if(s.contains(arr[i])){
                m=i;
            }
            else{
                s.add(arr[i]);
            }
        }
        if(m!=-1)
        return m+1;
        return m;
    }
}