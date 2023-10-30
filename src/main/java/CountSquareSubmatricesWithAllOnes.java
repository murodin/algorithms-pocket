public class CountSquareSubmatricesWithAllOnes {
    /*
        Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
        Example 1:

        Input: matrix =
        [
          [0,1,1,1],
          [1,1,1,1],
          [0,1,1,1]
        ]
        Output: 15
        Explanation:
        There are 10 squares of side 1.
        There are 4 squares of side 2.
        There is  1 square of side 3.
        Total number of squares = 10 + 4 + 1 = 15.
        Example 2:

        Input: matrix =
        [
          [1,0,1],
          [1,1,0],
          [1,1,0]
        ]
        Output: 7
        Explanation:
        There are 6 squares of side 1.
        There is 1 square of side 2.
        Total number of squares = 6 + 1 = 7.


        Constraints:

        1 <= arr.length <= 300
        1 <= arr[0].length <= 300
        0 <= arr[i][j] <= 1
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.countSquares(new int[][]{{1,0,1}, {1,1,0}, {1,1,0}}));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static int countSquares(int[][] A) {
            int res = 0;
            for (int i = 0; i < A.length; ++i) {
                for (int j = 0; j < A[0].length; ++j) {
                    if (A[i][j] > 0 && i > 0 && j > 0) {
                        A[i][j] = Math.min(A[i - 1][j - 1], Math.min(A[i - 1][j], A[i][j - 1])) + 1;
                    }
                    res += A[i][j];
                }
            }
            return res;
        }
    }
}
