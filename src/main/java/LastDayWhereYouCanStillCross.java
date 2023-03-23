import java.util.Arrays;

public class LastDayWhereYouCanStillCross {
    /*
        There is a 1-based binary matrix where 0 represents land and 1 represents water.
        You are given integers row and col representing the number of rows and columns in the matrix, respectively.
        Initially on day 0, the entire matrix is land. However, each day a new cell becomes flooded with water.
        You are given a 1-based 2D array cells, where cells[i] = [ri, ci] represents that on the ith day, the cell on the rith row and cith column (1-based coordinates)
        will be covered with water (i.e., changed to 1).
        You want to find the last day that it is possible to walk from the top to the bottom by only walking on land cells.
        You can start from any cell in the top row and end at any cell in the bottom row. You can only travel in the four cardinal directions (left, right, up, and down).
        Return the last day where it is possible to walk from the top to the bottom by only walking on land cells.

        Example 1.
        Input: row = 2, col = 2, cells = [[1,1],[2,1],[1,2],[2,2]]
        Output: 2
        Explanation: The above image depicts how the matrix changes each day starting from day 0.
        The last day where it is possible to cross from top to bottom is on day 2.
        Example 2.
        Input: row = 2, col = 2, cells = [[1,1],[1,2],[2,1],[2,2]]
        Output: 1
        Explanation: The above image depicts how the matrix changes each day starting from day 0.
        The last day where it is possible to cross from top to bottom is on day 1.
        Example 3.
        Input: row = 3, col = 3, cells = [[1,2],[2,1],[3,3],[2,2],[1,1],[1,3],[2,3],[3,2],[3,1]]
        Output: 3
        Explanation: The above image depicts how the matrix changes each day starting from day 0.
        The last day where it is possible to cross from top to bottom is on day 3.


        Constraints:

        2 <= row, col <= 2 * 104
        4 <= row * col <= 2 * 104
        cells.length == row * col
        1 <= ri <= row
        1 <= ci <= col
        All the values of cells are unique.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.latestDayToCross(
                3, 3, new int[][]{{1,2},{2,1},{3,3},{2,2},{1,1},{1,3},{2,3},{3,2},{3,1}}
        ));
    }

    static class DSU {
        static int[] parent;
        public DSU(int N) {
            parent = new int[N];
            for (int i = 0; i < N; i++) parent[i] = i;
        }
        public int find(int x) {
            if (parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }
        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }

    // Time:
    // Space:
    static class Solution {
        static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        public static int latestDayToCross(int row, int col, int[][] cells) {
            int M = row;
            int N = col;
            int[][] matrix = new int[M][N];
            for (int[] r : matrix) Arrays.fill(r, -1);
            DSU dsu = new DSU(M * N + 2);
            int top = M * N, bottom = M * N + 1;
            for (int j = 0; j < N; j++) {
                dsu.union(top, 0 * N + j); //first row all connect to top
                dsu.union(bottom, (M - 1) * N + j); //last row all connect to bottom
            }
            int index = cells.length - 1;
            while (dsu.find(top) != dsu.find(bottom) && index >= 0) {
                int[] cell = cells[index--];
                int i = cell[0] - 1, j = cell[1] - 1;
                matrix[i][j] = 0;
                for (int[] dir : dirs) {
                    int x = i + dir[0], y = j + dir[1];
                    if (x >= 0 && y >= 0 && x < M && y < N && matrix[x][y] == 0)
                        dsu.union(i * N + j, x * N + y);
                }
            }
            return index + 1;
        }
    }
}
