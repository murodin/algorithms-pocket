public class BestTimeToBuyAndSellStockIII {

    /*
        You are given an array prices where prices[i] is the price of a given stock on the ith day.
        Find the maximum profit you can achieve. You may complete at most two transactions.
        Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).

        Example 1.
        Input: prices = [3,3,5,0,0,3,1,4]
        Output: 6
        Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
        Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
        Example 2.
        Input: prices = [1,2,3,4,5]
        Output: 4
        Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
        Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
        Example 3.
        Input: prices = [7,6,4,3,1]
        Output: 0
        Explanation: In this case, no transaction is done, i.e. max profit = 0.
        Example 4.
        Input: prices = [1]
        Output: 0
     */

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println("Solution I: " + Solution_I.maxProfit(prices));
        System.out.println("Solution II: " + Solution_II.maxProfit(prices));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static int maxProfit(int[] prices) {
            int n=prices.length;
            if(n==0) return 0;

            int[] left=new int[n];
            int[] right=new int[n];

            int leftmin=prices[0];
            for(int i=1;i<n;i++){
                if(prices[i]<leftmin){
                    leftmin=prices[i];
                    left[i]=left[i-1];
                } else{
                    left[i]=Math.max(left[i-1],prices[i]-leftmin);
                }
            }

            int rightmax=prices[n-1];
            for(int i=n-2;i>=0;i--){
                if(prices[i]>rightmax){
                    rightmax=prices[i];
                    right[i]=right[i+1];
                } else{
                    right[i]=Math.max(right[i+1],rightmax-prices[i]);
                }
            }

            int max=0;
            for(int i=0;i<n;i++){
                max=Math.max(max,left[i]+right[i]);
            }

            return max;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static int maxProfit(int[] prices) {
            int t1Cost = Integer.MAX_VALUE,
                t2Cost = Integer.MAX_VALUE;
            int t1Profit = 0,
                t2Profit = 0;

            for (int price : prices) {
                t1Cost = Math.min(t1Cost, price);
                t1Profit = Math.max(t1Profit, price - t1Cost);
                t2Cost = Math.min(t2Cost, price - t1Profit);
                t2Profit = Math.max(t2Profit, price - t2Cost);
            }

            return t2Profit;
        }
    }
}
