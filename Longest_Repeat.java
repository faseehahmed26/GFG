/*
Write a java program to find the longest sequence of characters in a given string

Test Case:
Enter a string                                                                                                          
abcdfefgbcd                                                                                                             
Longest repeating sequence: bcd
*/
import java.util.Scanner;
class Test {
    public static void main(String[] args) {  
        int cnt=0;
        int max=0;
        String d="";
        String g1="";
          Scanner s = new Scanner(System.in);
          System.out.println("Enter a string");
          String a = s.nextLine();
          for(int i=0;i<a.length();i++){
              for(int j=i+1;j<a.length();j++){
                  if(a.charAt(i)==a.charAt(j)){
                      cnt=0;
                      d="";
                      for(int k=0;k<a.length()-j;k++){
                          if(a.charAt(i+k)==a.charAt(j+k)){
                              cnt++;
                              d=d+a.charAt(i+k);
                          }
                          else{
                              break;
                          }
                      }
                      if(cnt>max){
                          g1=d;
                          max=cnt;
                      }
                  }
              }
          }
          System.out.println("Longest repeating sequence: "+g1);
    }  
}