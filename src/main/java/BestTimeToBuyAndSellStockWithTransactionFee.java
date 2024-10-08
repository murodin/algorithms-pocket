public class BestTimeToBuyAndSellStockWithTransactionFee {

    /*
        You are given an array prices where prices[i] is the price of a given stock on the ith day, and an integer fee representing a transaction fee.
        Find the maximum profit you can achieve. You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
        Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

        Example 1.
        Input: prices = [1,3,2,8,4,9], fee = 2
        Output: 8
        Explanation: The maximum profit can be achieved by:
        - Buying at prices[0] = 1
        - Selling at prices[3] = 8
        - Buying at prices[4] = 4
        - Selling at prices[5] = 9
        The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
        Example 2.
        Input: prices = [1,3,7,5,10,3], fee = 3
        Output: 6

        Constraints:

        1 <= prices.length <= 5 * 104
        1 <= prices[i] < 5 * 104
        0 <= fee < 5 * 104
     */

    public static void main(String[] args) {
        int[] prices = {1,3,7,5,10,3};
        System.out.println("Max Profit: " + Solution.maxProfit(prices, 3));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int maxProfit(int[] prices, int fee) {
            //O(1) - Space
            int n = prices.length;
            int profit = 0;
            int effectiveBuyPrice = prices[0];

            //O(N) - Time
            for(int i = 1; i < n; i++) {
                profit = Math.max(profit, prices[i]-effectiveBuyPrice-fee);
                effectiveBuyPrice = Math.min(effectiveBuyPrice, prices[i]-profit);
            }
            return profit;
        }
    }
}
