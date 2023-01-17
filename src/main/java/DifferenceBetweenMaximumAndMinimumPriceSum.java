import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class DifferenceBetweenMaximumAndMinimumPriceSum {
    /*
        There exists an undirected and initially unrooted tree with n nodes indexed from 0 to n - 1.
        You are given the integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.
        Each node has an associated price. You are given an integer array price, where price[i] i
        The tree can be rooted at any node root of your choice.
        The incurred cost after choosing root is the difference between the maximum and minimum price sum amongst all paths starting at root.
        Return the maximum possible cost amongst all possible root choices.


        Example 1.
        Input: n = 6, edges = [[0,1],[1,2],[1,3],[3,4],[3,5]], price = [9,8,7,6,10,5]
        Output: 24
        Explanation: The diagram above denotes the tree after rooting it at node 2. The first part (colored in red) shows the path with the maximum price sum. The second part (colored in blue) shows the path with the minimum price sum.
        - The first path contains nodes [2,1,3,4]: the prices are [7,8,6,10], and the sum of the prices is 31.
        - The second path contains the node [2] with the price [7].
        The difference between the maximum and minimum price sum is 24. It can be proved that 24 is the maximum cost.
        Example 2.
        Input: n = 3, edges = [[0,1],[1,2]], price = [1,1,1]
        Output: 2
        Explanation: The diagram above denotes the tree after rooting it at node 0. The first part (colored in red) shows the path with the maximum price sum. The second part (colored in blue) shows the path with the minimum price sum.
        - The first path contains nodes [0,1,2]: the prices are [1,1,1], and the sum of the prices is 3.
        - The second path contains node [0] with a price [1].
        The difference between the maximum and minimum price sum is 2. It can be proved that 2 is the maximum cost.

        Constraints:

        1 <= n <= 105
        edges.length == n - 1
        0 <= ai, bi <= n - 1
        edges represents a valid tree.
        price.length == n
        1 <= price[i] <= 105
     */
    public static void main(String[] args) {
        System.out.println("Done...");
    }

    // Time: O(N)
    // Space: O(N)
    class Solution { // total TC: O(n)
        List<Integer>[] g;
        long res = 0;
        long[] maxSumFromLeaf;
        int[] price;

        @Data
        @AllArgsConstructor
        class Top {
            private int c1;
            private long m1;
            private long m2;
        }

        public long maxOutput(int n, int[][] edges, int[] price) {
            this.price = price;
            maxSumFromLeaf = new long[n]; // max path sum from current node to leaf

            // create adjacency list graph(tree)
            g = new List[n];
            for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
            for (var e : edges) {
                g[e[0]].add(e[1]);
                g[e[1]].add(e[0]);
            }

            dfs (0, -1); // to fill maxSumFromLeaf: O(n)
            dfs2(0, -1, 0); // update res: O(n)
            return res;
        }

        private void dfs2(int cur, int pre, long maxFromOtherLeafThroughParent) {
            var top = top(cur, pre);

            if (g[cur].size() == 1) // we only care about leaf nodes
                if (cur == 0) res = top.m1; // when root is special "leaf"
                else res = Math.max(res, maxFromOtherLeafThroughParent);

            for (int child : g[cur]) if (child != pre)
                dfs2(child, cur, Math.max(maxFromOtherLeafThroughParent, child == top.c1 ? top.m2 : top.m1) + price[cur]);
        }

        private Top top(int cur, int pre) {
            int c1 = -1;
            long m1 = 0, m2 = 0;
            for (int child : g[cur]) if (child != pre) {
                if (maxSumFromLeaf[child] > m1) {
                    m2 = m1;
                    c1 = child;
                    m1 = maxSumFromLeaf[child];
                } else if (maxSumFromLeaf[child] > m2) m2 = maxSumFromLeaf[child];
            }
            return new Top(c1, m1, m2);
        }

        private long dfs(int cur, int pre) {
            long m = 0;
            for (int child : g[cur]) if (child != pre)
                m = Math.max(m, dfs(child, cur));
            return maxSumFromLeaf[cur] = m + price[cur];
        }
    }
}
