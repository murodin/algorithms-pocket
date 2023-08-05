import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ExtraCharactersInAString {
    /*
        You are given a 0-indexed string s and a dictionary of words dictionary.
        You have to break s into one or more non-overlapping substrings such that each substring is present in dictionary.
        There may be some extra characters in s which are not present in any of the substrings.

        Return the minimum number of extra characters left over if you break up s optimally.

        Example 1:

        Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
        Output: 1
        Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8. There is only 1 unused character (at index 4), so we return 1.

        Example 2:

        Input: s = "sayhelloworld", dictionary = ["hello","world"]
        Output: 3
        Explanation: We can break s in two substrings: "hello" from index 3 to 7 and "world" from index 8 to 12.
        The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3.


        Constraints:

        1 <= s.length <= 50
        1 <= dictionary.length <= 50
        1 <= dictionary[i].length <= 50
        dictionary[i] and s consists of only lowercase English letters
        dictionary contains distinct words
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minExtraChar("sayhelloworld", new String[]{"hello","world"}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        static int n;
        static int[][] memo;

        public static int minExtraChar(String s, String[] dictionary) {
            n = s.length();
            memo = new int[n][n];
            for (int i = 0; i < n; i++) Arrays.fill(memo[i], n);
            Set<String> words = new HashSet<>(Arrays.asList(dictionary));
            return dfs(0, s, words);
        }

        private static int dfs(int index, String s, Set<String> words) {
            if (s.length() == 0) return 0;
            if (memo[index][s.length() - 1] != n) return memo[index][s.length() - 1];
            int res = s.length();
            for (int i = 0; i < s.length(); i++) {
                String str = s.substring(0, i + 1);
                int curExtra = words.contains(str) ? 0 : i + 1;
                res = Math.min(res, curExtra + dfs(i + 1, s.substring(i + 1), words));
            }
            memo[index][s.length() - 1] = res;
            return res;
        }
    }
}
