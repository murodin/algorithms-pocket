public class IncreasingTripletSubsequence {
    /*
        Given an integer array nums,
        return true if there exists a triple of indices (i, j, k) such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
        Example 1.
        Input: nums = [1,2,3,4,5]
        Output: true
        Explanation: Any triplet where i < j < k is valid.
        Example 2.
        Input: nums = [5,4,3,2,1]
        Output: false
        Explanation: No triplet exists.
        Example 3.
        Input: nums = [2,1,5,0,4,6]
        Output: true
        Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.

        Constraints:

        1 <= nums.length <= 5 * 105
        -231 <= nums[i] <= 231 - 1

        Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?
     */

    public static void main(String[] args) {
        System.out.println("Test 1 -> Increasing Triplet Subsequence: " + Solution.increasingTriplet(new int[]{1,2,3,4,5}));
        System.out.println("Test 2 -> Increasing Triplet Subsequence: " + Solution.increasingTriplet(new int[]{5,4,3,2,1}));
        System.out.println("Test 3 -> Increasing Triplet Subsequence: " + Solution.increasingTriplet(new int[]{2,1,5,0,4,6}));
        System.out.println("Test 4 -> Increasing Triplet Subsequence: " + Solution.increasingTriplet(new int[]{2,1}));
    }

    static class Solution {
        public static boolean increasingTriplet(int[] nums) {
            if(nums.length < 3) return false;
            int i = Integer.MAX_VALUE, j = Integer.MAX_VALUE;

            for (int num : nums) {
                if (num <= i) i = num;
                else if (num <= j) j = num;
                else return true;
            }
            return false;
        }
    }
}
