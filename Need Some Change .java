class Geeks{
    static void swapElements(int a[], int n){
        
        // Your code here
        for(int i=0;i<n-2;i++){
            int temp=a[i];
            a[i]=a[i+2];
            a[i+2]=temp;
        }
        for(int i=0;i<n;i++)
        System.out.print(a[i]+" ");
        
        System.out.println();
    }
}