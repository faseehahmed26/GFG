/* Write Perfect Number program in Java 

case=1
Enter a no                                                                                                              
496                                                                                                                     
496 is a Perfect Number                                                                                                
case=2
Enter a no                                                                                                              
144                                                                                                                     
144 is NOT a Perfect Number                                                                                            
*/

import java.util.*;

class Test{
    public static void main(String[] args){
        int n, i, j, a=0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a no");
        n=sc.nextInt();
        for(i=1; i<n; i++){
            if(n%i==0){
                a+=i;
            }
        }
        if(a==i){
            System.out.println(n + " is a Perfect Number");
        }
        else{
            System.out.println(n + " is NOT a Perfect Number");
        }
    }
}