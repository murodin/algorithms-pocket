public class CountSubIslands {
    /*
        You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land).
        An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.

        An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.

        Return the number of islands in grid2 that are considered sub-islands.



        Example 1:


        Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
        Output: 3
        Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
        The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
        Example 2:


        Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
        Output: 2
        Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
        The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.


        Constraints:

        m == grid1.length == grid2.length
        n == grid1[i].length == grid2[i].length
        1 <= m, n <= 500
        grid1[i][j] and grid2[i][j] are either 0 or 1.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.countSubIslands(
                new int[][]{{1,1,1,0,0},{0,1,1,1,1},{0,0,0,0,0},{1,0,0,0,0},{1,1,0,1,1}},
                new int[][]{{1,1,1,0,0},{0,0,1,1,1},{0,1,0,0,0},{1,0,1,1,0},{0,1,0,1,0}}
                )
        );
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int countSubIslands(int[][] B, int[][] A) {
            int m = A.length, n = A[0].length, res = 0;
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    if (A[i][j] == 1)
                        res += dfs(B, A, i, j);
            return res;
        }
        private static int dfs(int[][] B, int[][] A, int i, int j) {
            int m = A.length, n = A[0].length, res = 1;
            if (i < 0 || i == m || j < 0 || j == n || A[i][j] == 0) return 1;
            A[i][j] = 0;
            res &= dfs(B, A, i - 1, j);
            res &= dfs(B, A, i + 1, j);
            res &= dfs(B, A, i, j - 1);
            res &= dfs(B, A, i, j + 1);
            return res & B[i][j];
        }
    }
}
