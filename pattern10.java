/**
 * n=5
 * 1
 * 2+7 9
 * 3+5 8+2 10
 * 4+3 7+4 11+3 14
 * 5+1 6+6 12+1 13+2 15
 * 
 * n=6
 *  1 
    2 11 
    3 10 12 
    4 9 13 18 
    5 8 14 17 19 
    6 7 15 16 20 21 
    
    import java.util.*;

public class Main
{
	public static void main(String[] args) {
	    Scanner s = new Scanner(System.in);
	    int n = s.nextInt();
	    for(int i = 0; i < n; i++) {
	        int count = i+1;
	        for(int j = 0; j <= i; j++) {
	            if(j%2 == 0) {
	                System.out.print(count+" ");
	            } else {
	                System.out.print((count+n-(2*i)+j-1)+" ");
	            }
	            count = count+n-j-1;
	        }
	        System.out.println();
	    }
	}
}
 */


import java.util.*;
public class Main
{
	public static void main(String[] args) {
		
      Scanner sc=new Scanner(System.in);
      int n=sc.nextInt();
      
        for(int i=1;i<=n;i++){
            int x=i;
            int a=n-i;
            for(int j=1;j<=i;j++){
                if(j%2!=0){
                    System.out.print(x+" ");
                    x=x+2*a+1;
                    
                    
                }
                else{
                    System.out.print(x+" ");
                    x=x+2*(i-j);
                
                }
            }
            System.out.println();
        }
	}
}
