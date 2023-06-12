import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwentyFourGame {
    /*
        You are given an integer array cards of length 4. You have four cards, each containing a number in the range [1, 9].
        You should arrange the numbers on these cards in a mathematical expression using the operators ['+', '-', '*', '/']
        and the parentheses '(' and ')' to get the value 24.

        You are restricted with the following rules:

        The division operator '/' represents real division, not integer division.
        For example, 4 / (1 - 2 / 3) = 4 / (1 / 3) = 12.
        Every operation done is between two numbers. In particular, we cannot use '-' as a unary operator.
        For example, if cards = [1, 1, 1, 1], the expression "-1 - 1 - 1 - 1" is not allowed.
        You cannot concatenate numbers together
        For example, if cards = [1, 2, 1, 2], the expression "12 + 12" is not valid.
        Return true if you can get such expression that evaluates to 24, and false otherwise.



        Example 1:

        Input: cards = [4,1,8,7]
        Output: true
        Explanation: (8-4) * (7-1) = 24
        Example 2:

        Input: cards = [1,2,1,2]
        Output: false


        Constraints:

        cards.length == 4
        1 <= cards[i] <= 9
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.judgePoint24(new int[]{4,1,8,7}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        static boolean res = false;
        static final double eps = 0.001;

        public static boolean judgePoint24(int[] nums) {
            List<Double> arr = new ArrayList<>();
            for(int n: nums) arr.add((double) n);
            helper(arr);
            return res;
        }

        private static void helper(List<Double> arr){
            if(res) return;
            if(arr.size() == 1){
                if(Math.abs(arr.get(0) - 24.0) < eps)
                    res = true;
                return;
            }
            for (int i = 0; i < arr.size(); i++) {
                for (int j = 0; j < i; j++) {
                    List<Double> next = new ArrayList<>();
                    Double p1 = arr.get(i), p2 = arr.get(j);
                    next.addAll(Arrays.asList(p1+p2, p1-p2, p2-p1, p1*p2));
                    if(Math.abs(p2) > eps)  next.add(p1/p2);
                    if(Math.abs(p1) > eps)  next.add(p2/p1);

                    arr.remove(i);
                    arr.remove(j);
                    for (Double n: next){
                        arr.add(n);
                        helper(arr);
                        arr.remove(arr.size()-1);
                    }
                    arr.add(j, p2);
                    arr.add(i, p1);
                }
            }
        }
    }
}
