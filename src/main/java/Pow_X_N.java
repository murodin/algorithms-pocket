public class Pow_X_N {
    /*
        Implement pow(x, n), which calculates x raised to the power n (i.e., xn).

        Example 1:

        Input: x = 2.00000, n = 10
        Output: 1024.00000
        Example 2:

        Input: x = 2.10000, n = 3
        Output: 9.26100
        Example 3:

        Input: x = 2.00000, n = -2
        Output: 0.25000
        Explanation: 2-2 = 1/22 = 1/4 = 0.25


        Constraints:

        -100.0 < x < 100.0
        -231 <= n <= 231-1
        n is an integer.
        Either x is not zero or n > 0.
        -104 <= xn <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.myPow(2.1, 3));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static double myPow(double x, int n) {
            return myPowHelper(x, n);
        }

        private static double myPowHelper(double x, int n) {
            if (x == 0) return 0;
            if (n == 0) return 1;

            double res = myPowHelper(x, n / 2);
            res *= res;
            if (n % 2 != 0) {
                return (n > 0) ? res * x : res / x;
            } else {
                return res;
            }
        }
    }
}
