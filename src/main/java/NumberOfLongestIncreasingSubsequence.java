import java.util.Arrays;

public class NumberOfLongestIncreasingSubsequence {
    /*
        Given an integer array nums, return the number of longest increasing subsequences.
        Notice that the sequence has to be strictly increasing.

        Example 1:

        Input: nums = [1,3,5,4,7]
        Output: 2
        Explanation: The two longest increasing subsequences are [1, 3, 4, 7] and [1, 3, 5, 7].
        Example 2:

        Input: nums = [2,2,2,2,2]
        Output: 5
        Explanation: The length of the longest increasing subsequence is 1, and there are 5 increasing subsequences of length 1, so output 5.


        Constraints:

        1 <= nums.length <= 2000
        -106 <= nums[i] <= 106
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findNumberOfLIS(new int[]{2,2,2,2,2}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int findNumberOfLIS(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n];
            int[] count = new int[n];
            Arrays.fill(dp, 1);
            Arrays.fill(count,1);
            int max = 1;

            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        if (dp[j] + 1 > dp[i]) {
                            dp[i] = dp[j] + 1;
                            count[i] = count[j];
                        } else if (dp[j] + 1 == dp[i]) {
                            count[i] += count[j];
                        }
                        max = Math.max(dp[i],max);
                    }
                }
            }
            int ans = 0;
            for (int i = 0; i < n; i++) {
                if (dp[i] == max) {
                    ans += count[i];
                }
            }
            return ans;

        }
    }
}
