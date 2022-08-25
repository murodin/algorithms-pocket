public class RotatedDigits {
    /*
        An integer x is a good if after rotating each digit individually by 180 degrees,
        we get a valid number that is different from x. Each digit must be rotated - we cannot choose to leave it alone.
        A number is valid if each digit remains a digit after rotation. For example:
        0, 1, and 8 rotate to themselves,
        2 and 5 rotate to each other (in this case they are rotated in a different direction, in other words, 2 or 5 gets mirrored),
        6 and 9 rotate to each other, and
        the rest of the numbers do not rotate to any other number and become invalid.
        Given an integer n, return the number of good integers in the range [1, n].

        Example 1.
        Input: n = 10
        Output: 4
        Explanation: There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
        Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
        Example 2.
        Input: n = 1
        Output: 0
        Example 3:

        Input: n = 2
        Output: 1

        Constraints:

        1 <= n <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.rotatedDigits(10));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int rotatedDigits(int n) {
            //0, 1, 8 self rotate
            //2, 5, 6, 9 rotate

            //case 1 only rotate
            //case 2 remove self roate + rotate
            //dp case : 0 = not rotate, 1 = self rotate, 2 = rotate

            int result = 0;
            int[] dp = new int[n + 1];

            //under 10 case
            for(int i = 0; i <= 9 && i <= n; i++) {
                if(i == 0 || i == 1 || i== 8) dp[i] = 1;
                else if(i == 2 || i == 5 || i == 6 || i == 9) {
                    dp[i] = 2;
                    result++;
                }
            }

            if(n < 10) return result;

            for(int i = 10; i <= n; i++) {
                int case1 = dp[i / 10];
                int case2 = dp[i % 10];

                if(case1 == 1 && case2 == 1) dp[i] = 1;
                else if(case1 >= 1 && case2 >= 1) {
                    dp[i] = 2;
                    result++;
                }
            }

            return result;
        }
    }
}
