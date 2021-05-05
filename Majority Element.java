class Solution
{
    /* Function to find the candidate for Majority */
    static int findCandidate(int a[], int size) 
    { 
        int maj_index = 0, count = 1; 
        // use linear search to compute candidate for majority element
        for (int i = 1; i < size; i++) 
        { 
            if (a[maj_index] == a[i]) 
                count++; 
            else
                count--; 
            if (count == 0) 
            { 
                maj_index = i; 
                count = 1; 
            } 
        } 
        return a[maj_index]; 
    }
    // Function to check if the candidate occurs more than n/2 times 
    static boolean isMajority(int a[], int size, int cand) 
    { 
        int count = 0; 
        for (int i = 0; i < size; i++) 
            if (a[i] == cand) 
            count++; 
        if (count > size/2) 
            return true; 
        else
            return false; 
    }
    static int majorityElement(int a[], int size)
    {
        /* Find the candidate for Majority*/
        int cand = findCandidate(a, size); 
  
        /* Print the candidate if it is Majority*/
        if (isMajority(a, size, cand) == true) 
            return cand;
        else
            return -1;
    }
}
/**class Solution
{
    static int majorityElement(int a[], int size)
    {
        // your code here
        int max_i=0,c=1;
        for(int i=1;i<size;i++){
            if(a[i]==a[max_i]){
            c++;
            }
            else{
            c--;
            }
            if(c==0){
            max_i=i;
            c=1;
            }
        }
        int co=0;
        for(int i=0;i<size;i++){
            if(a[i]==a[max_i])
            co++;
        }
        if(co>size/2)
        return a[max_i];
        else
        return -1;
    }
}*/