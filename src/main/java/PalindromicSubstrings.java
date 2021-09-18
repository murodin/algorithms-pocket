public class PalindromicSubstrings {

    /*
        Given a string s, return the number of palindromic substrings in it.
        A string is a palindrome when it reads the same backward as forward.
        A substring is a contiguous sequence of characters within the string.

        Example 1.
        Input: s = "abc"
        Output: 3
        Explanation: Three palindromic strings: "a", "b", "c".
        Example 2.
        Input: s = "aaa"
        Output: 6
        Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
     */

    public static void main(String[] args) {
        String test = "abc";
        System.out.println("Solution: " + Solution.countSubstrings(test));
        System.out.println("Solution II: " + Solution_II.countSubstrings(test));
    }


    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        public static int countSubstrings(String s) {
            int n = s.length(), ans = 0;

            if (n <= 0)
                return 0;

            boolean[][] dp = new boolean[n][n];

            // Base case: single letter substrings
            for (int i = 0; i < n; ++i, ++ans)
                dp[i][i] = true;

            // Base case: double letter substrings
            for (int i = 0; i < n - 1; ++i) {
                dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
                ans += (dp[i][i + 1] ? 1 : 0);
            }

            // All other cases: substrings of length 3 to n
            for (int len = 3; len <= n; ++len)
                for (int i = 0, j = i + len - 1; j < n; ++i, ++j) {
                    dp[i][j] = dp[i + 1][j - 1] && (s.charAt(i) == s.charAt(j));
                    ans += (dp[i][j] ? 1 : 0);
                }

            return ans;
        }
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution_II {
        static public int countSubstrings(String s) {
            if(s.length() == 0) return 0;
            int n = s.length();
            int res=0;
            char[] c = s.toCharArray();

            for(int i = 0; i < n; i++){
                //Odd And even Lengths (Center for odd --> i,i)
                // Center for even --> i, i+1
                res+=isPalindrome(i,i,c);
                res+=isPalindrome(i,i+1,c);
            }
            return res;
        }

        static public int isPalindrome(int s, int e, char[] c){
            int count = 0;
            while(s >= 0 && e < c.length && c[s--]==c[e++])
                count++;

            return count;
        }
    }
}
