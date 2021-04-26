class GfG
{
    String convertToRoman(int n)
    {
	// Your code here	
	String romans[] = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
    int nums[]      = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    String result = "";
    int num=n;
    for(int i=12;i>=0;i--){
        while(num>=nums[i]){
        result=result+romans[i];
        num=num-nums[i];
        }
    }
    return result;
    }
}