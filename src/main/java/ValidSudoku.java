public class ValidSudoku {
    /*
        Determine if a 9 x 9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

        Each row must contain the digits 1-9 without repetition.
        Each column must contain the digits 1-9 without repetition.
        Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
        Note:

        A Sudoku board (partially filled) could be valid but is not necessarily solvable.
        Only the filled cells need to be validated according to the mentioned rules.


        Example 1:


        Input: board =
        [['5','3','.','.','7','.','.','.','.']
        ,['6','.','.','1','9','5','.','.','.']
        ,['.','9','8','.','.','.','.','6','.']
        ,['8','.','.','.','6','.','.','.','3']
        ,['4','.','.','8','.','3','.','.','1']
        ,['7','.','.','.','2','.','.','.','6']
        ,['.','6','.','.','.','.','2','8','.']
        ,['.','.','.','4','1','9','.','.','5']
        ,['.','.','.','.','8','.','.','7','9']]
        Output: true
        Example 2:

        Input: board =
        [['8','3','.','.','7','.','.','.','.']
        ,['6','.','.','1','9','5','.','.','.']
        ,['.','9','8','.','.','.','.','6','.']
        ,['8','.','.','.','6','.','.','.','3']
        ,['4','.','.','8','.','3','.','.','1']
        ,['7','.','.','.','2','.','.','.','6']
        ,['.','6','.','.','.','.','2','8','.']
        ,['.','.','.','4','1','9','.','.','5']
        ,['.','.','.','.','8','.','.','7','9']]
        Output: false
        Explanation: Same as Example 1, except with the 5 in the top left corner being modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.


        Constraints:

        board.length == 9
        board[i].length == 9
        board[i][j] is a digit 1-9 or '.'.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.isValidSudoku(
                new char[][] {
                        {'5','3','.','.','7','.','.','.','.'}
                        ,{'6','.','.','1','9','5','.','.','.'}
                        ,{'.','9','8','.','.','.','.','6','.'}
                        ,{'8','.','.','.','6','.','.','.','3'}
                        ,{'4','.','.','8','.','3','.','.','1'}
                        ,{'7','.','.','.','2','.','.','.','6'}
                        ,{'.','6','.','.','.','.','2','8','.'}
                        ,{'.','.','.','4','1','9','.','.','5'}
                        ,{'.','.','.','.','8','.','.','7','9'}    
                }
        ));
    }

    // Time: O(1)
    // Space: O(1)
    static class Solution {
        public static boolean isValidSudoku(char[][] board) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    if (board[i][j] != '.' && !valid(board, i, j)) {
                        return false;
                    }
                }
            }

            return true;
        }

        private static boolean valid(char[][] board, int i, int j) {
            // check in a single row
            for (int col = 0; col < 9; col++) {
                if (col != j && board[i][col] == board[i][j]) return false;
            }

            // check in a single col
            for (int row = 0; row < 9; row++) {
                if (row != i && board[row][j] == board[i][j]) return false;
            }

            // check in a 3x3 sub-box
            int row = (i/3) * 3;
            int col = (j/3) * 3;
            for (int r = row; r < row+3; r++) {
                for (int c = col; c < col+3; c++) {
                    if (i != r && c != j && board[i][j] == board[r][c]) {
                        return false;
                    }
                }
            }

            return true;
        }
    }
}
