/* Find largest Difference between array elements

Test case=1
Enter no. of elements
5                                                                               
Enter elements                                                                                                 
23 45 65 12 34                                                                                                          
Greatest Difference:53                                                                                                  
Two elements with largest difference:65 and 12                                                                          
*/

import java.util.*;

class Test{
    public static void main(String[] args){
        int n, i;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter no. of elements");
        n=sc.nextInt();
        int a[] = new int[n];
        System.out.println("Enter elements");
        for(i=0; i<n; i++){
            a[i]=sc.nextInt();
        }
        Arrays.sort(a);
        /*for(i=0; i<n; i++){
            System.out.println(a[i]);
        }*/
        System.out.println("Greatest Difference:"+(a[n-1]-a[0]));
        System.out.print("Two elements with largest difference:"+a[n-1]+" and "+a[0]);
    }
}