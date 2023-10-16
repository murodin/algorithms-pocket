public class NumberOfWaysToStayInTheSamePlaceAfterSomeSteps {
    /*
        You have a pointer at index 0 in an array of size arrLen. At each step, you can move 1 position to the left,
        1 position to the right in the array, or stay in the same place (The pointer should not be placed outside the array at any time).

        Given two integers steps and arrLen, return the number of ways such that your pointer is still at index 0 after exactly steps steps.
        Since the answer may be too large, return it modulo 109 + 7.



        Example 1:

        Input: steps = 3, arrLen = 2
        Output: 4
        Explanation: There are 4 differents ways to stay at index 0 after 3 steps.
        Right, Left, Stay
        Stay, Right, Left
        Right, Stay, Left
        Stay, Stay, Stay
        Example 2:

        Input: steps = 2, arrLen = 4
        Output: 2
        Explanation: There are 2 differents ways to stay at index 0 after 2 steps
        Right, Left
        Stay, Stay
        Example 3:

        Input: steps = 4, arrLen = 2
        Output: 8


        Constraints:

        1 <= steps <= 500
        1 <= arrLen <= 106
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numWays(3, 2));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        public static int numWays(int steps, int arrLen) {
            int m = (int) 1e9 + 7;
            arrLen = Math.min(arrLen, steps);
            int[][] dp = new int[arrLen][steps + 1];
            dp[0][0] = 1;
            int ans;
            for (int remain = 1; remain <= steps; remain++) {
                for (int curr = 0; curr<arrLen; curr++) {
                    ans = dp[curr][remain - 1];
                    if (curr > 0) {
                        ans = (ans + dp[curr - 1][remain - 1]) % m;
                    }

                    if (curr < arrLen - 1) {
                        ans = (ans + dp[curr + 1][remain - 1]) % m;
                    }

                    dp[curr][remain] = ans;
                }
            }
            return dp[0][steps];
        }
    }
}
