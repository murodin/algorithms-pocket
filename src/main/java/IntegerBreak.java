public class IntegerBreak {
    /*
        Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.

        Return the maximum product you can get.

        Example 1:

        Input: n = 2
        Output: 1
        Explanation: 2 = 1 + 1, 1 × 1 = 1.
        Example 2:

        Input: n = 10
        Output: 36
        Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.


        Constraints:

        2 <= n <= 58
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.integerBreak(10));
    }

    // Time: O(1)
    // Space: O(1)
    static class Solution {
        public static int integerBreak(int n) {
            int x = (int) Math.pow(3, n / 3);
            int y = (int) Math.pow(3, n / 3 - 1);

            if (n <= 5) {
                if (n == 2) return 1;
                if (n == 3) return 2;
                if (n % 2 == 0) return n / 2 * n / 2;
                else return 6;
            }
            if (n % 3 == 0) {
                return x;
            }
            if (n % 3 == 1) {
                return y * 4;
            }
            return x * 2;

        }
    }
}
