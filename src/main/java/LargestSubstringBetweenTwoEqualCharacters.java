public class LargestSubstringBetweenTwoEqualCharacters {
    /*
        Given a string s, return the length of the longest substring between two equal characters, excluding the two characters. If there is no such substring return -1.
        A substring is a contiguous sequence of characters within a string.

        Example 1:
        Input: s = "aa"
        Output: 0
        Explanation: The optimal substring here is an empty substring between the two 'a's.
        Example 2:
        Input: s = "abca"
        Output: 2
        Explanation: The optimal substring here is "bc".
        Example 3:
        Input: s = "cbzxy"
        Output: -1
        Explanation: There are no characters that appear twice in s.


        Constraints:

        1 <= s.length <= 300
        s contains only lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxLengthBetweenEqualCharacters("abca"));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static int maxLengthBetweenEqualCharacters(String s) {
            int ans = -1;
            for (int left = 0; left < s.length(); left++) {
                for (int right = left + 1; right < s.length(); right++) {
                    if (s.charAt(left) == s.charAt(right)) {
                        ans = Math.max(ans, right - left - 1);
                    }
                }
            }
            return ans;
        }
    }
}
