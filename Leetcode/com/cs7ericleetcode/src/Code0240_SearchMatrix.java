package Leetcode.Leetcode.com.cs7ericleetcode.src;

/**
 * @author C77eric
 * @version 1.0
 */
public class Code0240_SearchMatrix {

    public boolean searchMatrix(int[][] matrix,int target){
        int m = matrix.length,n = matrix[0].length;
        int x = 0,y =  n - 1;
        while(x < m && y >= 0){
            if(matrix[x][y] == target){
                return true;
            }
            if(matrix[x][y] > target){
                --y;
            } else {
                ++x;
            }
        }
        return false;
    }
}
