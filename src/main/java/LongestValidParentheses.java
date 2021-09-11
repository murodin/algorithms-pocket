import java.util.Stack;

public class LongestValidParentheses {

    /*
        Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
        Example 1.
        Input: s = "(()"
        Output: 2
        Explanation: The longest valid parentheses substring is "()".

        Example 2.
        Input: s = ")()())"
        Output: 4
        Explanation: The longest valid parentheses substring is "()()".

        Example 3.
        Input: s = ""
        Output: 0
     */

    public static void main(String[] args) {
        String testString = ")()())";
        System.out.println("Solution longestValidParentheses: " + Solution.longestValidParentheses(testString));
        System.out.println("Solution longestValidParentheses_II: " + Solution.longestValidParentheses_II(testString));
    }

    static class Solution {
        // Time: O(N)
        // Space: O(N)
        public static int longestValidParentheses(String s) {
            Stack<Integer> st = new Stack<>();
            st.push(-1);
            int max = 0;
            for(int i = 0; i < s.length(); i++){
                char c = s.charAt(i);
                if(c == '(') {
                    st.push(i);
                } else {
                    st.pop();
                    if(st.isEmpty()){
                        st.push(i);
                    } else {
                        int len = i - st.peek();
                        max = Math.max(max,len);
                    }
                }
            }
            return max;
        }

        // Time: O(N)
        // Space: O(1)
        public static int longestValidParentheses_II(String s) {
            int open = 0 , close = 0;
            int max = 0;
            // 0 -- n
            for(int i=0;i<s.length();i++){
                char c = s.charAt(i);
                if(c == '(') {
                    open++;
                } else {
                    close++;
                }

                if(open == close) {
                    int len = open + close;
                    max = Math.max(max,len);
                } else if(close > open ) {
                    open = close = 0;
                }
            }

            open = close = 0;
            // n -- 0
            for(int i= s.length()-1; i>=0 ;i--){
                char c = s.charAt(i);
                if(c == '(') {
                    open++;
                } else {
                    close++;
                }

                if(open == close) {
                    int len = open + close;
                    max = Math.max(max,len);
                } else if(open > close ) {
                    open = close = 0;
                }
            }
            return max;
        }
    }
}
