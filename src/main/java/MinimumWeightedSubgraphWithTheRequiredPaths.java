import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumWeightedSubgraphWithTheRequiredPaths {
    /*
        You are given an integer n denoting the number of nodes of a weighted directed graph. The nodes are numbered from 0 to n - 1.
        You are also given a 2D integer array edges where edges[i] = [fromi, toi, weighti] denotes that there exists a directed edge from fromi to toi with weight weighti.
        Lastly, you are given three distinct integers src1, src2, and dest denoting three distinct nodes of the graph.
        Return the minimum weight of a subgraph of the graph such that it is possible to reach dest from both src1 and src2 via a set of edges of this subgraph.
        In case such a subgraph does not exist, return -1.
        A subgraph is a graph whose vertices and edges are subsets of the original graph. The weight of a subgraph is the sum of weights of its constituent edges.

        Example 1:
        Input: n = 6, edges = [[0,2,2],[0,5,6],[1,0,3],[1,4,5],[2,1,1],[2,3,3],[2,3,4],[3,4,2],[4,5,1]], src1 = 0, src2 = 1, dest = 5
        Output: 9
        Explanation:
        The above figure represents the input graph.
        The blue edges represent one of the subgraphs that yield the optimal answer.
        Note that the subgraph [[1,0,3],[0,5,6]] also yields the optimal answer. It is not possible to get a subgraph with less weight satisfying all the constraints.
        Example 2:
        Input: n = 3, edges = [[0,1,1],[2,1,1]], src1 = 0, src2 = 1, dest = 2
        Output: -1
        Explanation:
        The above figure represents the input graph.
        It can be seen that there does not exist any path from node 1 to node 2, hence there are no subgraphs satisfying all the constraints.


        Constraints:

        3 <= n <= 105
        0 <= edges.length <= 105
        edges[i].length == 3
        0 <= fromi, toi, src1, src2, dest <= n - 1
        fromi != toi
        src1, src2, and dest are pairwise distinct.
        1 <= weight[i] <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minimumWeight(3, new int[][]{{0,1,1},{2,1,1}},0, 1, 2));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        static ArrayList<int[]>[] nextGraph, preGraph;
        public static long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
            buildGraph(n, edges);

            long[] src1To = new long[n], src2To = new long[n], toDest = new long[n];
            Arrays.fill(src1To, -1);
            Arrays.fill(src2To, -1);
            Arrays.fill(toDest, -1);

            shortestPath(src1, src1To, nextGraph);
            shortestPath(src2, src2To, nextGraph);
            shortestPath(dest, toDest, preGraph);

            long res = -1;
            for (int i = 0; i < n; i++) {
                long d1 = src1To[i], d2 = src2To[i], d3 = toDest[i];
                if (d1 >= 0 && d2 >= 0 && d3 >= 0) {
                    long d = d1 + d2 + d3;
                    if (res == -1 || d < res) {
                        res = d;
                    }
                }
            }

            return res;
        }

        private static void buildGraph(int n, int[][] edges) {
            nextGraph = new ArrayList[n];
            preGraph = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                nextGraph[i] = new ArrayList<int[]>();
                preGraph[i] = new ArrayList<int[]>();
            }

            for (int[] edge : edges) {
                int from = edge[0], to = edge[1], weight = edge[2];
                nextGraph[from].add(new int[] {to, weight});
                preGraph[to].add(new int[] {from, weight});
            }
        }

        private static void shortestPath(int src, long[] srcTo, ArrayList<int[]>[] graph) {
            PriorityQueue<long[]> heap = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
            heap.offer(new long[] {src, 0});
            while (!heap.isEmpty()) {
                long[] node = heap.poll();
                int to = (int) node[0];
                long dist = node[1];
                if (srcTo[to] != -1 && srcTo[to] <= dist) continue;
                srcTo[to] = dist;
                for (int[] next : graph[to]) {
                    heap.offer(new long[] {next[0], dist + next[1]});
                }
            }
        }
    }
}
