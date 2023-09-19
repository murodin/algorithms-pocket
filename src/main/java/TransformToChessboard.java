public class TransformToChessboard {
    /*
        You are given an n x n binary grid board. In each move, you can swap any two rows with each other, or any two columns with each other.
        Return the minimum number of moves to transform the board into a chessboard board. If the task is impossible, return -1.
        A chessboard board is a board where no 0's and no 1's are 4-directionally adjacent.

        Example 1:


        Input: board = [[0,1,1,0],[0,1,1,0],[1,0,0,1],[1,0,0,1]]
        Output: 2
        Explanation: One potential sequence of moves is shown.
        The first move swaps the first and second column.
        The second move swaps the second and third row.
        Example 2:


        Input: board = [[0,1],[1,0]]
        Output: 0
        Explanation: Also note that the board with 0 in the top left corner, is also a valid chessboard.
        Example 3:


        Input: board = [[1,0],[1,0]]
        Output: -1
        Explanation: No matter what sequence of moves you make, you cannot end with a valid chessboard.


        Constraints:

        n == board.length
        n == board[i].length
        2 <= n <= 30
        board[i][j] is either 0 or 1.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.movesToChessboard(new int[][]{{0, 1}, {0, 1}}));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static int movesToChessboard(int[][] b) {
            int N = b.length, rowSum = 0, colSum = 0, rowSwap = 0, colSwap = 0;
            for (int[] ints : b)
                for (int j = 0; j < N; ++j)
                    if ((b[0][0] ^ ints[0] ^ b[0][j] ^ ints[j]) == 1) return -1;
            for (int i = 0; i < N; ++i) {
                rowSum += b[0][i];
                colSum += b[i][0];
                if (b[i][0] == i % 2) rowSwap ++;
                if (b[0][i] == i % 2) colSwap ++ ;
            }
            if (rowSum != N / 2 && rowSum != (N + 1) / 2) return -1;
            if (colSum != N / 2 && colSum != (N + 1) / 2) return -1;
            if (N % 2 == 1) {
                if (colSwap % 2 == 1) colSwap = N - colSwap;
                if (rowSwap % 2 == 1) rowSwap = N - rowSwap;
            } else {
                colSwap = Math.min(N - colSwap, colSwap);
                rowSwap = Math.min(N - rowSwap, rowSwap);
            }
            return (colSwap + rowSwap) / 2;
        }
    }
}
