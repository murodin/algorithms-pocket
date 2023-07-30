import java.util.*;

public class MinimumCostToChangeTheFinalValueOfExpression {
    /*
        You are given a valid boolean expression as a string expression consisting of the characters '1','0','&' (bitwise AND operator),
        '|' (bitwise OR operator),'(', and ')'.

        For example, "()1|1" and "(1)&()" are not valid while "1", "(((1))|(0))", and "1|(0&(1))" are valid expressions.
        Return the minimum cost to change the final value of the expression.

        For example, if expression = "1|1|(0&0)&1", its value is 1|1|(0&0)&1 = 1|1|0&1 = 1|0&1 = 1&1 = 1. We want to apply operations
        so that the new expression evaluates to 0.
        The cost of changing the final value of an expression is the number of operations performed on the expression. The types of operations are described as follows:

        Turn a '1' into a '0'.
        Turn a '0' into a '1'.
        Turn a '&' into a '|'.
        Turn a '|' into a '&'.
        Note: '&' does not take precedence over '|' in the order of calculation. Evaluate parentheses first, then in left-to-right order.



        Example 1:

        Input: expression = "1&(0|1)"
        Output: 1
        Explanation: We can turn "1&(0|1)" into "1&(0&1)" by changing the '|' to a '&' using 1 operation.
        The new expression evaluates to 0.
        Example 2:

        Input: expression = "(0&0)&(0&0&0)"
        Output: 3
        Explanation: We can turn "(0&0)&(0&0&0)" into "(0|1)|(0&0&0)" using 3 operations.
        The new expression evaluates to 1.
        Example 3:

        Input: expression = "(0|(1|0&1))"
        Output: 1
        Explanation: We can turn "(0|(1|0&1))" into "(0|(0|0&1))" using 1 operation.
        The new expression evaluates to 0.


        Constraints:

        1 <= expression.length <= 105
        expression only contains '1','0','&','|','(', and ')'
        All parentheses are properly matched.
        There will be no empty parentheses (i.e: "()" is not a substring of expression).
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minOperationsToFlip("(0&0)&(0&0&0)"));
    }

    static class Solution {
        static Map<Integer, Integer> map;
        public static int minOperationsToFlip(String expression) {
            Stack<Integer> stack = new Stack<>();
            map = new HashMap<>();
            for (int i = 0; i < expression.length(); ++i){
                if (expression.charAt(i) == '(')
                    stack.push(i);
                else if (expression.charAt(i) == ')')
                    map.put(stack.pop(), i);
            }
            return evaluate(expression, 0, expression.length() - 1)[1];
        }

        private static int [] evaluate(String expression, int low, int high){
            List<int []> list = new ArrayList<>();
            List<Character> characters = new ArrayList<>();
            char c;
            int next;

            for (int i = low; i <= high; ++i){
                c = expression.charAt(i);
                if (c == '0' || c == '1'){
                    list.add(new int [] {c - '0', 1});
                } else if (c == '|' || c == '&'){
                    characters.add(c);
                } else {
                    next = map.get(i);
                    list.add(evaluate(expression, i + 1, next - 1));
                    i = next;
                }
            }

            int result = list.get(0)[0];

            for (int i = 1; i < list.size(); ++i){
                if (characters.get(i - 1) == '&')
                    result = result & list.get(i)[0];
                else
                    result = result | list.get(i)[0];
            }
            int [] minChange = minChange(list, characters);
            return new int [] {result ,minChange[result^1]};
        }

        private static int[] minChange(List<int[]> list, List<Character> vals){
            int one = 0;
            int zero = 0;

            int pZero, pOne;

            if (list.get(0)[0] == 1)
                zero += list.get(0)[1];
            else
                one += list.get(0)[1];

            for (int i = 1, j = 0; i < list.size(); ++i, ++j){
                pZero = zero;
                pOne = one;
                if (vals.get(j) == '|'){
                    if (list.get(i)[0] == 0){
                        zero = Math.min(pZero,1);
                        one = Math.min(pOne, list.get(i)[1]);
                    }else {
                        zero = Math.min(pZero + 1, list.get(i)[1] + 1);
                        one = 0;
                    }
                } else{
                    if (list.get(i)[0] == 0){
                        zero = 0;
                        one = Math.min(1 + pOne, list.get(i)[1] + 1);
                    } else {
                        zero = Math.min(pZero, list.get(i)[1]);
                        one = Math.min(pOne, 1);
                    }
                }
            }

            return new int [] {zero, one};
        }
    }
}
