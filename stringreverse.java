/*  Write a Java program to reverse every word in a string using methods. 

Sample Testcase:
Enter a String                                                                                                          
welcome to kmit                                                                                                         
The string reversed word by word is:                                                                                    
emoclew ot timk                                                                                                         

*/
import java.util.Scanner;
class Test{
    public static String rev(String a){
        char[] ar=a.toCharArray();
        for(int i=0;i<a.length()/2;i++){
            char t=ar[i];
            ar[i]=ar[a.length()-1-i];
            ar[a.length()-1-i]=t;
        }
        return new String(ar);
    }
    public static void main(String [] args){
        Scanner s= new Scanner(System.in);
        System.out.println("Enter a String");
        String sc=s.nextLine();
        String r="";
        String arr[]=sc.split(" ");
        System.out.println("The string reversed word by word is:");
        for(int i=0;i<arr.length;i++){
            System.out.print(rev(arr[i])+" "); 
            
        }
        
     
    }
}