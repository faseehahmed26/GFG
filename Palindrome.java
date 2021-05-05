/*Java Program to Check whether a String is a Palindrome
Test case1:
madam
palindrome

Test case2:
alphabet
not a palindrome
*/
import java.lang.*;
import java.util.Scanner;
class Test
{
  public static void main(String args[])
  {Scanner sc=new Scanner(System.in);
    String s=sc.next();
    String t="";
   for (int i=s.length()-1;i>=0;i--){
       t+=s.charAt(i);
   }
   // System.out.println(s);
   // System.out.println(t);
    
    if(s.equals(t)){
        System.out.print("palindrome");
    }
    else{
        System.out.print("not a palindrome");
    }
    
  }
}