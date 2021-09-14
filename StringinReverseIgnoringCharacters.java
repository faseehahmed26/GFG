import java.util.*;

class Test{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String a=sc.next();
         StringBuilder str=new StringBuilder();
            for(int i=0;i<a.length();i++){
                if(Character.isLetter(a.charAt(i))){
                    str.append(a.charAt(i)+"");
                }
            }
           str= str.reverse();
           int p=0;
            for(int i=0;i<a.length();i++){
               if(Character.isLetter(a.charAt(i))){
                   System.out.print(str.charAt(p++));
               }else{
                   System.out.print(a.charAt(i));
                }
            }
    }
}
/**
 * write a program to read a string from the user and print 
only the alphabets(a-z,A-Z) of the string in reverse order ignoring 
other characters 


Input- One input string from the user 
Output - Print the alphabets of input string in reverse order ignoring other characters 

Sample Test Case -1

input = a&b*c$rt
output = t&r*c$ba

Explanation - 

From the above test case we have a,b,c,r,t as the alphabets 
and reversing only this characters without disturbing other characters we will have 
t&r*c$ba as the output

Sample Test Case -2

input = a&b$c#d
output = d&c$b#a

Explanation - 

From the above test case we have a,b,c,d as the alphabets 
and reversing only this characters without disturbing other characters we will have 
d&c$b#a  as the output
 */