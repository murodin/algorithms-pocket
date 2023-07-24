public class KnightProbabilityInChessboard {
    /*
        On an n x n chessboard, a knight starts at the cell (row, column) and attempts to make exactly k moves.
        The rows and columns are 0-indexed, so the top-left cell is (0, 0), and the bottom-right cell is (n - 1, n - 1).
        A chess knight has eight possible moves it can make, as illustrated below. Each move is two cells in a cardinal direction, then one cell in an orthogonal direction.
        Each time the knight is to move, it chooses one of eight possible moves uniformly at random (even if the piece would go off the chessboard) and moves there.
        The knight continues moving until it has made exactly k moves or has moved off the chessboard.
        Return the probability that the knight remains on the board after it has stopped moving.

        Example 1:

        Input: n = 3, k = 2, row = 0, column = 0
        Output: 0.06250
        Explanation: There are two moves (to (1,2), (2,1)) that will keep the knight on the board.
        From each of those positions, there are also two moves that will keep the knight on the board.
        The total probability the knight stays on the board is 0.0625.
        Example 2:

        Input: n = 1, k = 0, row = 0, column = 0
        Output: 1.00000


        Constraints:

        1 <= n <= 25
        0 <= k <= 100
        0 <= row, column <= n - 1
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.knightProbability(3, 2, 0, 0));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        public static double knightProbability(int n, int k, int row, int column) {
            int[][] moves = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2}, {1, -2}, {1, 2}, {2, -1}, {2, 1}};
            double [][] curBoard;
            double [][] nextBoard = new double[n][n];
            nextBoard[row][column] = 1.0;
            for (int i = 0; i < k; i++) {
                curBoard = nextBoard;
                nextBoard = new double[n][n];
                for (int r = 0; r < n; r++) {
                    for (int c = 0; c < n; c++) {
                        if (curBoard[r][c] == 0.0)
                            continue;
                        for (int [] dir : moves) {
                            int nextRow = r + dir[0];
                            int nextCol = c + dir[1];

                            if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < n) {
                                nextBoard[nextRow][nextCol] += curBoard[r][c] / 8.0;
                            }
                        }
                    }
                }
            }

            double total = 0.0;
            for (int r = 0; r < n; r++)
                for (int c = 0; c < n; c++)
                    total += nextBoard[r][c];
            return total;
        }
    }
}
