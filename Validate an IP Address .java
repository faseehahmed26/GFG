
class Solution {

    public Set<String> allnums;

    public boolean isValid(String s) {
        allnums = new HashSet<>();
        // allowed segments
        for (int i = 0; i < 256; i++) {
            allnums.add(String.valueOf(i));
        }

        int dots = 0;
        // counting dots
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '.') dots++;
        }
        if (dots != 3) return false;
        
        // split according to positions of '.'
        String[] nums = s.split("\\.");
        if (nums.length != 4) return false;

        for (String x : nums) {
            if (!allnums.contains(x)) return false;
        }
        return true;
    }
}



/**

// Initial Template for Java

import java.util.*;
import java.io.*;

  public class validip {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            String s = sc.next();
            Solution obj = new Solution();

            if (obj.isValid(s))
                System.out.println(1);
            else
                System.out.println(0);
        }
    }
}// } Driver Code Ends


// User function Template for Java

class Solution {

    public boolean isValid(String s) {
        // Write your code here
        int st=0;
        int sum=0;
        int c=0;
        int z=0;
        int d=0;
        while(st<s.length()){
            if(s.charAt(st)=='.'){
                return false;
            }
            int k=st;
            if(s.charAt(st)>='a' && s.charAt(st)<='z')
            return false;
            while(st<s.length() && s.charAt(st)!='.'){
                sum=sum*10+s.charAt(st)-'0';
                if(s.charAt(st)=='0')
                z++;
                
                st++;
                c++;
                
            }
            if(z>1 && sum==0 || s.charAt(k)=='0' && sum!=0){
                return false;
            }
            if(st<s.length() && s.charAt(st)=='.')
            d++;
            if(sum>255 || sum<0 || c>3)
            return false;
            c=0;
            sum=0;
            z=0;
            
            st++;
        }
        
        
        if(d!=3 )
        return false;
        return true;
    }
} */