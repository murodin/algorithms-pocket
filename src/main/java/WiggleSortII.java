import java.util.Arrays;

public class WiggleSortII {
    /*
        Given an integer array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
        You may assume the input array always has a valid answer.
        Example 1:

        Input: nums = [1,5,1,1,6,4]
        Output: [1,6,1,5,1,4]
        Explanation: [1,4,1,5,1,6] is also accepted.
        Example 2:

        Input: nums = [1,3,2,2,3,1]
        Output: [2,3,1,3,1,2]


        Constraints:

        1 <= nums.length <= 5 * 104
        0 <= nums[i] <= 5000
        It is guaranteed that there will be an answer for the given input nums.


        Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
     */
    public static void main(String[] args) {
        int[] nums = new int[]{1,5,1,1,6,4};
        Solution.wiggleSort(nums);
        System.out.println("Solution: " + Arrays.toString(nums));
    }

    // Time: O(LogN)
    // Space: O(1)
    static class Solution {
        public static void wiggleSort(int[] nums) {
            int[] arr = nums.clone();
            Arrays.sort(arr);
            int n = nums.length;
            int i = (n - 1) >> 1, j = n - 1;
            for (int x = 0; x < n; ++x) {
                if (x % 2 == 0) {
                    nums[x] = arr[i--];
                } else {
                    nums[x] = arr[j--];
                }
            }
        }
    }
}
