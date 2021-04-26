class GfG
{ 	
    void addFraction(int num1, int den1, int num2, int den2)
    {
        // Your code here	
        int den3=gcd(den1,den2);
        den3=(den1*den2)/den3;
        int num3=(num1)*(den3/den1)+(num2)*(den3/den2);
        simp(den3,num3);
        
    }
    static int gcd(int a,int b){
        if(b==0)
        return a;
        return gcd(b,a%b);
    }
    static void simp(int den3,int num3){
        int common_factor=gcd(num3,den3);
        den3=den3/common_factor;
        num3=num3/common_factor;
        System.out.println(num3+"/"+den3);
    }
}