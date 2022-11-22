public class PerfectSquares {
    /*
        Given an integer n, return the least number of perfect square numbers that sum to n.
        A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself.
        For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

        Example 1.
        Input: n = 12
        Output: 3
        Explanation: 12 = 4 + 4 + 4.
        Example 2.
        Input: n = 13
        Output: 2
        Explanation: 13 = 4 + 9.


        Constraints:

        1 <= n <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numSquares(13));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int numSquares(int n) {
            int [] dp = new int[n+1];
            for(int i=0;i<=n;i++)
                dp[i] = Integer.MAX_VALUE;

            dp[0]=0;
            int ind=1;
            while(ind*ind <= n) {
                int square = ind*ind;
                for(int i=square;i<=n;i++){
                    dp[i] = Math.min(dp[i-square]+1,dp[i]);
                }
                ind++;
            }
            return dp[n];
        }
    }
}
