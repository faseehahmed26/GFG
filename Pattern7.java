import java.util.*;
class Test{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int a,b,n;
        n=sc.nextInt();
        for( a=0;a<n;a++){
            for(b=a+1;b<2*a+1;b++){
                System.out.print(b+" ");
            }
            for(;b>a;b--){
                System.out.print(b+" ");
            }
            System.out.println();
        }
    }
}
//write a program to print the following pattern

/***input = 5
output = 
1
2 3 2
3 4 5 4 3
4 5 6 7 6 5 4
5 6 7 8 9 8 7 6 5

***/
