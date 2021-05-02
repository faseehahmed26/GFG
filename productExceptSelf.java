class Solution 
{ 
	public static long[] productExceptSelf(int arr[], int n) 
	{ 
        // code here
        long l[]=new long[n];
        long p=1;
        int no_zero=0;
        for(int i=0;i<n;i++){
            if(arr[i]!=0)
            p=p*arr[i];
            else
            no_zero++;
            
            
        }
        if(no_zero>1)
        return l;
        else if(no_zero==1){
            for(int i=0;i<n;i++){
                if(arr[i]==0){
                l[i]=p;
                }
                else{
                    l[i]=p%arr[i];
                }
            }
                
        }
        else{
            for(int i=0;i<n;i++){
                l[i]=p/arr[i];
            }
        }
        
        return l;
        
	} 
} 
