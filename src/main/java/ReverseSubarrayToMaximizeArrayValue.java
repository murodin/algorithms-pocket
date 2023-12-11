public class ReverseSubarrayToMaximizeArrayValue {
    /*
        You are given an integer array nums. The value of this array is defined as the sum of |nums[i] - nums[i + 1]| for all 0 <= i < nums.length - 1.
        You are allowed to select any subarray of the given array and reverse it. You can perform this operation only once.
        Find maximum possible value of the final array.

        Example 1:
        Input: nums = [2,3,1,5,4]
        Output: 10
        Explanation: By reversing the subarray [3,1,5] the array becomes [2,5,1,3,4] whose value is 10.
        Example 2:
        Input: nums = [2,4,9,24,2,1,10]
        Output: 68


        Constraints:

        1 <= nums.length <= 3 * 104
        -105 <= nums[i] <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxValueAfterReverse(new int[]{2,3,1,5,4}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int maxValueAfterReverse(int[] A) {
            int total = 0, res = 0, min2 = 123456, max2 = -123456, n = A.length;
            for (int i = 0; i < n - 1; ++i) {
                int a = A[i], b = A[i + 1];
                total += Math.abs(a - b);
                res = Math.max(res, Math.abs(A[0] - b) - Math.abs(a - b));
                res = Math.max(res, Math.abs(A[n - 1] - a) - Math.abs(a - b));
                min2 = Math.min(min2, Math.max(a, b));
                max2 = Math.max(max2, Math.min(a, b));
            }
            return total + Math.max(res, (max2 - min2) * 2);
        }
    }
}
