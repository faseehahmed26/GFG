/*Write a Java program to check whether a given string starts with the contents of another string.
Test case:
Enter first string
red is my favorite color
Enter second string
orange is my favorite color
Enter start string 
red
red is my favorite color starts with red:true                                                                           
orange is my favorite color starts with red:false      
*/
import java.util.Scanner;
class Test {
    
   public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String a, b, c;
        System.out.println("Enter first string");
        a = sc.nextLine();
        System.out.println("Enter second string");
        b = sc.nextLine();
        System.out.println("Enter start string");
        c = sc.nextLine();
        System.out.println(a + " starts with " + c + ":" + a.startsWith(c));
        System.out.println(b + " starts with " + c + ":" + b.startsWith(c));
    }
}
