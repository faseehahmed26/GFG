class Solution{
public:
    long long getSmallestDivNum(long long n){
        // code here
        long s=1;
        for(int i=2;i<=n;i++){
            s =lcm(s,i);
        }
        return s;
}
public:
    static long gcd(long a,long b){
    if(b==0)
    return a;
    else
    return gcd(b,a%b);
    
}
public: 
    static long lcm(long a,long b){
    return (a*b)/gcd(a,b);   
}
};