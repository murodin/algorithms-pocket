import java.util.Arrays;

public class DiagonalTraverse {
    /*
        Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.

        Example 1.
        Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
        Output: [1,2,4,7,5,3,6,8,9]
        Example 2.
        Input: mat = [[1,2],[3,4]]
        Output: [1,2,3,4]

        Constraints:

        m == mat.length
        n == mat[i].length
        1 <= m, n <= 104
        1 <= m * n <= 104
        -105 <= mat[i][j] <= 105
     */

    public static void main(String[] args) {
       int[][] mat = {
               {1,2,3},
               {4,5,6},
               {7,8,9}
       };

        System.out.println("Diagonal Traverse: " + Arrays.toString(Solution.findDiagonalOrder(mat)));
    }

    // Time: O(MxN)
    // Space: O(MxN)
    static class Solution {
        public static int[] findDiagonalOrder(int[][] mat) {
            if(mat.length == 0 || mat[0].length == 0) return new int[0];

            int m = mat.length, n = mat[0].length;
            int[] result = new int[m*n];
            int i = 0;
            int row = 0;
            int col = 0;
            boolean up = true;

            while (row<m && col<n) {
                if(up){
                    while (row>0 && col<n-1) {
                        result[i++] = mat[row][col];
                        row--;
                        col++;
                    }
                    result[i++] = mat[row][col];
                    if(col == n-1) row++;
                    else col++;
                } else {
                    while (col>0 && row<m-1) {
                        result[i++] = mat[row][col];
                        row++;
                        col--;
                    }
                    result[i++] = mat[row][col];
                    if(row == m-1) col++;
                    else row++;
                }
                up=!up;
            }
            return result;
        }
    }
}
