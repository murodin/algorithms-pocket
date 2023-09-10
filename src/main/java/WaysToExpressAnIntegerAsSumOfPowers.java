public class WaysToExpressAnIntegerAsSumOfPowers {
    /*
        Given two positive integers n and x.
        Return the number of ways n can be expressed as the sum of the xth power of unique positive integers, in other words,
        the number of sets of unique integers [n1, n2, ..., nk] where n = n1x + n2x + ... + nkx.
        Since the result can be very large, return it modulo 109 + 7.
        For example, if n = 160 and x = 3, one way to express n is n = 23 + 33 + 53.


        Example 1:

        Input: n = 10, x = 2
        Output: 1
        Explanation: We can express n as the following: n = 32 + 12 = 10.
        It can be shown that it is the only way to express 10 as the sum of the 2nd power of unique integers.
        Example 2:

        Input: n = 4, x = 1
        Output: 2
        Explanation: We can express n in the following ways:
        - n = 41 = 4.
        - n = 31 + 11 = 4.


        Constraints:

        1 <= n <= 300
        1 <= x <= 5
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numberOfWays(4, 1));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static int numberOfWays(int n, int x) {
            int[] dp = new int[301];
            int mod = 1000000007;
            int v;
            dp[0] = 1;
            for (int a = 1; (v = (int)Math.pow(a, x)) <= n; a++)
                for (int i = n; i >= v; i--)
                    dp[i] = (dp[i] + dp[i - v]) % mod;
            return dp[n];
        }
    }
}
