import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {

    /*
        Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
        A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
        All the visited cells of the path are 0.
        All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
        The length of a clear path is the number of visited cells of this path.

        Example 1:.
        Input: grid = [[0,1],[1,0]]
        Output: 2
        Example 2.
        Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
        Output: 4
        Example 3.
        Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
        Output: -1
     */

    public static void main(String[] args) {
        int[][] testGrid = {
                {0,0,0},
                {1,1,0},
                {1,1,0}
        };
        System.out.println("Shortest Path In Binary Matrix: " + Solution.shortestPathBinaryMatrix(testGrid));
    }

    static class Solution {
        public static int shortestPathBinaryMatrix(int[][] grid) {
            if(grid[0][0] == 1) return -1;
            int m = grid.length;
            int n = grid[0].length;

            Queue<int[]> q = new LinkedList<>();
            q.add(new int[]{0,0,1});
            grid[0][0] =1;

            int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
            while(!q.isEmpty()) {
                int size = q.size();
                while(size-- > 0) {
                    int[] point = q.poll();

                    if(point[0] ==  m-1 && point[1] == n-1)
                        return point[2];

                    for(int[] d : dir) {
                        int r = point[0] + d[0];
                        int c = point[1] + d[1];

                        if(r >= 0 && c >= 0 && r < m && c < n && grid[r][c] == 0){
                            q.add(new int[]{r, c, point[2]+1});
                            grid[r][c] = 1;
                        }
                    }
                }
            }

            return -1;
        }
    }
}
