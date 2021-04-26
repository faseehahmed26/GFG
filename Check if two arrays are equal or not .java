
class Solution{
    //Function to check if two arrays are equal or not.
public static boolean check(long A[],long B[],int N)
{
    //using a HashMap to store frequency of elements.
    HashMap<Long, Long> hm = new HashMap<Long, Long>();
    
    //incrementing frequencies of elements present in first array in HashMap.
    for(int i = 0;  i< N; i++)
        {
            long num = A[i];
            if(hm.containsKey(num))
            {
                long freq = hm.get(num);
                freq++;
                hm.put(num, freq);
            }
            else{
                hm.put(num, (long)1);
            }
        }
        //decrementing frequencies of elements present in
        //second array in the HashMap.
        for(int i = 0; i < N; i++)
        {
            long num = B[i];
            if(hm.containsKey(num))
            {
                long freq = hm.get(num);
                freq--;
                hm.put(num, freq);
            }
        }
         
        //iterating over the HashMap.
        for(Map.Entry<Long, Long> entry: hm.entrySet())
        {
            //if frequency of any element in HashMap now is not zero it means
            //that its count in two arrays was not equal so arrays aren't equal.
            if(entry.getValue() != 0)
            {
                //returning false since arrays are not equal.
                return false;
            }
        }
        //returning true if arrays are equal.
        return true;
}
}