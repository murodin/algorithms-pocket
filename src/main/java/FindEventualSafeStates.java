import java.util.ArrayList;
import java.util.List;

public class FindEventualSafeStates {
    /*
        There is a directed graph of n nodes with each node labeled from 0 to n - 1.
        The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i,
        meaning there is an edge from node i to each node in graph[i].
        A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that
        node leads to a terminal node (or another safe node).
        Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.

        Example 1:

        Illustration of graph
        Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
        Output: [2,4,5,6]
        Explanation: The given graph is shown above.
        Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
        Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
        Example 2:

        Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
        Output: [4]
        Explanation:
        Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.


        Constraints:

        n == graph.length
        1 <= n <= 104
        0 <= graph[i].length <= n
        0 <= graph[i][j] <= n - 1
        graph[i] is sorted in a strictly increasing order.
        The graph may contain self-loops.
        The number of edges in the graph will be in the range [1, 4 * 104].
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.eventualSafeNodes(new int[][]{{1,2},{2,3},{5},{0},{5},{},{}}));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        private static boolean DFS(int s, List<List<Integer>> graph, boolean[] visited, boolean[] dfsVisited, boolean[] checkCycle) {
            visited[s] = dfsVisited[s] = true;
            for (int it : graph.get(s)) {
                if (!visited[it]) {
                    if (DFS(it, graph, visited, dfsVisited, checkCycle))
                        return checkCycle[s] = true;
                } else if (dfsVisited[it]) {
                    return checkCycle[s] = true;
                }
            }
            dfsVisited[s] = false;
            return false;
        }

        public static List<Integer> eventualSafeNodes(int[][] graph) {
            int v = graph.length;
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                adjList.add(new ArrayList<>());
            }
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < graph[i].length; j++) {
                    adjList.get(i).add(graph[i][j]);
                }
            }

            boolean[] visited = new boolean[v];
            boolean[] dfsVisited = new boolean[v];
            boolean[] checkCycle = new boolean[v];
            List<Integer> ans = new ArrayList<>();

            for (int i = 0; i < v; i++) {
                if (!visited[i])
                    DFS(i, adjList, visited, dfsVisited, checkCycle);
            }

            for (int i = 0; i < v; i++) {
                if (!checkCycle[i])
                    ans.add(i);
            }

            return ans;
        }
    }
}
