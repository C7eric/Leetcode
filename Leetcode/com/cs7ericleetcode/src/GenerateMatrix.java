package Leetcode.Leetcode.com.cs7ericleetcode.src;

/**
 * @author C77eric
 * @version 1.0
 */
public class GenerateMatrix {
    public int[][] generateMatrix(int n){
        int[][] matrix = new int[n][n];
        int curNum = 0;
        int maxNum = n * n;
        int[][] directions ={{0,1},{1,0},{0,-1},{-1,0}};
        int directionIndex = 0;
        int row = 0;
        int column = 0;
        while(curNum < maxNum){
            matrix[row][column] = curNum;
            curNum++;
            int nextRow = row + directions[directionIndex][0],nextColumn = column + directions[directionIndex][1];
            if(nextRow < 0 || nextRow >= n || nextColumn < 0 || nextColumn >= n || matrix[nextRow][nextColumn] != 0){
                directionIndex = (directionIndex + 1) % 4;
            }
            row = row + directions[directionIndex][0];
            column = column + directions[directionIndex][1];
        }
        return matrix;
    }
}
