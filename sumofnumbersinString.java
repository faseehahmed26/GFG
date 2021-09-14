import java.util.*;

class Test{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String str=sc.next();
        int sum=0;
        int t=0;
        for(int i=0;i<str.length();i++){
            if(Character.isDigit(str.charAt(i))){
                sum=sum*10+(str.charAt(i)-'0');
            }
            else{
                t+=sum;
                sum=0;
            }
            
        }
        t+=sum;
        System.out.print(t);
        
    }
}
/**
 * 
Given a string which contains only numbers(0-9) and alphabets(a-z,A-Z)
print the sum of the all numbers in the string


Input - Read one string from the user.
Output - Addition of all Numbers of the input string 


Sample Test Case - 1

input = a2b3c4d5
output = 14

Explanation - 
In the above test case we have 2,3,4,5 are the numbers so the addition of this numbers is 14 

Sample Test Case - 2

input = a10b20c30
output = 60

Explanation - 
In the above test case we have 10,20,30. So the addition of this numbers is 60

Sample Test Case-3

input = k100
output = 100

Explanation -
In the above test case we have 100 only. so the output is 100

Sample Test Case - 4

input = abc
output = 0

Explanation -
Since no numbers print 0 

 */