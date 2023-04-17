import java.util.PriorityQueue;

public class NumberOfWaysToSeparateNumbers {
    /*
        You wrote down many positive integers in a string called num. However, you realized that you forgot to add commas to seperate the different numbers.
        You remember that the list of integers was non-decreasing and that no integer had leading zeros.
        Return the number of possible lists of integers that you could have written down to get the string num. Since the answer may be large, return it modulo 109 + 7.

        Example 1.
        Input: num = "327"
        Output: 2
        Explanation: You could have written down the numbers:
        3, 27
        327
        Example 2.
        Input: num = "094"
        Output: 0
        Explanation: No numbers can have leading zeros and all numbers must be positive.
        Example 3.
        Input: num = "0"
        Output: 0
        Explanation: No numbers can have leading zeros and all numbers must be positive.


        Constraints:

        1 <= num.length <= 3500
        num consists of digits '0' through '9'.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numberOfCombinations("327"));
    }

    // Time: O(MxN)
    // Space: O(MxN)
    static class Solution {
        static int mod = (int) 1e9 + 7;
        public static int numberOfCombinations(String num) {
            char[] cs = num.toCharArray();
            int n = cs.length;
            int[][] rank = new int[n][n + 1];
            PriorityQueue<int[]> pq = new PriorityQueue<int[]>(1, (a, b) -> a[1] - b[1]);
            for (int i = 1; i <= n; ++i) {
                int c = 0, prev = 0;
                for (int j = 0; j + i <= n; ++j) pq.add(new int[]{j, rank[j][i - 1] * 10 + cs[i + j - 1] - '0'});
                while (!pq.isEmpty()) {
                    int[] cur = pq.poll();
                    if (cur[1] != prev) c++;
                    rank[cur[0]][i] = c;
                    prev = cur[1];
                }
            }
            int[][] dp = new int[n][n + 1];
            for (int j = n - 1; 0 <= j; --j) {
                if ('0' == cs[j]) continue;
                int len = n - j;
                dp[j][len] = 1;
                for (int i = len - 1; 1 <= i; --i) {
                    dp[j][i] = dp[j][i + 1];
                    int next = i + j;
                    if (next >= n || next + i > n) continue;
                    if (rank[j][i] <= rank[next][i]) dp[j][i] = (dp[j][i] + dp[next][i]) % mod;
                    else if (next + i < n) dp[j][i] = (dp[j][i] + dp[next][i + 1]) % mod;
                }
                dp[j][0] = dp[j][1];
            }
            return dp[0][0];
        }
    }
}
