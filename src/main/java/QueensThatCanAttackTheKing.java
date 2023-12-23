import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueensThatCanAttackTheKing {
    /*
        On a 0-indexed 8 x 8 chessboard, there can be multiple black queens and one white king.
        You are given a 2D integer array queens where queens[i] = [xQueeni, yQueeni] represents the position of the ith black queen on the chessboard.
        You are also given an integer array king of length 2 where king = [xKing, yKing] represents the position of the white king.
        Return the coordinates of the black queens that can directly attack the king. You may return the answer in any order.

        Example 1:
        Input: queens = [[0,1],[1,0],[4,0],[0,4],[3,3],[2,4]], king = [0,0]
        Output: [[0,1],[1,0],[3,3]]
        Explanation: The diagram above shows the three queens that can directly attack the king and the three queens that cannot attack the king (i.e., marked with red dashes).
        Example 2:
        Input: queens = [[0,0],[1,1],[2,2],[3,4],[3,5],[4,4],[4,5]], king = [3,3]
        Output: [[2,2],[3,4],[4,4]]
        Explanation: The diagram above shows the three queens that can directly attack the king and the three queens that cannot attack the king (i.e., marked with red dashes).


        Constraints:

        1 <= queens.length < 64
        queens[i].length == king.length == 2
        0 <= xQueeni, yQueeni, xKing, yKing < 8
        All the given positions are unique.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.queensAttacktheKing(new int[][]{{0,1}, {1,0}, {4,0}, {0,4}, {3,3},{2,4}}, new int[]{0, 0}));
    }

    // Time: O(N)
    // Space: O(N^2)
    static class Solution {
        public static List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
            List<List<Integer>> result = new ArrayList<>();
            boolean[][] seen = new boolean[8][8];
            for (int[] queen : queens) seen[queen[0]][queen[1]] = true;
            int[] dirs = {-1, 0, 1};
            for (int dx : dirs) {
                for (int dy : dirs) {
                    if (dx == 0 && dy == 0) continue;
                    int x = king[0], y = king[1];
                    while (x + dx >= 0 && x + dx < 8 && y + dy >= 0 && y + dy < 8) {
                        x += dx;
                        y += dy;
                        if (seen[x][y]) {
                            result.add(Arrays.asList(x, y));
                            break;
                        }
                    }
                }
            }
            return result;
        }
    }
}
