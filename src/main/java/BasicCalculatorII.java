import java.util.Stack;

public class BasicCalculatorII {

    /*
        Given a string s which represents an expression, evaluate this expression and return its value.
        The integer division should truncate toward zero.
        You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].
        Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

        Example 1.
        Input: s = "3+2*2"
        Output: 7
        Example 2.
        Input: s = " 3/2 "
        Output: 1
        Example 3.
        Input: s = " 3+5 / 2 "
        Output: 5

        Constraints:

        1 <= s.length <= 3 * 105
        s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.
        s represents a valid expression.
        All the integers in the expression are non-negative integers in the range [0, 231 - 1].
        The answer is guaranteed to fit in a 32-bit integer.
     */

    public static void main(String[] args) {
        System.out.println("Calculation-I: " + Solution_I.calculate(" 3+5 / 2 "));
        System.out.println("Calculation-II: " + Solution_II.calculate(" 3+5 / 2 "));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static int calculate(String s) {
            if(s==null || s.length() == 0) return 0;

            Stack<Integer> stack = new Stack<>();

            int curr = 0;
            char op = '+';
            char[] ch = s.toCharArray();

            for(int i=0; i<ch.length;i++) {
                if(Character.isDigit(ch[i])) curr = curr*10 + ch[i] - '0';

                if(Character.isDigit(ch[i]) && ch[i] != ' ' || i == ch.length -1) {
                    if(op == '+') stack.push(curr);
                    else if(op == '-') stack.push(-curr);
                    else if(op == '*') stack.push(stack.pop() * curr);
                    else if(op == '/') stack.push(stack.pop() / curr);
                }
                op = ch[i];
                curr = 0;
            }

            int sum = 0;
            while (!stack.isEmpty()) sum += stack.pop();
            return sum;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static int calculate(String s) {
            if(s==null || s.length() == 0) return 0;

            int curr = 0;
            char op = '+';
            char[] ch = s.toCharArray();
            int last = 0;
            int sum = 0;

            for(int i=0; i<ch.length;i++) {
                if(Character.isDigit(ch[i])) curr = curr*10 + ch[i] - '0';

                if(Character.isDigit(ch[i]) && ch[i] != ' ' || i == ch.length -1) {
                    if(op == '+') {
                        sum += last;
                        last = curr;
                    } else if(op == '-') {
                        sum += last;
                        last -= curr;
                    }
                    else if(op == '*') last = last * curr;
                    else if(op == '/') last = last / curr;
                }
                op = ch[i];
                curr = 0;
            }

            sum += last;
            return sum;
        }
    }
}
