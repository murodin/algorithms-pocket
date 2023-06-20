import java.util.Arrays;

public class NumberOfIncreasingPathsInAGrid {
    /*
        You are given an m x n integer matrix grid, where you can move from a cell to any adjacent cell in all 4 directions.
        Return the number of strictly increasing paths in the grid such that you can start from any cell and end at any cell.
        Since the answer may be very large, return it modulo 109 + 7
        Two paths are considered different if they do not have exactly the same sequence of visited cells.

        Example 1:


        Input: grid = [[1,1],[3,4]]
        Output: 8
        Explanation: The strictly increasing paths are:
        - Paths with length 1: [1], [1], [3], [4].
        - Paths with length 2: [1 -> 3], [1 -> 4], [3 -> 4].
        - Paths with length 3: [1 -> 3 -> 4].
        The total number of paths is 4 + 3 + 1 = 8.
        Example 2:

        Input: grid = [[1],[2]]
        Output: 3
        Explanation: The strictly increasing paths are:
        - Paths with length 1: [1], [2].
        - Paths with length 2: [1 -> 2].
        The total number of paths is 2 + 1 = 3.


        Constraints:

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 1000
        1 <= m * n <= 105
        1 <= grid[i][j] <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.countPaths(new int[][]{{1,1}, {3,4}}));
    }

    // Time: O(4^N)
    // Space: O(N^2)
    static class Solution {
        static int mod = (int)(1e9+7);
        public static int countPaths(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;

            int[][] dp = new int[n][m];
            for(int[] arr: dp){
                Arrays.fill(arr, -1);
            }
            int paths = 0;
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    paths = (paths+solve(grid, i, j, -1, dp))%mod;
                }
            }
            return paths;
        }

        public static int solve(int[][] grid, int i, int j, int prev, int[][] dp){
            if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j]<=prev){
                return 0;
            }
            if(dp[i][j]!=-1){
                return dp[i][j];
            }
            int left = solve(grid, i, j-1, grid[i][j], dp);
            int right = solve(grid, i, j+1, grid[i][j], dp);
            int up = solve(grid, i-1, j, grid[i][j], dp);
            int down = solve(grid, i+1, j, grid[i][j], dp);
            return dp[i][j] = (1+left+right+up+down)%mod;
        }
    }
}
