import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    /*
        Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.

        Note that the same word in the dictionary may be reused multiple times in the segmentation.

        Example 1:

        Input: s = "leetcode", wordDict = ["leet","code"]
        Output: true
        Explanation: Return true because "leetcode" can be segmented as "leet code".
        Example 2:

        Input: s = "applepenapple", wordDict = ["apple","pen"]
        Output: true
        Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
        Note that you are allowed to reuse a dictionary word.
        Example 3:

        Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
        Output: false


        Constraints:

        1 <= s.length <= 300
        1 <= wordDict.length <= 1000
        1 <= wordDict[i].length <= 20
        s and wordDict[i] consist of only lowercase English letters.
        All the strings of wordDict are unique.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.wordBreak("catsandog", List.of("cats","dog","sand","and","cat")));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static boolean wordBreak(String s, List<String> wordDict) {
            Set<String> dict = new HashSet<>(wordDict);
            int n = s.length();
            boolean[] dp = new boolean[n + 1];
            dp[0] = true;
            for (int i = 1; i <= n; ++i) {
                for (int j = 0; j < i; ++j) {
                    if (dp[j] && dict.contains(s.substring(j, i))) {
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[n];
        }
    }
}
