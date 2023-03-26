public class LongestCycleInAGraph {
    /*
        You are given a directed graph of n nodes numbered from 0 to n - 1, where each node has at most one outgoing edge.
        The graph is represented with a given 0-indexed array edges of size n,
        indicating that there is a directed edge from node i to node edges[i]. If there is no outgoing edge from node i, then edges[i] == -1.
        Return the length of the longest cycle in the graph. If no cycle exists, return -1.
        A cycle is a path that starts and ends at the same node.

        Example 1.
        Input: edges = [3,3,4,2,3]
        Output: 3
        Explanation: The longest cycle in the graph is the cycle: 2 -> 4 -> 3 -> 2.
        The length of this cycle is 3, so 3 is returned.
        Example 2.
        Input: edges = [2,-1,3,1]
        Output: -1
        Explanation: There are no cycles in this graph.


        Constraints:

        n == edges.length
        2 <= n <= 105
        -1 <= edges[i] < n
        edges[i] != i
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.longestCycle(new int[]{3,3,4,2,3}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int longestCycle(int[] edges) {
            int longestCycleLen = -1;
            int timeStep = 1;
            int[] nodeVisitedAtTime = new int[edges.length];
            for (int currentNode = 0; currentNode < edges.length; ++currentNode) {
                if (nodeVisitedAtTime[currentNode] > 0)
                    continue;
                final int startTime = timeStep;
                int u = currentNode;
                while (u != -1 && nodeVisitedAtTime[u] == 0) {
                    nodeVisitedAtTime[u] = timeStep++;
                    u = edges[u];
                }
                if (u != -1 && nodeVisitedAtTime[u] >= startTime)
                    longestCycleLen = Math.max(longestCycleLen, timeStep - nodeVisitedAtTime[u]);
            }
            return longestCycleLen;
        }
    }
}
