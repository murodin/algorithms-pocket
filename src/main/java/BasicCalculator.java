public class BasicCalculator {
    /*
        Given a string s representing a valid expression, implement a basic calculator to evaluate it, and return the result of the evaluation.
        Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

        Example 1.
        Input: s = "1 + 1"
        Output: 2
        Example 2.
        Input: s = " 2-1 + 2 "
        Output: 3
        Example 3.
        Input: s = "(1+(4+5+2)-3)+(6+8)"
        Output: 23


        Constraints:

        1 <= s.length <= 3 * 105
        s consists of digits, '+', '-', '(', ')', and ' '.
        s represents a valid expression.
        '+' is not used as a unary operation (i.e., "+1" and "+(2 + 3)" is invalid).
        '-' could be used as a unary operation (i.e., "-1" and "-(2 + 3)" is valid).
        There will be no two consecutive operators in the input.
        Every number and running calculation will fit in a signed 32-bit integer.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.calculate("(1+(4+5+2)-3)+(6+8)"));
    }

    // Time: O(N), where n is the length of the string
    // Space: O(NofBrackets),  which is less than n/2;
    static class Solution {
        static int idx; // this index traverse the string in one pass, between different level of recursion
        public static int calculate(String s) {
            idx = 0; // Initialization should be here
            return calc(s);
        }

        private static int calc(String s) {
            int res = 0, num = 0, sign = 1;
            while (idx < s.length()) {
                char c = s.charAt(idx++);
                if (c >= '0' && c <= '9') num = num * 10 + c - '0';
                else if (c == '(') num = calc(s); // ( is start of a new sub-problem, Let recursion solve the sub-problem
                else if (c == ')') return res + sign * num;
                else if (c == '+' || c == '-') { // only when we meet a new sign, we know a while number has been read
                    res += sign * num;
                    num = 0;
                    sign = c == '-' ? -1 : 1;
                }
            }
            return res + sign * num; // last number is not processed yet
        }
    }
}
