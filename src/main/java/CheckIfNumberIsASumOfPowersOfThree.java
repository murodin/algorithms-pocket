public class CheckIfNumberIsASumOfPowersOfThree {
    /*
        Given an integer n, return true if it is possible to represent n as the sum of distinct powers of three. Otherwise, return false.
        An integer y is a power of three if there exists an integer x such that y == 3x.

        Example 1.
        Input: n = 12
        Output: true
        Explanation: 12 = 3^1 + 3^2
        Example 2.
        Input: n = 91
        Output: true
        Explanation: 91 = 3^0 + 3^2 + 3^4
        Example 3.
        Input: n = 21
        Output: false


        Constraints:

        1 <= n <= 107
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.checkPowersOfThree(91));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static boolean checkPowersOfThree(int n) {
            // we need to apply to loop till n is greater than equal to 1, if n is 1 then it's actually power of 3.
            while(n>=1) {
                if(n%3>1) return false;
                n/=3;
            }
            return true;
        }
    }
}
