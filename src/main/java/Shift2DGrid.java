import java.util.ArrayList;
import java.util.List;

public class Shift2DGrid {
    /*
        Given a 2D grid of size m x n and an integer k. You need to shift the grid k times.
        In one shift operation:

        Element at grid[i][j] moves to grid[i][j + 1].
        Element at grid[i][n - 1] moves to grid[i + 1][0].
        Element at grid[m - 1][n - 1] moves to grid[0][0].
        Return the 2D grid after applying shift operation k times.

        Example 1.
        Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
        Output: [[9,1,2],[3,4,5],[6,7,8]]
        Example 2.
        Input: grid = [[3,8,1,9],[19,7,2,5],[4,6,11,10],[12,0,21,13]], k = 4
        Output: [[12,0,21,13],[3,8,1,9],[19,7,2,5],[4,6,11,10]]
        Example 3:

        Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 9
        Output: [[1,2,3],[4,5,6],[7,8,9]]


        Constraints:

        m == grid.length
        n == grid[i].length
        1 <= m <= 50
        1 <= n <= 50
        -1000 <= grid[i][j] <= 1000
        0 <= k <= 100
     */

    public static void main(String[] args) {
        int[][] grid = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println("Solution I: " + Solution_I.shiftGrid(grid, 9));
        System.out.println("Solution II: " + Solution_II.shiftGrid(grid, 9));
        System.out.println("Solution III: " + Solution_I.shiftGrid(grid, 9));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
            List<List<Integer>> result = new ArrayList();
            int rows = grid.length, col = grid[0].length;

            for (int r = 0; r < rows; r++)// filling all the rows of result with empty lists
                result.add(new ArrayList());
            k %= (rows * col);// every rows*col shifts grid became the same, skipping the similar shifts
            int dimension = rows * col;
            int begin = dimension - k;// element at (begin) will be at 0,0 in new grid
            int x = 0;// counter variable
            for (int i = begin; i < begin + dimension; i++) {
                int r = (i / col) % rows, c = i % col;// calculating row and cell for new grid
                result.get(x / col).add(grid[r][c]);// adding new element in new row
                x++;
            }
            return result;// returning result list
        }
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution_II {
        public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
            int m = grid.length;
            int n = grid[0].length;
            int total = m * n;
            // if shifting total times, it shifts back to original state
            k = k % (total);
            List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                List<Integer> list = new ArrayList<>();
                result.add(list);
                for (int j = 0; j < n; j++) {
                    // i * n + j original place index in 1D array
                    // i * n + j - k  is to get value k steps before
                    int index = ((i * n + j) - k + total) % total;
                    list.add(grid[index / n][index % n]);
                }
            }
            return result;
        }
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution_III {
        public static List<List<Integer>> shiftGrid(int[][] grid, int k) {
            int m = grid.length, n = grid[0].length;
            List<List<Integer>> result = new ArrayList<>(m);
            ArrayList<Integer> al = new ArrayList<>();
            for (int[] row : grid)
                for (int element : row)
                    al.add(element);

            //ROTATING THE LAST ELEMENTS
            while (k-- > 0) {
                al.add(0, al.remove(al.size() - 1));
            }

            int index = 0;
            for (int i = 0; i < m; i++) {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int j = 0; j < n; j++) {
                    temp.add(al.get(index++));
                }
                result.add(temp);
            }
            return result;

        }
    }
}
