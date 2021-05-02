class Solution{
    String reverseWords(String S){
        String st[]=S.split("\\.");
        int i=0;
        int n=st.length-1;
        while(i<n){
            String temp=st[i];
            st[i]=st[n];
            st[n]=temp;
            i++;
            n--;
        }
        String r=String.join(".",st);
        return res
    }
}