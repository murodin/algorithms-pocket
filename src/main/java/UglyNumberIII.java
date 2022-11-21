public class UglyNumberIII {
    /*
        An ugly number is a positive integer that is divisible by a, b, or c.
        Given four integers n, a, b, and c, return the nth ugly number.

        Example 1.
        Input: n = 3, a = 2, b = 3, c = 5
        Output: 4
        Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.
        Example 2.
        Input: n = 4, a = 2, b = 3, c = 4
        Output: 6
        Explanation: The ugly numbers are 2, 3, 4, 6, 8, 9, 10, 12... The 4th is 6.
        Example 3.
        Input: n = 5, a = 2, b = 11, c = 13
        Output: 10
        Explanation: The ugly numbers are 2, 4, 6, 8, 10, 11, 12, 13... The 5th is 10.


        Constraints:

        1 <= n, a, b, c <= 109
        1 <= a * b * c <= 1018
        It is guaranteed that the result will be in range [1, 2 * 109].
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.nthUglyNumber(3, 2, 3, 5));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int nthUglyNumber(int n, int a, int b, int c) {
            int lo = 1, hi = (int)2e9;
            long ab = lcm(a, b), ac = lcm(a, c), bc = lcm(b, c), abc = lcm(ab, c);
            while(lo < hi){
                int mid = lo + (hi - lo)/2;
                long count = ((long) mid / a + mid / b + mid / c) - mid / ab - mid / ac - mid / bc + mid / abc;
                if (count >= n)
                    hi = mid;
                else
                    lo = mid + 1;
            }
            return lo;
        }

        private static long gcd(long a, long b){
            return b == 0? a : gcd(b, a % b);
        }

        private static long lcm(long a, long b){
            return a * b / gcd(a, b);
        }
    }
}
