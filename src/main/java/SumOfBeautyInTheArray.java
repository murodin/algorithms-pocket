public class SumOfBeautyInTheArray {
    /*
        You are given a 0-indexed integer array nums. For each index i (1 <= i <= nums.length - 2) the beauty of nums[i] equals:
        2, if nums[j] < nums[i] < nums[k], for all 0 <= j < i and for all i < k <= nums.length - 1.
        1, if nums[i - 1] < nums[i] < nums[i + 1], and the previous condition is not satisfied.
        0, if none of the previous conditions holds.
        Return the sum of beauty of all nums[i] where 1 <= i <= nums.length - 2.

        Example 1.
        Input: nums = [1,2,3]
        Output: 2
        Explanation: For each index i in the range 1 <= i <= 1:
        - The beauty of nums[1] equals 2.
        Example 2.
        Input: nums = [2,4,6,4]
        Output: 1
        Explanation: For each index i in the range 1 <= i <= 2:
        - The beauty of nums[1] equals 1.
        - The beauty of nums[2] equals 0.
        Example 3:

        Input: nums = [3,2,1]
        Output: 0
        Explanation: For each index i in the range 1 <= i <= 1:
        - The beauty of nums[1] equals 0.


        Constraints:

        3 <= nums.length <= 105
        1 <= nums[i] <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.sumOfBeauties(new int[]{2, 4, 6, 4}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int sumOfBeauties(int[] nums) {
            int[] minInRight = new int[nums.length];
            minInRight[nums.length - 1] = nums[nums.length - 1];
            for (int i = nums.length - 2; i >= 2; i--)
                minInRight[i] = Math.min(minInRight[i + 1], nums[i]);

            int maxInLeft = nums[0], sum = 0;
            for (int i = 1; i < nums.length - 1; i++){
                if (nums[i] > maxInLeft && nums[i] < minInRight[i + 1])  sum += 2;
                else if (nums[i] > nums[i - 1] && nums[i] < nums[i + 1]) sum += 1;
                maxInLeft = Math.max(maxInLeft, nums[i]);
            }
            return sum;
        }
    }
}
