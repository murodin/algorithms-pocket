public class ScoreAfterFlippingMatrix {
    /*
        You are given an m x n binary matrix grid.
        A move consists of choosing any row or column and toggling each value in that row or column (i.e., changing all 0's to 1's, and all 1's to 0's).
        Every row of the matrix is interpreted as a binary number, and the score of the matrix is the sum of these numbers.
        Return the highest possible score after making any number of moves (including zero moves).

        Example 1:
        Input: grid = [[0,0,1,1],[1,0,1,0],[1,1,0,0]]
        Output: 39
        Explanation: 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
        Example 2:
        Input: grid = [[0]]
        Output: 1


        Constraints:

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 20
        grid[i][j] is either 0 or 1.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.matrixScore(new int[][]{{0,0,1,1},{1,0,1,0},{1,1,0,0}}));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static int matrixScore(int[][] A) {
            int M = A.length, N = A[0].length, res = (1 << (N - 1)) * M;
            for (int j = 1; j < N; j++) {
                int cur = 0;
                for (int[] ints : A) cur += ints[j] == ints[0] ? 1 : 0;
                res += Math.max(cur, M - cur) * (1 << (N - j - 1));
            }
            return res;
        }
    }
}
