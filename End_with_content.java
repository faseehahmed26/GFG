/*Write a java program to check whether a given string ends with the contents of another string
Testcase1:
Enter first String                                                                                                      
kmit                                                                                                                    
Enter second String                                                                                                     
ngit                                                                                                                    
Enter end string                                                                                                        
it                                                                                                                      
First String: true                                                                                                      
Second String: true

Testcase2:
Enter first String                                                                                                      
kmitcollege                                                                                                                    
Enter second String                                                                                                     
ngit                                                                                                                    
Enter end string                                                                                                        
it                                                                                                                      
First String: false                                                                                                      
Second String: true


*/
import java.util.Scanner;
class Test {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String a, b, c;
        System.out.println("Enter first String");
        a = sc.nextLine();
        System.out.println("Enter second String");
        b = sc.nextLine();
        System.out.println("Enter end string");
        c = sc.nextLine();
        System.out.println("First String: " + a.endsWith(c));
        System.out.println("Second String: " + b.endsWith(c));
    }
}
