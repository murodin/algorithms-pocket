public class UniquePaths {
    /*
        There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]).
        The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
        Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
        The test cases are generated so that the answer will be less than or equal to 2 * 109.

        Example 1.
        Input: m = 3, n = 7
        Output: 28
        Example 2.
        Input: m = 3, n = 2
        Output: 3
        Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
        1. Right -> Down -> Down
        2. Down -> Down -> Right
        3. Down -> Right -> Down
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.uniquePaths(3, 7));
    }

    // Time: O(MxN)
    // Space: O(MxN)
    static class Solution {
        public static int uniquePaths(int m, int n) {
            int [][]dp = new int [m][n];
            for(int i =0;i<dp.length;i++){
                for(int j =0;j<dp[0].length;j++){
                    if(i==0 || j==0)
                        dp[i][j]=1;
                }
            }

            for(int i=1;i<dp.length;i++) {
                for(int j =1;j<dp[i].length;j++)
                    dp[i][j] = dp[i-1][j]+dp[i][j-1];
            }
            return dp[m-1][n-1];
        }
    }
}
