public class MaximumValueAtAGivenIndexInABoundedArray {
    /*
        You are given three positive integers: n, index, and maxSum. You want to construct an array nums (0-indexed) that satisfies the following conditions:

        nums.length == n
        nums[i] is a positive integer where 0 <= i < n.
        abs(nums[i] - nums[i+1]) <= 1 where 0 <= i < n-1.
        The sum of all the elements of nums does not exceed maxSum.
        nums[index] is maximized.
        Return nums[index] of the constructed array.

        Note that abs(x) equals x if x >= 0, and -x otherwise.



        Example 1:

        Input: n = 4, index = 2,  maxSum = 6
        Output: 2
        Explanation: nums = [1,2,2,1] is one array that satisfies all the conditions.
        There are no arrays that satisfy all the conditions and have nums[2] == 3, so 2 is the maximum nums[2].
        Example 2:

        Input: n = 6, index = 1,  maxSum = 10
        Output: 3


        Constraints:

        1 <= n <= maxSum <= 109
        0 <= index < n
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxValue(6, 1, 10));
    }

    // Time: O(LogN)
    // Space: O(1)
    static class Solution {
        public static int maxValue(int n, int index, int maxSum) {
            int left = 0, right = maxSum - n;
            while (left < right) {
                int middle = (left + right + 1) / 2;
                if (isPossible(n, index, maxSum - n, middle))
                    left = middle;
                else
                    right = middle - 1;
            }
            return left + 1;
        }

        private static boolean isPossible(int n, int index, int maxSum, int value) {
            int leftValue = Math.max(value - index, 0);
            int rightValue = Math.max(value - ((n - 1) - index), 0);
            long sumBefore = (long) (value + leftValue) * (value - leftValue + 1) / 2;
            long sumAfter = (long) (value + rightValue) * (value - rightValue + 1) / 2;
            return sumBefore + sumAfter - value <= maxSum;
        }
    }
}
