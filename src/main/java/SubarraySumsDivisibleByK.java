public class SubarraySumsDivisibleByK {
    /*
        Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
        A subarray is a contiguous part of an array.

        Example 1.
        Input: nums = [4,5,0,-2,-3,1], k = 5
        Output: 7
        Explanation: There are 7 subarrays with a sum divisible by k = 5:
        [4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
        Example 2.
        Input: nums = [5], k = 9
        Output: 0


        Constraints:

        1 <= nums.length <= 3 * 104
        -104 <= nums[i] <= 104
        2 <= k <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.subarraysDivByK(new int[]{4,5,0,-2,-3,1}, 5));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int subarraysDivByK(int[] nums, int k) {
            int[] counting = new int[k];
            for (int i = 0; i < nums.length; i++) {
                if (i > 0) nums[i] += nums[i - 1];
                counting[(nums[i] % k + k) % k]++;
            }
            int result = counting[0];
            for (int frequency : counting)
                result += frequency * (frequency - 1) / 2;
            return result;
        }
    }
}
