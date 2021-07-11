import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestStringChain {

    /*
        You are given an array of words where each word consists of lowercase English letters.

        wordA is a predecessor of wordB if and only if we can insert exactly one letter anywhere in wordA without changing the order of the other characters to make it equal to wordB.

        For example, "abc" is a predecessor of "abac", while "cba" is not a predecessor of "bcad".
        A word chain is a sequence of words [word1, word2, ..., wordk] with k >= 1, where word1 is a predecessor of word2, word2 is a predecessor of word3, and so on.
        A single word is trivially a word chain with k == 1.

        Return the length of the longest possible word chain with words chosen from the given list of words.
     */

    public static void main(String[] args) {
        String[] testWords = {"a","b","ba","bca","bda","bdca"};
        System.out.println("Longest String Chain:" + Solution.longestStrChain(testWords));
    }

    // Time: O(NLogN + N*Length*Length)
    // Space: O(N*Length)
    static class Solution {
        public static int longestStrChain(String[] words) {
            // Time: O(NLogN)
            Arrays.sort(words, (a, b) -> a.length() - b.length());
            int res = 0;
            Map<String, Integer> memo = new HashMap<>();

            // Time: O(N)
            for(String word: words) {
                memo.put(word, 1);
                // Time: O(Length*Length)
                for(int i = 0; i<word.length(); i++) {
                    StringBuilder current = new StringBuilder(word);
                    String next = current.deleteCharAt(i).toString();// Time: O(Length)
                    if(memo.containsKey(next)) {
                        memo.put(word, Math.max(memo.get(word), memo.get(next)+1));
                    }
                }
                res = Math.max(res, memo.get(word));
            }
            return res;
        }
    }
}
