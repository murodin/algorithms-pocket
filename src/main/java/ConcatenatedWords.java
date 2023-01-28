import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ConcatenatedWords {
    /*
        Given an array of strings words (without duplicates), return all the concatenated words in the given list of words.
        A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.

        Example 1.
        Input: words = ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
        Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
        Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
        "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
        "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
        Example 2.
        Input: words = ["cat","dog","catdog"]
        Output: ["catdog"]


        Constraints:

        1 <= words.length <= 104
        1 <= words[i].length <= 30
        words[i] consists of only lowercase English letters.
        All the strings of words are unique.
        1 <= sum(words[i].length) <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: "  + Solution.findAllConcatenatedWordsInADict(new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"}));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static List<String> findAllConcatenatedWordsInADict(String[] words) {
            Arrays.sort(words, (a, b) -> a.length() - b.length());
            List<String> result = new ArrayList<>();
            HashSet<String> preWords = new HashSet<>();
            for (String word : words) {
                if (wordBreak(word, preWords)) result.add(word);
                preWords.add(word);
            }
            return result;
        }

        private static boolean wordBreak(String s, HashSet<String> preWords){
            if(preWords.isEmpty()) return false;
            boolean[] dp = new boolean[s.length() + 1];
            dp[0] = true;
            for(int i = 1; i <= s.length(); i++){
                for(int j = 0; j < i; j++){
                    if(dp[j] && preWords.contains(s.substring(j, i))){
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[s.length()];
        }
    }
}
