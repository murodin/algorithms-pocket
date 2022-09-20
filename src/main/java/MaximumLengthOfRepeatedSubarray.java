public class MaximumLengthOfRepeatedSubarray {
    /*
        Given two integer arrays nums1 and nums2, return the maximum length of a subarray that appears in both arrays.

        Example 1.
        Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
        Output: 3
        Explanation: The repeated subarray with maximum length is [3,2,1].
        Example 2.
        Input: nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
        Output: 5

        Constraints:

        1 <= nums1.length, nums2.length <= 1000
        0 <= nums1[i], nums2[i] <= 100
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findLength(new int[] {1,2,3,2,1}, new int[]{3,2,1,4,7}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int findLength(int[] nums1, int[] nums2) {
            int m = nums1.length;
            int n = nums2.length;

            // Check for empty nums1 or nums2. If any is 0, then there won't be any repeated subarray.
            if (m == 0 || n == 0) {
                return 0;
            }

            // Dynamic Programming using 1D Array.
            int[] dp = new int[n + 1];
            int max = 0;

            // Traverse both nums2 for every numbers in nums1, to check if same number.
            // Method is similar to the 2D Matrix solution, but we only use an array to keep track of the count.
            // Example:
            // nums1 = [1,2,3,4]
            // nums2 = [4,2,3,4]
            // i = 3, dp = [1,0,0,1,0], max = 1
            // i = 2, dp = [0,0,2,0,0], max = 2
            // i = 1, dp = [0,3,0,0,0], max = 3
            // i = 0, dp = [0,0,0,0,0], max = 3
            // Note that the count for each round only increase when there is a continuing match from previous number.
            for (int i = m - 1; i >= 0; i--) {
                for (int j = 0; j < n; j++) {
                    dp[j] = nums1[i] == nums2[j] ? dp[j + 1] + 1 : 0;
                    max = Math.max(max, dp[j]);
                }
            }
            return max;
        }
    }
}
