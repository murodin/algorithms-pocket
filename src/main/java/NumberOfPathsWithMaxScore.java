import java.util.Arrays;
import java.util.List;

public class NumberOfPathsWithMaxScore {
    /*
        You are given a square board of characters. You can move on the board starting at the bottom right square marked with the character 'S'.
        You need to reach the top left square marked with the character 'E'. The rest of the squares are labeled either with a numeric character 1, 2, ..., 9 or
        with an obstacle 'X'. In one move you can go up, left or up-left (diagonally) only if there is no obstacle there.

        Return a list of two integers: the first integer is the maximum sum of numeric characters you can collect,
        and the second is the number of such paths that you can take to get that maximum sum, taken modulo 10^9 + 7.

        In case there is no path, return [0, 0].



        Example 1:

        Input: board = ["E23","2X2","12S"]
        Output: [7,1]
        Example 2:

        Input: board = ["E12","1X1","21S"]
        Output: [4,2]
        Example 3:

        Input: board = ["E11","XXX","11S"]
        Output: [0,0]


        Constraints:

        2 <= board.length == board[i].length <= 100
     */
    public static void main(String[] args) {
        System.out.println("solution: " + Arrays.toString(Solution.pathsWithMaxScore(List.of("E12", "1X1", "21S"))));
    }

    // Time: O(MxN)
    // Space: O(MxN)
    static class Solution {
        private static final int[][] DIRS = new int[][]{{0, -1}, {-1, 0}, {-1, -1}};
        public static int[] pathsWithMaxScore(List<String> board) {
            int m = board.size(), n = board.get(0).length();
            int[][] dpSum = new int[m][n];
            int[][] dpCnt = new int[m][n];
            dpCnt[m - 1][n - 1] = 1;
            for (int r = m - 1; r >= 0; r--) {
                for (int c = n - 1; c >= 0; c--) {
                    if (dpCnt[r][c] == 0) continue;
                    for (int[] dir : DIRS) {
                        int nr = r + dir[0], nc = c + dir[1];
                        if (nr >= 0 && nc >= 0 && board.get(nr).charAt(nc) != 'X') {
                            int nsum = dpSum[r][c];
                            if (board.get(nr).charAt(nc) != 'E')
                                nsum += board.get(nr).charAt(nc) - '0';
                            if (nsum > dpSum[nr][nc]) {
                                dpCnt[nr][nc] = dpCnt[r][c];
                                dpSum[nr][nc] = nsum;
                            } else if (nsum == dpSum[nr][nc]) {
                                dpCnt[nr][nc] = (dpCnt[nr][nc] + dpCnt[r][c]) % 1000000007;
                            }
                        }
                    }
                }
            }
            return new int[]{dpSum[0][0], dpCnt[0][0]};
        }
    }
}
