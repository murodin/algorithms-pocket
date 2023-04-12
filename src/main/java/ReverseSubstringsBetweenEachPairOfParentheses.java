import java.util.Stack;

public class ReverseSubstringsBetweenEachPairOfParentheses {
    /*
        You are given a string s that consists of lower case English letters and brackets.
        Reverse the strings in each pair of matching parentheses, starting from the innermost one.
        Your result should not contain any brackets.

        Example 1:

        Input: s = "(abcd)"
        Output: "dcba"
        Example 2:

        Input: s = "(u(love)i)"
        Output: "iloveu"
        Explanation: The substring "love" is reversed first, then the whole string is reversed.
        Example 3:

        Input: s = "(ed(et(oc))el)"
        Output: "leetcode"
        Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.


        Constraints:

        1 <= s.length <= 2000
        s only contains lower case English characters and parentheses.
        It is guaranteed that all parentheses are balanced.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.reverseParentheses("(ed(et(oc))el)"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static String reverseParentheses(String s) {
            int n = s.length();
            Stack<Integer> opened = new Stack<>();
            int[] pair = new int[n];
            for (int i = 0; i < n; ++i) {
                if (s.charAt(i) == '(')
                    opened.push(i);
                if (s.charAt(i) == ')') {
                    int j = opened.pop();
                    pair[i] = j;
                    pair[j] = i;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0, d = 1; i < n; i += d) {
                if (s.charAt(i) == '(' || s.charAt(i) == ')') {
                    i = pair[i];
                    d = -d;
                } else {
                    sb.append(s.charAt(i));
                }
            }
            return sb.toString();
        }
    }
}
