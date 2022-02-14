public class BestTimeToBuyAndSellStockIV {

    /*
        You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
        Find the maximum profit you can achieve. You may complete at most k transactions.
        Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

        Example 1.
        Input: k = 2, prices = [2,4,1]
        Output: 2
        Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
        Example 2.
        Input: k = 2, prices = [3,2,6,5,0,3]
        Output: 7
        Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

        Constraints:

        0 <= k <= 100
        0 <= prices.length <= 1000
        0 <= prices[i] <= 1000
     */

    public static void main(String[] args) {
        int[] prices = {3,2,6,5,0,3};

        System.out.println("Soluiton I: " + Solution_I.maxProfit(2, prices));
        System.out.println("Solution II: " + Solution_II.maxProfit(2, prices));
    }

    // Time: O(K*N*N)
    // Space: O(K*N)
    static class Solution_I {
        public static int maxProfit(int k, int[] prices) {
            int n = prices.length;
            int[][] dp = new int[k+1][n];

            if(n < 2) return 0;

            for(int i = 1; i <= k; i++) {
                for(int j = 1; j < n; j++) {
                    dp[i][j] = Math.max(dp[i][j-1], helper(i, j, prices, dp));
                }
            }

            return dp[k][n-1];
        }

        private static int helper(int k, int x, int[] prices, int[][] dp){
            int max = 0;
            for(int i = 0; i < x; i++) {
                //Sell on day_x, buy on day_i and add profit from (i-1) transsactions dp[k-1][i]
                max = Math.max(max, prices[x]-prices[i] + dp[k-1][i]);
            }
            return max;
        }
    }

    // Time: O(K*N)
    // Space: O(K*N)
    static class Solution_II {
        public static int maxProfit(int k, int[] prices) {
            int n = prices.length;
            int[][] dp = new int[k+1][n];   //Space : O(K*N)
            if(n < 2) return 0;

            //Time O(K*N)
            for(int i = 1; i <= k; i++) {
                int effectiveBuyPrice = prices[0];
                for(int j = 1; j < n; j++) {
                    //Profit by selling on current price (j)
                    // Profit = SP-EBP
                    // SP => price[j]
                    // BP => Effective Buy Price -> price[j] - previous profit (dp[k-1][j])
                    dp[i][j] = Math.max(dp[i][j-1], prices[j] - effectiveBuyPrice);
                    effectiveBuyPrice = Math.min(effectiveBuyPrice, prices[j] - dp[i-1][j]);
                }
            }
            return dp[k][n-1];
        }
    }
}
