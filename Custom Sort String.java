class Solution {
    public String customSortString(String order, String str) {
        int[] freq= new int[26];
        
        for(char c: str.toCharArray()){
            freq[c-'a']++;
        }
        StringBuilder ans=new StringBuilder();
        for(char orderChar: order.toCharArray()){
            while(freq[orderChar-'a']>0){
                ans.append(orderChar);
                freq[orderChar-'a']--;
            }
        }
        for(int i=0;i<26;i++){
            int freqc=freq[i];
            while(freqc>0){
                ans.append((char)(i+'a'));
                freqc--;
            }
        }
        return ans.toString();
        
    }
}