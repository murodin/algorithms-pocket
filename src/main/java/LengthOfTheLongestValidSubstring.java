import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LengthOfTheLongestValidSubstring {
    /*
        You are given a string word and an array of strings forbidden.
        A string is called valid if none of its substrings are present in forbidden.
        Return the length of the longest valid substring of the string word.
        A substring is a contiguous sequence of characters in a string, possibly empty.

        Example 1:
        Input: word = "cbaaaabc", forbidden = ["aaa","cb"]
        Output: 4
        Explanation: There are 11 valid substrings in word: "c", "b", "a", "ba", "aa", "bc", "baa", "aab", "ab", "abc" and "aabc". The length of the longest valid substring is 4.
        It can be shown that all other substrings contain either "aaa" or "cb" as a substring.
        Example 2:
        Input: word = "leetcode", forbidden = ["de","le","e"]
        Output: 4
        Explanation: There are 11 valid substrings in word: "l", "t", "c", "o", "d", "tc", "co", "od", "tco", "cod", and "tcod". The length of the longest valid substring is 4.
        It can be shown that all other substrings contain either "de", "le", or "e" as a substring.


        Constraints:

        1 <= word.length <= 105
        word consists only of lowercase English letters.
        1 <= forbidden.length <= 105
        1 <= forbidden[i].length <= 10
        forbidden[i] consists only of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.longestValidSubstring("leetcode", List.of("de","le","e")));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int longestValidSubstring(String word, List<String> forbidden) {
            int len = 0;
            Set<String> all = new HashSet<>();
            for (String s : forbidden) {
                all.add(s);
                len = Math.max(len, s.length());
            }
            int n = word.length();
            int r = 0;
            for (int i = n - 1, right = n; right > r && i >= 0; --i) {
                int now = 0;
                StringBuilder temp = new StringBuilder();
                for (int j = i; j < right && j - i < len; ++j) {
                    temp.append(word.charAt(j));
                    if (all.contains(temp.toString())) {
                        right = j;
                        break;
                    }
                }
                r = Math.max(r, right - i);
            }
            return r;
        }
    }
}
