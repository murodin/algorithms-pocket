import java.util.Arrays;

public class SortColors {
    /*
        Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent,
         with the colors in the order red, white, and blue.
        We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.
        You must solve this problem without using the library's sort function.

        Example 1.
        Input: nums = [2,0,2,1,1,0]
        Output: [0,0,1,1,2,2]
        Example 2.
        Input: nums = [2,0,1]
        Output: [0,1,2]

        Constraints:

        n == nums.length
        1 <= n <= 300
        nums[i] is either 0, 1, or 2.

        Follow up: Could you come up with a one-pass algorithm using only constant extra space?
     */
    public static void main(String[] args) {
        int[] colors = new int[] {2,0,2,1,1,0};
        Solution.sortColors(colors);
        System.out.println("Solution: " + Arrays.toString(colors));
    }

    // Time: O(N)
    // Space: O(1)
   static class Solution {
        public static void sortColors(int[] nums) {
            int[] arr = new int[3];
            for (int i : nums){
                arr[i]++;
            }
            int i = 0;
            while (arr[0]>0){
                nums[i++] = 0;
                arr[0]--;
            }
            while (arr[1]>0){
                nums[i++] = 1;
                arr[1]--;
            }
            while (arr[2]>0){
                nums[i++] = 2;
                arr[2]--;
            }
        }
    }
}
