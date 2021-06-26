public class RedundantConnection {

    /*
        A tree is a undirected graph that is connected and has no cycles.
        Return an edge that can be removed so that the resulting graph is a tree of n nodes.
     */

    public static void main(String[] args) {
        int[][] testEdges = {{1,2},{1,3},{2,3}};

        System.out.println("Redundant Connection:" + Solution.findRedundantConnection(testEdges));
    }

    static class Solution {
        private static int[] parent;

        public static int[] findRedundantConnection(int[][] edges) {
            int n = edges.length;
            parent = new int[n+1];

            // make each node parent of itself
            for(int i = 0; i <= n; i++) parent[i] = i;

            // loop all edges
            for(int[] edge: edges) {
                if(find(edge[0]) == find(edge[1])) return edge;
                union(edge[0], edge[1]);
            }

            return null;
        }

        public static int find(int node) {
            while (parent[node] != node) {
                node = parent[node];
            }

            return node;
        }

        public static void union(int i, int j) {
            int iRoot = find(i);
            int jRoot = find(j);

            if(iRoot != jRoot)
                parent[jRoot] = iRoot;
        }
    }
}
