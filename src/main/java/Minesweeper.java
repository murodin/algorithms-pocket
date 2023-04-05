import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Minesweeper {
    /*
        Let's play the minesweeper game (Wikipedia, online game)!
        You are given an m x n char matrix board representing the game board where:

        'M' represents an unrevealed mine,
        'E' represents an unrevealed empty square,
        'B' represents a revealed blank square that has no adjacent mines (i.e., above, below, left, right, and all 4 diagonals),
        digit ('1' to '8') represents how many mines are adjacent to this revealed square, and
        'X' represents a revealed mine.
        You are also given an integer array click where click = [clickr, clickc] represents the next click position among all the unrevealed squares ('M' or 'E').

        Return the board after revealing this position according to the following rules:

        If a mine 'M' is revealed, then the game is over. You should change it to 'X'.
        If an empty square 'E' with no adjacent mines is revealed, then change it to a revealed blank 'B' and all of its adjacent unrevealed squares should be revealed recursively.
        If an empty square 'E' with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
        Return the board when no more squares will be revealed.


        Example 1.
        Input: board = [['E','E','E','E','E'],['E','E','M','E','E'],['E','E','E','E','E'],['E','E','E','E','E']], click = [3,0]
        Output: [['B','1','E','1','B'],['B','1','M','1','B'],['B','1','1','1','B'],['B','B','B','B','B']]
        Example 2.
        Input: board = [['B','1','E','1','B'],['B','1','M','1','B'],['B','1','1','1','B'],['B','B','B','B','B']], click = [1,2]
        Output: [['B','1','E','1','B'],['B','1','X','1','B'],['B','1','1','1','B'],['B','B','B','B','B']]


        Constraints:

        m == board.length
        n == board[i].length
        1 <= m, n <= 50
        board[i][j] is either 'M', 'E', 'B', or a digit from '1' to '8'.
        click.length == 2
        0 <= clickr < m
        0 <= clickc < n
        board[clickr][clickc] is either 'M' or 'E'.
     */
    public static void main(String[] args) {
        char[][] board = new char[][]{{'E','E','E','E','E'},{'E','E','M','E','E'},{'E','E','E','E','E'},{'E','E','E','E','E'}};
        System.out.println("Solution: " + Arrays.deepToString(Solution.updateBoard(board, new int[]{3, 0})));
    }

    // Time: O(M * N)
    // Space: O(M * N)
    static class Solution {
        private static final int[][] DIRS = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };
        public static char[][] updateBoard(char[][] board, int[] click) {
            if (board == null || click == null) {
                throw new IllegalArgumentException("Inputs are null");
            }
            if (board[click[0]][click[1]] != 'M' && board[click[0]][click[1]] != 'E') {
                return board;
            }
            if (board[click[0]][click[1]] == 'M') {
                board[click[0]][click[1]] = 'X';
                return board;
            }

            int mines = getMinesCount(board, click[0], click[1]);
            if (mines != 0) {
                board[click[0]][click[1]] = (char) (mines + '0');
                return board;
            }
            board[click[0]][click[1]] = 'B';

            Queue<int[]> queue = new LinkedList<>();
            queue.offer(click);
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                for (int[] d : DIRS) {
                    int x = cur[0] + d[0];
                    int y = cur[1] + d[1];
                    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'E') {
                        continue;
                    }
                    mines = getMinesCount(board, x, y);
                    if (mines != 0) {
                        board[x][y] = (char) (mines + '0');
                        continue;
                    }
                    board[x][y] = 'B';
                    queue.offer(new int[] { x, y });
                }
            }

            return board;
        }

        private static int getMinesCount(char[][] board, int x, int y) {
            int mines = 0;
            for (int[] d : DIRS) {
                int r = x + d[0];
                int c = y + d[1];
                if (r >= 0 && r < board.length && c >= 0 && c < board[0].length && board[r][c] == 'M') {
                    mines++;
                }
            }
            return mines;
        }
    }
}
