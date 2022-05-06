public class NQueensII {
    /*
        The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
        Given an integer n, return the number of distinct solutions to the n-queens puzzle.

        Example 1.
        Input: n = 4
        Output: 2
        Explanation: There are two distinct solutions to the 4-queens puzzle as shown.
        Example 2.
        Input: n = 1
        Output: 1
     */

    public static void main(String[] args) {
        System.out.println("Solution I: " + Solution_I.totalNQueens(4));
        System.out.println("Solution II: " + Solution_II.totalNQueens(4));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution_I {
        static int ans = 0;
        public static int totalNQueens(int n) {
            int[][] chess = new int[n][n];
            int r = 0;
            solve(chess, r);
            return ans;
        }

        private static void solve(int[][] chess, int r) {
            if (r == chess.length) {
                ans++;
                return;
            }

            for (int c = 0; c < chess.length; c++) {
                if (isQueenSafe(chess, r, c)) {
                    chess[r][c] = 1;
                    solve(chess, r + 1);
                    chess[r][c] = 0;
                }
            }
        }

        private static boolean isQueenSafe(int[][] chess, int r, int c) {
            for (int i = r - 1, j = c; i >= 0; i--) {
                if (chess[i][j] == 1) return false;
            }
            for (int i = r - 1, j = c - 1; i >= 0 && j >= 0; i--, j--){
                if (chess[i][j] == 1) return false;
            }
            for (int i = r - 1, j = c + 1; i >= 0 && j < chess.length;i--, j++) {
                if (chess[i][j] == 1) return false;
            }
            return true;
        }
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution_II {
        static int res = 0;
        public static int totalNQueens(int n) {
            int[][] chess = new int[n][n];
            int row = 0;
            solve(chess, row);
            return res;
        }

        private static void solve(int[][] chess, int row) {
            if (row == chess.length) {
                res++;
                return;
            }

            for (int col = 0; col < chess.length; col++) {
                if (isQueenSafe(chess, row, col)) {
                    chess[row][col] = 1;
                    solve(chess, row + 1);
                    chess[row][col] = 0;
                }
            }
        }

        private static boolean isQueenSafe(int[][] chess, int row, int col) {
            //checking vertically - up
            for (int i = row - 1, j = col; i >= 0; i--) {
                if (chess[i][j] == 1) {
                    return false;
                }
            }
            //checking for left diagonal - up
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
                if (chess[i][j] == 1) {
                    return false;
                }
            }
            //checking for right diagonal - up
            for (int i = row - 1, j = col + 1; i >= 0 && j < chess.length; i--, j++) {
                if (chess[i][j] == 1) {
                    return false;
                }
            }
            return true;
        }
    }
}
