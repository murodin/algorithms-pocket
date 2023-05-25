public class StoneGameVII {
    /*
        Alice and Bob take turns playing a game, with Alice starting first.
        There are n stones arranged in a row. On each player's turn,
        they can remove either the leftmost stone or the rightmost stone from the row and receive points equal to the sum of the remaining stones'
        values in the row. The winner is the one with the higher score when there are no stones left to remove.
        Bob found that he will always lose this game (poor Bob, he always loses), so he decided to minimize the score's difference.
        Alice's goal is to maximize the difference in the score.
        Given an array of integers stones where stones[i] represents the value of the ith stone from the left, return the difference in Alice and
        Bob's score if they both play optimally.



        Example 1:

        Input: stones = [5,3,1,4,2]
        Output: 6
        Explanation:
        - Alice removes 2 and gets 5 + 3 + 1 + 4 = 13 points. Alice = 13, Bob = 0, stones = [5,3,1,4].
        - Bob removes 5 and gets 3 + 1 + 4 = 8 points. Alice = 13, Bob = 8, stones = [3,1,4].
        - Alice removes 3 and gets 1 + 4 = 5 points. Alice = 18, Bob = 8, stones = [1,4].
        - Bob removes 1 and gets 4 points. Alice = 18, Bob = 12, stones = [4].
        - Alice removes 4 and gets 0 points. Alice = 18, Bob = 12, stones = [].
        The score difference is 18 - 12 = 6.
        Example 2:

        Input: stones = [7,90,5,1,100,10,10,2]
        Output: 122


        Constraints:

        n == stones.length
        2 <= n <= 1000
        1 <= stones[i] <= 1000
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.stoneGameVII(new int[]{7,90,5,1,100,10,10,2}));
    }

    // Time: O(2^N)
    // Space: O(N^3)
    static class Solution {
        static int[] preSum;
        static Integer[][][] memo;
        public static int stoneGameVII(int[] stones) {
            int n = stones.length;
            preSum = new int[n + 1];
            memo = new Integer[n][n][2];
            for (int i = 0; i < n; i++)
                preSum[i + 1] = preSum[i] + stones[i];
            return dp(0, n - 1, 1);
        }
        static int getSum(int left, int right) {
            return preSum[right + 1] - preSum[left];
        }
        static int dp(int left, int right, int isAlice) {
            if (left == right) return 0; // only 1 store, score = 0
            if (memo[left][right][isAlice] != null) return memo[left][right][isAlice];
            if (isAlice == 1) {
                int a = dp(left + 1, right, 1 - isAlice) + getSum(left + 1, right);
                int b = dp(left, right - 1, 1 - isAlice) + getSum(left, right - 1);
                return memo[left][right][isAlice] = Math.max(a, b);
            } else {
                int a = dp(left + 1, right, 1 - isAlice) - getSum(left + 1, right);
                int b = dp(left, right - 1, 1 - isAlice) - getSum(left, right - 1);
                return memo[left][right][isAlice] = Math.min(a, b);
            }
        }
    }
}
