public class CatAndMouse {
    /*
        A game on an undirected graph is played by two players, Mouse and Cat, who alternate turns.
        The graph is given as follows: graph[a] is a list of all nodes b such that ab is an edge of the graph.
        The mouse starts at node 1 and goes first, the cat starts at node 2 and goes second, and there is a hole at node 0.
        During each player's turn, they must travel along one edge of the graph that meets where they are.
        For example, if the Mouse is at node 1, it must travel to any node in graph[1].
        Additionally, it is not allowed for the Cat to travel to the Hole (node 0.)
        Then, the game can end in three ways:
        If ever the Cat occupies the same node as the Mouse, the Cat wins.
        If ever the Mouse reaches the Hole, the Mouse wins.
        If ever a position is repeated (i.e., the players are in the same position as a previous turn, and it is the same player's turn to move), the game is a draw.
        Given a graph, and assuming both players play optimally, return

        1 if the mouse wins the game,
        2 if the cat wins the game, or
        0 if the game is a draw.

        Example 1.
        Input: graph = [[2,5],[3],[0,4,5],[1,4,5],[2,3],[0,2,3]]
        Output: 0
        Example 2.
        Input: graph = [[1,3],[0],[3],[0,2]]
        Output: 1
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.catMouseGame(new int[][]{{1,3},{0},{3}, {0,2}}));
    }

    // Space: O(2^N)
    // Time: O(N^3)
    static class Solution {
        static private int M;
        static Integer memo[][][];
        public static int catMouseGame(int[][] graph) {
            int n = graph.length;
            M = 4*n + 200; // randomly chosen max move
            memo = new Integer[n+1][n+1][M+1];
            return dp(1, 2, 0, graph);
        }

        static int dp(int m, int c, int moves, int[][] graph) {
            if(moves > M) return 0;
            if(c == m) return 2;
            if(m == 0) return 1;
            if(memo[m][c][moves] != null) return memo[m][c][moves];
            if(moves % 2 == 0){
                boolean draw = false;
                for(int nei : graph[m]) {
                    int ans = dp(nei, c, moves+1, graph);
                    if(ans == 1) return memo[m][c][moves] = 1;
                    if(ans == 0)draw = true;
                }
                return memo[m][c][moves] = draw ? 0 : 2;
            } else {
                boolean draw = false;
                for(int nei : graph[c]) {
                    if(nei == 0) continue;
                    int ans = dp(m, nei, moves+1, graph);
                    if(ans == 2) return memo[m][c][moves] = 2;
                    if(ans == 0)draw = true;
                }
                return memo[m][c][moves] = draw ? 0 : 1;
            }
        }
    }
}
