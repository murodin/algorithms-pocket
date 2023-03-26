import java.util.ArrayList;
import java.util.List;

public class CountUnreachablePairsOfNodesInAnUndirectedGraph {
    /*
        You are given an integer n. There is an undirected graph with n nodes, numbered from 0 to n - 1.
        You are given a 2D integer array edges where edges[i] = [ai, bi] denotes that there exists an undirected edge connecting nodes ai and bi.
        Return the number of pairs of different nodes that are unreachable from each other.

        Example 1.
        Input: n = 3, edges = [[0,1],[0,2],[1,2]]
        Output: 0
        Explanation: There are no pairs of nodes that are unreachable from each other. Therefore, we return 0.
        Example 2.
        Input: n = 7, edges = [[0,2],[0,5],[2,4],[1,6],[5,4]]
        Output: 14
        Explanation: There are 14 pairs of nodes that are unreachable from each other:
        [[0,1],[0,3],[0,6],[1,2],[1,3],[1,4],[1,5],[2,3],[2,6],[3,4],[3,5],[3,6],[4,6],[5,6]].
        Therefore, we return 14.


        Constraints:

        1 <= n <= 105
        0 <= edges.length <= 2 * 105
        edges[i].length == 2
        0 <= ai, bi < n
        ai != bi
        There are no repeated edges.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.countPairs(7, new int[][]{{0,2}, {0,5}, {2,4}, {1,6}, {5,4}}));
    }

    // Time: O(n^2)
    // Space: O(N)
    static class Solution {
        static List<Integer>[] graph;
        public static long countPairs(int n, int[][] edges) {
            createGraph(n, edges);
            boolean[] visited = new boolean[n];
            int numVisitedNodes = 0;
            long numUnreachablePairsOfNodes = 0;

            for (int node = 0; node < n; ++node) {
                if (!visited[node]) {
                    int numNodesInCurrentGroup = depthFirstSearch_countConnectedNodesInCurrentGroup(node, visited);
                    numUnreachablePairsOfNodes += (long) numNodesInCurrentGroup * numVisitedNodes;
                    numVisitedNodes += numNodesInCurrentGroup;
                }
            }
            return numUnreachablePairsOfNodes;
        }

        private static int depthFirstSearch_countConnectedNodesInCurrentGroup(int node, boolean[] visited) {
            visited[node] = true;
            int numConnectedNodes = 1;

            for (int neighbor : graph[node]) {
                if (!visited[neighbor]) {
                    numConnectedNodes += depthFirstSearch_countConnectedNodesInCurrentGroup(neighbor, visited);
                }
            }
            return numConnectedNodes;
        }
        private static void createGraph(int n, int[][] edges) {
            graph = new List[n];
            for (int node = 0; node < n; ++node) {
                graph[node] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }
        }
    }
}
