
class Solution{
    //Function to return the count of the number of elements in
//the intersection of two arrays.
public static int NumberofElementsInIntersection (int a[],int b[],int n,int m)
    {
        //using HashSet to store the elements.
    	HashSet<Integer> s1=new HashSet<>();
    	
        int count = 0;
        
        //we insert all the elements of first array in HashSet s1. 
    	for(int i=0;i<n;i++)
    		s1.add(a[i]);
    
        //iterating over the second array.
    	for(int i=0;i<m;i++)
    	{
    	    //if current element already exists in set, we update the counter.
    		if(s1.contains(b[i])){
    		    
    		    //erasing element from HashSet because if same element is
    		    //present again in the array we don't need to count it again.
    		    s1.remove(b[i]);
    		    
    		    //incrementing the counter.
    		    count++;
    		}
    	}
    	
    	//returning the counter.
    	return count;
    }
}
/**
class Solution{
    //Function to return the count of the number of elements in
    //the intersection of two arrays.
    public static int NumberofElementsInIntersection(int a[],int b[],int n,int m)
    {
        //Your code here
        
        HashSet<Integer> bc=new HashSet<>();
         HashSet<Integer> cd=new HashSet<>();
        for(int i=0;i<n;i++){
            bc.add(a[i]);
        }
        int c=0;
        for(int i=0;i<m;i++){
            cd.add(b[i]);
        }
        bc.retainAll(cd);
        c=bc.size();
        
        return c;
    }
};
 */