import java.util.Arrays;

public class UniquePathsII {

    /*

        A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
        The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
        Now consider if some obstacles are added to the grids. How many unique paths would there be?
        An obstacle and space is marked as 1 and 0 respectively in the grid.

        Example 1:

        Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
        Output: 2
        Explanation: There is one obstacle in the middle of the 3x3 grid above.
        There are two ways to reach the bottom-right corner:
        1. Right -> Right -> Down -> Down
        2. Down -> Down -> Right -> Right
     */

    public static void main(String[] args) {
        int[][] testObstacleGrid = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println("Unique paths: " + Solution.uniquePathsWithObstacles_II(testObstacleGrid));
    }

    static class Solution {
        static int[][] dp;
        // Time: O(M*N)
        // Space: O(M*N)
        public static int uniquePathsWithObstacles(int[][] grid) {
            dp = new int[grid.length][grid[0].length];
            for(int[] d :dp)
                Arrays.fill(d, -1);
            return dfs(grid, 0, 0);
        }

        private static int dfs(int[][] grid, int row, int col) {
            // Exit condition
            if(row < 0 || col < 0 || row == grid.length || col == grid[0].length || grid[row][col] == 1)
                return 0;
            if(row == grid.length-1 && col == grid[0].length-1) {
                return 1;
            }
            if(dp[row][col] > -1) return dp[row][col];
            dp[row][col] = dfs(grid, row, col+1) + dfs(grid, row+1, col);
            return dp[row][col];
        }

        // Time: O(M*N)
        // Space: O(1)
        public static int uniquePathsWithObstacles_II(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            if(grid[0][0] == 1) return 0;

            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    if(i == 0 || j == 0) {
                        if(grid[i][j] == 1 || (i != 0 && grid[i-1][0] == 0) || (j != 0 && grid[0][j-1] == 0))
                            grid[i][j] = 0;
                        else
                            grid[i][j] = 1;
                    } else {
                        if(grid[i][j] == 1) {
                            grid[i][j] = 0;
                        } else {
                            grid[i][j] = grid[i-1][j] + grid[i][j-1];
                        }
                    }
                }
            }
            return grid[m-1][n-1];
        }
    }
}
