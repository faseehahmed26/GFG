import java.util.*;

class Test{
    static boolean verify(String n){
        for(int i=1;i<=n.length()-1;i++){
            if(n.charAt(0)<n.charAt(i)){
                return false;
            }
        }
        return true;
            
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String n=sc.next();
        System.out.println(verify(n));
    }
}
/** 
Write a program to check if the given number is Camel Case No or Not.

A number is said to be Camel Case if  the first digit is greater than or equal to the digits on its right .


Example - 
input = 987
output = true

input = 998
output = true 
*/