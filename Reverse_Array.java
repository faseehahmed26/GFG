/* Basic Java program that reverses an array

Output :Reversed array is:                                                                                                      
Reversed array is:                                                                                                      
5 4 3 2 1                                                                                                                


*/

public class Test{ 
  
        static void reverse(int a[], int n) 
    { 
        // write code here
        System.out.println("Reversed array is:");
        for(int i=n-1;i>=0;i--){
            System.out.print(a[i]+" ");
        }
    } 
  
    public static void main(String[] args) 
    { 
        int [] arr = {10, 20, 30, 40, 50}; 
        reverse(arr, arr.length); 
    } 
} 
