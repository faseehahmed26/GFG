/*  write a java program to create the following pattern for given number n.
Note: n should be a positive integer, for other boundary condions print -1.
input = 5
output =
1
2 2
3 3 3 
4 4 4 4 
5 5 5 5 5

aasaq
input = 6
output =
1
2 2
3 3 3 
4 4 4 4 
5 5 5 5 5
6 6 6 6 6 6

*/
import java.util.*;

class Test{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n,i,j;
        n=sc.nextInt();
        if(n<0){
            System.out.println("-1");
        }
        else{
            for(i=1;i<=n;i++){
                for(j=1;j<=i;j++){
                    System.out.print(i+" ");
                }
                System.out.println("");
            }
        }
    }
}