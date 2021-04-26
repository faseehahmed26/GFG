class GfG
{		
	public boolean searchEle(int a[],int x)
       {
		//add code here.
		for(int i=0;i<a.length;i++)
		if(a[i]==x) return true;
		return false;
	}
	public boolean insertEle(int a[],int y,int yi)
        {
              //add code here.	
            for(int i=0;i<a.length;i++){
                  if(i == yi){
                      a[i]=y;
                      return true;
                  }
                  
              }
              return false;
	}
	public boolean deleteEle(int a[],int z)
        {
		//add code here.
		for(int i=0;i<a.length;i++){
		    if(a[i]==z){
		        a[i]=-1;
		        return true;
		    }
		}
		return true;
	}
}