public class StoneGameV {
    /*
        There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.
        In each round of the game, Alice divides the row into two non-empty rows (i.e. left row and right row),
        then Bob calculates the value of each row which is the sum of the values of all the stones in this row.
        Bob throws away the row which has the maximum value, and Alice's score increases by the value of the remaining row.
        If the value of the two rows are equal, Bob lets Alice decide which row will be thrown away. The next round starts with the remaining row.
        The game ends when there is only one stone remaining. Alice's is initially zero.
        Return the maximum score that Alice can obtain.


        Example 1:

        Input: stoneValue = [6,2,3,4,5,5]
        Output: 18
        Explanation: In the first round, Alice divides the row to [6,2,3], [4,5,5]. The left row has the value 11 and the right row has value 14. Bob throws away the right row and Alice's score is now 11.
        In the second round Alice divides the row to [6], [2,3]. This time Bob throws away the left row and Alice's score becomes 16 (11 + 5).
        The last round Alice has only one choice to divide the row which is [2], [3]. Bob throws away the right row and Alice's score is now 18 (16 + 2). The game ends because only one stone is remaining in the row.
        Example 2:

        Input: stoneValue = [7,7,7,7,7,7,7]
        Output: 28
        Example 3:

        Input: stoneValue = [4]
        Output: 0


        Constraints:

        1 <= stoneValue.length <= 500
        1 <= stoneValue[i] <= 106
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.stoneGameV(new int[]{6,2,3,4,5,5}));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        public static int stoneGameV(int[] stoneValue) {
            int n = stoneValue.length;
            int[][] dp = new int[n][n], max = new int[n][n];
            for(int i = 0; i < n; i++) {
                max[i][i] = stoneValue[i];
            }
            for(int j = 1; j < n; j++) {
                int mid = j, sum = stoneValue[j], rightHalf = 0;
                for(int i = j-1; i >= 0; i--) {
                    sum += stoneValue[i];
                    while((rightHalf + stoneValue[mid]) * 2 <= sum) {
                        rightHalf += stoneValue[mid--];
                    }
                    dp[i][j] = rightHalf * 2 == sum ? max[i][mid] : (mid == i ? 0 : max[i][mid - 1]);
                    dp[i][j] = Math.max(dp[i][j], mid == j ? 0 : max[j][mid + 1]);
                    max[i][j] = Math.max(max[i][j - 1], dp[i][j] + sum);
                    max[j][i] = Math.max(max[j][i + 1], dp[i][j] + sum);
                }
            }
            return dp[0][n-1];
        }
    }
}
