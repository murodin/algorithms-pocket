public class DungeonGame {
    /*
        The demons had captured the princess and imprisoned her in the bottom-right corner of a dungeon. The dungeon consists of m x n rooms laid out in a 2D grid.
        Our valiant knight was initially positioned in the top-left room and must fight his way through dungeon to rescue the princess.
        The knight has an initial health point represented by a positive integer. If at any point his health point drops to 0 or below, he dies immediately.
        Some rooms are guarded by demons (represented by negative integers), so the knight loses health upon entering these rooms;
        other rooms are either empty (represented as 0) or contain magic orbs that increase the knight's health (represented by positive integers).
        To reach the princess as quickly as possible, the knight decides to move only rightward or downward in each step.
        Return the knight's minimum initial health so that he can rescue the princess.
        Note that any room can contain threats or power-ups, even the first room the knight enters and the bottom-right room where the princess is imprisoned.

        Example 1:
        Input: dungeon = [[-2,-3,3],[-5,-10,1],[10,30,-5]]
        Output: 7
        Explanation: The initial health of the knight must be at least 7 if he follows the optimal path: RIGHT-> RIGHT -> DOWN -> DOWN.
        Example 2:
        Input: dungeon = [[0]]
        Output: 1
        Constraints:

        m == dungeon.length
        n == dungeon[i].length
        1 <= m, n <= 200
        -1000 <= dungeon[i][j] <= 1000
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.calculateMinimumHP(new int[][]{{-2,-3,3},{-5,-10,1},{10,30,-5}}));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        public static int calculateMinimumHP(int[][] dungeon) {
            int m = dungeon.length;
            int n = m == 0 ? 0 : dungeon[0].length;
            int[][] minRequired = new int[m + 1][n + 1];
            for (int i = 0; i < m + 1; i++) {
                minRequired[i][n] = Integer.MAX_VALUE;
            }
            for (int j = 0; j < n + 1; j++) {
                minRequired[m][j] = Integer.MAX_VALUE;
            }
            minRequired[m][n - 1] = 0;
            minRequired[m - 1][n] = 0;
            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    minRequired[i][j] = Math.max(
                            Math.min(minRequired[i + 1][j], minRequired[i][j + 1]) - dungeon[i][j], 0
                    );
                }
            }
            return minRequired[0][0] + 1;
        }
    }
}
