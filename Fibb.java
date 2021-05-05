/*WAP TO IMPLEMENT Fibonacci Series
case=1
10                                                                                                                      
0 1 1 2 3 5 8 13 21 34
case=2
5                                                                                                                       
0 1 1 2 3                                                                                                               
*/
import java.util.*;

class Test{
    public static int fib(int n){
        if(n==0){
            return 0;
        }
        else if(n==1 || n==2){
            return 1;
        }
        else{
            return fib(n-2)+fib(n-1);
        }
        
    }
    
    public static void main(String[] args){
        int n;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        for(int i=0;i<n;i++){
            System.out.print(fib(i)+" ");
        }
    }
}