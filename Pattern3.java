/*

Write a program to print the following pattern

input = 5
output =
10101
01010
10101
01010
10101

input = 4
output =
1010
1010
1010
1010


*/
import java.util.*;

class Test{
    public static void main(String[] args){
        int n,i,j,x=1;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        for(i=1;i<=n;i++){
            for(j=1;j<=n;j++){
                System.out.print(x);
                x=swap(x);
            }
            System.out.println();
        }
    }
    public static int swap(int x){
        if(x==0){
            x=1;
        }
        else{
            x=0;
        }
        return x;
    }
}

