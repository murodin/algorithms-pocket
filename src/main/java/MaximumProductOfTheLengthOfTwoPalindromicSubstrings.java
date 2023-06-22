public class MaximumProductOfTheLengthOfTwoPalindromicSubstrings {
    /*
        You are given a 0-indexed string s and are tasked with finding two non-intersecting palindromic substrings of odd length such
        that the product of their lengths is maximized.
        More formally, you want to choose four integers i, j, k, l such that 0 <= i <= j < k <= l < s.length and both the substrings s[i...j] and s[k...l]
         are palindromes and have odd lengths. s[i...j] denotes a substring from index i to index j inclusive.
        Return the maximum possible product of the lengths of the two non-intersecting palindromic substrings.
        A palindrome is a string that is the same forward and backward. A substring is a contiguous sequence of characters in a string.

        Example 1:

        Input: s = "ababbb"
        Output: 9
        Explanation: Substrings "aba" and "bbb" are palindromes with odd length. product = 3 * 3 = 9.
        Example 2:

        Input: s = "zaaaxbbby"
        Output: 9
        Explanation: Substrings "aaa" and "bbb" are palindromes with odd length. product = 3 * 3 = 9.


        Constraints:

        2 <= s.length <= 105
        s consists of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxProduct("zaaaxbbby"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        private static final long base = 29L;
        private static final long mod = Integer.MAX_VALUE;

        private static boolean isPalindromic(int l, int r, long[] lh, long[] rh, long[] pow) {
            return lh(l, r, lh, pow) == rh(l, r, rh, pow);
        }

        private static long lh(int l, int r, long[] hash, long[] pow) {
            long ans = (hash[r] - hash[l] * pow[r - l]) % mod;
            if (ans < 0) ans += mod;
            return ans;
        }

        private static long rh(int l, int r, long[] hash, long[] pow) {
            long ans = (hash[l] - hash[r] * pow[r - l]) % mod;
            if (ans < 0) ans += mod;
            return ans;
        }

        public static long maxProduct(String s) {
            int n = s.length();

            long[] pow = new long[n + 1]; // pow[i] = BASE^i
            pow[0] = 1;
            for (int i = 1; i <= n; i++) pow[i] = pow[i - 1] * base % mod;

            long[] lh = new long[n + 1]; // hash[i] is hash value from str[0..i)
            for (int i = 1; i <= n; i++) lh[i] = (lh[i - 1] * base + s.charAt(i - 1) - 'a') % mod;

            long[] rh = new long[n + 1]; // hash[i] is hash value from str[i..n)
            for (int i = n - 1; i >= 0; i--) rh[i] = (rh[i + 1] * base + s.charAt(i) - 'a') % mod;

            int[] left = new int[n];
            for (int i = 0, max = 1; i < n; i++) {
                if (max < i && isPalindromic(i - max - 1, i + 1, lh, rh, pow)) max += 2;
                left[i] = max;
            }

            int[] right = new int[n];
            for (int i = n - 1, max = 1; i >= 0; i--) {
                if (i + max + 2 <= n && isPalindromic(i, i + max + 2, lh, rh, pow)) max += 2;
                right[i] = max;
            }

            long ans = 1;
            for (int i = 1; i < n; i++) ans = Math.max(ans, (long) left[i - 1] * right[i]);
            return ans;
        }
    }
}
