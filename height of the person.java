import java.io.*;
import java.util.*;
import java.lang.*;
public class h
{
	public static int calculate_h(int arr[], int p)
	{   int i=0;
	    int j=arr.length;
		int m=0;
		while(i<j){
		    m=(i+j)/2;
		    if(arr[m]==p){
		        return arr[m];
		    }
		    else if(p>arr[m]){
		        if(m<arr.length-1 && p<arr[m+1]){
		            return height(arr[m],arr[m+1],p);
		        }
		        i=m+1;
		    }
		    else{
		        if(m>0 && p>arr[m-1]){
		            return height(arr[m-1],arr[m],p);
		        }
		        j=m;
		        
		    }
		}
		
		return arr[m];
	}
	public static int height(int a,int b,int c ){
	    if(c-a>=b-c){
	        return b;
	    }
	    else
	    return a;
	}
	
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		int p=sc.nextInt();
		int arr[]=new int[n];
		for(int i=0;i<n;i++){
		    arr[i]=sc.nextInt();
		}
		
		int myval = calculate_h(arr,p);
		System.out.println( myval);
	}
}