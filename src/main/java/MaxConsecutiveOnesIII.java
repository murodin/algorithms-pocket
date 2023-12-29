public class MaxConsecutiveOnesIII {
    /*
        Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.

        Example 1:
        Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
        Output: 6
        Explanation: [1,1,1,0,0,1,1,1,1,1,1]
        Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
        Example 2:
        Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
        Output: 10
        Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
        Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.


        Constraints:

        1 <= nums.length <= 105
        nums[i] is either 0 or 1.
        0 <= k <= nums.length
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1}, 3));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int longestOnes(int[] A, int K) {
            int i = 0, j;
            for (j = 0; j < A.length; ++j) {
                if (A[j] == 0) K--;
                if (K < 0 && A[i++] == 0) K++;
            }
            return j - i;
        }
    }
}
