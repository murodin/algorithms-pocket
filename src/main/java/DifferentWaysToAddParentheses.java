import java.util.ArrayList;
import java.util.List;

public class DifferentWaysToAddParentheses {
    /*
        Given a string expression of numbers and operators, return all possible results from computing
        all the different possible ways to group numbers and operators. You may return the answer in any order.
        The test cases are generated such that the output values fit in a 32-bit integer and the number of different results does not exceed 104.

        Example 1:

        Input: expression = "2-1-1"
        Output: [0,2]
        Explanation:
        ((2-1)-1) = 0
        (2-(1-1)) = 2
        Example 2:

        Input: expression = "2*3-4*5"
        Output: [-34,-14,-10,-10,10]
        Explanation:
        (2*(3-(4*5))) = -34
        ((2*3)-(4*5)) = -14
        ((2*(3-4))*5) = -10
        (2*((3-4)*5)) = -10
        (((2*3)-4)*5) = 10


        Constraints:

        1 <= expression.length <= 20
        expression consists of digits and the operator '+', '-', and '*'.
        All the integer values in the input expression are in the range [0, 99].
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.diffWaysToCompute("2*3-4*5"));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static List<Integer> diffWaysToCompute(String input) {
            List<Integer> res = new ArrayList<Integer>();
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == '-' || c == '+' || c == '*') {
                    String a = input.substring(0, i);
                    String b = input.substring(i + 1);
                    List<Integer> al = diffWaysToCompute(a);
                    List<Integer> bl = diffWaysToCompute(b);
                    for (int x : al) {
                        for (int y : bl) {
                            if (c == '-') {
                                res.add(x - y);
                            } else if (c == '+') {
                                res.add(x + y);
                            } else {
                                res.add(x * y);
                            }
                        }
                    }
                }
            }
            if (res.size() == 0) res.add(Integer.valueOf(input));
            return res;
        }
    }
}
