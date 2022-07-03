public class WiggleSubsequence {
    /*
        A wiggle sequence is a sequence where the differences between successive numbers strictly alternate between positive and negative.
        The first difference (if one exists) may be either positive or negative.
         A sequence with one element and a sequence with two non-equal elements are trivially wiggle sequences.
        For example, [1, 7, 4, 9, 2, 5] is a wiggle sequence because the differences (6, -3, 5, -7, 3) alternate between positive and negative.
        In contrast, [1, 4, 7, 2, 5] and [1, 7, 4, 5, 5] are not wiggle sequences. The first is not because its first two differences are positive,
         and the second is not because its last difference is zero.
        A subsequence is obtained by deleting some elements (possibly zero) from the original sequence, leaving the remaining elements in their original order.

        Given an integer array nums, return the length of the longest wiggle subsequence of nums.

        Example 1.
        Input: nums = [1,7,4,9,2,5]
        Output: 6
        Explanation: The entire sequence is a wiggle sequence with differences (6, -3, 5, -7, 3).
        Example 2.
        Input: nums = [1,17,5,10,13,15,10,5,16,8]
        Output: 7
        Explanation: There are several subsequences that achieve this length.
        One is [1, 17, 10, 13, 10, 16, 8] with differences (16, -7, 3, -3, 6, -8).
        Example 3.
        Input: nums = [1,2,3,4,5,6,7,8,9]
        Output: 2


        Constraints:

        1 <= nums.length <= 1000
        0 <= nums[i] <= 1000
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.wiggleMaxLength(new int[] {1,17,5,10,13,15,10,5,16,8}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int wiggleMaxLength(int[] nums) {
            if(nums.length == 1) return 1; // if length is one answer will be one
            int prevSign = nums[1] - nums[0];
            int length = prevSign != 0 ? 2:1;// if we have some difference other than 0
            //then 1st and 2nd are a part of the sequence therefore length will be 2 otherwise element at 0th or 1st index will alone make a sequence
            for(int i=2;i<nums.length;i++){ //starting with 2nd index
                int currSign = nums[i] - nums[i-1];
                if((currSign > 0 && prevSign <= 0) || (currSign < 0 && prevSign >= 0)){ // if any of the two conditions that the wiggle sequence asks
                    // the equals in prevSign <= 0 && prevSign >=0 can only be used at the first iteration, otherwise prevSign will never be zero afterwards
                    length++; // increase the length
                    prevSign = currSign; // maintaining the prev sign
                }
            }
            return length;
        }
    }

}
