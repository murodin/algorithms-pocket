import java.util.*;

public class ShortestPathWithAlternatingColors {
    /*
        You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1.
        Each edge is red or blue in this graph, and there could be self-edges and parallel edges.
        You are given two arrays redEdges and blueEdges where:
        redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
        blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
        Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path,
         or -1 if such a path does not exist.

        Example 1.
        Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
        Output: [0,1,-1]
        Example 2.
        Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
        Output: [0,1,-1]


        Constraints:

        1 <= n <= 100
        0 <= redEdges.length, blueEdges.length <= 400
        redEdges[i].length == blueEdges[j].length == 2
        0 <= ai, bi, uj, vj < n
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.shortestAlternatingPaths(3, new int[][]{{0, 1}, {1, 2}}, new int[][]{})));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        public static int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
            Set<Integer>[][] graph = new HashSet[2][n];
            for (int i = 0; i < n; i++) {
                graph[0][i] = new HashSet<>();
                graph[1][i] = new HashSet<>();
            }
            for (int[] re : red_edges) {
                graph[0][ re[0] ].add(re[1]);
            }
            for (int[] blu : blue_edges) {
                graph[1][ blu[0] ].add(blu[1]);
            }
            int[][] res = new int[2][n];
            for (int i = 1; i < n; i++) {
                res[0][i] = 2 * n;
                res[1][i] = 2 * n;
            }

            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] {0, 0}); // either with red
            q.offer(new int[] {0, 1}); // or with blue
            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int vert = cur[0];
                int colr = cur[1];
                for (int nxt : graph[1 - colr][vert]) {
                    if (res[1 - colr][nxt] == 2 * n) {
                        res[1 - colr][nxt] = 1 + res[colr][vert];
                        q.offer(new int[] {nxt, 1 - colr});
                    }
                }
            }
            int[] ans = new int[n];
            for (int i = 0; i < n; i++) {
                int t = Math.min(res[0][i], res[1][i]);
                ans[i] = (t == 2 * n) ? -1 : t;
            }
            return ans;
        }
    }
}
