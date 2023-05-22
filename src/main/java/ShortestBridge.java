import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class ShortestBridge {
    /*
        You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
        An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.
        You may change 0's to 1's to connect the two islands to form one island.
        Return the smallest number of 0's you must flip to connect the two islands.

        Example 1:

        Input: grid = [[0,1],[1,0]]
        Output: 1
        Example 2:

        Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
        Output: 2
        Example 3:

        Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
        Output: 1


        Constraints:

        n == grid.length == grid[i].length
        2 <= n <= 100
        grid[i][j] is either 0 or 1.
        There are exactly two islands in grid.
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    // Time: O(4^N)
    // Space: O(N^2)
    class Solution {
        class Position {
            int row;
            int col;

            Position(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }

        public boolean isValid(int row, int col, int[][] grid) {
            return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
        }

        public void dfs(int row, int col, int[][] grid, boolean[][] visited, List<Position> visitedPosition) {
            if (!isValid(row, col, grid) || grid[row][col] == 0 || visited[row][col]) {
                return;
            }
            visited[row][col] = true;
            visitedPosition.add(new Position(row, col));
            dfs(row + 1, col, grid, visited, visitedPosition);
            dfs(row - 1, col, grid, visited, visitedPosition);
            dfs(row, col + 1, grid, visited, visitedPosition);
            dfs(row, col - 1, grid, visited, visitedPosition);
        }

        public int bfs(int[][] grid, boolean[][] visited, List<Position> visitedPosition) {
            int flip = 0;
            int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
            Deque<Position> deque = new LinkedList<>(visitedPosition);
            while (!deque.isEmpty()) {
                int size = deque.size();
                for (int i = 0; i < size; i++) {
                    Position position = deque.pollFirst();
                    assert position != null;
                    int row = position.row;
                    int col = position.col;
                    for (int[] direction : directions) {
                        int newRow = row + direction[0];
                        int newCol = col + direction[1];
                        if (!isValid(newRow, newCol, grid) || visited[newRow][newCol]) {
                            continue;
                        }
                        if (grid[newRow][newCol] == 1) {
                            return flip;
                        }
                        deque.add(new Position(newRow, newCol));
                        visited[newRow][newCol] = true;
                    }
                }
                flip++;
            }
            return flip;
        }

        public int shortestBridge(int[][] grid) {
            int result = 0;
            List<Position> visitedPosition = new ArrayList<>();
            boolean[][] visited = new boolean[grid.length][grid[0].length];
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                    if (grid[row][col] == 1) {
                        dfs(row, col, grid, visited, visitedPosition);
                        return bfs(grid, visited, visitedPosition);
                    }
                }
            }
            return result;
        }
    }
}
