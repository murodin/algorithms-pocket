import java.util.ArrayList;
import java.util.List;

public class MakingALargeIsland {
    /*
        You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
        Return the size of the largest island in grid after applying this operation.
        An island is a 4-directionally connected group of 1s.

        Example 1.
        Input: grid = [[1,0],[0,1]]
        Output: 3
        Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
        Example 2.
        Input: grid = [[1,1],[1,0]]
        Output: 4
        Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
        Example 3.
        Input: grid = [[1,1],[1,1]]
        Output: 4
        Explanation: Can't change any 0 to 1, only one island with area = 4.


        Constraints:

        n == grid.length
        n == grid[i].length
        1 <= n <= 500
        grid[i][j] is either 0 or 1.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.largestIsland(new int[][]{{1,1}, {1,1}}));
    }

    // Time:
    // Space:
    static class Solution {
        public static int maxArea(int[][] mat, int r, int c, int num, int area) {
            area++;
            mat[r][c] = num;

            if (r < mat.length-1 && mat[r+1][c] == 1) {
                area = maxArea(mat, r+1, c, num, area);
            }

            if (r > 0 && mat[r-1][c] == 1){
                area = maxArea(mat, r-1, c, num, area);
            }

            if (c < mat[0].length-1 && mat[r][c+1] == 1) {
                area = maxArea(mat, r, c+1, num, area);
            }

            if (c > 0 && mat[r][c-1] == 1){
                area = maxArea(mat, r, c-1, num, area);
            }

            return area;
        }

        public static int largestIsland(int[][] grid) {
            List<Integer> sizes = new ArrayList<>();
            sizes.add(0);
            sizes.add(0);
            int cur = 2;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        sizes.add(maxArea(grid, i, j, cur, 0));
                        cur++;
                    }
                }
            }

            int max = 0;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 0) {
                        int aa = 0, bb = 0, cc = 0, dd = 0;
                        if (i < grid.length-1 && grid[i+1][j] != 0) {
                            aa = grid[i+1][j];
                        }

                        if (i > 0 && grid[i-1][j] != 0) {
                            if (grid[i-1][j] != aa)
                                bb = grid[i-1][j];
                        }

                        if (j < grid[0].length-1 && grid[i][j+1] != 0) {
                            if (grid[i][j+1] != aa && grid[i][j+1] != bb)
                                cc = grid[i][j+1];
                        }

                        if (j > 0 && grid[i][j-1] != 0) {
                            if (grid[i][j-1] != aa && grid[i][j-1] != bb && grid[i][j-1] != cc)
                                dd = grid[i][j-1];
                        }
                        max = Math.max(max, sizes.get(aa) + sizes.get(bb) + sizes.get(cc) + sizes.get(dd));
                    }
                }
            }

            for (Integer size : sizes) {
                max = Math.max(max, size);
            }

            return Math.min(grid.length * grid[0].length, max + 1);
        }
    }
}
