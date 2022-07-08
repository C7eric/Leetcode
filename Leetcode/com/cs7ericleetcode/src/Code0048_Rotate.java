package Leetcode.Leetcode.com.cs7ericleetcode.src;

/**
 * @author C77eric
 * @version 1.0
 */
public class Code0048_Rotate {
    public void rotate(int[][] matrix){
        int n = matrix.length;
        int[][] tmpMatrix = new int[n][n];
        for(int i = 0;i < n;++i){
            for(int j = 0;j < n;++j){
                tmpMatrix[j][n - i -1] = matrix[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = tmpMatrix[i][j];
            }
        }
    }
}
