import java.util.Arrays;

public class OutOfBoundaryPaths {

    /*
        There is an m x n grid with a ball. The ball is initially at the position [startRow, startColumn].
         You are allowed to move the ball to one of the four adjacent cells in the grid (possibly out of the grid crossing the grid boundary). You can apply at most maxMove moves to the ball.
        Given the five integers m, n, maxMove, startRow, startColumn, return the number of paths to move the ball out of the grid boundary.
        Since the answer can be very large, return it modulo 109 + 7.

        Example 1.
        Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
        Output: 6
        Example 2.
        Input: m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
        Output: 12


        Constraints:

        1 <= m, n <= 50
        0 <= maxMove <= 50
        0 <= startRow < m
        0 <= startColumn < n
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findPaths(2, 2, 2, 0, 0));
    }

    // Time: O(MxN)
    // Space: O(MxNxMaxMove)
    static class Solution {
        static int mod=1000000007;
        public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
            int [][][] dp=new int[m][n][maxMove+1];
            for(int [][] outer:dp){
                for(int [] inner:outer){
                    Arrays.fill(inner,-1);
                }
            }

            return memoization(m,n,maxMove,startRow,startColumn,dp);
        }

        public static int memoization(int m, int n, int maxMove, int startRow, int startColumn, int[][][] dp){
            if(startRow==m || startColumn==n || startRow<0 || startColumn<0){
                return 1;
            }
            if(maxMove==0){
                return 0;
            }
            if(dp[startRow][startColumn][maxMove]>=0){
                return dp[startRow][startColumn][maxMove];
            }

            dp[startRow][startColumn][maxMove]=(
                    (memoization(m,n,maxMove-1,startRow-1,startColumn,dp)+
                    memoization(m,n,maxMove-1,startRow+1,startColumn,dp))%mod+
                    (memoization(m,n,maxMove-1,startRow,startColumn-1,dp)+
                            memoization(m,n,maxMove-1,startRow,startColumn+1,dp))%mod)%mod;

            return dp[startRow][startColumn][maxMove];
        }
    }
}
