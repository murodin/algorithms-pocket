public class CherryPickupII {

    /*
        You are given a rows x cols matrix grid representing a field of cherries where grid[i][j] represents the number of cherries that you can collect from the (i, j) cell.
        You have two robots that can collect cherries for you:
        Robot #1 is located at the top-left corner (0, 0), and
        Robot #2 is located at the top-right corner (0, cols - 1).
        Return the maximum number of cherries collection using both robots by following the rules below:

        From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
        When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
        When both robots stay in the same cell, only one takes the cherries.
        Both robots cannot move outside of the grid at any moment.
        Both robots should reach the bottom row in grid.

        Example 1.
        Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
        Output: 24
        Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
        Cherries taken by Robot #1, (3 + 2 + 5 + 2) = 12.
        Cherries taken by Robot #2, (1 + 5 + 5 + 1) = 12.
        Total of cherries: 12 + 12 = 24.
        Example 2.
        Input: grid = [[1,0,0,0,0,0,1],[2,0,0,0,0,3,0],[2,0,9,0,0,0,0],[0,3,0,5,4,0,0],[1,0,2,3,0,0,6]]
        Output: 28
        Explanation: Path of robot #1 and #2 are described in color green and blue respectively.
        Cherries taken by Robot #1, (1 + 9 + 5 + 2) = 17.
        Cherries taken by Robot #2, (1 + 3 + 4 + 3) = 11.
        Total of cherries: 17 + 11 = 28.

        Constraints:

        rows == grid.length
        cols == grid[i].length
        2 <= rows, cols <= 70
        0 <= grid[i][j] <= 100

     */

    public static void main(String[] args) {
        int[][] grid1 = {
                {1,0,0,0,0,0,1},
                {2,0,0,0,0,3,0},
                {2,0,9,0,0,0,0},
                {0,3,0,5,4,0,0},
                {1,0,2,3,0,0,6}
        };
        System.out.println("Test 1 -> Max Cherry To Pick: " + Solution.cherryPickup(grid1));

        int[][] grid2 = {
                {3,1,1},
                {2,5,1},
                {1,5,5},
                {2,1,1}
        };
        System.out.println("Test 2 -> Max Cherry To Pick: " + Solution.cherryPickup(grid2));
    }

    // Time: O(MxN^2)
    // Space: O(MxN^2)
    static class Solution {
        static int[] dir = {-1,0,1};
        public static int cherryPickup(int[][] grid) {
            int row = grid.length;
            int col = grid[0].length;
            int[][][] dp = new int[row][col][col];

            for (int i = 0; i<row; i++)
                for (int j = 0; j<col; j++)
                    for (int k = 0; k<col; k++)
                        dp[i][j][k] = -1;

            int col1 = 0, col2 = col -1;
            dp[0][col1][col2] = grid[0][col1] + grid[0][col2];

            int max = dp[0][col1][col2];

            for (int i = 1; i<row; i++) {
                for (int c1 = 0; c1<col; c1++) {
                    for (int c2 = 0; c2<col; c2++) {
                        int prev = dp[i-1][c1][c2];
                        if(prev >= 0) {
                            for(int d1: dir) {
                                col1 = c1 + d1;
                                for(int d2: dir) {
                                    col2 = c2 + d2;

                                    if(inRange(col1, col) && inRange(col2, col)) {
                                        dp[i][col1][col2] = Math.max(
                                                dp[i][col1][col2],
                                                prev+(col1==col2 ? grid[i][col1]: grid[i][col1] + grid[i][col2])
                                        );
                                        max = Math.max(max, dp[i][col1][col2]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return max;
        }

        private static boolean inRange(int val, int limit) {
            return 0<=val && val<limit;
        }
    }
}
