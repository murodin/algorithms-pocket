public class WaysToSplitArrayIntoThreeSubarrays {
    /*
        A split of an integer array is good if:
        The array is split into three non-empty contiguous subarrays - named left, mid, right respectively from left to right.
        The sum of the elements in left is less than or equal to the sum of the elements in mid, and the sum of the elements in mid is less than or equal to the sum of the elements in right.
        Given nums, an array of non-negative integers, return the number of good ways to split nums. As the number may be too large, return it modulo 109 + 7.

        Example 1.
        Input: nums = [1,1,1]
        Output: 1
        Explanation: The only good way to split nums is [1] [1] [1].
        Example 2.
        Input: nums = [1,2,2,2,5,0]
        Output: 3
        Explanation: There are three good ways of splitting nums:
        [1] [2] [2,2,5,0]
        [1] [2,2] [2,5,0]
        [1,2] [2,2] [5,0]
        Example 3.
        Input: nums = [3,2,1]
        Output: 0
        Explanation: There is no good way to split nums.


        Constraints:

        3 <= nums.length <= 105
        0 <= nums[i] <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.waysToSplit(new int[]{1,2,2,2,5,0}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int waysToSplit(int[] nums) {
            for (int i = 1; i < nums.length; i++) {
                nums[i] += nums[i-1];
            }
            int ways = 0;
            for (int i = 0, j = 0, k = 0; i < nums.length - 2 && 3 * nums[i] <= nums[nums.length - 1]; i++) {
                j = Math.max(j, i + 1);
                while (j < nums.length - 1 && nums[j] - nums[i] < nums[i])
                    j++;
                k = Math.max(k, j);
                while (k < nums.length - 1 && nums[k] - nums[i] <= nums[nums.length - 1] - nums[k])
                    k++;
                ways = (ways + k - j) % 1000000007;
            }
            return ways;
        }
    }
}
