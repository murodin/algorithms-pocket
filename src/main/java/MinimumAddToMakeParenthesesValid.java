import java.util.Stack;

public class MinimumAddToMakeParenthesesValid {
    /*
        A parentheses string is valid if and only if:

        It is the empty string,
        It can be written as AB (A concatenated with B), where A and B are valid strings, or
        It can be written as (A), where A is a valid string.
        You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.

        For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
        Return the minimum number of moves required to make s valid.



        Example 1:

        Input: s = "())"
        Output: 1
        Example 2:

        Input: s = "((("
        Output: 3


        Constraints:

        1 <= s.length <= 1000
        s[i] is either '(' or ')'.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minAddToMakeValid("())"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int minAddToMakeValid(String s) {
            Stack<Character> stack=new Stack<>();
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)==')'){
                    if(!stack.isEmpty() && stack.peek()=='(' ){
                        stack.pop();
                    } else{
                        stack.push(s.charAt(i));
                    }
                } else{
                    stack.push(s.charAt(i));
                }
            }
            return stack.size();
        }
    }
}
