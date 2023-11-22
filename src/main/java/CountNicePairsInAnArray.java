import java.util.HashMap;

public class CountNicePairsInAnArray {
    /*
        You are given an array nums that consists of non-negative integers. Let us define rev(x) as the reverse of the non-negative integer x.
        For example, rev(123) = 321, and rev(120) = 21. A pair of indices (i, j) is nice if it satisfies all the following conditions:

        0 <= i < j < nums.length
        nums[i] + rev(nums[j]) == nums[j] + rev(nums[i])
        Return the number of nice pairs of indices. Since that number can be too large, return it modulo 109 + 7.

        Example 1:
        Input: nums = [42,11,1,97]
        Output: 2
        Explanation: The two pairs are:
         - (0,3) : 42 + rev(97) = 42 + 79 = 121, 97 + rev(42) = 97 + 24 = 121.
         - (1,2) : 11 + rev(1) = 11 + 1 = 12, 1 + rev(11) = 1 + 11 = 12.
        Example 2:
        Input: nums = [13,10,35,24,76]
        Output: 4


        Constraints:

        1 <= nums.length <= 105
        0 <= nums[i] <= 109
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.countNicePairs(new int[]{13,10,35,24,76}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        private static int rev(int n) {
            var rev = 0;
            while (n > 0) {
                rev = (rev * 10) + (n % 10);
                n /= 10;
            }
            return rev;
        }
        public static int countNicePairs(int[] nums) {
            var map = new HashMap<Integer, Integer>();
            var ans = 0;
            for (var n : nums) {
                var rev = rev(n);
                var a = map.getOrDefault(n - rev, 0);
                ans = (ans + a) % 1000000007;
                map.put(n - rev, a + 1);
            }
            return ans;
        }
    }
}
