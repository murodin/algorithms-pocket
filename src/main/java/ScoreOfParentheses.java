import java.util.Stack;

public class ScoreOfParentheses {

    /*
        Given a balanced parentheses string s, return the score of the string.
        The score of a balanced parentheses string is based on the following rule:

        "()" has score 1.
        AB has score A + B, where A and B are balanced parentheses strings.
        (A) has score 2 * A, where A is a balanced parentheses string.

        Example 1.
        Input: s = "()"
        Output: 1
        Example 2.
        Input: s = "(())"
        Output: 2
        Example 3.
        Input: s = "()()"
        Output: 2
        Example 4.
        Input: s = "(()(()))"
        Output: 6
     */

    public static void main(String[] args) {
        System.out.println("Score: " + Solution.scoreOfParentheses("(()(()))"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int scoreOfParentheses(String S) {
            int count = 0;
            Stack<Integer> st = new Stack<>();
            for(char c : S.toCharArray()) {
                int val = 0;
                if(c=='(') st.push(0);
                else {
                    //Calculate the score
                    while(st.peek() != 0) val+=st.pop();
                    val = Math.max(2*val,1);   //() gives 1
                    st.pop();
                    st.push(val);
                }
            }
            while(!st.isEmpty()) count+=st.pop();
            return count;
        }
    }
}
