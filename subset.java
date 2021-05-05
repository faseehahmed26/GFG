/* Write a pgm to find all subsets of a string.
take input as str=
case=1
FUN
All subsets for given string
F
FU
FUN
U
UN 
N 
*/
import java.util.*;

public class Test {  
    public static void main(String[] args) {  
        Scanner sc=new Scanner(System.in);
        String str = sc.nextLine();  
        int len = str.length();  
        int temp = 0;  
        String arr[] = new String[len*(len+1)/2]; 
        for(int i = 0; i < len; i++) {  
            for(int j = i; j < len; j++) {  
                arr[temp] = str.substring(i, j+1);  
                temp++;  
            }  
        }  
        System.out.println("All subsets for given string");  
        for(int i = 0; i < arr.length; i++) {  
            System.out.println(arr[i]);  
        }  
    }  
}  