class Solution {

	public String countAndSay(int n){ 
		if(n == 1) return "1";  //if n==1 simply return "1"
		
		String s = countAndSay(n-1);  // else make recursive calls until n==1
		
		StringBuilder str = new StringBuilder(); // create string builder or string buffer object for easy mutations
		
		int count = 1 , i = 0; // count of every character is atleast 1 so take count as 1
		
		while(i < s.length()){ // main loop
		
			char ch = s.charAt(i++); // store charcter at i and increment i
			
			while(i < s.length() && s.charAt(i) == ch){ // create a loop to count the number of adjacent characters in string
				i++;    count++;
			}
			
			str.append(count).append(ch); // add count and current character to str
			count = 1; // reset count to 1
		}
		
	return str.toString(); // return str as string
}
}