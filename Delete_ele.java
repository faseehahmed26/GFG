/* WAP to delete an element at a specified index

case=1:
Enter size
4                                                                           
Enter elements  
23 45 54 67
Enter the element you want to delete:45                                                                              
After Deleting:23,54,67                                                                                                 
*/                                                                                                                        
import java.lang.*;
import java.util.*;

class Test{
    public static void main(String[] args){
        int[] a=new int[100];
        int n,i,x;
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter size");
        n=sc.nextInt();
        System.out.println("Enter elements");
        for(i=0;i<n;i++){
            a[i]=sc.nextInt();
        }
        System.out.print("Enter the element you want to delete:");
        x=sc.nextInt();
        
        System.out.print("After Deleting:");
        for(i=0;i<n;i++){
            if(a[i]==x) continue;
            System.out.print(a[i]);
            if(i!=n-1){
                System.out.print(",");
            }
        }
    }
}