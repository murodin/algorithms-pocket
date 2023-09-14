public class CheckIfItIsAGoodArray {
    /*
        Given an array nums of positive integers. Your task is to select some subset of nums, multiply each element by an integer and add all these numbers.
        The array is said to be good if you can obtain a sum of 1 from the array by any possible subset and multiplicand.
        Return True if the array is good otherwise return False.

        Example 1:

        Input: nums = [12,5,7,23]
        Output: true
        Explanation: Pick numbers 5 and 7.
        5*3 + 7*(-2) = 1
        Example 2:

        Input: nums = [29,6,10]
        Output: true
        Explanation: Pick numbers 29, 6 and 10.
        29*1 + 6*(-3) + 10*(-1) = 1
        Example 3:

        Input: nums = [3,6]
        Output: false


        Constraints:

        1 <= nums.length <= 10^5
        1 <= nums[i] <= 10^9
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.isGoodArray(new int[]{12,5,7,23}));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static boolean isGoodArray(int[] A) {
            int x = A[0], y;
            for (int a: A) {
                while (a > 0) {
                    y = x % a;
                    x = a;
                    a = y;
                }
            }
            return x == 1;
        }
    }
}
