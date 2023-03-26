import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StrangePrinterII {
    /*
        There is a strange printer with the following two special requirements:
        On each turn, the printer will print a solid rectangular pattern of a single color on the grid. This will cover up the existing colors in the rectangle.
        Once the printer has used a color for the above operation, the same color cannot be used again.
        You are given a m x n matrix targetGrid, where targetGrid[row][col] is the color in the position (row, col) of the grid.
        Return true if it is possible to print the matrix targetGrid, otherwise, return false.

        Example 1.
        Input: targetGrid = [[1,1,1,1],[1,2,2,1],[1,2,2,1],[1,1,1,1]]
        Output: true
        Example 2.
        Input: targetGrid = [[1,1,1,1],[1,1,3,3],[1,1,3,4],[5,5,1,4]]
        Output: true
        Example 3.
        Input: targetGrid = [[1,2,1],[2,1,2],[1,2,1]]
        Output: false
        Explanation: It is impossible to form targetGrid because it is not allowed to print the same color in different turns.


        Constraints:

        m == targetGrid.length
        n == targetGrid[i].length
        1 <= m, n <= 60
        1 <= targetGrid[row][col] <= 60
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.isPrintable(new int[][]{{1,1,1,1},{1,1,3,3},{1,1,3,4},{5,5,1,4}}));
    }

    // Time: O(N^3)
    // Space: O(N)
    static class Solution {
        public static boolean isPrintable(int[][] targetGrid) {
            Map<Integer, int[]> pos = new HashMap<>();
            int n = targetGrid.length;
            int m = targetGrid[0].length;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    pos.putIfAbsent(targetGrid[i][j], new int[]{n, m, -1, -1});
                    int[] coord = pos.get(targetGrid[i][j]);
                    coord[0] = Math.min(coord[0], i);
                    coord[1] = Math.min(coord[1], j);
                    coord[2] = Math.max(coord[2], i);
                    coord[3] = Math.max(coord[3], j);
                }
            }
            Set<Integer> colors = new HashSet<>(pos.keySet());
            while (!colors.isEmpty()) {
                Set<Integer> next = new HashSet<>();
                for (int color : colors) {
                    if (!erase(targetGrid, pos.get(color), color)) {
                        next.add(color);
                    }
                }
                if (colors.size() == next.size()) {
                    return false;
                }
                colors = next;
            }
            return true;
        }

        private static boolean erase(int[][] targetGrid, int[] coord, int color) {
            for (int i = coord[0]; i <= coord[2]; i++) {
                for (int j = coord[1]; j <= coord[3]; j++) {
                    if (targetGrid[i][j] > 0 && targetGrid[i][j] != color) {
                        return false;
                    }
                }
            }
            for (int i = coord[0]; i <= coord[2]; i++) {
                for (int j = coord[1]; j <= coord[3]; j++) {
                    targetGrid[i][j] = 0;
                }
            }
            return true;
        }
    }
}
