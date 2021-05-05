/*write a program to print out every new word of a string in a new line. 

Sample Testcase:
input:
Enter a string                                                                                                          
welcome to kmit 
output:
welcome                                                                                                                 
to                                                                                                                      
kmit                                                                                                                    


*/
import java.util.Scanner;
class Test{
    public static void main(String args[]){
       Scanner s=new Scanner(System.in);
       //System.out.println("input:");
       System.out.println("Enter a string");
       String s1=s.nextLine();
       String ar[]=s1.split(" ");
       //System.out.println("output:");
       for(int i=0;i<ar.length;i++){
           System.out.println(ar[i]);
       }
    }
}