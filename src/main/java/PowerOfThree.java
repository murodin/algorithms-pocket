public class PowerOfThree {

    /*
        Given an integer n, return true if it is a power of three. Otherwise, return false.
        An integer n is a power of three, if there exists an integer x such that n == 3x.

        Example 1:

        Input: n = 27
        Output: true
     */

    public static void main(String[] args) {
        int trueTest = 27, falseTest = 36;
        System.out.println("Passed 36:" + Solution.isPowerOfThree(trueTest) + " Failed 23:" + Solution.isPowerOfThree(falseTest));
    }

    static class Solution {
        // Time: O(log(Base 3)(N))
        // Space: O(1)
        public static boolean isPowerOfThree(int n) {
            while (n>=3) {
                if(n%3!=0) return false;
                n /= 3;
            }
            return n == 1;
        }
    }
}
