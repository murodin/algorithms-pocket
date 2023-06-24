import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSubsequence {
    /*
        Given an array nums of integers, return the length of the longest arithmetic subsequence in nums.
        Note that:

        A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
        A sequence seq is arithmetic if seq[i + 1] - seq[i] are all the same value (for 0 <= i < seq.length - 1).

        Example 1:

        Input: nums = [3,6,9,12]
        Output: 4
        Explanation:  The whole array is an arithmetic sequence with steps of length = 3.
        Example 2:

        Input: nums = [9,4,7,2,10]
        Output: 3
        Explanation:  The longest arithmetic subsequence is [4,7,10].
        Example 3:

        Input: nums = [20,1,15,3,10,5,8]
        Output: 4
        Explanation:  The longest arithmetic subsequence is [20,15,10,5].


        Constraints:

        2 <= nums.length <= 1000
        0 <= nums[i] <= 500
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.longestArithSeqLength(new int[] {20,1,15,3,10,5,8}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int longestArithSeqLength(int[] nums) {
            Map<Integer, Integer>[] dp = new HashMap[nums.length];
            int longest = 2;

            for (int j = 0; j < nums.length; j++) {
                dp[j] = new HashMap<>();
                for (int i = 0; i < j; i++) {
                    int diff = nums[j] - nums[i];
                    dp[j].put(diff, dp[i].getOrDefault(diff, 1) + 1);
                    longest = Math.max(longest, dp[j].get(diff));
                }
            }

            return longest;
        }
    }
}
