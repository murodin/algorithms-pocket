public class NumberOfEnclaves {
    /*
        You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
        A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
        Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

        Example 1.
        Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
        Output: 3
        Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
        Example 2.
        Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
        Output: 0
        Explanation: All 1s are either on the boundary or can reach the boundary.


        Constraints:

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 500
        grid[i][j] is either 0 or 1.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numEnclaves(new int[][]{{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}}));
    }

    // Time: O(4^N)
    // Space: O(1)
    static class Solution {
        public static int numEnclaves(int[][] grid) {
            int n = grid.length;
            int m = grid[0].length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if ((i == 0 || j == 0 || i == n-1 || j == m-1) && grid[i][j] == 1) {
                        dfs(grid,i, j);
                    }
                }
            }

            int s = 0;
            for (int[] ints : grid) {
                for (int j = 0; j < m; j++) {
                    s += ints[j];
                }
            }
            return s;
        }

        private static void dfs(int[][] grid, int i, int j){
            int n = grid.length;
            int m = grid[0].length;
            if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == 0) {
                return;
            }
            grid[i][j] = 0;
            dfs(grid,i+1, j);
            dfs(grid,i-1, j);
            dfs(grid,i, j+1);
            dfs(grid,i, j-1);
        }
    }
}
