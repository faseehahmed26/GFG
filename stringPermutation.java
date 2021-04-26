class Solution
{
    public ArrayList<String> al1 = new ArrayList<>();
    public ArrayList<String> permutation(String s)
    {
        //Your code here
        int m=0;int n=s.length()-1;
        ArrayList<String> al=perm(s,m,n);
        Collections.sort(al);
        return al;
    }
    public String swap(String s,int i ,int k){
        char[] ch=s.toCharArray();
        char t=ch[i];
        ch[i]=ch[k];
        ch[k]=t;
        return String.valueOf(ch);
        
    }
    public ArrayList<String> perm(String s,int m,int n){
        if(m==n){
            al1.add(s);
        }
        else{
        for(int j=m;j<=n;j++){
            s=swap(s,m,j);
        
            perm(s,m+1,n);
            
            s=swap(s,m,j);
            
        }}
        return al1;
    }
}