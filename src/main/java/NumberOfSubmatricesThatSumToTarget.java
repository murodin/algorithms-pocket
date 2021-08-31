import java.util.HashMap;
import java.util.Map;

public class NumberOfSubmatricesThatSumToTarget {

    /*
        Given a matrix and a target, return the number of non-empty submatrices that sum to target.
        A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.

        Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.

        Example.

        Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
        Output: 4
        Explanation: The four 1x1 sub matrices that only contain 0.
    */

    public static void main(String[] args) {
        int[][] testMatrix = {{0,1,0}, {1,1,1}, {0,1,0}};
        System.out.println("Num SubMatrix Sum to Target: " + Solution.NumSubMatrixSumTarget(testMatrix, 0));

    }

    static class Solution {
        public static int NumSubMatrixSumTarget(int[][] matrix, int target) {
            int m = matrix.length, n = matrix[0].length;

            for (int row = 0; row < m; row++) {
                for (int col = 1; col < n; col++) {
                    matrix[row][col] += matrix[row][col - 1];
                }
            }

            int count = 0;

            for (int c1 = 0; c1 < n; c1++) {
                for (int c2 = c1; c2 < n; c2++) {

                    Map<Integer, Integer> map = new HashMap<>();
                    map.put(0, 1);
                    int sum = 0;

                    for (int row = 0; row < m; row++) {
                        sum += matrix[row][c2] - (c1 > 0 ? matrix[row][c1 - 1] : 0);
                        count += map.getOrDefault(sum - target, 0);
                        map.put(sum, map.getOrDefault(sum, 0) + 1);
                    }

                }
            }

            return count;
        }
    }
}
