public class MajorityElement {
    /*
        Given an array nums of size n, return the majority element.
        The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

        Example 1:
        Input: nums = [3,2,3]
        Output: 3
        Example 2:
        Input: nums = [2,2,1,1,1,2,2]
        Output: 2

        Constraints:

        n == nums.length
        1 <= n <= 5 * 104
        -109 <= nums[i] <= 109


        Follow-up: Could you solve the problem in linear time and in O(1) space?
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.majorityElement(new int[]{2,2,1,1,1,2,2}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int majorityElement(int[] nums) {
            int candidate = 0, count = 0;
            for (int num : nums) {
                if (count == 0) {
                    candidate = num;
                }
                count += (num == candidate) ? 1 : -1;
            }
            return candidate;
        }
    }
}
