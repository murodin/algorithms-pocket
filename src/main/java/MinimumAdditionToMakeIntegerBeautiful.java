public class MinimumAdditionToMakeIntegerBeautiful {
    /*
        You are given two positive integers n and target.
        An integer is considered beautiful if the sum of its digits is less than or equal to target.
        Return the minimum non-negative integer x such that n + x is beautiful. The input will be generated such that it is always possible to make n beautiful.

        Example 1:

        Input: n = 16, target = 6
        Output: 4
        Explanation: Initially n is 16 and its digit sum is 1 + 6 = 7. After adding 4, n becomes 20 and digit sum becomes 2 + 0 = 2.
        It can be shown that we can not make n beautiful with adding non-negative integer less than 4.
        Example 2:

        Input: n = 467, target = 6
        Output: 33
        Explanation: Initially n is 467 and its digit sum is 4 + 6 + 7 = 17. After adding 33, n becomes 500 and digit sum becomes 5 + 0 + 0 = 5.
        It can be shown that we can not make n beautiful with adding non-negative integer less than 33.
        Example 3:

        Input: n = 1, target = 1
        Output: 0
        Explanation: Initially n is 1 and its digit sum is 1, which is already smaller than or equal to target.


        Constraints:

        1 <= n <= 1012
        1 <= target <= 150
        The input will be generated such that it is always possible to make n beautiful.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.makeIntegerBeautiful(467, 6));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        private static int sum_of_digits(long n) {
            int s = 0;
            while (n > 0) { s += n % 10; n /= 10; }
            return s;
        }

        public static long makeIntegerBeautiful(long n, int target) {
            long m = n;
            double p = 10;
            while (sum_of_digits(n) > target) {
                n = (long)(Math.ceil(n/p)*p);
                p *= 10;
            }
            return n - m;
        }
    }
}
