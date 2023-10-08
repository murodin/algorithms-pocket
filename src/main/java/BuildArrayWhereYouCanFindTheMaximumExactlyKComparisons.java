import java.util.Arrays;

public class BuildArrayWhereYouCanFindTheMaximumExactlyKComparisons {
    /*
        You are given three integers n, m and k. Consider the following algorithm to find the maximum element of an array of positive integers:
        You should build the array arr which has the following properties:

        arr has exactly n integers.
        1 <= arr[i] <= m where (0 <= i < n).
        After applying the mentioned algorithm to arr, the value search_cost is equal to k.
        Return the number of ways to build the array arr under the mentioned conditions. As the answer may grow large, the answer must be computed modulo 109 + 7.

        Example 1:

        Input: n = 2, m = 3, k = 1
        Output: 6
        Explanation: The possible arrays are [1, 1], [2, 1], [2, 2], [3, 1], [3, 2] [3, 3]
        Example 2:

        Input: n = 5, m = 2, k = 3
        Output: 0
        Explanation: There are no possible arrays that satisify the mentioned conditions.
        Example 3:

        Input: n = 9, m = 1, k = 1
        Output: 1
        Explanation: The only possible array is [1, 1, 1, 1, 1, 1, 1, 1, 1]


        Constraints:

        1 <= n <= 50
        1 <= m <= 100
        0 <= k <= n
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numOfArrays(5, 2, 3));
    }

    // Time: O(NxKxM)
    // Space: O(NxKxM)
    static class Solution {
        static int numOfArrays(int n, int m, int k) {
            long[][][] dp =new long[n][k][m];
            long mod = 1000000007;
            Arrays.fill(dp[0][0], 1);
            for (int i = 1; i<n; i++) {
                for (int cost = 0; cost <Math.min(i+1, k); cost++) {
                    for (int max = 0; max < m; max++){
                        long sum = 0;
                        sum += dp[i-1][cost][max] * (max+1);
                        if (cost != 0) {
                            long[] arr = dp[i-1][cost-1];
                            for (int prevMax = 0;prevMax < max; prevMax++) {
                                sum += arr[prevMax];
                            }
                        }
                        dp[i][cost][max] = sum %mod;
                    }
                }
            }
            long ans = 0;
            for (int max = 0;max < m;max++) {
                ans += dp[n-1][k-1][max];
                ans %= mod;
            }
            return (int) ans;
        }
    }
}
