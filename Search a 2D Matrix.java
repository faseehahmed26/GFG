class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int r=matrix.length;
        int c=matrix[0].length;
        int i=0;
        int j=c-1;
        while(i>=0 && i<r && j>=0 && j<c ){
            if(target==matrix[i][j])
                return true;
            else if(target<matrix[i][j])
                j--;
            else
                i++;
        }
        return false;
    }
}