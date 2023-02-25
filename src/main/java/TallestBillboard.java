public class TallestBillboard {
    /*
        You are installing a billboard and want it to have the largest height. The billboard will have two steel supports, one on each side. Each steel support must be an equal height.
        You are given a collection of rods that can be welded together. For example, if you have rods of lengths 1, 2, and 3, you can weld them together to make a support of length 6.
        Return the largest possible height of your billboard installation. If you cannot support the billboard, return 0.

        Example 1.
        Input: rods = [1,2,3,6]
        Output: 6
        Explanation: We have two disjoint subsets {1,2,3} and {6}, which have the same sum = 6.
        Example 2.
        Input: rods = [1,2,3,4,5,6]
        Output: 10
        Explanation: We have two disjoint subsets {2,3,5} and {4,6}, which have the same sum = 10.
        Example 3.
        Input: rods = [1,2]
        Output: 0
        Explanation: The billboard cannot be supported, so we return 0.


        Constraints:

        1 <= rods.length <= 20
        1 <= rods[i] <= 1000
        sum(rods[i]) <= 5000
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.tallestBillboard(new int[]{1,2,3,4,5,6}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int tallestBillboard(int[] rods) {
            int[] dp = new int[5001];
            for (int d = 1; d < 5001; d++) dp[d] = -10000;
            for (int x : rods) {
                int[] cur = dp.clone();
                for (int d = 0; d + x < 5001; d++) {
                    dp[d + x] = Math.max(dp[d + x], cur[d]);
                    dp[Math.abs(d - x)] = Math.max(dp[Math.abs(d - x)], cur[d] + Math.min(d, x));
                }
            }
            return dp[0];
        }
    }
}
