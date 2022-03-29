public class FindTheDuplicateNumber {

    /*
        Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
        There is only one repeated number in nums, return this repeated number.
        You must solve the problem without modifying the array nums and uses only constant extra space.

        Example 1.
        Input: nums = [1,3,4,2,2]
        Output: 2
        Example 2.
        Input: nums = [3,1,3,4,2]
        Output: 3

        Constraints:

        1 <= n <= 105
        nums.length == n + 1
        1 <= nums[i] <= n
        All the integers in nums appear only once except for precisely one integer which appears two or more times.


        Follow up:

        How can we prove that at least one duplicate number must exist in nums?
        Can you solve the problem in linear runtime complexity?
     */

    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
        System.out.println("Solution I: " + Solution_I.findDuplicate(nums));
        System.out.println("Solution II: " + Solution_II.findDuplicate(nums));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_I {
        public static int findDuplicate(int[] nums) {
            for(int i = 0; i < nums.length; i++) {
                int idx = Math.abs(nums[i]);
                if(nums[idx] < 0) return idx;
                nums[idx] = -nums[idx];
            }
            return -1;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static int findDuplicate(int[] nums) {
            int slow = 0;
            int fast = 0;
            do {
                slow = nums[slow];
                fast = nums[nums[fast]];
            } while (slow != fast);

            slow = 0;
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[fast];
            }

            return slow;
        }
    }
}
