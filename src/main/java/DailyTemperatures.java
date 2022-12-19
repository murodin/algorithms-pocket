import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DailyTemperatures {
    /*
        Given an array of integers temperatures represents the daily temperatures,
        return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
        If there is no future day for which this is possible, keep answer[i] == 0 instead.

        Example 1.
        Input: temperatures = [73,74,75,71,69,72,76,73]
        Output: [1,1,4,2,1,1,0,0]
        Example 2.
        Input: temperatures = [30,40,50,60]
        Output: [1,1,1,0]
        Example 3:

        Input: temperatures = [30,60,90]
        Output: [1,1,0]


        Constraints:

        1 <= temperatures.length <= 105
        30 <= temperatures[i] <= 100
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int[] dailyTemperatures(int[] temperatures) {
            int[] ans = new int[temperatures.length];
            Deque<Integer> stack = new ArrayDeque<>(); // Decreasing stack
            for (int i = 0; i < temperatures.length; ++i) {
                while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                    final int index = stack.pop();
                    ans[index] = i - index;
                }
                stack.push(i);
            }
            return ans;
        }
    }
}
