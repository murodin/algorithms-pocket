public class RunningSumOf1dArray {

    /*
        Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
        Return the running sum of nums.

        Example 1:
        Input: nums = [1,2,3,4]
        Output: [1,3,6,10]
        Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
     */

    public static void main(String[] args) {
        int[] testNums = {1,2,3,4};
        System.out.println("Result: " + Solution.runningSum(testNums));
    }

    static class Solution {
        // Time: O(N)
        // Space: O(1)
        public static int[] runningSum(int[] nums) {
            for(int i = 1; i<nums.length; i++) {
                nums[i] += nums[i-1];
            }
            return nums;
        }
    }
}
