import java.util.*;
class UT_2{
    private static boolean ispossible(int[] arr,int n,int d,int mid){
        if(mid==0){
            return false;
        }
        int cnt=0;
        for(int i=0;i<n;i++){
            if(arr[i]>mid){
               
                cnt=cnt+(arr[i]/mid);
                if(arr[i]%mid==0){
                    cnt--;
                }
                
            }
        }
       
        return cnt<=d;
    }
    private static int find(int[] arr,int n,int d){
        int start=0;
        int end=arr[0];
        for(int i=1;i<n;i++){
            
            if(end<arr[i]){
                end=arr[i];
            }
        }
        int mid;
        while(start<end){
            
            mid=(start+end)/2;
            
            if(ispossible(arr,n,d,mid)){
                end=mid;
            }
            else{
                start=mid+1;
            }
        }
        return end;
        
        
        
    }
    
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n=scan.nextInt();
        int d=scan.nextInt();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=scan.nextInt();
            
        }
        System.out.println(find(arr,n,d));
    }
}