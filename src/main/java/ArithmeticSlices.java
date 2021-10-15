public class ArithmeticSlices {

    /*
        An integer array is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
        For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
        Given an integer array nums, return the number of arithmetic subarrays of nums.
        A subarray is a contiguous subsequence of the array.

        Example 1.
        Input: nums = [1,2,3,4]
        Output: 3
        Explanation: We have 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4] and [1,2,3,4] itself.
        Example 2.
        Input: nums = [1]
        Output: 0
     */

    public static void main(String[] args) {
        int[] test = {1,2,3,4};
        System.out.println("Solution I:" + Solution_I.numberOfArithmeticSlices(test));
        System.out.println("Solution II:" + Solution_II.numberOfArithmeticSlices(test));
        System.out.println("Solution III:" + Solution_III.numberOfArithmeticSlices(test));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static int numberOfArithmeticSlices(int[] A) {
            int[] dp = new int[A.length];
            int res = 0;
            for(int i = 2; i < dp.length; i++) {

                if(A[i]-A[i-1] == A[i-1]-A[i-2]) {
                    dp[i]=1+dp[i-1];
                    res+=dp[i];
                }
            }
            return res;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static int numberOfArithmeticSlices(int[] A) {
            int dp = 0;
            int sum = 0;
            for (int i = 2; i < A.length; i++) {
                if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                    dp = 1 + dp;
                    sum += dp;
                } else dp = 0;
            }
            return sum;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_III {
        public static int numberOfArithmeticSlices(int[] A) {
            int count = 0;
            int sum = 0;
            for (int i = 2; i < A.length; i++) {
                if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                    count++;
                } else {
                    sum += (count + 1) * (count) / 2;
                    count = 0;
                }
            }
            return sum += count * (count + 1) / 2;
        }
    }
}
