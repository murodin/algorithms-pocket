import java.util.Arrays;

public class StoneGameII {
    /*
        Alice and Bob continue their games with piles of stones.
        There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones.
        Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
        On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
        The game continues until all the stones have been taken.
        Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.

        Example 1.
        Input: piles = [2,7,9,4,4]
        Output: 10
        Explanation:  If Alice takes one pile at the beginning, Bob takes two piles, then Alice takes 2 piles again. Alice can get 2 + 4 + 4 = 10 piles in total. If Alice takes two piles at the beginning, then Bob can take all three piles left. In this case, Alice get 2 + 7 = 9 piles in total. So we return 10 since it's larger.
        Example 2.
        Input: piles = [1,2,3,4,5,100]
        Output: 104

        Constraints:

        1 <= piles.length <= 100
        1 <= piles[i] <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.stoneGameII(new int[]{1,2,3,4,5,100}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int stoneGameII(int[] piles) {
            int[] presum =  Arrays.copyOf(piles, piles.length);
            for (int i = presum.length - 2; i >= 0; i--) presum[i] += presum[i + 1];
            return dfs(presum, 1, 0, new int[piles.length][piles.length]);
        }
        private static int dfs(int[] presum, int m, int p, int[][] memo) {
            if (p + 2 * m >= presum.length) {
                return presum[p];
            }
            if (memo[p][m] > 0) return memo[p][m];
            int res = 0, take = 0;
            for (int i = 1; i <= 2 * m; i++) {
                take = presum[p] - presum[p + i];
                res = Math.max(res, take + presum[p + i] - dfs(presum, Math.max(i, m), p + i, memo));
            }
            memo[p][m] = res;
            return res;
        }
    }
}
