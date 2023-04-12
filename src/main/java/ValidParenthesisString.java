public class ValidParenthesisString {
    /*
        Given a string s containing only three types of characters: '(', ')' and '*', return true if s is valid.
        The following rules define a valid string:

        Any left parenthesis '(' must have a corresponding right parenthesis ')'.
        Any right parenthesis ')' must have a corresponding left parenthesis '('.
        Left parenthesis '(' must go before the corresponding right parenthesis ')'.
        '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string "".

        Example 1.
        Input: s = "()"
        Output: true
        Example 2.
        Input: s = "(*)"
        Output: true
        Example 3.
        Input: s = "(*))"
        Output: true


        Constraints:

        1 <= s.length <= 100
        s[i] is '(', ')' or '*'.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.checkValidString("(*))"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static boolean checkValidString(String s) {
            int cmin = 0, cmax = 0;
            for (char c : s.toCharArray()) {
                if (c == '(') {
                    cmax++;
                    cmin++;
                } else if (c == ')') {
                    cmax--;
                    cmin--;
                } else if (c == '*') {
                    cmax++;
                    cmin--;
                }
                if (cmax < 0) return false;
                cmin = Math.max(cmin, 0);
            }
            return cmin == 0;
        }
    }
}
