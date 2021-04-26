class GfG

{   
    
    public static ArrayList<String> spaceString(String str)
    {   
        ArrayList<String>arr=new ArrayList<>();
        String op="";
        op+=str.charAt(0);
        str=str.substring(1);
        solve(str,op,arr);
        return arr;
    }
    static void solve(String in,String op,ArrayList<String>a){
        if(in.length()==0){
            System.out.print("");
            a.add(op);
            return;
        }
        String op1=op;
        String op2=op;
        op1+=in.charAt(0);
        op2+=" ";
        op2+=in.charAt(0);
        
        // a.add(op1);
        // a.add(op2);
        solve(in.substring(1),op1,a);
        solve(in.substring(1),op2,a);
        return;
    }
}
        
/*ABCD
ABC D
AB CD
AB C D
A BCD
A BC D
A B CD
A B C D
*/  