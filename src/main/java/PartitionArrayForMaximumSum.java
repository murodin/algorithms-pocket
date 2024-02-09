import java.util.Arrays;

public class PartitionArrayForMaximumSum {
    /*
        Given an integer array arr, partition the array into (contiguous) subarrays of length at most k.
        After partitioning, each subarray has their values changed to become the maximum value of that subarray.

        Return the largest sum of the given array after partitioning. Test cases are generated so that the answer fits in a 32-bit integer.

        Example 1:
        Input: arr = [1,15,7,9,2,5,10], k = 3
        Output: 84
        Explanation: arr becomes [15,15,15,9,10,10,10]
        Example 2:
        Input: arr = [1,4,1,5,7,3,6,1,9,9,3], k = 4
        Output: 83
        Example 3:
        Input: arr = [1], k = 1
        Output: 1


        Constraints:

        1 <= arr.length <= 500
        0 <= arr[i] <= 109
        1 <= k <= arr.length
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxSumAfterPartitioning(new int[]{1,4,1,5,7,3,6,1,9,9,3}, 4));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        static public int maxSumAfterPartitioning(int[] arr, int k) {
            int N = arr.length;
            int K = k + 1;
            int[] dp = new int[k + 1];
            Arrays.fill(dp, 0);
            for (int start = N - 1; start >= 0; start--) {
                int currMax = 0;
                int end = Math.min(N, start + k);
                for (int i = start; i < end; i++) {
                    currMax = Math.max(currMax, arr[i]);
                    dp[start % K] = Math.max(dp[start % K], dp[(i + 1) % K] + currMax * (i - start + 1));
                }
            }
            return dp[0];
        }
    }
}
