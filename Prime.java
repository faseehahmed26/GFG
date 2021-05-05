/*WAP to Display Prime Numbers Between two Intervals 
case=1
20                                                                                                                      
100                                                                                                                     
23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97 
*/import java.util.*;
class Test{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int x,y;
        x=sc.nextInt();
        y=sc.nextInt();
        for(int i=x;i<y;i++){
            if(isPrime(i)){
                System.out.print(i+" ");
            }
        }
        //System.out.print("23 29 31 37 41 43 47 53 59 61 67 71 73 79 83 89 97");
    }
    public static boolean isPrime(int n){
        int i, count=0;
        for(i=1;i<=n;i++){
            if(n%i==0){
                count+=1;
            }
        }
        if(count==2){
            return true;
        }
        else{
            return false;
        }
    }
}