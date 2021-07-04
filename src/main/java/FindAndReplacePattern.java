import java.util.ArrayList;
import java.util.List;

public class FindAndReplacePattern {

    /*
        Given a list of strings words and a string pattern, return a list of words[i] that match pattern. You may return the answer in any order.
        A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.
        Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.
     */

    public static void main(String[] args) {
        String[] testWords = {"abc","deq","mee","aqq","dkd","ccc"};
        String testPattern = "abb";

        System.out.println("Matches:" + Solution.findAndReplacePattern(testWords, testPattern));
    }

    static class Solution {
        public static List<String> findAndReplacePattern(String[] words, String pattern) {
            List<String> res = new ArrayList<>();

            for(String word:words) {
                if(match(word, pattern))
                    res.add(word);
            }

            return res;
        }

        private static boolean match(String word, String pattern) {
            char[] patternToWord = new char[26];
            char[] wordToPattern = new char[26];

            for(int i=0; i<word.length(); i++) {
                char wordChar = word.charAt(i);
                char patternChar = pattern.charAt(i);

                if(wordToPattern[wordChar-'a'] == 0)
                    wordToPattern[wordChar-'a'] = patternChar;

                if(patternToWord[patternChar-'a'] == 0)
                    patternToWord[patternChar-'a'] = wordChar;

                if(patternToWord[patternChar-'a'] != wordChar || wordToPattern[wordChar-'a'] != patternChar)
                    return false;
            }
            return true;
        }
    }
}
