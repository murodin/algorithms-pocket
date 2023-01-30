public class StrangePrinter {
    /*
        There is a strange printer with the following two special properties:
        The printer can only print a sequence of the same character each time.
        At each turn, the printer can print new characters starting from and ending at any place and will cover the original existing characters.
        Given a string s, return the minimum number of turns the printer needed to print it.

        Example 1.
        Input: s = "aaabbb"
        Output: 2
        Explanation: Print "aaa" first and then print "bbb".
        Example 2.
        Input: s = "aba"
        Output: 2
        Explanation: Print "aaa" first and then print "b" from the second place of the string, which will cover the existing character 'a'.

        Constraints:

        1 <= s.length <= 100
        s consists of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.strangePrinter("aaabbb"));
    }

    // Time: O(N^3)
    // Space:O(N^2)
    static class Solution {
        public static int strangePrinter(String s) {
            // Memoization ----- Approach - 1
            // Integer[][] dp = new Integer[s.length()][s.length()];
            // return solveMemo(s, 0, s.length() - 1, dp);

            // Tabulation ------ Approach - 2
            int[][] dp = new int[s.length()][s.length()];
            for(int i = dp.length - 1; i >= 0; i--){
                for(int j = 0; j < dp.length; j++){
                    if(i > j) {
                        continue;
                    } else if(i == j) {
                        dp[i][j] = 1;
                    } else {
                        int min = Integer.MAX_VALUE;
                        for(int k = i; k < j; k++) {
                            min = Math.min(min, dp[i][k] + dp[k + 1][j]);
                        }
                        if(s.charAt(i) == s.charAt(j)) {
                            min--;
                        }
                        dp[i][j] = min;
                    }
                }
            }
            return dp[0][s.length() - 1];
        }
        public int solveMemo(String s, int i, int j, Integer[][] dp){
            if(i == j){
                return 1;
            }
            if(dp[i][j] != null){
                return dp[i][j];
            }
            int ans = Integer.MAX_VALUE;
            for(int k = i; k < j; k++){
                ans = Math.min(ans, solveMemo(s, i, k, dp) + solveMemo(s, k + 1, j, dp));
            }
            return dp[i][j] = s.charAt(i) == s.charAt(j) ? ans - 1 : ans;
        }
    }
}
