import java.util.LinkedList;
import java.util.Queue;

public class IsGraphBipartite {

    /*
        There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
        You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to.
        More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:

        There are no self-edges (graph[u] does not contain u).
        There are no parallel edges (graph[u] does not contain duplicate values).
        If v is in graph[u], then u is in graph[v] (the graph is undirected).
        The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
        A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.

        Return true if and only if it is bipartite.

        Example 1.
        Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
        Output: false
        Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
        Example 2.
        Input: graph = [[1,3],[0,2],[1,3],[0,2]]
        Output: true
        Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.
     */

    public static void main(String[] args) {
        int[][] graph = {{1,2,3},{0,2},{0,1,3},{0,2}};
        System.out.println("Solution I: " + Solution_I.isBipartite(graph));
        System.out.println("Solution II: " + Solution_II.isBipartite(graph));
        System.out.println("Solution III: " + Solution_III.isBipartite(graph));
    }

    // Graph Coloring - DFS
    // 0 -> not colored, 1 -> blue, -1 -> red
    // Time: O(V+N); N: Nodes, V:Vertex
    // Space: O(N)
    static class Solution_I {
        public static boolean isBipartite(int[][] g) {
            int[] colors = new int[g.length];
            for (int i = 0; i < g.length; i++)
                if (colors[i] == 0 && !validColor(g, colors, 1, i))
                    return false;
            return true;
        }

        static boolean validColor(int[][] g, int[] colors, int color, int node) {
            if (colors[node] != 0)
                return colors[node] == color;
            colors[node] = color;
            for (int adjacent : g[node])
                if (!validColor(g, colors, -color, adjacent))
                    return false;
            return true;
        }
    }

    //Graph Coloring - BFS
    // Time: O(N+V); N: Nodes, V:Vertex
    // Space: O(N)
    static class Solution_II {
        public static boolean isBipartite(int[][] g) {
            int[] colors = new int[g.length];
            for (int i = 0; i < g.length; i++)
                if (colors[i] == 0) {
                    Queue<Integer> q = new LinkedList<>();
                    q.add(i);
                    colors[i] = 1;
                    while (!q.isEmpty()) {
                        Integer node = q.poll();
                        for (int adjacent : g[node])
                            if (colors[adjacent] == colors[node])
                                return false;
                            else if (colors[adjacent] == 0) {
                                q.add(adjacent);
                                colors[adjacent] = -colors[node];
                            }
                    }
                }
            return true;
        }
    }

    // Union Find
    // Time: O(N*LogV); N: Nodes, V:Vertex
    // Space: O(N)
    static class Solution_III {
        static int[] parent;
        public static boolean isBipartite(int[][] graph) {
            parent = new int[graph.length];
            for(int i = 0; i < parent.length; i++) parent[i]  = i;
            for (int i=0; i<graph.length; i++) {
                int[] adjs = graph[i];
                for (int j=0; j<adjs.length; j++) {
                    if (find(i) ==find(adjs[j])) return false;
                    union(adjs[0], adjs[j]);
                }
            }
            return true;
        }

        static int find(int i) {
            if (parent[i] == i) {
                return parent[i];
            }
            parent[i] = find(parent[i]);
            return parent[i];
        }

        static void union(int i, int j) {
            int parentI = find(i);
            int parentJ = find(j);
            if (parentI != parentJ) {
                parent[parentI] = parentJ;
            }
        }
    }
}
