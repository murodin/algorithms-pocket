public class PathsInMatrixWhoseSumIsDivisibleByK {
    /*
        You are given a 0-indexed m x n integer matrix grid and an integer k.
        You are currently at position (0, 0) and you want to reach position (m - 1, n - 1) moving only down or right.
        Return the number of paths where the sum of the elements on the path is divisible by k. Since the answer may be very large, return it modulo 109 + 7.

        Example 1.
        Input: grid = [[5,2,4],[3,0,5],[0,7,2]], k = 3
        Output: 2
        Explanation: There are two paths where the sum of the elements on the path is divisible by k.
        The first path highlighted in red has a sum of 5 + 2 + 4 + 5 + 2 = 18 which is divisible by 3.
        The second path highlighted in blue has a sum of 5 + 3 + 0 + 5 + 2 = 15 which is divisible by 3.
        Example 2.
        Input: grid = [[0,0]], k = 5
        Output: 1
        Explanation: The path highlighted in red has a sum of 0 + 0 = 0 which is divisible by 5.
        Example 3.
        Input: grid = [[7,3,4,9],[2,3,6,2],[2,3,7,0]], k = 1
        Output: 10
        Explanation: Every integer is divisible by 1 so the sum of the elements on every possible path is divisible by k.


        Constraints:

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 5 * 104
        1 <= m * n <= 5 * 104
        0 <= grid[i][j] <= 100
        1 <= k <= 50
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numberOfPaths(new int[][]{{5,2,4},{3,0,5},{0,7,2}}, 3));
    }

    // Time: O(MxNxK)
    // Space: O(MxNxK)
    static class Solution {
        static int MOD = 1_000_000_000 + 7;
        public static int numberOfPaths(int[][] grid, int k) {
            int m = grid.length, n = grid[0].length;
            int[][][] dp = new int[m][n][k + 1];
            int lastCellRemainder = grid[m-1][n-1] % k;
            dp[m-1][n-1][lastCellRemainder] = 1;

            int lastRemainder = lastCellRemainder;
            for (int c = n-2; c >= 0; c--) {
                int cellRemainder = grid[m-1][c] % k;
                dp[m-1][c][(lastRemainder + cellRemainder) % k] = 1;
                lastRemainder = (lastRemainder + cellRemainder) % k;
            }

            lastRemainder = lastCellRemainder;
            for (int r = m-2; r >= 0; r--) {
                int cellRemainder = grid[r][n-1] % k;
                dp[r][n-1][(lastRemainder + cellRemainder) % k] = 1;
                lastRemainder = (lastRemainder + cellRemainder) % k;
            }

            for (int r = m-2; r >= 0; r--) {
                for (int c = n-2; c >= 0; c--) {
                    int cellRemainder = grid[r][c] % k;
                    for (int rem = 0; rem <= k; rem++) {
                        dp[r][c][(cellRemainder + rem) % k] = (dp[r][c][(cellRemainder + rem) % k] + dp[r][c+1][rem]) % MOD;
                        dp[r][c][(cellRemainder + rem) % k] = (dp[r][c][(cellRemainder + rem) % k] + dp[r+1][c][rem]) % MOD;
                    }
                }
            }
            return dp[0][0][0];
        }
    }
}
