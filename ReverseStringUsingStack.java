import java.util.*;
class Test1{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int i;
        String a=sc.next();
        Stack<Character> abc=new Stack<Character>();                  
        for( i=0;i<a.length();i++){
            abc.push(a.charAt(i));
        }
         for( i=0;i<a.length();i++){
             System.out.print(abc.pop());
        }
    }
}
/***
 *  write a program to reverse a string using stack data structure 


input = hello
output = olleh

input = welcome
output = emoclew
 */