public class MonotonicArray {
    /*
        An array is monotonic if it is either monotone increasing or monotone decreasing.
        An array nums is monotone increasing if for all i <= j, nums[i] <= nums[j]. An array nums is monotone decreasing if for all i <= j, nums[i] >= nums[j].
        Given an integer array nums, return true if the given array is monotonic, or false otherwise.

        Example 1:

        Input: nums = [1,2,2,3]
        Output: true
        Example 2:

        Input: nums = [6,5,4,4]
        Output: true
        Example 3:

        Input: nums = [1,3,2]
        Output: false


        Constraints:

        1 <= nums.length <= 105
        -105 <= nums[i] <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.isMonotonic(new int[]{6,5,4,4}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static boolean isMonotonic(int[] nums) {
            if (nums.length < 2) return true;
            int check = 0;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] > nums[i - 1]) {
                    if (check == 0) check = 1;
                    else if (check == -1) return false;
                } else if (nums[i] < nums[i - 1]) {
                    if (check == 0) check = -1;
                    else if (check == 1) return false;
                }
            }
            return true;
        }
    }
}
