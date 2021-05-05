/*
Write a java program to find the duplicate characters in a given string

Sample Test case1:
Enter a String                                                                                                          
hello kmit                                                                                                              
Duplicate characters in a given string                                                                                  
l
*/

import java.util.Scanner;
public class Test {
 public static void main(String args[]) {
  Scanner sc = new Scanner(System.in);
  System.out.println("Enter a String");
  String str = sc.nextLine();
  int cnt = 0;
  char[] inp = str.toCharArray();
  System.out.println("Duplicate characters in a given string");
  for (int i = 0; i < str.length(); i++) {
   for (int j = i + 1; j < str.length(); j++) {
    if (inp[i] == inp[j]) {
     System.out.println(inp[j]);
     cnt++;
     break;
    }
   }
  }
 }
}
