public class UglyNumber {
    /*
        An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
        Given an integer n, return true if n is an ugly number.

        Example 1:

        Input: n = 6
        Output: true
        Explanation: 6 = 2 × 3
        Example 2:

        Input: n = 1
        Output: true
        Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
        Example 3:

        Input: n = 14
        Output: false
        Explanation: 14 is not ugly since it includes the prime factor 7.


        Constraints:

        -231 <= n <= 231 - 1
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.isUgly(6));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static boolean isUgly(int num)  {
            if (num == 0) return false;
            for (int i = 2; i <= 5; i += i-1)   // a small trick to iterate 2,3,5 (and skip 4)
                while (num % i == 0)
                    num /= i;
            return num == 1;
        }
    }
}
