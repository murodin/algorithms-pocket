import java.util.Arrays;

public class UniqueLengthThreePalindromicSubsequences {
    /*
        Given a string s, return the number of unique palindromes of length three that are a subsequence of s.
        Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.
        A palindrome is a string that reads the same forwards and backwards.
        A subsequence of a string is a new string generated from the original string with some characters (can be none)
        deleted without changing the relative order of the remaining characters.

        For example, "ace" is a subsequence of "abcde".


        Example 1:

        Input: s = "aabca"
        Output: 3
        Explanation: The 3 palindromic subsequences of length 3 are:
        - "aba" (subsequence of "aabca")
        - "aaa" (subsequence of "aabca")
        - "aca" (subsequence of "aabca")
        Example 2:

        Input: s = "adc"
        Output: 0
        Explanation: There are no palindromic subsequences of length 3 in "adc".
        Example 3:

        Input: s = "bbcbaba"
        Output: 4
        Explanation: The 4 palindromic subsequences of length 3 are:
        - "bbb" (subsequence of "bbcbaba")
        - "bcb" (subsequence of "bbcbaba")
        - "bab" (subsequence of "bbcbaba")
        - "aba" (subsequence of "bbcbaba")


        Constraints:

        3 <= s.length <= 105
        s consists of only lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.countPalindromicSubsequence("bbcbaba"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int countPalindromicSubsequence(String inputString) {
            int[] firstIndex = new int[26];
            int[] lastIndex = new int[26];
            int result = 0;
            Arrays.fill(firstIndex, Integer.MAX_VALUE);
            for (int i = 0; i < inputString.length(); ++i) {
                firstIndex[inputString.charAt(i) - 'a'] = Math.min(firstIndex[inputString.charAt(i) - 'a'], i);
                lastIndex[inputString.charAt(i) - 'a'] = i;
            }
            for (int i = 0; i < 26; ++i)
                if (firstIndex[i] < lastIndex[i])
                    result += inputString.substring(firstIndex[i] + 1, lastIndex[i]).chars().distinct().count();
            return result;
        }
    }
}
