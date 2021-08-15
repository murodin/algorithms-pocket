import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriticalConnectionsInANetwork {

    /*
        There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi.
        Any server can reach other servers directly or indirectly through the network.
        A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
        Return all critical connections in the network in any order.

        Example.
        Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
        Output: [[1,3]]
        Explanation: [[3,1]] is also accepted.

     */

    public static void main(String[] args) {
       List<List<Integer>> testServers = List.of(List.of(0,1), List.of(1,2), List.of(2,0), List.of(1,3));
        int connections = 4;
        System.out.println("Critical Connection:" + Solution.criticalConnections(connections, testServers));
    }

    static class Solution {
        static int time =0;
        static List<List<Integer>> result;

        // Time: O(N)
        // Space: O(N)
        public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
            List<Integer>[] adj = new ArrayList[n];

            for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();

            for(List<Integer> edge:connections){
                int a = edge.get(0);
                int b = edge.get(1);

                adj[a].add(b);
                adj[b].add(a);
            }

            boolean[] visited = new boolean[n];
            int[] timestamp = new int[n];
            result = new ArrayList<>();
            dfs(adj,visited,timestamp,0,-1);
            return result;
        }

        static private void dfs(List<Integer>[] adj,boolean[] visited,int[] timestamp,int vertex, int prev){
            visited[vertex]=true;
            timestamp[vertex] = time++;
            int currentTimeStamp = timestamp[vertex];


            for(int v : adj[vertex]){
                if(v == prev) continue;

                if(!visited[v]) dfs(adj,visited,timestamp,v,vertex);

                timestamp[vertex] = Math.min(timestamp[vertex],timestamp[v]);

                if(currentTimeStamp < timestamp[v]) result.add(Arrays.asList(vertex,v));

            }
        }
    }

}
