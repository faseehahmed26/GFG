class Solution{
    public static long modified(String a){
        // Your code here
        long c=0;
        for(int i=0;i<a.length()-2;i++){
            if(a.charAt(i)==a.charAt(i+1) && a.charAt(i)==a.charAt(i+2)){
                c++;
                i=i+1;
            }
        }
        return c;
    }
}