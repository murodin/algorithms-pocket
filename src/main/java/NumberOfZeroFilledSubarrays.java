public class NumberOfZeroFilledSubarrays {
    /*
        Given an integer array nums, return the number of subarrays filled with 0.
        A subarray is a contiguous non-empty sequence of elements within an array.

        Example 1.
        Input: nums = [1,3,0,0,2,0,0,4]
        Output: 6
        Explanation:
        There are 4 occurrences of [0] as a subarray.
        There are 2 occurrences of [0,0] as a subarray.
        There is no occurrence of a subarray with a size more than 2 filled with 0. Therefore, we return 6.
        Example 2.
        Input: nums = [0,0,0,2,0,0]
        Output: 9
        Explanation:
        There are 5 occurrences of [0] as a subarray.
        There are 3 occurrences of [0,0] as a subarray.
        There is 1 occurrence of [0,0,0] as a subarray.
        There is no occurrence of a subarray with a size more than 3 filled with 0. Therefore, we return 9.
        Example 3:

        Input: nums = [2,10,2019]
        Output: 0
        Explanation: There is no subarray filled with 0. Therefore, we return 0.


        Constraints:

        1 <= nums.length <= 105
        -109 <= nums[i] <= 109
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.zeroFilledSubarray(new int[]{0,0,0,2,0,0}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static long zeroFilledSubarray(int[] nums) {
            long res = 0;
            for (int i = 0, j = 0; i < nums.length; ++i) {
                if (nums[i] != 0)
                    j = i + 1;
                res += i - j + 1;
            }
            return res;
        }
    }
}
