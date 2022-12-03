public class LastSubstringInLexicographicalOrder {
    /*
        Given a string s, return the last substring of s in lexicographical order.

        Example 1.
        Input: s = "abab"
        Output: "bab"
        Explanation: The substrings are ["a", "ab", "aba", "abab", "b", "ba", "bab"]. The lexicographically maximum substring is "bab".
        Example 2.
        Input: s = "leetcode"
        Output: "tcode"


        Constraints:

        1 <= s.length <= 4 * 105
        s contains only lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.lastSubstring("leetcode"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static String lastSubstring(String s) {
            int i = 0, j = 1, offset = 0, len = s.length();
            while (i + offset < len && j + offset < len) {
                char c = s.charAt(i + offset), d = s.charAt(j + offset);
                if (c == d) {
                    ++offset;
                } else {
                    if (c < d)  { i += offset + 1; } // chars in [i, ..., i + offset] <= charAt(i) == charAt(j)
                    else { j += offset + 1; } // c > d, chars in [j, ..., j + offset] <= charAt(i) == charAt(j)
                    if (i == j) { ++j; } // avoid duplicate start indices.
                    offset = 0; // reset offset to 0.
                }
            }
            return s.substring(i);
        }
    }
}
