import java.util.Arrays;

public class CountPalindromicSubsequences {
    /*
        Given a string of digits s, return the number of palindromic subsequences of s having length 5. Since the answer may be very large, return it modulo 109 + 7.
        Note:
        A string is palindromic if it reads the same forward and backward.
        A subsequence is a string that can be derived from another string by deleting some or no characters without changing the order of the remaining characters.

        Example 1:
        Input: s = "103301"
        Output: 2
        Explanation:
        There are 6 possible subsequences of length 5: "10330","10331","10301","10301","13301","03301".
        Two of them (both equal to "10301") are palindromic.
        Example 2:
        Input: s = "0000000"
        Output: 21
        Explanation: All 21 subsequences are "00000", which is palindromic.
        Example 3:
        Input: s = "9999900000"
        Output: 2
        Explanation: The only two palindromic subsequences are "99999" and "00000".


        Constraints:

        1 <= s.length <= 104
        s consists of digits.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.countPalindromes("0000000"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int countPalindromes(String s) {
            int mod = 1000_000_007;
            int n = s.length();
            int ans = 0;
            int[] cnts = new int[10];
            int[][][] pre = new int[n][10][10];
            int[][][] suf = new int[n][10][10];
            for (int i = 0; i < n; i++) {
                int c = s.charAt(i) - '0';
                if (i > 0)
                    for (int j = 0; j < 10; j++)
                        for (int k = 0; k < 10; k++) {
                            pre[i][j][k] = pre[i - 1][j][k];
                            if (k == c) pre[i][j][k] += cnts[j];
                        }
                cnts[c]++;
            }
            Arrays.fill(cnts, 0);
            for (int i = n - 1; i >= 0; i--) {
                int c = s.charAt(i) - '0';
                if (i < n - 1)
                    for (int j = 0; j < 10; j++)
                        for (int k = 0; k < 10; k++) {
                            suf[i][j][k] = suf[i + 1][j][k];
                            if (k == c) suf[i][j][k] += cnts[j];
                        }
                cnts[c]++;
            }
            for (int i = 2; i < n - 2; i++)
                for (int j = 0; j < 10; j++)
                    for (int k = 0; k < 10; k++)
                        ans = (int)((ans + (long) pre[i - 1][j][k] * suf[i + 1][j][k]) % mod);
            return ans;
        }
    }
}
