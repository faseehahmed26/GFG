/*write a program to print the following pattern for any given postive integer 'n'
Case=1
input = 5
output =
*
#*#
*#*#*
#*#*#*#
*#*#*#*#*
Case=2
input = 6
output =
*
#*#
*#*#*
#*#*#*#
*#*#*#*#*
#*#*#*#*#*#
*/
import java.util.*;

class Test{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        char x='*';
        int a[]=new int[n*2];
        int q=0;
        for(int k=0;k<n*2;k++){
            if(k%2!=0){
                a[q]=k;
                q++;
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<a[i];j++){
                System.out.print(x);
                x=swap(x);
            }
            System.out.println();
        }
    }
    public static char swap(char x){
        if(x=='*'){
            return '#';
        }
        return '*';
    }
}
