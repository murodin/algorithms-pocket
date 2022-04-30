import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaximumPathQualityOfAGraph {
    /*
        There is an undirected graph with n nodes numbered from 0 to n - 1 (inclusive).
        You are given a 0-indexed integer array values where values[i] is the value of the ith node.
        You are also given a 0-indexed 2D integer array edges, where each edges[j] = [uj, vj, time j] indicates that
        there is an undirected edge between the nodes uj and vj, and it takes time j seconds to travel between the two nodes. Finally, you are given an integer maxTime.

        A valid path in the graph is any path that starts at node 0, ends at node 0, and takes at most maxTime seconds to complete.
        You may visit the same node multiple times. The quality of a valid path is the sum of the values of the unique nodes visited in the path (each node's value is added at most once to the sum).

        Return the maximum quality of a valid path.

        Note: There are at most four edges connected to each node.

        Example 1.
        Input: values = [0,32,10,43], edges = [[0,1,10],[1,2,15],[0,3,10]], maxTime = 49
        Output: 75
        Explanation:
        One possible path is 0 -> 1 -> 0 -> 3 -> 0. The total time taken is 10 + 10 + 10 + 10 = 40 <= 49.
        The nodes visited are 0, 1, and 3, giving a maximal path quality of 0 + 32 + 43 = 75.
        Example 2.
        Input: values = [5,10,15,20], edges = [[0,1,10],[1,2,10],[0,3,10]], maxTime = 30
        Output: 25
        Explanation:
        One possible path is 0 -> 3 -> 0. The total time taken is 10 + 10 = 20 <= 30.
        The nodes visited are 0 and 3, giving a maximal path quality of 5 + 20 = 25.
        Example 3.
        Input: values = [1,2,3,4], edges = [[0,1,10],[1,2,11],[2,3,12],[1,3,13]], maxTime = 50
        Output: 7
        Explanation:
        One possible path is 0 -> 1 -> 3 -> 1 -> 0. The total time taken is 10 + 13 + 13 + 10 = 46 <= 50.
        The nodes visited are 0, 1, and 3, giving a maximal path quality of 1 + 2 + 4 = 7.


        Constraints:

        n == values.length
        1 <= n <= 1000
        0 <= values[i] <= 108
        0 <= edges.length <= 2000
        edges[j].length == 3
        0 <= uj < vj <= n - 1
        10 <= time j, maxTime <= 100
        All the pairs [uj, vj] are unique.
        There are at most four edges connected to each node.
        The graph may not be connected.
     */

    public static void main(String[] args) {
        int[] values = {5,10,15,20};
        int[][] edges = {{0,1,10},{1,2,10},{0,3,10}};
        int maxTime = 30;
        System.out.println("Solution I: " + Solution_I.maximalPathQuality(values, edges, maxTime));
        System.out.println("Solution II: " + Solution_II.maximalPathQuality(values, edges, maxTime));
    }

    // Time: O(|V| + |E|)
    // Space: O(|V| + |E|)
    static class Solution_I {
        static class Node{
            int n;
            int t;
            Node(int n,int t){
                this.n = n;
                this.t = t;
            }
        }
        static int ans = 0;
        static int[] values;
        public static int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
            Solution_I.values = values;
            List<List<Node>> graph = new ArrayList<>();

            for(int i=0;i<values.length;i++){
                graph.add(new ArrayList<>());
            }

            for (int[] edge : edges) {
                graph.get(edge[0]).add(new Node(edge[1], edge[2]));
                graph.get(edge[1]).add(new Node(edge[0], edge[2]));
            }
            boolean[] visit = new boolean[values.length];
            visit[0] = true;
            solve(graph,maxTime,0,visit,values[0]);
            return ans;
        }
        private static void solve(List<List<Node>> graph,int maxTime,int node,boolean[] visit,int val){
            if(node==0){
                ans = Math.max(ans,val);
            }
            for(Node i:graph.get(node)){
                if(maxTime-i.t<0){
                    continue;
                }
                if(!visit[i.n]){
                    visit[i.n] = true;
                    solve(graph,maxTime-i.t,i.n,visit,val+values[i.n]);
                    visit[i.n] = false;
                } else{
                    solve(graph,maxTime-i.t,i.n,visit,val);
                }
            }
        }
    }

    // Time: O(|V| + |E|)
    // Space: O(|V| + |E|)
    static class Solution_II {
        static Map<Integer, List<int[]>> graph;
        public static int maximalPathQuality(int[] values, int[][] edges, int maxTime) {
            graph = getGraph(edges);
            //we can visit a node multiple times, thus the int[] and not boolean[] type
            int[] visited = new int[values.length];
            int[] max = new int[1];
            dfs(0, 0, 0, max, maxTime, values, visited);
            return max[0];
        }

        private static void dfs(int node, int value, int time, int[] max, int maxTime, int[] values, int[] visited) {
            if (time > maxTime) {
                return;
            }
            //we add the value only when we visit the node the first time
            if (visited[node] == 0) {
                value += values[node];
            }
            //the valid path ends with 0
            if (node == 0) {
                max[0] = Math.max(max[0], value);
            }
            if (graph.get(node) == null) {
                return;
            }
            visited[node]++;
            for (int[] next : graph.get(node)) {
                int nextTime = time + next[1];
                dfs(next[0], value, nextTime, max, maxTime, values, visited);
            }
            visited[node]--;
        }

        private static Map<Integer, List<int[]>> getGraph(int[][] edges) {
            Map<Integer, List<int[]>> result = new HashMap<>();
            for (int[] edge : edges) {
                int from = edge[0];
                int to = edge[1];
                int time = edge[2];
                result.putIfAbsent(from, new ArrayList<>());
                result.putIfAbsent(to, new ArrayList<>());
                result.get(from).add(new int[] {to, time});
                result.get(to).add(new int[] {from, time});
            }
            return result;
        }
    }
}
