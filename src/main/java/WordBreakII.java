import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class WordBreakII {
    /*
        Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word.
        Return all such possible sentences in any order.
        Note that the same word in the dictionary may be reused multiple times in the segmentation.

        Example 1.
        Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
        Output: ["cats and dog","cat sand dog"]
        Example 2.
        Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
        Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
        Explanation: Note that you are allowed to reuse a dictionary word.
        Example 3.
        Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
        Output: []


        Constraints:

        1 <= s.length <= 20
        1 <= wordDict.length <= 1000
        1 <= wordDict[i].length <= 10
        s and wordDict[i] consist of only lowercase English letters.
        All the strings of wordDict are unique.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.wordBreak("catsanddog", List.of("cat","cats","and","sand","dog")));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static List<String> wordBreak(String s, List<String> wordDict) {
            return backtrack(s, wordDict, new HashMap<String, LinkedList<String>>());
        }
        private static List<String> backtrack(String s, List<String> wordDict, HashMap<String, LinkedList<String>> map) {
            if (map.containsKey(s))
                return map.get(s);
            LinkedList<String> res = new LinkedList<String>();
            if (s.length() == 0) {
                res.add("");
                return res;
            }
            System.out.println("s:" + s);
            for (String word : wordDict) {
                if (s.startsWith(word)) {
                    List<String> sublist = backtrack(s.substring(word.length()), wordDict, map);
                    System.out.println("sublist: " + sublist);
                    for (String sub : sublist)
                        res.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
            map.put(s, res);
            System.out.println("map: " + map);
            return res;
        }
    }
}
