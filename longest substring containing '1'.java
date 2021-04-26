class GfG{
    public int count(String s)
    {
        //add code here.
        int ans=0;
        String[] arr=s.replace("0"," ").split(" ");
        for(String g:arr){
            ans=Math.max(ans,g.length());
        }
        return ans;
    }
}