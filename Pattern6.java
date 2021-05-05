/*
 
write a program  to print the following pattern for any given positive integer
 
Note : any other boundary conditions print -1.
 
For example: 
Sample input:
N=4
Sample output:
1
2 1
3 2 1
4 3 2 1
 
 
*/
import java.util.*; 

class Test{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        if(n<=0){
            System.out.print(-1);
        }
        for(int i=1;i<=n;i++){
            int x=i;
            for(int j=1;j<=i;j++){
                System.out.print(x+" ");
                x--;
            }
            System.out.println();
        }
    }
}
