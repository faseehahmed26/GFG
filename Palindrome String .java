//User function Template for Java

class Solution {
    int isPlaindrome(String S) {
        // code here
        int n=S.length()-1;
        for(int i=0;i<(n+1)/2;i++){
            if(S.charAt(i)!=S.charAt(n-i)){
                return 0;
            }
        }
        return 1;
    }
};