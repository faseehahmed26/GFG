
/*write a java program if a given string contains the specified sequence of char values
Test case1:
Enter String
kmit is a good college and also great college
Enter sequence characters
college
Original String: kmit is a good college and also great college                                                          
true

Test case2:
Enter String
kmit is a good college and also great college
Enter sequence characters
ngit
Original String: kmit is a good college and also great college                                                          
false 

*/
import java.util.Scanner;
public class Test {

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String a, b;
        System.out.println("Enter String");
        a = sc.nextLine();
        System.out.println("Enter sequence characters");
        b = sc.nextLine();
        System.out.println("Original String: " + a);
        if(a.contains(b)){
            System.out.println("true");
        }
        else{
            System.out.println("false");
        }
    }
}
