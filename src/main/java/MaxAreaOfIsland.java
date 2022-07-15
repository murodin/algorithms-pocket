import java.util.ArrayDeque;
import java.util.Queue;

public class MaxAreaOfIsland {
    /*
        You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
        You may assume all four edges of the grid are surrounded by water.
        The area of an island is the number of cells with a value 1 in the island.
        Return the maximum area of an island in grid. If there is no island, return 0.

        Example 1.
        Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
        Output: 6
        Explanation: The answer is not 11, because the island must be connected 4-directionally.
        Example 2.
        Input: grid = [[0,0,0,0,0,0,0,0]]
        Output: 0


        Constraints:

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 50
        grid[i][j] is either 0 or 1.
     */
    public static void main(String[] args) {
        int[][] grid = {
                {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        System.out.println("Solution I: " + Solution_I.maxAreaOfIsland(grid));
        System.out.println("Solution II: " + Solution_II.maxAreaOfIsland(grid));
        System.out.println("Solution III: " + Solution_III.maxAreaOfIsland(grid));
    }

    // Time: O(rows*cols)
    // Space: O(rows*cols)
    static class Solution_I {
        public static int maxAreaOfIsland(int[][] grid) {
            int maxArea = 0;
            int rows = grid.length;
            int cols = grid[0].length;
            boolean[][] visited = new boolean[rows][cols];

            for(int row=0; row<rows; row++){
                for(int col=0; col<cols; col++){
                    if(grid[row][col] == 1 && !visited[row][col]){
                        int currArea = dfs(grid,visited,row,col,rows,cols);
                        maxArea = Math.max(maxArea,currArea);
                    }
                }
            }

            return maxArea;
        }

        private static int dfs(int[][] grid, boolean[][] visited, int row, int col, int rows, int cols){
            if(row<0 || col<0 || row>=rows || col>=cols || visited[row][col] || grid[row][col] == 0)
                return 0;

            visited[row][col] = true;

            int currArea = 1;
            currArea += dfs(grid,visited,row-1,col,rows,cols);
            currArea += dfs(grid,visited,row+1,col,rows,cols);
            currArea += dfs(grid,visited,row,col+1,rows,cols);
            currArea += dfs(grid,visited,row,col-1,rows,cols);

            return currArea;
        }
    }

    // Time: O(rows*cols)
    // Space : O(rows*cols)
    static class Solution_II {
        public static int maxAreaOfIsland(int[][] grid) {
            int maxArea = 0;
            int rows = grid.length;
            int cols = grid[0].length;
            boolean[][] visited = new boolean[rows][cols];

            for(int row=0; row<rows; row++){
                for(int col=0; col<cols; col++){
                    if(grid[row][col] == 1 && !visited[row][col]){
                        int currArea = bfs(grid,visited,row,col,rows,cols);
                        maxArea = Math.max(maxArea,currArea);
                    }
                }
            }

            return maxArea;
        }

        private static int bfs(int[][] grid, boolean[][] visited, int row, int col, int rows, int cols){
            Queue<int[]> queue = new ArrayDeque();
            queue.add(new int[]{row,col});
            int currArea = 0;

            while(queue.size() > 0){
                int[] arr = queue.remove();
                row = arr[0];
                col = arr[1];

                if(visited[row][col]) continue;

                visited[row][col] = true;
                currArea++;

                if(row-1 >= 0 && grid[row-1][col] == 1 && !visited[row-1][col]){
                    queue.add(new int[]{row-1,col});
                }
                if(row+1 < rows && grid[row+1][col] == 1 && !visited[row+1][col]){
                    queue.add(new int[]{row+1,col});
                }
                if(col-1 >= 0 && grid[row][col-1] == 1 && !visited[row][col-1]){
                    queue.add(new int[]{row,col-1});
                }
                if(col+1 < cols && grid[row][col+1] == 1 && !visited[row][col+1]){
                    queue.add(new int[]{row,col+1});
                }
            }

            return currArea;
        }
    }

    // Time: O(rows*cols*alpha) , alpha being constant can be ignored
    // Space: O(rows*cols)
    static class Solution_III {
        public static int maxAreaOfIsland(int[][] grid) {
            int maxArea = 0;
            int rows = grid.length;
            int cols = grid[0].length;
            int totalCells = rows*cols;
            int[] parent = new int[totalCells];
            int[] rank = new int[totalCells];
            int[] size = new int[totalCells];

            for(int row=0; row<rows; row++){
                for(int col=0; col<cols; col++){
                    if(grid[row][col] == 1){
                        int cell = row*cols+col;
                        parent[cell] = cell;
                        size[cell] = 1;
                    }
                }
            }

            for(int row=0; row<rows; row++){
                for(int col=0; col<cols; col++){
                    if(grid[row][col] == 1){
                        if(row-1 >=0 && grid[row-1][col] == 1){
                            union(row, col, row-1, col, cols, parent, rank, size);
                        }
                        if(row+1 < rows && grid[row+1][col] == 1){
                            union(row, col,row+1, col, cols, parent, rank, size);
                        }
                        if(col-1 >= 0 && grid[row][col-1] == 1){
                            union(row, col, row, col-1, cols, parent, rank, size);
                        }
                        if(col+1 < cols && grid[row][col+1] == 1){
                            union(row, col, row, col+1, cols, parent, rank, size);
                        }
                    }
                }
            }

            for(int head=0; head<totalCells; head++){
                maxArea = Math.max(maxArea,size[head]);
            }

            return maxArea;
        }

        private static void union(int row1, int col1, int row2, int col2, int cols, int[] parent, int[] rank, int[] size){
            int cell1 = row1*cols+col1;
            int cell2 = row2*cols+col2;

            int parCell1 = find(parent,cell1);
            int parCell2 = find(parent,cell2);

            if(parCell1 == parCell2) return;

            if(rank[parCell1] > rank[parCell2]){
                parent[parCell2] = parCell1;
                size[parCell1] += size[parCell2];
                size[parCell2] = 0;
            }else if(rank[parCell1] < rank[parCell2]){
                parent[parCell1] = parCell2;
                size[parCell2] += size[parCell1];
                size[parCell1] = 0;
            }else{
                parent[parCell2] = parCell1;
                size[parCell1] += size[parCell2];
                size[parCell2] = 0;
                rank[parCell1]++;
            }
        }

        private static int find(int[] parent, int cell){
            if(parent[cell] == cell) return cell;
            return parent[cell] = find(parent,parent[cell]);
        }
    }
}
