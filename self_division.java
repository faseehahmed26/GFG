import java.util.*;
class Test{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int a=sc.nextInt();
        int b=sc.nextInt();
        for(int i=a;i<b;i++){
            int num=i;
            n=i;
            while(num>0){
                if(num%10==0 || n%(num%10)!=0){
                    break;
                }
                num/=10;
                if(num==0){
                    System.out.print(i+" ")
                }
                    
            }
            
        }
    }
}
/***
 * Given a lower and upper number bound, output a list of every possible self dividing number, including the
bounds if possible.


A self-dividing number is a number that is divisible by every digit it contains.

For example, 128 is a self-dividing number because 128 % 1 == 0 , 128 % 2 == 0 , and 128 % 8 == 0 .

Also, a self-dividing number is not allowed to contain the digit zero.

Given a lower and upper number bound, output a list of every possible self dividing number, including the
bounds if possible.

Example 1:


Input = 1 22
Output = 1 2 3 4 5 6 7 8 9 11 12 15 22 


 */