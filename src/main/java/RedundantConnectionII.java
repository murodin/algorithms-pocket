import java.util.Arrays;

public class RedundantConnectionII {
    /*
        In this problem, a rooted tree is a directed graph such that, there is exactly one node (the root) for which all other nodes are descendants of this node,
        plus every node has exactly one parent, except for the root node which has no parents.
        The given input is a directed graph that started as a rooted tree with n nodes (with distinct values from 1 to n), with one additional directed edge added.
        The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed.
        The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [ui, vi] that represents a directed edge connecting nodes ui and vi,
        where ui is a parent of child vi.
        Return an edge that can be removed so that the resulting graph is a rooted tree of n nodes. If there are multiple answers, return the answer
        that occurs last in the given 2D-array.

        Example 1.
        Input: edges = [[1,2],[1,3],[2,3]]
        Output: [2,3]
        Example 2.
        Input: edges = [[1,2],[2,3],[3,4],[4,1],[1,5]]
        Output: [4,1]


        Constraints:

        n == edges.length
        3 <= n <= 1000
        edges[i].length == 2
        1 <= ui, vi <= n
        ui != vi
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    // Time: O(N)
    // Space: O(N)
    class Solution {
        public int[] findRedundantDirectedConnection(int[][] edges) {
            int ans1 = -1;
            int ans2 = -1;
            int [] inDegree = new int[edges.length+1];
            Arrays.fill(inDegree,-1);

            for( int i = 0 ; i < edges.length; i++ ){
                int var1 = edges[i][0];
                int var2 = edges[i][1];
                if( inDegree[var2] == -1 ) inDegree[var2] = i;
                else {
                    ans1 = inDegree[var2];
                    ans2 = i;
                }
            }

            UnionFind uf = new UnionFind(edges.length*2);
            for(int [] edge: edges){
                if(ans2!=-1 && edge == edges[ans2]) continue;
                int u = edge[0];
                int v = edge[1];
                int paru = uf.find(u);
                int parv = uf.find(v);
                if(paru == parv) return ans1==-1 ? edge: edges[ans1];
                uf.union(u,v);
            }
            return edges[ans2];
        }



        private class UnionFind{
            int parent[];
            public UnionFind(int n){
                parent = new int[n];
                for( int i = 0 ; i < n; i++ ){
                    parent[i] = i;
                }
            }

            public int find( int a ){
                int parA = parent[a];
                if(parA == a) return a;
                parent[a] = find(parent[a]);
                return parent[a];
            }

            public void union(int a, int b){
                int parA = find(a);
                int parB = find(b);
                if(parA == parB) return;
                parent[parB] = parA;
            }
        }
    }
}
