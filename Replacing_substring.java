/*Write a Java program to replace each substring of a given string that matches 
the given regular expression with the given replacement.
Test case:
Enter a String
the quick brown fox jumps over the lazy dog
Enter which string you want replace
fox
Enter which string you want replace with
cat
Original string: the quick brown fox jumps over the lazy dog                                                            
New String: the quick brown cat jumps over the lazy dog

*/
import java.util.Scanner;
class Test {
   public static void main(String[] args)
    {
        Scanner s=new Scanner(System.in);
        System.out.println("Enter a String");
        String s1=s.nextLine();
        
        System.out.println("Enter which string you want replace");
        String s2=s.next();
        
        System.out.println("Enter which string you want replace with");
        String s3=s.next();
        
        
        System.out.println("Original string: "+s1);
        s1=s1.replace(s2,s3);
         System.out.println("New String: "+s1);
    }
}