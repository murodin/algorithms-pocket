public class SmallestValueAfterReplacingWithSumOfPrimeFactors {
    /*
        You are given a positive integer n.

        Continuously replace n with the sum of its prime factors.

        Note that if a prime factor divides n multiple times, it should be included in the sum as many times as it divides n.
        Return the smallest value n will take on.



        Example 1:

        Input: n = 15
        Output: 5
        Explanation: Initially, n = 15.
        15 = 3 * 5, so replace n with 3 + 5 = 8.
        8 = 2 * 2 * 2, so replace n with 2 + 2 + 2 = 6.
        6 = 2 * 3, so replace n with 2 + 3 = 5.
        5 is the smallest value n will take on.
        Example 2:

        Input: n = 3
        Output: 3
        Explanation: Initially, n = 3.
        3 is the smallest value n will take on.


        Constraints:

        2 <= n <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.smallestValue(15));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int smallestValue(int n) {
            if(isPrime(n)) return n;
            int sum = getPrimeFactorSum(n);
            if(sum == n) return n;
            return smallestValue(sum);
        }

        public static boolean isPrime(int n) {
            if(n == 2) return true;
            for(int i = 2; i < Math.sqrt(n) + 1; i++) {
                if(n % i == 0) return false;
            }
            return true;
        }

        public static int getFirstPrimeFactor(int n) {
            if(isPrime(n)) return n;
            for(int i = 2; i < n; i++) {
                if(n % i == 0) return i;
            }
            return n;
        }

        public static int getPrimeFactorSum(int n) {
            int sum = 0;
            while(!isPrime(n)) {
                int m = getFirstPrimeFactor(n);
                n /= m;
                sum += m;
            }
            sum += n;
            return sum;
        }

    }
}
