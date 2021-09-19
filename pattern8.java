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

,
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

2nd Method
import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner s=new Scanner (System.in);
		int n=s.nextInt();
		int g=1,x=1,d=0;
		for(int i=1;i<=n;i++)
		{
			int y=x+1;
			 d=i%2==1?(i*(g++)):(1);
		     x=d;
			for(int j=1;j<=i;j++){
				System.out.print(i%2==0?(j==i?y+"\n":y++ + ""):(j==i?d+"\n":d-- + ""));
			}
		
		}
	}
}

 */