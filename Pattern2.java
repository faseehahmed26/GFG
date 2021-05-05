/*
write a java program to print the following pattern for any given postive integer 'n'

for other boundary conditions print -1 

input = 4
output =
1
2 3
4 5 6
7 8 9 10

input = 5
output =
1
2 3
4 5 6
7 8 9 10
11 12 13 14 15



*/
import java.util.*;

class Test{
    public static void main(String[] args){
        int n,i,j,x=1;
        Scanner sc=new Scanner(System.in);
        n=sc.nextInt();
        if(n<0){
            System.out.println("-1");
        }
        else{
            for(i=0;i<=n;i++){
                for(j=0;j<i;j++){
                    System.out.print(x+" ");
                    x+=1;
                }
                System.out.println();
            }
        }
    }
}