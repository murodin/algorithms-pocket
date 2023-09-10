public class CountAllValidPickupAndDeliveryOptions {
    /*
        Given n orders, each order consist in pickup and delivery services.
        Count all valid pickup/delivery possible sequences such that delivery(i) is always after of pickup(i).
        Since the answer may be too large, return it modulo 10^9 + 7.

        Example 1:

        Input: n = 1
        Output: 1
        Explanation: Unique order (P1, D1), Delivery 1 always is after of Pickup 1.
        Example 2:

        Input: n = 2
        Output: 6
        Explanation: All possible orders:
        (P1,P2,D1,D2), (P1,P2,D2,D1), (P1,D1,P2,D2), (P2,P1,D1,D2), (P2,P1,D2,D1) and (P2,D2,P1,D1).
        This is an invalid order (P1,D2,P2,D1) because Pickup 2 is after of Delivery 2.
        Example 3:

        Input: n = 3
        Output: 90


        Constraints:

        1 <= n <= 500
     */
    public static void main(String[] args) {
        System.out.println("Done...");
    }

    // Time: O(N)
    // Space: O(1)
    class Solution {
        private int MOD = (int)1e9 + 7;  // Modulus for calculations
        private static final int MAX_PAIRS = 510; // Maximum possible pairs value
        private long[] memoization = new long[MAX_PAIRS];  // Memoization array to store computed values
        private long calculateOrdersCount(long remainingPairs) {
            if (remainingPairs == 0)
                return 1;
            if (memoization[(int)remainingPairs] != -1)
                return memoization[(int)remainingPairs];
            long currentResult = calculateOrdersCount(remainingPairs - 1) * (2 * remainingPairs - 1) * remainingPairs % MOD;
            return memoization[(int)remainingPairs] = currentResult;
        }

        public int countOrders(int numPairs) {
            for(int i = 0 ; i < numPairs + 5 ; i ++){
                memoization[i] = -1 ;
            }
            return (int)calculateOrdersCount(numPairs);
        }
    }
}
