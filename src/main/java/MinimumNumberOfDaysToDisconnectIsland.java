public class MinimumNumberOfDaysToDisconnectIsland {
    /*
        You are given an m x n binary grid grid where 1 represents land and 0 represents water.
         An island is a maximal 4-directionally (horizontal or vertical) connected group of 1's.
        The grid is said to be connected if we have exactly one island, otherwise is said disconnected.
        In one day, we are allowed to change any single land cell (1) into a water cell (0).
        Return the minimum number of days to disconnect the grid.


        Example 1.
        Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]

        Output: 2
        Explanation: We need at least 2 days to get a disconnected grid.
        Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.
        Example 2.
        Input: grid = [[1,1]]
        Output: 2
        Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands.


        Constraints:

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 30
        grid[i][j] is either 0 or 1.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minDays(new int[][]{{0,1,1,0},{0,1,1,0},{0,0,0,0}}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        private static int[] low, disc;
        private static int time = 0;
        private static boolean[] vis;
        public static int minDays(int[][] grid) {
            int n = grid.length, m = grid[0].length;

            disc = new int[n * m];
            low = new int[n * m];
            vis = new boolean[n * m];
            int root = -1;
            int noOfComponents = 0, size = 0;
            for (int i = 0; i < n * m; i++) {
                int r = i / m, c = i % m;

                if (grid[r][c] == 1 && !vis[i]) {
                    root = i;
                    size += dfs_size(i, grid, vis);
                    noOfComponents++;
                }
            }

            if (noOfComponents == 0 || noOfComponents > 1)
                return 0;
            else if (size <= 2)
                return size;

            vis = new boolean[n * m];
            boolean res = tarjans(root, -1, grid);
            return res ? 1 : 2;
        }

        private static int dfs_size(int idx, int[][] grid, boolean[] vis) {
            int n = grid.length, m = grid[0].length;
            int sr = idx / m, sc = idx % m;

            vis[idx] = true;

            int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
            int count = 0;

            for (int[] ints : dir) {
                int r = sr + ints[0];
                int c = sc + ints[1];

                if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1 && !vis[r * m + c]) {
                    count += dfs_size(r * m + c, grid, vis);
                }
            }

            return count + 1;
        }

        private static boolean tarjans(int src, int par, int[][] grid) {
            int n = grid.length, m = grid[0].length;
            disc[src] = low[src] = time++;
            vis[src] = true;

            int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

            boolean res = false;
            for (int[] ints : dir) {
                int sr = src / m, sc = src % m;

                int r = sr + ints[0];
                int c = sc + ints[1];

                if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                    int nbr = r * m + c;
                    if (!vis[nbr]) {
                        res = res || tarjans(nbr, src, grid);
                        if (disc[src] < low[nbr]) {
                            return true;
                        }
                        low[src] = Math.min(low[nbr], low[src]);
                    } else if (nbr != par) {
                        low[src] = Math.min(low[src], disc[nbr]);
                    }
                }

            }
            return res;
        }
    }
}
