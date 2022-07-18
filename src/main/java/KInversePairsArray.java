public class KInversePairsArray {
    /*
        For an integer array nums, an inverse pair is a pair of integers [i, j] where 0 <= i < j < nums.length and nums[i] > nums[j].
        Given two integers n and k, return the number of different arrays consist of numbers from 1 to n such that there are exactly k inverse pairs.
        Since the answer can be huge, return it modulo 109 + 7.

        Example 1.
        Input: n = 3, k = 0
        Output: 1
        Explanation: Only the array [1,2,3] which consists of numbers from 1 to 3 has exactly 0 inverse pairs.
        Example 2.
        Input: n = 3, k = 1
        Output: 2
        Explanation: The array [1,3,2] and [2,1,3] have exactly 1 inverse pair.


        Constraints:

        1 <= n <= 1000
        0 <= k <= 1000
     */

    /*
        Explanation:
        The difficulty of this question is how to find the relationship between the big problem and the sub-problems.
        I'm sure many of us think about dynamic programming but fail to find the inner relationship.
        Let us consider this question step by step. For a given k, when we get a new numebr n, we have lots of position to put it.

        If we put it in the last position, we can get dp[n - 1][k] inverse pairs.
        If we put it in the second last position, we can get dp[n - 1][k - 1] inverse pairs, since n is bigger than the number in the last position.
        ...
        If we put it in the first position, we can get dp[n - 1][k - n + 1] inverse pairs.
        So, we get the relationship!
        dp[n][k] = dp[n - 1][k] + dp[n - 1][k - 1] + ... + dp[n - 1][k - n + 1]

        Let dp[n][k] minus dp[n][k - 1], we can get the formula:
        dp[n][k] = dp[n][k - 1] + dp[n - 1][k] - dp[n - 1][k - n]

        Now we figure out it~
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.kInversePairs(3, 1));
    }

    // Time: O(N)
    // Space: o(N)
    static class Solution {
        public static int kInversePairs(int n, int k) {
            if (k > n * (n - 1) / 2) // n numbers can generate at most n * (n - 1) / 2 inverse pairs
                return 0;

            if (k == n * (n - 1) / 2 || k == 0)
                return 1;

            int mod = 1000000007;
            int[][] dp = new int[n + 1][k + 1];

            for (int i = 1; i < n + 1; i++) {
                dp[i][0] = 1; // deal with j = 0
                for (int j = 1; j < Math.min(k, i * (i - 1) / 2) + 1; j++) {
                    dp[i][j] = (dp[i][j - 1] + dp[i - 1][j] - (j >= i ? dp[i - 1][j - i] : 0)) % mod;
                    // all dp[i][j] modulo 10^9 + 7
                    // so dp[i - 1][j - 1] might bigger than dp[i][j - 1] + dp[i - 1][j]
                    if (dp[i][j] < 0)
                        dp[i][j] += mod;
                }
            }

            return dp[n][k];
        }
    }
}
