public class BattleshipsInABoard {
    /*
        Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships on board.

        Battleships can only be placed horizontally or vertically on board. In other words,
        they can only be made of the shape 1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size. At least one horizontal
        or vertical cell separates between two battleships (i.e., there are no adjacent battleships).

        Example 1:
        Input: board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
        Output: 2
        Example 2:
        Input: board = [["."]]
        Output: 0


        Constraints:

        m == board.length
        n == board[i].length
        1 <= m, n <= 200
        board[i][j] is either '.' or 'X'.


        Follow up: Could you do it in one-pass, using only O(1) extra memory and without modifying the values board?
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.countBattleships(new char[][]{{'.'}}));
    }

    // Time: O(MxN)
    // Space: O(1)
    static class Solution {
        public static int countBattleships(char[][] board) {
            int m = board.length;
            if (m==0) return 0;
            int n = board[0].length;

            int count=0;

            for (int i=0; i<m; i++) {
                for (int j=0; j<n; j++) {
                    if (board[i][j] == '.') continue;
                    if (i > 0 && board[i-1][j] == 'X') continue;
                    if (j > 0 && board[i][j-1] == 'X') continue;
                    count++;
                }
            }

            return count;
        }
    }
}
