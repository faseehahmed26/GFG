/*
Write a java program to remove all duplicate elements in a given string
case=1
Enter string                                                                                                            
good morning                                                                                                            
After removing duplicates new string is:god mrni   
case=2
Enter string                                                                                                            
kmit ngit                                                                                                               
After removing duplicates new string is:kmit ng        
*/
import java.util.Scanner;
class Test{
    public static void main(String args[]){
        Scanner s=new Scanner(System.in);
        System.out.println("Enter string");
        String s1=s.nextLine();
        
        System.out.print("After removing duplicates new string is:");
        char l,m;
        int f=0;
        String t="";
        int j;
       for(int i=0;i<s1.length();i++){
           l=s1.charAt(i);
           f=0;
           j=0;
           while(j<i){
               m=s1.charAt(j);
               if(l==m){
                   f=1;
               }
               j++;
           }
           if(f==0){
               t+=l;
           }
       }
       System.out.print(t);
    }
}