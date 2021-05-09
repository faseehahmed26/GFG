class Geeks{
    
    static void interchange(int a[][],int r, int c){
        
        //swapping the elements of 
        //the last and first columns
        for(int i = 0;i<r;i++){
            int temp = a[i][0];
            a[i][0] = a[i][c-1];
            a[i][c-1] = temp;
        }
        
        //printing the elements row-wise
        for(int i = 0;i<r;i++){
            for(int j = 0;j<c;j++){
                System.out.print(a[i][j] + " ");
            }
            System.out.println();
        }
    }
}