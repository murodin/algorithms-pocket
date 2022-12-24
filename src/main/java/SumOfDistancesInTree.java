import java.util.ArrayList;
import java.util.Arrays;

public class SumOfDistancesInTree {
    /*
        There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.
        You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
        Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.

        Example 1.
        Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
        Output: [8,12,6,10,10,10]
        Explanation: The tree is shown above.
        We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
        equals 1 + 1 + 2 + 2 + 2 = 8.
        Hence, answer[0] = 8, and so on.
        Example 2.
        Input: n = 1, edges = []
        Output: [0]
        Example 3.
        Input: n = 2, edges = [[1,0]]
        Output: [1,1]


        Constraints:

        1 <= n <= 3 * 104
        edges.length == n - 1
        edges[i].length == 2
        0 <= ai, bi < n
        ai != bi
        The given input represents a valid tree.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.sumOfDistancesInTree(6, new int[][]{{0, 1}, {0, 2}, {2, 3}, {2, 4}, {2, 5}})));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int[] sumOfDistancesInTree(int n, int[][] edges) {
            // build graph and declare results
            final ArrayList<Integer>[] graph = new ArrayList[n];
            final int[] count = new int[n];
            Arrays.fill(count, 1);
            final int[] answer = new int[n];
            for (int i = 0; i < graph.length; i++) {
                graph[i] = new ArrayList<>();
            }
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }

            postOrder(0, -1, graph, count, answer);
            // after postOrder, only answer[root] is correct, so do preOrder to update answer
            preOrder(0, -1, graph, count, answer, n);

            return answer;
        }

        // set count et subTreeSum, here use answer[]
        private static void postOrder(int node, int parent, ArrayList<Integer>[] graph, int[] count, int[] answer) {
            for (int child : graph[node]) {
                if (child != parent) {
                    postOrder(child, node, graph, count, answer);
                    count[node] += count[child];
                    answer[node] += answer[child] + count[child];
                }
            }
        }

        private static void preOrder(int node, int parent, ArrayList<Integer>[] graph, int[] count, int[] answer, int n) {
            for (int child : graph[node]) {
                if (child != parent) {
                    answer[child] = answer[node] + (n - count[child]) - count[child];
                    preOrder(child, node, graph, count, answer, n);
                }
            }
        }
    }
}
