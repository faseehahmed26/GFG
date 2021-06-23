
import java.io.*;
import java.util.*;

class GfG
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while(t-->0)
        {
            int N = sc.nextInt();
            Solution ob = new Solution();
            ArrayList<Integer> ans = ob.factorial(N);
            for (Integer val: ans) 
                System.out.print(val); 
            System.out.println();
        }   
    }
}// } Driver Code Ends


//User function Template for Java

class Solution {
    static ArrayList<Integer> factorial(int N){
        //code here
        ArrayList<Integer> a=new ArrayList<>();
        a.add(1);
       for(int i=2;i<=N;i++){
           multiply(i,a);
       }
       Collections.reverse(a);
       return a;
       
    }
    static void multiply(int x,ArrayList<Integer> a){
        int carry=0;
        int product=1;
        for(int i=0;i<a.size();i++){
            int temp=a.get(i);
            //System.out.println(temp);
            product=a.get(i)*x+carry;
            temp=product%10;
            //System.out.println(temp);
            //System.out.println(carry);
            a.remove(i);
            a.add(i,temp);
            carry=product/10;
        }
        while(carry!=0){
            a.add(carry%10);
            carry/=10;
        }
        
    }
}