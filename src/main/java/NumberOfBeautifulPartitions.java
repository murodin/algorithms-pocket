public class NumberOfBeautifulPartitions {
    /*
        You are given a string s that consists of the digits '1' to '9' and two integers k and minLength.
        A partition of s is called beautiful if:
        s is partitioned into k non-intersecting substrings.
        Each substring has a length of at least minLength.
        Each substring starts with a prime digit and ends with a non-prime digit. Prime digits are '2', '3', '5', and '7', and the rest of the digits are non-prime.
        Return the number of beautiful partitions of s. Since the answer may be very large, return it modulo 109 + 7.

        A substring is a contiguous sequence of characters within a string.

        Example 1:

        Input: s = "23542185131", k = 3, minLength = 2
        Output: 3
        Explanation: There exists three ways to create a beautiful partition:
        "2354 | 218 | 5131"
        "2354 | 21851 | 31"
        "2354218 | 51 | 31"
        Example 2:

        Input: s = "23542185131", k = 3, minLength = 3
        Output: 1
        Explanation: There exists one way to create a beautiful partition: "2354 | 218 | 5131".
        Example 3:

        Input: s = "3312958", k = 3, minLength = 1
        Output: 1
        Explanation: There exists one way to create a beautiful partition: "331 | 29 | 58".


        Constraints:

        1 <= k, minLength <= s.length <= 1000
        s consists of the digits '1' to '9'.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.beautifulPartitions("23542185131", 3, 3));
    }

    // Time: O(MxN)
    // Space: O(N)
    static class Solution {
        private static int mod = (int) 1e9 + 7;
        public static int beautifulPartitions(String s, int k, int l) {
            char[] cs = s.toCharArray();
            int n = cs.length;
            if (!prime(cs[0]) || prime(cs[n - 1])) return 0;
            int[][] dp = new int[k][n];
            for (int i = n - l; 0 <= i; --i) dp[0][i] = prime(cs[i]) ? 1 : 0;
            for (int i = 1; i < k; ++i) {
                for (int j = n - i * l, sum = 0; 0 <= j; --j) {
                    if (0 == dp[i - 1][j]) dp[i - 1][j] = sum;
                    else if (0 != j && 0 == dp[i - 1][j - 1]) sum = (sum + dp[i - 1][j]) % mod;
                }
                for (int j = 0, p = l - 1; j + l * i < n; ++j) {
                    if (!prime(cs[j])) continue;
                    p = Math.max(p, j + l - 1);
                    while (prime(cs[p])) p++;
                    if (0 == dp[i - 1][p]) break;
                    dp[i][j] = dp[i - 1][p];
                }
            }
            return dp[k - 1][0];
        }
        private static boolean prime(char c) {
            return '2' == c || '3' == c || '5' == c || '7' == c;
        }
    }
}
