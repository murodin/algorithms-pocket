public class CountElementsWithStrictlySmallerAndGreaterElements {
    /*
        Given an integer array nums, return the number of elements that have both a strictly smaller and a strictly greater element appear in nums.

        Example 1:

        Input: nums = [11,7,2,15]
        Output: 2
        Explanation: The element 7 has the element 2 strictly smaller than it and the element 11 strictly greater than it.
        Element 11 has element 7 strictly smaller than it and element 15 strictly greater than it.
        In total there are 2 elements having both a strictly smaller and a strictly greater element appear in nums.
        Example 2:

        Input: nums = [-3,3,3,90]
        Output: 2
        Explanation: The element 3 has the element -3 strictly smaller than it and the element 90 strictly greater than it.
        Since there are two elements with the value 3, in total there are 2 elements having both a strictly smaller and a strictly greater element appear in nums.


        Constraints:

        1 <= nums.length <= 100
        -105 <= nums[i] <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.countElements(new int[]{-3,3,3,90}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int countElements(int[] nums) {
            if (nums == null || nums.length == 0) return 0;
            int min = nums[0], minOccurrences = 1, max = nums[0], maxOccurrences = 1;
            for (int i = 1; i < nums.length; ++i) {
                if (nums[i] < min) {
                    min = nums[i];
                    minOccurrences = 1;
                } else if (nums[i] == min) {
                    minOccurrences++;
                }
                if (max < nums[i]) {
                    max = nums[i];
                    maxOccurrences = 1;
                } else if (max == nums[i]) {
                    maxOccurrences++;
                }
            }
            return min == max ? nums.length - minOccurrences : nums.length - minOccurrences - maxOccurrences;
        }
    }
}
