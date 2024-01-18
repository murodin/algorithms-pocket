public class CountAnagrams {
    /*
        You are given a string s containing one or more words. Every consecutive pair of words is separated by a single space ' '.
        A string t is an anagram of string s if the ith word of t is a permutation of the ith word of s.
        For example, "acb dfe" is an anagram of "abc def", but "def cab" and "adc bef" are not.
        Return the number of distinct anagrams of s. Since the answer may be very large, return it modulo 109 + 7.

        Example 1:
        Input: s = "too hot"
        Output: 18
        Explanation: Some of the anagrams of the given string are "too hot", "oot hot", "oto toh", "too toh", and "too oht".
        Example 2:
        Input: s = "aa"
        Output: 1
        Explanation: There is only one anagram possible for the given string.


        Constraints:

        1 <= s.length <= 105
        s consists of lowercase English letters and spaces ' '.
        There is single space between consecutive words.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.countAnagrams("too hot"));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int countAnagrams(String s) {
            final int mod = 1_000_000_007;
            int n = s.length();
            long[] fact = new long[n+1], ifact = new long[n+1], inv = new long[n+1];
            fact[0] = ifact[0] = inv[0] = inv[1] = 1;
            for (int x = 1; x <= n; ++x) {
                if (x >= 2) inv[x] = mod - mod/x * inv[mod%x] % mod;
                fact[x] = fact[x-1] * x % mod;
                ifact[x] = ifact[x-1] * inv[x] % mod;
            }
            long ans = 1;
            for (var buff : s.split(" ")) {
                ans = ans * fact[buff.length()] % mod;
                int[] freq = new int[26];
                for (var ch : buff.toCharArray())  ++freq[ch-'a'];
                for (var x : freq) ans = ans * ifact[x] % mod;
            }
            return (int) ans;
        }
    }
}
