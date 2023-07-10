public class LongestSubarrayOfOnesAfterDeletingOneElement {
    /*
        Given a binary array nums, you should delete one element from it.

        Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.

        Example 1:

        Input: nums = [1,1,0,1]
        Output: 3
        Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
        Example 2:

        Input: nums = [0,1,1,1,0,1,1,0,1]
        Output: 5
        Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
        Example 3:

        Input: nums = [1,1,1]
        Output: 2
        Explanation: You must delete one element.


        Constraints:

        1 <= nums.length <= 105
        nums[i] is either 0 or 1.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.longestSubarray(new int[]{0,1,1,1,0,1,1,0,1}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int longestSubarray(int[] nums) {
            int n = nums.length;

            int left = 0;
            int zeros = 0;
            int ans = 0;

            for (int right = 0; right < n; right++) {
                if (nums[right] == 0) {
                    zeros++;
                }
                while (zeros > 1) {
                    if (nums[left] == 0) {
                        zeros--;
                    }
                    left++;
                }
                ans = Math.max(ans, right - left + 1 - zeros);
            }
            return (ans == n) ? ans - 1 : ans;
        }
    }
}
