public class WordSearch {
    /*
        Given an m x n grid of characters board and a string word, return true if word exists in the grid.
        The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
        The same letter cell may not be used more than once.

        Example 1.
        Input: board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 'ABCCED'
        Output: true
        Example 2.
        Input: board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 'SEE'
        Output: true
        Example 3.
        Input: board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 'ABCB'
        Output: false


        Constraints:

        m == board.length
        n = board[i].length
        1 <= m, n <= 6
        1 <= word.length <= 15
        board and word consists of only lowercase and uppercase English letters.


        Follow up: Could you use search pruning to make your solution faster with a larger board?
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.exist(new char[][]{
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        }, "ABCCED"));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static boolean exist(char[][] board, String word) {
            char c = word.charAt(0);
            int m = board.length;
            int n = board[0].length;
            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++)
                    if (c == board[i][j] && sub(board, word, i, j, 0, m, n, c))
                        return true;

            return false;
        }

        private static boolean exist(char[][] board, String word, int i, int j, int index, int m, int n) {
            if (word.length() == index)
                return true;
            char c = word.charAt(index);

            if (i > 0 && board[i - 1][j] == c
                    && sub(board, word, i - 1, j, index, m, n, c)) //top
                return true;
            if (i < m - 1 && board[i + 1][j] == c
                    && sub(board, word, i + 1, j, index, m, n, c)) //down
                return true;
            if (j > 0 && board[i][j - 1] == c && sub(board, word, i, j - 1, index, m, n, c)) //left
                return true;
            if (j < n - 1 && board[i][j + 1] == c) //right
                return sub(board, word, i, j + 1, index, m, n, c);

            return false;
        }

        private static boolean sub(char[][] board, String word, int i, int j,
                                    int index, int m, int n, char c) {
            board[i][j] = 0;
            if (exist(board, word, i, j, index + 1, m, n))
                return true;
            else board[i][j] = c;
            return false;
        }
    }
}
