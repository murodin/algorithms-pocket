public class PowerOfFour {
    /*
        Given an integer n, return true if it is a power of four. Otherwise, return false.
        An integer n is a power of four, if there exists an integer x such that n == 4x.

        Example 1.
        Input: n = 16
        Output: true
        Example 2.
        Input: n = 5
        Output: false
        Example 3.
        Input: n = 1
        Output: true


        Constraints:

        -231 <= n <= 231 - 1


        Follow up: Could you solve it without loops/recursion?
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.isPowerOfFour(16));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static boolean isPowerOfFour(int n) {
            if(n==0) return false;
            if(n==1) return true;
            while(n%4==0) {
                n/=4;
            }
            return n==1;
        }
    }
}
