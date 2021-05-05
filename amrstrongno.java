/*WAP to find the given number is ARMSTRONG no

case=1
input=153
output=it is an Armstrong number.  
case=2
input=100
output=it is not an Armstrong number.
*/
import java.util.*;

class Test{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n,x,rem,a=0;
        n=sc.nextInt();
        x=n;
        while(n!=0){
            rem=n%10;
            a=a+(rem*rem*rem);
            n=n/10;
        }
        //System.out.println(a);
        if(a==x){
            System.out.println("it is an Armstrong number.");
        }
        else{
            System.out.println("it is not an Armstrong number.");
        }
    }
}