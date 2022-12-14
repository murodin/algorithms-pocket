import java.util.Arrays;

public class MinimumFallingPathSum {
    /*
        Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.
        A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right.
        Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

        Example 1.
        Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
        Output: 13
        Explanation: There are two falling paths with a minimum sum as shown.
        Example 2.
        Input: matrix = [[-19,57],[-40,-5]]
        Output: -59
        Explanation: The falling path with a minimum sum is shown.


        Constraints:

        n == matrix.length == matrix[i].length
        1 <= n <= 100
        -100 <= matrix[i][j] <= 100
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minFallingPathSum(new int[][]{{2,1,3},{6,5,4},{7,8,9}}));
    }

    // Time: O(MxN)
    // Space: O(1)
    static class Solution {
        public static int minFallingPathSum(int[][] A) {
            for (int i = 1; i < A.length; ++i)
                for (int j = 0; j < A.length; ++j)
                    A[i][j] += Math.min(
                            A[i - 1][j],
                            Math.min(A[i - 1][Math.max(0, j - 1)],
                                    A[i - 1][Math.min(A.length - 1, j + 1)]
                            )
                    );
            return Arrays.stream(A[A.length - 1])
                    .min()
                    .getAsInt();
        }
    }
}
