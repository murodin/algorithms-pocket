public class ClimbingStairs {
    /*
        You are climbing a staircase. It takes n steps to reach the top.
        Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

        Example 1.
        Input: n = 2
        Output: 2
        Explanation: There are two ways to climb to the top.
        1. 1 step + 1 step
        2. 2 steps
        Example 2.
        Input: n = 3
        Output: 3
        Explanation: There are three ways to climb to the top.
        1. 1 step + 1 step + 1 step
        2. 1 step + 2 steps
        3. 2 steps + 1 step


        Constraints:

        1 <= n <= 45
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.climbStairs(3));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int climbStairs(int n) {
            int[] memo = new int[n + 1];
            return climbStairs(n, memo);
        }

        private static int climbStairs(int n, int[] memo) {
            if (n == 1) memo[1] = 1;
            if (n == 2) memo[2] = 2;
            if (memo[n] > 0) return memo[n];
            return memo[n] = climbStairs(n - 1, memo) + climbStairs(n - 2, memo);
        }
    }
}
