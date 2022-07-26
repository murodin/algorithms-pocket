import java.util.ArrayDeque;
import java.util.Queue;

public class MinimumMovesToMoveABoxToTheirTargetLocation {
    /*
        A storekeeper is a game in which the player pushes boxes around in a warehouse trying to get them to target locations.
        The game is represented by an m x n grid of characters grid where each element is a wall, floor, or box.
        Your task is to move the box 'B' to the target position 'T' under the following rules:
        The character 'S' represents the player. The player can move up, down, left, right in grid if it is a floor (empty cell).
        The character '.' represents the floor which means a free cell to walk.
        The character '#' represents the wall which means an obstacle (impossible to walk there).
        There is only one box 'B' and one target cell 'T' in the grid.
        The box can be moved to an adjacent free cell by standing next to the box and then moving in the direction of the box. Solution is a push.
        The player cannot walk through the box.
        Return the minimum number of pushes to move the box to the target. If there is no way to reach the target, return -1.

        Example 1.
        Input: grid = [['#','#','#','#','#','#'],
                       ['#','T','#','#','#','#'],
                       ['#','.','.','B','.','#'],
                       ['#','.','#','#','.','#'],
                       ['#','.','.','.','S','#'],
                       ['#','#','#','#','#','#']]
        Output: 3
        Explanation: We return only the number of times the box is pushed.
        Example 2.
        Input: grid = [['#','#','#','#','#','#'],
                       ['#','T','#','#','#','#'],
                       ['#','.','.','B','.','#'],
                       ['#','#','#','#','.','#'],
                       ['#','.','.','.','S','#'],
                       ['#','#','#','#','#','#']]
        Output: -1
        Example 3.
        Input: grid = [['#','#','#','#','#','#'],
                       ['#','T','.','.','#','#'],
                       ['#','.','#','B','.','#'],
                       ['#','.','.','.','.','#'],
                       ['#','.','.','.','S','#'],
                       ['#','#','#','#','#','#']]
        Output: 5
        Explanation: push the box down, left, left, up and up.


        Constraints:

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 20
        grid contains only characters '.', '#', 'S', 'T', or 'B'.
        There is only one character 'S', 'B', and 'T' in the grid.
     */

    /**
         The algorithm is not too hard though it took me a long time to write the actual code
    
         Use An augmented BFS to find 'pushable positions' among the box's neighbors
         1. Find all neighbors of a box where it can be successfully pushed. 
         We'll need to do a BFS from the  player's current position to determine if the box can be pushed successfully
         2. Record each neighbor and the push direction to avoid revisiting the cell in a similar manner.
         It's okay to visit a cell more than once but only if the direction of the push from the cell is different
         3. Update players location to box's current location
         4. Push each neighbor to a queue
         5. Repeat until queue is empty

     **/
    public static void main(String[] args) {
        char[][] grid = {
                {'#','#','#','#','#','#'},
                {'#','T','.','.','#','#'},
                {'#','.','#','B','.','#'},
                {'#','.','.','.','.','#'},
                {'#','.','.','.','S','#'},
                {'#','#','#','#','#','#'}
        };
        System.out.println("Solution: " + Solution.minPushBox(grid));
    }

    // Time: O(N^2)
    // Space: ON^2)
    static class Solution {
        static char[][] grid;
        static int[][] directions = new int[][]{{0,1},{0,-1},{-1,0},{1,0}};

        public static int minPushBox(char[][] grid) {
            Solution.grid = grid;

            int[] start = new int[2], end = new int[2],  player = new int[2];

            for (int i = 0; i < Solution.grid.length; i++){
                for (int j = 0; j < Solution.grid[0].length; j++){
                    if (Solution.grid[i][j] == 'S'){
                        player[0] = i;
                        player[1] = j;
                    }
                    else if (Solution.grid[i][j] == 'B'){
                        start[0] = i;
                        start[1] = j;
                    }
                    else if (Solution.grid[i][j] == 'T'){
                        end[0] = i;
                        end[1] = j;
                    }
                }
            }
            Queue<int[][]> q = new ArrayDeque();
            //Track if we have visited a cell going in a certain direction
            boolean[][][] visited = new boolean[Solution.grid.length][Solution.grid[0].length][4];

            //Push first location to queue. Note since we haven't attempted to push the box yet, we don't mark it as visited 
            q.add(new int[][]{{start[0],start[1]}, {player[0], player[1]}, {0}});

            while (q.size() > 0){
                int[][] item =  q.poll();
                int[] boxLoc = item[0];
                int[] playerLoc = item[1];
                int dist = item[2][0];
                int row = boxLoc[0], col = boxLoc[1];

                if (row == end[0] && col == end[1]) return dist;

                for (int i = 0; i < directions.length; i++){
                    int[] d = directions[i];
                    int newRow = row+d[0];
                    int newCol = col+d[1];
                    if (newRow >=0 && newRow < Solution.grid.length && newCol >= 0 && newCol < Solution.grid[0].length && Solution.grid[newRow][newCol] != '#' && !visited[newRow][newCol][i]){
                        if (canPush(boxLoc, i, playerLoc)){
                            q.add(new int[][]{{newRow, newCol}, {row, col}, {dist+1}});
                            // Mark neighbor as visited as we know a successful push can happen
                            visited[newRow][newCol][i] = true;
                        }
                    }
                }
            }
            return -1;
        }

        /**
         Checks if the player can can push the in a certain direction given the box' and player's current location. 
         Simple BFS to check if the player can reach the point needed to push the box.
         **/
        private static boolean canPush(int[] boxLoc, int direction, int[] playerStart){
            // Player's desired location           
            int [] playerDest = new int[2];
            playerDest[0] = boxLoc[0] - Solution.directions[direction][0];
            playerDest[1] = boxLoc[1] - Solution.directions[direction][1];

            if (playerDest[0] < 0 || playerDest[1] < 0 || playerDest[0] >= Solution.grid.length || playerDest[1] >= Solution.grid[0].length || Solution.grid[playerDest[0]][playerDest[1]] == '#'){
                return false;
            }

            Queue<int[]> q = new ArrayDeque();
            boolean [][] visited = new boolean[Solution.grid.length][Solution.grid[0].length];

            q.add(playerStart);
            visited[playerStart[0]][playerStart[1]] = true;
            while(q.size() > 0){
                int[] currentPlayerLoc = q.poll();
                int row = currentPlayerLoc[0], col = currentPlayerLoc[1];
                if(row == playerDest[0] && col == playerDest[1]){
                    return true;
                }
                
                for (int[] d : directions) {
                    int newRow = row + d[0];
                    int newCol = col + d[1];
                    if (newRow >= 0 && newRow < Solution.grid.length && newCol >= 0 && newCol < Solution.grid[0].length && Solution.grid[newRow][newCol] != '#' && !visited[newRow][newCol] && !(newRow == boxLoc[0] && newCol == boxLoc[1])) {
                        q.add(new int[]{newRow, newCol});
                        visited[newRow][newCol] = true;
                    }
                }
            }
            return false;
        }
    }
}
