import java.util.*;

class Test{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int i,a=1;
        for(i=1;i<=n;i++)
        {
            if(i%2!=0){
                for(int j=a+i-1;j>a;j--){
                     System.out.print(j+"*");  
                }
                 System.out.println(a);  
            }
            else{
                for(int j=a;j<a+i-1;j++){
                     System.out.print(j+"*");   
                }
                 System.out.println(a+i-1);   
            }
            a=a+i;
            
            
             
        }
/**
 * write a program to print the following pattern 


input = 3
output =
1
2*3
6*5*4


input = 4
output =
1
2*3
6*5*4
7*8*9*10


 */