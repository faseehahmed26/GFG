/*
write a Java Program to print perfect numbers between two given numbers
case=1
Enter Start Number                                                                                                      
1                                                                                                                       
Enter End Number                                                                                                        
800                                                                                                                     
Perfect Numbers between 1 and 800 are                                                                                 
6       28      496                                                                                                     
*/    

import java.util.*;

class Test{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int i, x, y;
        System.out.println("Enter Start Number");
        x=sc.nextInt();
        System.out.println("Enter End Number");
        y=sc.nextInt();
        System.out.println("Perfect Numbers between " + x + " and " + y + " are");
        for(i=x;i<y;i++){
            if(isPerfect(i)){
                System.out.print(i+"       ");
            }
        }
    }
    public static boolean isPerfect(int n){
        int i, s = 0;
        for(i=1;i<n;i++){
            if(n%i==0){
                s+=i;
            }
        }
        if(s==n){
            return true;
        }
        else{
            return false;
        }
    }
}