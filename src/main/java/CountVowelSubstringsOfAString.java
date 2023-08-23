import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CountVowelSubstringsOfAString {
    /*
        A substring is a contiguous (non-empty) sequence of characters within a string.
        A vowel substring is a substring that only consists of vowels ('a', 'e', 'i', 'o', and 'u') and has all five vowels present in it.
        Given a string word, return the number of vowel substrings in word.


        Example 1:

        Input: word = "aeiouu"
        Output: 2
        Explanation: The vowel substrings of word are as follows (underlined):
        - "aeiouu"
        - "aeiouu"
        Example 2:

        Input: word = "unicornarihan"
        Output: 0
        Explanation: Not all 5 vowels are present, so there are no vowel substrings.
        Example 3:

        Input: word = "cuaieuouac"
        Output: 7
        Explanation: The vowel substrings of word are as follows (underlined):
        - "cuaieuouac"
        - "cuaieuouac"
        - "cuaieuouac"
        - "cuaieuouac"
        - "cuaieuouac"
        - "cuaieuouac"
        - "cuaieuouac"


        Constraints:

        1 <= word.length <= 100
        word consists of lowercase English letters on
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.countVowelSubstrings("cuaieuouac"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int countVowelSubstrings(String word) {
            int count = 0;
            Map<Character, Integer> lastSeen =
                    new HashMap<>(Map.of('a', -1, 'e', -1, 'i', -1, 'o', -1, 'u', -1));
            for (int i = 0, lastInvalidPos = -1; i < word.length(); ++i) {
                if (lastSeen.containsKey(word.charAt(i))) {
                    lastSeen.put(word.charAt(i), i);
                    count += Math.max(Collections.min(lastSeen.values()) - lastInvalidPos, 0);
                } else {
                    lastInvalidPos = i;
                }
            }
            return count;
        }
    }
}
