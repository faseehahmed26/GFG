import java.util.*;
public class Main
{
	public static void main(String[] args) {
		
      Scanner sc=new Scanner(System.in);
      int n=sc.nextInt();
      for(int i=1;i<=n;i++){
          int x=i;
          int a=n-1;
          for(int j=1;j<=i;j++){
              System.out.print(x+" ");
              x=x+a;
              a--;
          }
          
          System.out.println("");
      }
  



	}
}
/**
 *
n= 6
1 
2 7 
3 8 12 
4 9 13 16 
5 10 14 17 19 
6 11 15 18 20 21

n=5
1 
2 6 
3 7 10 
4 8 11 13 
5 9 12 14 15 

import java.util.*;

public class Main
{
	public static void main(String[] args) {
	    Scanner s = new Scanner(System.in);
	    int n = s.nextInt();
	    for(int i = 0; i < n; i++) {
	        int count = i+1;
	        for(int j = 0; j <= i; j++) {
	            System.out.print(count+" ");
	            count = count+n-j-1;
	        }
	        System.out.println();
	    }
	}
}
 */