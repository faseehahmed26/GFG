import java.util.*;
class PalindromicArray{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while(t-->0)
		{
			int n = sc.nextInt();
			int[] a = new int[n];
			for(int i = 0 ;i < n; i++)
				a[i]=sc.nextInt();
			GfG g = new GfG();
			System.out.println(g.palinArray(a , n));
		}
	}
}// } Driver Code Ends


/*Complete the Function below*/
class GfG
{
	public static int palinArray(int[] a, int n)
           {
                  //add code here.
                  int flag=1;
                  for(int j=0;j<n;j++){
                      int temp=a[j];
                      int rem,sum=0;
                      while(temp>0){
                          rem=temp%10;
                          sum=sum*10+rem;
                          temp/=10;
                      }
                      if(a[j]!=sum){
                          flag=0;
                          break;
                      }else
                      flag=1;
                  }
                  return flag;
           }
}