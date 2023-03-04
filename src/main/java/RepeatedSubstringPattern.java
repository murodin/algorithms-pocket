public class RepeatedSubstringPattern {
    /*
        Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
        Example 1.
        Input: s = "abab"
        Output: true
        Explanation: It is the substring "ab" twice.
        Example 2.
        Input: s = "aba"
        Output: false
        Example 3.
        Input: s = "abcabcabcabc"
        Output: true
        Explanation: It is the substring "abc" four times or the substring "abcabc" twice.


        Constraints:

        1 <= s.length <= 104
        s consists of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.repeatedSubstringPattern("abcabcabcabc"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static boolean repeatedSubstringPattern(String str) {
            String s = str + str;
            return s.substring(1, s.length() - 1).contains(str);
        }
    }
}
