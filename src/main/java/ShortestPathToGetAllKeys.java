import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathToGetAllKeys {
    /*
        You are given an m x n grid grid where:

        '.' is an empty cell.
        '#' is a wall.
        '@' is the starting point.
        Lowercase letters represent keys.
        Uppercase letters represent locks.
        You start at the starting point and one move consists of walking one space in one of the four cardinal directions.
        You cannot walk outside the grid, or walk into a wall.

        If you walk over a key, you can pick it up and you cannot walk over a lock unless you have its corresponding key.

        For some 1 <= k <= 6, there is exactly one lowercase and one uppercase letter of the first k letters of the English alphabet in the grid.
        This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys
        and locks were chosen in the same order as the English alphabet.

        Return the lowest number of moves to acquire all keys. If it is impossible, return -1.



        Example 1:


        Input: grid = ["@.a..","###.#","b.A.B"]
        Output: 8
        Explanation: Note that the goal is to obtain all the keys not to open all the locks.
        Example 2:


        Input: grid = ["@..aA","..B#.","....b"]
        Output: 6
        Example 3:


        Input: grid = ["@Aa"]
        Output: -1


        Constraints:

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 30
        grid[i][j] is either an English letter, '.', '#', or '@'.
        There is exactly one '@' in the grid.
        The number of keys in the grid is in the range [1, 6].
        Each key in the grid is unique.
        Each key in the grid has a matching lock.
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    class Solution {
        public int shortestPathAllKeys(String[] grid) {
            int m = grid.length;
            int n = grid[0].length();
            int allKeys = 0;
            int startRow = -1;
            int startCol = -1;
            int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    char c = grid[i].charAt(j);
                    if (c == '@') {
                        startRow = i;
                        startCol = j;
                    } else if (c >= 'a' && c <= 'f') {
                        allKeys |= (1 << (c - 'a'));
                    }
                }
            }

            Queue<State> queue = new LinkedList<>();
            boolean[][][] visited = new boolean[m][n][64];
            State initialState = new State(startRow, startCol, 0);
            queue.offer(initialState);
            visited[startRow][startCol][0] = true;

            int steps = 0;

            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    State currentState = queue.poll();
                    assert currentState != null;
                    if (currentState.keys == allKeys) return steps;
                    for (int[] direction : directions) {
                        int newRow = currentState.row + direction[0];
                        int newCol = currentState.col + direction[1];
                        if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n) {
                            char newCell = grid[newRow].charAt(newCol);
                            if (newCell == '#') continue;
                            int newKeys = currentState.keys;

                            if (newCell >= 'a' && newCell <= 'f') newKeys |= (1 << (newCell - 'a'));
                            if (newCell >= 'A' && newCell <= 'F' &&
                                    ((currentState.keys >> (newCell - 'A')) & 1) == 0) continue;

                            if (!visited[newRow][newCol][newKeys]) {
                                State newState = new State(newRow, newCol, newKeys);
                                queue.offer(newState);
                                visited[newRow][newCol][newKeys] = true;
                            }
                        }
                    }
                }
                steps++;
            }
            return -1;
        }

        private class State {
            int row;
            int col;
            int keys;

            public State(int row, int col, int keys) {
                this.row = row;
                this.col = col;
                this.keys = keys;
            }
        }
    }
}
