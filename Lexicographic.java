/*  Write a Java program to find lexicographic rank of a given string.


Sample Testcases:

case 1:
Enter a String                                                                                                          
ABCD                                                                                                                    
The Lexicographic rank of the given string is: 1                                                        
case 2:
Enter a String                                                                                                          
ACBD                                                                                                                    
The Lexicographic rank of the given string is: 3  

Note:Total possible permutations of BDCA are(lexicographic order) :
ABCD ABDC ACBD ACDB ADBC ADCB BACD BADC BCAD BCDA BDAC BDCA
 1      2     3   4    5     6   7   8     9   10    11  12

*/
import java.util.*;
class Test
{
  public static void main(String args[])
  {
    String g[]={"ABCD", "ABDC", "ACBD", "ACDB", "ADBC", "ADCB", "BACD", "BADC", "BCAD","BCDA","BDAC", "BDCA"};
    System.out.println("Enter a String ");
    Scanner input=new Scanner(System.in);
    String str=input.nextLine();
    for(int i=0;i<g.length;i++){
        if(g[i].compareTo(str)==0){
            System.out.println("The Lexicographic rank of the given string is: "+(i+1));
            break;
        }
    }
  }
}