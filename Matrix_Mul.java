/*Write a java program to perform matrix multiplication

case=1:
Enter no of rows in first matrix:2                                                                                  
Enter no of columns in first matrix:3                                                                               
Enter no of rows in second matrix:3                                                                                 
Enter no of columns in second matrix:2                                                                              
Enter the elements of 1st martix row wise                                                                               
1 2 3 4 5 6                                                                                                             
Enter the elements of 2nd martix row wise                                                                               
4 5 6 1 2 3                                                                                                             
Multiplying the matrices                                                                                             
The product is:                                                                                                         
22 16                                                                                                                   
58 43                                                                                                                   

case=2:
Enter no of rows in first matrix:2                                                                                  
Enter no of columns in first matrix:3                                                                               
Enter no of rows in second matrix:2                                                                                 
Enter no of columns in second matrix:3                                                                              
Multiplication would not be possible                                                                                    
*/
import java.util.*;
 
class Test{
  public static void main(String args[]){
    int m, n, p, q, sum = 0, c, d, k;
    Scanner in = new Scanner(System.in);
    System.out.print("Enter no of rows in first matrix:");
    m = in.nextInt();
    System.out.print("Enter no of columns in first matrix:");
    n = in.nextInt();
 
    int first[][] = new int[m][n];
 
    System.out.print("Enter no of rows in second matrix:");
    p = in.nextInt();
    System.out.print("Enter no of columns in second matrix:");
    q = in.nextInt();
 
    if (n != p)
      System.out.println("Multiplication would not be possible");
    else{
        int second[][] = new int[p][q];
        int multiply[][] = new int[m][q];
        System.out.println("Enter the elements of 1st martix row wise");
        for (c = 0; c < m; c++)
            for (d = 0; d < n; d++)
                first[c][d] = in.nextInt();
        System.out.println("Enter the elements of 2nd martix row wise");
        for (c = 0; c < p; c++)
            for (d = 0; d < q; d++)
                second[c][d] = in.nextInt();
        for (c = 0; c < m; c++) {
            for (d = 0; d < q; d++) {
                for (k = 0; k < p; k++)
                    sum = sum + first[c][k]*second[k][d];
                multiply[c][d] = sum;
                sum = 0;
            }
        }
        System.out.println("Multiplying the matrices");
        System.out.println("The product is:"); 
        for (c = 0; c < m; c++) {
            for (d = 0; d < q; d++)
                System.out.print(multiply[c][d]+" ");
            System.out.print("\n");
        }
    }
  }
}