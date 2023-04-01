public class WildcardMatching {
    /*
        Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*' where:
        '?' Matches any single character.
        '*' Matches any sequence of characters (including the empty sequence).
        The matching should cover the entire input string (not partial).

        Example 1.
        Input: s = "aa", p = "a"
        Output: false
        Explanation: "a" does not match the entire string "aa".
        Example 2.
        Input: s = "aa", p = "*"
        Output: true
        Explanation: '*' matches any sequence.
        Example 3.
        Input: s = "cb", p = "?a"
        Output: false
        Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.


        Constraints:

        0 <= s.length, p.length <= 2000
        s contains only lowercase English letters.
        p contains only lowercase English letters, '?' or '*'.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.isMatch("aa", "*"));
    }

    // Time: O(MN)
    // Space: O(1)
    static class Solution {
        public static boolean isMatch(String s, String p) {
            if(s == null || p == null) {
                assert s != null;
                return false;
            }
            int m = s.length();
            int n = p.length();
            boolean[][] dp = new boolean[m+1][n+1];
            dp[0][0] = true;
            for(int i = 0; i < n ;i++){
                if(p.charAt(i) == '*')
                    dp[0][i+1] = dp[0][i];
            }

            for(int i = 1; i < m+1; i++) {
                for(int j = 1; j < n+1; j++)  {
                    if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?')
                        dp[i][j] = dp[i-1][j-1];
                    else if(p.charAt(j-1) == '*')
                        dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
            return dp[m][n];
        }
    }
}
