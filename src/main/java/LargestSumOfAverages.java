public class LargestSumOfAverages {
    /*
        You are given an integer array nums and an integer k. You can partition the array into at most k non-empty adjacent subarrays.
        The score of a partition is the sum of the averages of each subarray.
        Note that the partition must use every integer in nums, and that the score is not necessarily an integer.
        Return the maximum score you can achieve of all the possible partitions. Answers within 10-6 of the actual answer will be accepted.

        Example 1:

        Input: nums = [9,1,2,3,9], k = 3
        Output: 20.00000
        Explanation:
        The best choice is to partition nums into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
        We could have also partitioned nums into [9, 1], [2], [3, 9], for example.
        That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
        Example 2:

        Input: nums = [1,2,3,4,5,6,7], k = 4
        Output: 20.50000


        Constraints:

        1 <= nums.length <= 100
        1 <= nums[i] <= 104
        1 <= k <= nums.length
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.largestSumOfAverages(new int[]{1,2,3,4,5,6,7}, 4));
    }

    // Time: O(N)
    // Space: O(N^2)
    static class Solution {
        public static double largestSumOfAverages(int[] A, int K) {
            int N = A.length;
            double[][] memo = new double[N+1][N+1];
            double cur = 0;
            for (int i = 0; i < N; ++i) {
                cur += A[i];
                memo[i + 1][1] = cur / (i + 1);
            }
            return search(N, K, A, memo);
        }

        public static double search(int n, int k, int[] A, double[][] memo) {
            if (memo[n][k] > 0) return memo[n][k];
            if (n < k) return 0;
            double cur = 0;
            for (int i = n - 1; i > 0; --i) {
                cur += A[i];
                memo[n][k] = Math.max(memo[n][k], search(i, k - 1, A, memo) + cur / (n - i));
            }
            return memo[n][k];
        }
    }
}
