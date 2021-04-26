
class Solution{
    // Function to find the sum of all
    // the numbers in the given string s
    public static long findSum(String s){
        
        // your code here
        long sum=0;
        String str="";
        s=s+" ";
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if(Character.isDigit(ch))
            str+=ch;
            else if(str.length()>0){
            sum+=Integer.parseInt(str);
            str="";
            }
                
        }
        return sum;
    }
    
} 