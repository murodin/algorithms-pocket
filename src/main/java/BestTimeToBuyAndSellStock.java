public class BestTimeToBuyAndSellStock {

    /*
        You are given an array prices where prices[i] is the price of a given stock on the ith day.
        You want to maximize your profit by choosing a single day to buy one stock and choosing a different day in the future to sell that stock.
        Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.

        Example 1.
        Input: prices = [7,1,5,3,6,4]
        Output: 5
        Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
        Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.
        Example 2.
        Input: prices = [7,6,4,3,1]
        Output: 0
        Explanation: In this case, no transactions are done and the max profit = 0.

        Constraints:

        1 <= prices.length <= 105
        0 <= prices[i] <= 104
     */

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println("Solution I: " + Solution_I.maxProfit(prices));
        System.out.println("Solution I: " + Solution_II.maxProfit(prices));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static int maxProfit(int[] prices) {
            int[] left = new int[prices.length];
            int[] right = new int[prices.length];
            left[0] = prices[0];
            right[prices.length-1] = prices[prices.length-1];

            for(int i = 1; i < prices.length; i++) {
                left[i]= Math.min(left[i-1], prices[i]);
            }
            for(int i = prices.length-2; i >= 0; i--) {
                right[i] = Math.max(right[i+1], prices[i]);
            }
            int max = 0;
            for(int i = 0; i < prices.length; i++) {
                max = Math.max(max, right[i]-left[i]);
            }
            return max;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II{
        public static int maxProfit(int[] prices) {
            int minPrice = Integer.MAX_VALUE;
            int maxProfit = 0;

            for(int i = 0; i < prices.length; i++) {
                if(prices[i] < minPrice)
                    minPrice = prices[i];
                maxProfit = Math.max(prices[i] - minPrice, maxProfit);
            }
            return maxProfit;
        }
    }
}
