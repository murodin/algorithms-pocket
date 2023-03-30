import java.util.ArrayList;
import java.util.List;

public class CatAndMouseII {
    /*
        A game is played by a cat and a mouse named Cat and Mouse.
        The environment is represented by a grid of size rows x cols, where each element is a wall, floor, player (Cat, Mouse), or food.
        Players are represented by the characters 'C'(Cat),'M'(Mouse).
        Floors are represented by the character '.' and can be walked on.
        Walls are represented by the character '#' and cannot be walked on.
        Food is represented by the character 'F' and can be walked on.
        There is only one of each character 'C', 'M', and 'F' in grid.
        Mouse and Cat play according to the following rules:
        Mouse moves first, then they take turns to move.
        During each turn, Cat and Mouse can jump in one of the four directions (left, right, up, down). They cannot jump over the wall nor outside of the grid.
        catJump, mouseJump are the maximum lengths Cat and Mouse can jump at a time, respectively. Cat and Mouse can jump less than the maximum length.
        Staying in the same position is allowed.
        Mouse can jump over Cat.
        The game can end in 4 ways:
        If Cat occupies the same position as Mouse, Cat wins.
        If Cat reaches the food first, Cat wins.
        If Mouse reaches the food first, Mouse wins.
        If Mouse cannot get to the food within 1000 turns, Cat wins.
        Given a rows x cols matrix grid and two integers catJump and mouseJump, return true if Mouse can win the game if both Cat and Mouse play optimally, otherwise return false.

        Example 1.
        Input: grid = ["####F","#C...","M...."], catJump = 1, mouseJump = 2
        Output: true
        Explanation: Cat cannot catch Mouse on its turn nor can it get the food before Mouse.
        Example 2.
        Input: grid = ["M.C...F"], catJump = 1, mouseJump = 4
        Output: true
        Example 3.
        Input: grid = ["M.C...F"], catJump = 1, mouseJump = 3
        Output: false


        Constraints:

        rows == grid.length
        cols = grid[i].length
        1 <= rows, cols <= 8
        grid[i][j] consist only of characters 'C', 'M', 'F', '.', and '#'.
        There is only one of each character 'C', 'M', and 'F' in grid.
        1 <= catJump, mouseJump <= 8
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.canMouseWin(new String[]{"####F","#C...","M...."}, 1 ,2));
    }

    // Time: O(2^N)
    // Space: O(N^3)
    static class Solution {
        private static final int MOUSE_TURN = 0, CAT_TURN = 1;
        private static final List<Integer>[][] graphs = new List[2][]; // [mouse:0/cat:1][vertex] => list of edges
        private static int foodPos;
        private static int[][][] memo;
        public static boolean canMouseWin(String[] grid, int catJump, int mouseJump) {
            int m = grid.length, n = grid[0].length();
            int mousePos = 0, catPos = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    char c = grid[i].charAt(j);
                    if (c == 'F')       foodPos = i * n + j;
                    else if (c == 'C')   catPos = i * n + j;
                    else if (c == 'M') mousePos = i * n + j;
                }
            }
            graphs[0] = buildGraph(mouseJump, grid);
            graphs[1] = buildGraph(catJump, grid);
            memo = new int[m*n][m*n][2];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    char c = grid[i].charAt(j);
                    if (c == '#' || c == 'F') continue;
                    dfs(i * n + j, foodPos, CAT_TURN); // mouse win position
                }
            }
            return memo[mousePos][catPos][MOUSE_TURN] < 0;
        }

        private static List<Integer>[] buildGraph(int jump, String[] grid) {
            int[][] dirs = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
            int m = grid.length, n = grid[0].length();
            List<Integer>[] graph = new List[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    List<Integer> list = new ArrayList<>();
                    graph[i * n + j] = list;
                    if (grid[i].charAt(j) == '#') continue;
                    list.add(i * n + j);
                    for (int[] dir : dirs) {
                        for (int step = 1; step <= jump; step++) {
                            int x = i + dir[0] * step, y = j + dir[1] * step;
                            if (x < 0 || x >= m || y < 0 || y >= n || grid[x].charAt(y) == '#') break;
                            list.add(x * n + y);
                        }
                    }
                }
            }
            return graph;
        }

        private static void dfs(int p1, int p2, int turn) { // p1: player 1, p2: player 2
            if (p1 == p2) return;
            if ((turn == 0 ? p2 : p1) == foodPos) return;
            if (memo[p1][p2][turn] < 0) return;
            memo[p1][p2][turn] = -1;
            turn ^= 1;
            for (int w : graphs[turn][p2]) {
                if (turn == MOUSE_TURN) {
                    dfs(w, p1, turn);
                } else if (++memo[w][p1][turn] == graphs[turn][w].size()) {
                    dfs(w, p1, turn);
                }
            }
        }
    }
}
