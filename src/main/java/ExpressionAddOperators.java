import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {
    /*
        Given a string num that contains only digits and an integer target,
        return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num
        so that the resultant expression evaluates to the target value.
        Note that operands in the returned expressions should not contain leading zeros.

        Example 1.
        Input: num = "123", target = 6
        Output: ["1*2*3","1+2+3"]
        Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.
        Example 2.
        Input: num = "232", target = 8
        Output: ["2*3+2","2+3*2"]
        Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.
        Example 3:

        Input: num = "3456237490", target = 9191
        Output: []
        Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.


        Constraints:

        1 <= num.length <= 10
        num consists of only digits.
        -231 <= target <= 231 - 1
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.addOperators("232", 8));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        static List<String> ans = new ArrayList<>();
        static String s;
        static int target;
        public static List<String> addOperators(String s, int target) {
            Solution.s = s;
            Solution.target = target;
            backtrack( 0, "", 0, 0);
            return ans;
        }
        static void backtrack(int i, String path, long resSoFar, long prevNum) {
            if (i == s.length()) {
                if (resSoFar == target) ans.add(path);
                return;
            }
            for (int j = i; j < s.length(); j++) {
                if (j > i && s.charAt(i) == '0') break; // Skip leading zero number
                long num = Long.parseLong(s.substring(i, j + 1));
                if (i == 0) {
                    backtrack(j + 1, path + num, num, num); // First num, pick it without adding any operator!
                } else {
                    backtrack(j + 1, path + "+" + num, resSoFar + num, num);
                    backtrack(j + 1, path + "-" + num, resSoFar - num, -num);
                    backtrack(j + 1, path + "*" + num, resSoFar - prevNum + prevNum * num, prevNum * num); // Can imagine with example: 1-2*3
                }
            }
        }
    }
}
