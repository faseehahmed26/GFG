
class Solution{

    public Stack<Integer> deleteMid_util(Stack<Integer>st,int n,int i)
    {
        //Your code here
        if(i==n/2){
            
            st.pop();
            return st;
        }
        int e = st.peek();
        st.pop();
        i++;
        deleteMid_util(st,n,i);
        
        st.push(e);
        return st;
      
    } 
     public Stack<Integer> deleteMid(Stack<Integer>st,int n)
    {
        //Your code here
        return deleteMid_util(st,n,0);
        
    } 
}
