public class FindIfPathExistsInGraph {
    /*
        There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive).
        The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge
        between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
        You want to determine if there is a valid path that exists from vertex source to vertex destination.
        Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.

        Example 1.
        Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
        Output: true
        Explanation: There are two paths from vertex 0 to vertex 2:
        - 0 → 1 → 2
        - 0 → 2
        Example 2.
        Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
        Output: false
        Explanation: There is no path from vertex 0 to vertex 5.


        Constraints:

        1 <= n <= 2 * 105
        0 <= edges.length <= 2 * 105
        edges[i].length == 2
        0 <= ui, vi <= n - 1
        ui != vi
        0 <= source, destination <= n - 1
        There are no duplicate edges.
        There are no self edges.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.validPath(6, new int[][]{{0,1},{1,2},{2,0}}, 0, 2));
    }

    // Time: O(ELogV)
    // Space: O(N)
    static class Solution {
        public static boolean validPath(int n, int[][] edges, int start, int end) {
            DisjointSetUnion set = new DisjointSetUnion(n);
            for(int[] edge : edges){
                set.union(edge[0], edge[1]);
            }
            return set.areConnected(start, end);
        }
    }
    static class DisjointSetUnion{
        private int[] parent;
        private int N;

        public DisjointSetUnion(int n){
            this.N = n;
            this.parent = new int[this.N];
            for(int i = 0; i < this.N; i++){
                this.parent[i] = i;
            }
        }

        public boolean areConnected(int u, int v){
            return find(u) == find(v);
        }

        public void union(int u, int v){
            if(u != v){
                int a = find(u);
                int b = find(v);
                parent[a] = b;
            }
        }

        private int find(int u){
            int x = u;
            while(x != this.parent[x]){
                x = this.parent[x];
            }

            this.parent[u] = x;
            return x;
        }
    }
}
