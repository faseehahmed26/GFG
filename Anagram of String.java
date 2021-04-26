class GfG
{
	public int remAnagrams(String s,String s1)
        {
        //add code here.
        String m="";
        String n="";
        if(s.length()>s1.length()){
            m=s;
            n=s1;
        }
        else{
            m=s1;
            n=s;
            
        }
        int c=0;
        for(int i=0;i<m.length();i++){
            String h=m.charAt(i)+"";
            if(n.contains(h)){
                n=n.replaceFirst(h,"");
            }
            else{
                c++;
            }
        }
        return n.length()+c;
        }
}