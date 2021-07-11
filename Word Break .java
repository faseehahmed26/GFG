//Initial Template for Java

import java.io.*;
import java.util.*;
public class GfG
{
    public static void main(String args[])
        {
            Scanner sc = new Scanner(System.in);
            int t = sc.nextInt();
            while(t-->0)
                {
                    int n;
                    n = sc.nextInt();
                    ArrayList<String> arr = new ArrayList<String>();
                    for(int i = 0;i<n;i++)
                        {
                            String p = sc.next();
                            arr.add(p);
                        }
                    String line = sc.next();
                    Sol obj = new Sol();  
                    System.out.println(obj.wordBreak(line,arr));  
                    
                }
        }
}// } Driver Code Ends


//User function Template for Java
//Back-end complete function Template for Java

class Sol
{
    public static int wordBreak(String A, ArrayList<String> B )
    {
        int i,j,k,n=B.size();
        TreeSet<String> mp = new TreeSet<String>(); 
        for(i=0;i<n;i++)
            mp.add(B.get(i));
    
        int len = A.length();

        ArrayList<Boolean> dp = new ArrayList<Boolean>(len + 1);
        for(i=0;i<len;i++)
            dp.add(i,false);
            
        dp.add(0,true);
    
        for(i = 1; i <= len; i++) {
            for(j = 0; j <len; j++) {
                if(dp.get(j)  && mp.contains(A.substring(j, i) )) {
                    dp.add(i,true);
                    break;
                }
            }
        }
        
        if(dp.get(len))
            return 1;
        return 0;    
            
    }
}
