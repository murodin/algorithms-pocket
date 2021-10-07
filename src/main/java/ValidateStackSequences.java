import java.util.Stack;

public class ValidateStackSequences {

    /*
        Given two integer arrays pushed and popped each with distinct values,
        return true if this could have been the result of a sequence of push and pop operations on an initially empty stack, or false otherwise.

        Example 1.
        Input: pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
        Output: true
        Explanation: We might do the following sequence:
        push(1), push(2), push(3), push(4),
        pop() -> 4,
        push(5),
        pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
        Example 2.
        Input: pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
        Output: false
        Explanation: 1 cannot be popped before 2.
     */

    public static void main(String[] args) {
        int[] pushed = {1,2,3,4,5}, popped = {4,5,3,2,1};
        System.out.println("Validate Stack Sequences: " + Solution.validateStackSequences(pushed, popped));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static boolean validateStackSequences(int[] pushed, int[] popped) {
            Stack<Integer> st = new Stack<>();
            int j = 0;
            for(int p : pushed) {
                st.push(p);
                while(!st.isEmpty() && j < popped.length && st.peek() == popped[j]) {
                    st.pop();
                    j++;
                }
            }
            return j == popped.length;
        }
    }
}
