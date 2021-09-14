import java.util.*;
class Test{
    public static boolean Test(int i,int j,String a){
        StringBuilder n1=new StringBuilder(a.substring(i,j+1));
        StringBuilder n2=new StringBuilder(a.substring(i,j+1));
        n2=n2.reverse();
      
        if(n1.toString().equals(n2.toString())){
            return true;
        }
        return false;
    }
    public static void main(String args[]){
        Scanner s=new Scanner(System.in);
        String w=s.next();
        int c=0;
        for(int i=0;i<w.length()-1;i++){
            for(int j=i+1;j<w.length();j++){
                if(Test(i,j,w)){
                    c++;
                }
            }
        }
        System.out.print(c);
    }
}
/**
 * write a program to find the substrings of the given string and count the number of 
palindrome substrings 

Input - first line of input will contain the string from the user
Output - print the count of substrings which are palindrome 

Note - The Length of the palindrome substring should be greater than equal = 2
If the substrings doesnt'c contain palindrome print 0 

Sample Test Case - 1
input = abcba
output = 2

Explanation - 
From the given input string we have the following substrings 
abcba
a,ab,abc,abcb,abcba,b,bc,bcb,bcba,c,cb,cba,b,ba,a
And among those substrings we have two palindrome 
abcba and bcb so the output is 2 

Sample Test Case - 2
input = abc
output = 0

Explanation - 
From the given input string we have the following substrings 
a,ab,abc,b,bc,c,0
And among those substrings we dont have any palindrome substrings 

 */