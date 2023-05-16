import java.util.Arrays;

public class SudokuSolver {
    /*
        Write a program to solve a Sudoku puzzle by filling the empty cells.

        A sudoku solution must satisfy all of the following rules:

        Each of the digits 1-9 must occur exactly once in each row.
        Each of the digits 1-9 must occur exactly once in each column.
        Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
        The '.' character indicates empty cells.



        Example 1:


        Input: board = [['5','3','.','.','7','.','.','.','.'],['6','.','.','1','9','5','.','.','.'],['.','9','8','.','.','.','.','6','.'],['8','.','.','.','6','.','.','.','3'],['4','.','.','8','.','3','.','.','1'],['7','.','.','.','2','.','.','.','6'],['.','6','.','.','.','.','2','8','.'],['.','.','.','4','1','9','.','.','5'],['.','.','.','.','8','.','.','7','9']]
        Output: [['5','3','4','6','7','8','9','1','2'],['6','7','2','1','9','5','3','4','8'],['1','9','8','3','4','2','5','6','7'],['8','5','9','7','6','1','4','2','3'],['4','2','6','8','5','3','7','9','1'],['7','1','3','9','2','4','8','5','6'],['9','6','1','5','3','7','2','8','4'],['2','8','7','4','1','9','6','3','5'],['3','4','5','2','8','6','1','7','9']]
        Explanation: The input board is shown above and the only valid solution is shown below:




        Constraints:

        board.length == 9
        board[i].length == 9
        board[i][j] is a digit or '.'.
        It is guaranteed that the input board has only one solution.'
     */
    public static void main(String[] args) {
        char[][] board= {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        Solution.solveSudoku(board);
        System.out.println("Solution: " + Arrays.deepToString(board));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static void solveSudoku(char[][] board) {
            solve(board, 0, 0);
        }

        private static boolean solve(char[][] board, int row, int col) {
            if (row == board.length) {
                return true;
            }
            if (col == board[0].length) {
                return solve(board, row + 1, 0);
            }
            if (board[row][col] != '.') {
                return solve(board, row, col + 1);
            }
            
            for (char num = '1'; num <= '9'; num++) {
                if (isValidPlacement(board, row, col, num)) {
                    board[row][col] = num; 
                    if (solve(board, row, col + 1)) {
                        return true;
                    }
                    board[row][col] = '.';
                }
            }
            return false;
        }

        private static boolean isValidPlacement(char[][] board, int row, int col, char num) {
            for (int i = 0; i < board.length; i++) {
                if (board[i][col] == num) {
                    return false;
                }
                if (board[row][i] == num) {
                    return false;
                }

                int subgridRow = 3 * (row / 3) + i / 3; 
                int subgridCol = 3 * (col / 3) + i % 3; 

                if (board[subgridRow][subgridCol] == num) {
                    return false;
                }
            }
            return true;
        }
    }
}
