import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestWordInDictionary {
    /*
        Given an array of strings words representing an English Dictionary,
        return the longest word in words that can be built one character at a time by other words in words.
        If there is more than one possible answer, return the longest word with the smallest lexicographical order. If there is no answer, return the empty string.
        Note that the word should be built from left to right with each additional character being added to the end of a previous word.

        Example 1.
        Input: words = ["w","wo","wor","worl","world"]
        Output: "world"
        Explanation: The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
        Example 2.
        Input: words = ["a","banana","app","appl","ap","apply","apple"]
        Output: "apple"
        Explanation: Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".


        Constraints:

        1 <= words.length <= 1000
        1 <= words[i].length <= 30
        words[i] consists of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.longestWord(new String[]{"a","banana","app","appl","ap","apply","apple"}));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static String longestWord(String[] words) {
            Arrays.sort(words);
            Set<String> built = new HashSet<String>();
            String res = "";
            for (String w : words) {
                if (w.length() == 1 || built.contains(w.substring(0, w.length() - 1))) {
                    res = w.length() > res.length() ? w : res;
                    built.add(w);
                }
            }
            return res;
        }
    }
}
