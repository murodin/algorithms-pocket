import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RemoveInvalidParentheses {
    /*
        Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.

        Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.

        Example 1.
        Input: s = "()())()"
        Output: ["(())()","()()()"]
        Example 2.
        Input: s = "(a)())()"
        Output: ["(a())()","(a)()()"]
        Example 3.
        Input: s = ")("
        Output: [""]


        Constraints:

        1 <= s.length <= 25
        s consists of lowercase English letters and parentheses '(' and ')'.
        There will be at most 20 parentheses in s.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.removeInvalidParentheses("()())()"));
    }

    // Time: O(2^N)
    // Space: O(N^2)
    static class Solution {
        public static List<String> removeInvalidParentheses(String s) {
            int n = s.length();
            HashSet<String> validParentheses = dfs(s, 0, 0, new HashSet[n + 1][n + 1]);
            int maxLen = 0;
            for (String str : validParentheses) maxLen = Math.max(maxLen, str.length());

            List<String> ans = new ArrayList<>();
            for (String str : validParentheses)
                if (str.length() == maxLen)
                    ans.add(str);
            return ans;
        }

        static HashSet<String> dfs(String s, int i, int nOpen, HashSet<String>[][] memo) {
            HashSet<String> ans = new HashSet<>();
            if (nOpen < 0) return ans; // invalid parentheses
            if (memo[i][nOpen] != null) return memo[i][nOpen];
            if (i == s.length()) {
                if (nOpen == 0) ans.add(""); // valid parentheses
            } else {
                char c = s.charAt(i);
                if (c == '(' || c == ')') {
                    ans.addAll(dfs(s, i + 1, nOpen, memo));
                }
                int newOpen = nOpen;
                if (c == '(') newOpen++;
                else if (c == ')') newOpen--;
                for (String suffix : dfs(s, i + 1, newOpen, memo))
                    ans.add(c + suffix);
            }
            return memo[i][nOpen] = ans;
        }
    }
}
