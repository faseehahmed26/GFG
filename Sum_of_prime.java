/*Write a program to find the sum of prime digits of a given number
Sample testcases:
input = 245
output = 7
prime digits of 245 are 2 and 5
sum of 2 and 5 is 7
 Note :- If no primes are present print '0'
input = 237
output = 12
input = 44
output = 0
*/

import java.util.*;

class Test{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n,x,a,i=0,s=0;
        n=sc.nextInt();
        int arr[]=new int[n];
        x=n;
        while(n!=0){
            a=n%10;
            arr[i]=a;
            n=n/10;
            i+=1;
        }
        for(i=0;i<arr.length;i++){
            if(isPrime(arr[i])==true){
                s+=arr[i];
            }
        }
        System.out.print(s);
    }
    public static boolean isPrime(int n){
        int i,j;
        boolean flag=true;
        for(i=2;i<=n/2;i++){
            if(n%i==0){
                flag=false;
                break;
            }
        }
        return flag;
    }
}