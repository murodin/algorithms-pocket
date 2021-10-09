public class SearchA2DMatrixII {
    /*
        Write an efficient algorithm that searches for a target value in an m x n integer matrix. The matrix has the following properties:
        Integers in each row are sorted in ascending from left to right.
        Integers in each column are sorted in ascending from top to bottom.

        Example 1.

        Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
        Output: true
        Example 2.
        Input: matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
        Output: false
     */

    public static void main(String[] args) {
        int[][] test = {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };
        System.out.println(" Search Matrix II - Found: " + Solution.searchMatrix(test, 5));
    }

    // Time: O(rows + cols)
    // Space: O(1)
    static class Solution {
        public static boolean searchMatrix(int[][] matrix, int target) {
            int r = 0, c = matrix[0].length-1;
            while(r < matrix.length && c >= 0) {
                if(matrix[r][c] == target) return true;
                else if(matrix[r][c] < target) r++;
                else c--;
            }
            return false;
        }
    }
}
