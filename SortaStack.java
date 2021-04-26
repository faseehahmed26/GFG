class GfG{
	public Stack<Integer> sort(Stack<Integer> s)
	{
		//add code here.
		Stack<Integer> sk=new Stack();
		while(!s.isEmpty()){
		    int temp=s.pop();
		    
		     while(!sk.isEmpty() && sk.peek()>temp){
		        s.push(sk.peek());
		        sk.pop();
		        
		    }
		    sk.push(temp);

		}
		return sk;
	}
}