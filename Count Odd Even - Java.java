class Geeks{
    static void countOddEven(int a[], int n){
        
        // Your code here
        
        int o=0;
        for(int i=0;i<n;i++){
            if((a[i]&1)==0)
            o++;
        }
        System.out.println((n-o)+" "+o);
        
        
        
    }
}