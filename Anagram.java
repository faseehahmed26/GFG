/*
WAP two check two strings are anagrams are not
case=1
Enter the First String : listen                                                                                         
Enter the second String : silent                                                                                        
listen and silent are Anagrams                                                                                          
case=2
Enter the First String : kmit                                                                                           
Enter the second String : ngit                                                                                          
kmit and ngit are NOT Anagrams                                                                                          
                                                                                                                        
*/                                                                                                                        

import java.util.*;

class Test{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String x, y, a, b;
        System.out.print("Enter the First String : ");
        x = sc.nextLine();
        System.out.print("Enter the second String : ");
        y = sc.nextLine();
        a = sort(x);
        b = sort(y);
        if(a.equals(b)){
            System.out.println(x + " and "+ y + " are Anagrams");
        }
        else{
            System.out.println(x + " and "+ y + " are NOT Anagrams");
        }
    }
    public static String sort(String x){
        char temp[] = x.toCharArray();
        Arrays.sort(temp);
        return new String(temp);
    }
}