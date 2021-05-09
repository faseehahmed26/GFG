class RomanToNumber {
    // Finds decimal value of a given roman numeral
    public int romanToDecimal(String str) {
        // code here
        int ans=0,temp,n=str.length();
        for(int i=0;i<n-1;i++){
            temp=res(str.charAt(i));
            if(temp<res(str.charAt(i+1))) temp*=-1;
            ans+=temp;
        }
        return ans+res(str.charAt(n-1));
    }
    int res(char ch){
        int res=1;
        switch(ch){
            case 'M': res=1000;break;
            case 'D': res=500;break;
            case 'C': res=100;break;
            case 'L': res=50;break;
            case 'X': res=10;break;
            case 'V':res=5;}
            return res;
        
    }
}