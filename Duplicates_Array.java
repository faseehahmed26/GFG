/*
Write a Java program to find the duplicate values of an array of integer values

Test case:
Enter array size                                                                                                        
6                                                                                                                       
4 2 9 7 2 4                                                                                                             
4 2                                                                                                                     
      
 
*/
import java.util.*;

class Test{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter array size");
        int n=sc.nextInt();
        int i,j,m;
        int arr[]=new int[n];
        for(i=0;i<n;i++){
            m=sc.nextInt();
            arr[i]=m;
        }
        for(i=0;i<n;i++){
            for(j=i+1;j<n;j++){
                if(arr[i]==arr[j]){
                    System.out.print(arr[i]+" ");
                }
            }
        }
    }
}