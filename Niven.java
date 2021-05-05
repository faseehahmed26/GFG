/*
 Write a java Program to find whether the given number is Niven number or not.

Create a class Niven with a method with parameter and  checks for niven number.
Create a another class Test with main method which accept a positive integer and
    instantiate Niven class and calls method by passing positive integer to it.

Note: If it is Niven number print 1 else 0.
    
Niven Number:
Any positive integer which is divisible by the sum of its digits is Niven Number.
e.g.:
152
(1+5+2)=8
152 is divisible 8
so 152 is a Niven number.

Test case 1:
input: 16
output: 0
Test case 2:
input: 152
output: 1

*/
import java.util.*;

class Test{
    public static void main(String[] args){
        int n,x,r,s=0;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        x=n;
        while(n!=0){
            r=n%10;
            s+=r;
            n=n/10;
        }
        if(x%s==0){
            System.out.print(1);
        }
        else{
            System.out.print(0);
        }
    }
}