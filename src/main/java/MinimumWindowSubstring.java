import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    /*
        Given two strings s and t of lengths m and n respectively,
        return the minimum window substring of s such that every character in t (including duplicates) is included in the window.
        If there is no such substring, return the empty string "".
        The testcases will be generated such that the answer is unique.

        A substring is a contiguous sequence of characters within the string.

        Example 1.
        Input: s = "ADOBECODEBANC", t = "ABC"
        Output: "BANC"
        Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
        Example 2.
        Input: s = "a", t = "a"
        Output: "a"
        Explanation: The entire string s is the minimum window.
        Example 3.
        Input: s = "a", t = "aa"
        Output: ""
        Explanation: Both 'a's from t must be included in the window.
        Since the largest window of s only has one 'a', return empty string.


        Constraints:

        m == s.length
        n == t.length
        1 <= m, n <= 105
        s and t consist of uppercase and lowercase English letters.


        Follow up: Could you find an algorithm that runs in O(m + n) time?
     */

    public static void main(String[] args) {
        System.out.println("Solution I: " + Solution.minWindow("ADOBECODEBANC", "ABC"));
    }

    // Time: O(N + M) = O(M); M >= N.
    // Space: O(N)
    static class Solution {
        public static String minWindow(String s, String t) {
            int m = s.length(), n = t.length();
            if (n > m) return "";

            Map<Character, Integer> freq = new HashMap();

            // 'count' is the number of total chars in 't'.
            int count = 0;

            for (Character c : t.toCharArray()) {
                if (!freq.containsKey(c)) freq.put(c, 0);
                freq.put(c, freq.get(c) + 1);
                count++;
            }

            // window initially consists of single character.
            int left = 0, right = 0;

            // the longest valid substring is the full string 's'.
            // Setting 'len' to max_len indicates an invalid case, which allows us to know if a valid substring exists.
            int start = 0, len = Integer.MAX_VALUE;

            while (right < s.length()) {

                // If the character is not in 'freq', it cannot count towards forming 't'.
                // But, in order to expand the window, we must include it anyway.
                if (!freq.containsKey(s.charAt(right))) { right++; continue; }

                // If char's frequency is > 0, then it is required to form 't'.
                // Thus, including it allows us to decrement 'count'.
                // However, if its frequency is = 0, then this char is just a duplicate.
                // Thus, it isn't necessary to decrement 'count', because it isn't a required char.
                if (freq.get(s.charAt(right)) > 0) count--;
                freq.put(s.charAt(right), freq.get(s.charAt(right)) - 1);

                while (count == 0) {

                    if (right - left + 1 < len) { start = left; len = right - left + 1; }

                    if (!freq.containsKey(s.charAt(left))) { left++; continue; }

                    // If, by excluding s.charAt(left), the frequency of that char is > 0,
                    // then it was a required char in 't', so we must increment 'count'
                    // to indicate that this is no longer a valid window.
                    // Note, however, that if its freq was < 0, then it was just a duplicate,
                    // which means we wouldn't have to increment 'count'.
                    freq.put(s.charAt(left), freq.get(s.charAt(left)) + 1);
                    if (freq.get(s.charAt(left)) > 0) count++;
                    left++;

                }
                right++;

            }
            return len == Integer.MAX_VALUE ? "" : s.substring(start, start + len);

        }
    }
}
