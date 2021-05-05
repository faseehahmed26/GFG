/*
Write a java program to compare two strings lexicographically.
Test case1:
Enter first String                                                                                                      
kmit                                                                                                                    
Enter second String                                                                                                     
ngit                                                                                                          
kmit is less than ngit

Testcase2:
Enter first String                                                                                                      
kmit                                                                                                                    
Enter second String                                                                                                     
kmit                                                                                                                    
kmit is equals to kmit

Test case3:
Enter first String                                                                                                      
ngit                                                                                                                    
Enter second String                                                                                                     
kmit                                                                                                          
ngit is greater than kmit
*/

import java.util.*;

class Test {
public static void main(String[] args){
       Scanner sc = new Scanner(System.in);
       String a, b;
       System.out.println("Enter first String");
       a = sc.nextLine();
       System.out.println("Enter second String");
       b = sc.nextLine();
       if(a.compareTo(b)==0){
           System.out.println(a + " is equals to " + b);
       }
       else if(a.compareTo(b)<0){
           System.out.println(a + " is less than " + b);
       }
       else{
           System.out.println(a + " is greater than " + b);
       }
    }
}
