public class UniquePathsIII {

    /*
        You are given an m x n integer array grid where grid[i][j] could be:
        1 representing the starting square. There is exactly one starting square.
        2 representing the ending square. There is exactly one ending square.
        0 representing empty squares we can walk over.
        -1 representing obstacles that we cannot walk over.
        Return the number of 4-directional walks from the starting square to the ending square, that walk over every non-obstacle square exactly once.

        Example 1.
        Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,2,-1]]
        Output: 2
        Explanation: We have the following two paths:
        1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2)
        2. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2)
        Example 2.
        Input: grid = [[1,0,0,0],[0,0,0,0],[0,0,0,2]]
        Output: 4
        Explanation: We have the following four paths:
        1. (0,0),(0,1),(0,2),(0,3),(1,3),(1,2),(1,1),(1,0),(2,0),(2,1),(2,2),(2,3)
        2. (0,0),(0,1),(1,1),(1,0),(2,0),(2,1),(2,2),(1,2),(0,2),(0,3),(1,3),(2,3)
        3. (0,0),(1,0),(2,0),(2,1),(2,2),(1,2),(1,1),(0,1),(0,2),(0,3),(1,3),(2,3)
        4. (0,0),(1,0),(2,0),(2,1),(1,1),(0,1),(0,2),(0,3),(1,3),(1,2),(2,2),(2,3)
        Example 3.
        Input: grid = [[0,1],[2,0]]
        Output: 0
        Explanation: There is no path that walks over every empty square exactly once.
        Note that the starting and ending square can be anywhere in the grid.


        Constraints:

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 20
        1 <= m * n <= 20
        -1 <= grid[i][j] <= 2
        There is exactly one starting cell and one ending cell.
     */

    public static void main(String[] args) {
        int[][] grid = {{1,0,0,0},{0,0,0,0},{0,0,2,-1}};
        System.out.println("Solution: " + Solution.uniquePathsIII(grid));
    }

    // Time:
    // Space:
    static class Solution {
        static int paths = 0;
        static int[][] directions = {{1,0}, {0,1}, {-1,0}, {0,-1}};
        public static int uniquePathsIII(int[][] grid) {
            int startRow=0, startCol=0, endRow=0, endCol=0;
            int m = grid.length;
            int n = grid[0].length;
            int countEmptySpots = 0;
            for(int i = 0;i<m;i++){
                for(int j =0;j<n;j++){
                    if(grid[i][j] == 1){
                        startRow = i;
                        startCol = j;
                    } else if(grid[i][j] == 0) {
                        countEmptySpots++;
                    }
                }
            }
            boolean[][] visited = new boolean[m][n];
            visited[startRow][startCol] = true;
            dfs(startRow, startCol, grid, visited, 0, countEmptySpots);
            return Solution.paths;
        }

        private static void dfs(int currentRow, int currentCol, int[][] grid, boolean[][] visited, int walk, int countEmptySpots){
            if(grid[currentRow][currentCol] == 2 && walk == countEmptySpots+1){
                Solution.paths++;
                return;
            }
            int m = grid.length;
            int n = grid[0].length;

            for(int[] direction: directions){
                int row = currentRow+direction[0];
                int col = currentCol+direction[1];
                if(row<m && row>=0 && col<n && col>=0 && !visited[row][col] && grid[row][col] != -1){
                    visited[row][col] = true;
                    dfs(row, col, grid, visited, walk+1, countEmptySpots);
                    visited[row][col] = false;
                }
            }

        }
    }
}
