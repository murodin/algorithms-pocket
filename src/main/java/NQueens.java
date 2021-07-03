import java.util.ArrayList;
import java.util.List;

public class NQueens {
    /*
        The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
        Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
        Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
     */

    public static void main(String[] args) {
        int testN = 4;
        System.out.println("Combination of Queens:" + Solution.solveNQueens(testN));
    }

    static class Solution {
        public static List<List<String>> result;

        public static List<List<String>> solveNQueens(int n) {
            result = new ArrayList<>();
            char[][] board = new char[n][n];

            // Fill the empty cell
            for(int i=0; i<n; i++) {
                for(int j=0; j<n; j++) {
                    board[i][j] = '.';
                }
            }

            List<int[]> queens = new ArrayList<>();
            dfs(board, 0, queens);
            return result;
        }

        private static void dfs(char[][] board, int r, List<int[]> queens) {
            // Check all the queens are placed
            if(queens.size() == board.length) {
                // Construct output
                List<String> rows = new ArrayList<>();
                for(char[] row: board) {
                    rows.add(new String(row));
                }
                result.add(rows);
            }

            // Try adding queens
            for(int c=0; c<board.length; c++){
                if(canQueenAdd(r,c,queens)) {
                    board[r][c] = 'Q';
                    queens.add(new int[]{r,c});
                    dfs(board, r+1, queens);
                    board[r][c] = '.';
                    queens.remove(queens.size()-1);
                }
            }
        }

        private static boolean canQueenAdd(int r, int c, List<int[]> queens) {
            for (int[] q: queens) {
                int dx = Math.abs(r-q[0]);
                int dy = Math.abs(c-q[1]);
                if(dx == 0 || dy == 0 || dx == dy) return false;
            }
            return true;
        }
    }
}
