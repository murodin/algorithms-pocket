import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class CollectCoinsInATree {
    /*
        There exists an undirected and unrooted tree with n nodes indexed from 0 to n - 1.
        You are given an integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi]
        indicates that there is an edge between nodes ai and bi in the tree. You are also given an array coins of size n where coins[i] can be either 0 or 1,
        where 1 indicates the presence of a coin in the vertex i.

        Initially, you choose to start at any vertex in the tree. Then, you can perform the following operations any number of times:

        Collect all the coins that are at a distance of at most 2 from the current vertex, or
        Move to any adjacent vertex in the tree.
        Find the minimum number of edges you need to go through to collect all the coins and go back to the initial vertex.

        Note that if you pass an edge several times, you need to count it into the answer several times.



        Example 1:


        Input: coins = [1,0,0,0,0,1], edges = [[0,1],[1,2],[2,3],[3,4],[4,5]]
        Output: 2
        Explanation: Start at vertex 2, collect the coin at vertex 0, move to vertex 3, collect the coin at vertex 5 then move back to vertex 2.
        Example 2:


        Input: coins = [0,0,0,1,1,0,0,1], edges = [[0,1],[0,2],[1,3],[1,4],[2,5],[5,6],[5,7]]
        Output: 2
        Explanation: Start at vertex 0, collect the coins at vertices 4 and 3, move to vertex 2,  collect the coin at vertex 7, then move back to vertex 0.


        Constraints:

        n == coins.length
        1 <= n <= 3 * 104
        0 <= coins[i] <= 1
        edges.length == n - 1
        edges[i].length == 2
        0 <= ai, bi < n
        ai != bi
        edges represents a valid tree.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.collectTheCoins(new int[]{1,0,0,0,0,1}, new int[][] {{0,1},{1,2},{2,3},{3,4},{4,5}}));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static int collectTheCoins(int[] coins, int[][] edges) {
            HashSet<Integer>[] map = new HashSet[coins.length];
            for (int i = 0; i < coins.length; i++) {
                map[i] = new HashSet<>();
            }
            for (int[] e : edges) {
                map[e[0]].add(e[1]);
                map[e[1]].add(e[0]);
            }

            int[] jumps = new int[coins.length];
            int[] dist = new int[coins.length];
            Arrays.fill(dist, 2);

            Queue<Integer> q = new LinkedList<>();

            for (int i = 0; i < coins.length; i++) {
                if (map[i].size() == 1 && coins[i] == 0) {
                    q.add(i);
                }
            }
            while ( ! q.isEmpty()) {
                int u = q.poll();
                if (map[u].size() == 0) {
                    continue;
                }
                int v = map[u].stream().findFirst().get();
                map[u].clear();
                map[v].remove(u);
                if ( map[v].size() == 1 && coins[v] == 0) {
                    q.add(v);
                }
            }

            for (int i = 0; i < coins.length; i++) {
                if (map[i].size() == 1) {
                    q.add(i);
                }
            }
            while ( ! q.isEmpty()) {
                int u = q.poll();
                if (map[u].size() == 0) {
                    // last vortex
                    return jumps[u];
                }

                int v = map[u].stream().findFirst().get();
                map[u].clear();
                map[v].remove(u);

                if (jumps[u] > 0) {
                    jumps[v] += (jumps[u]+2);
                } else if (dist[u] == 0) {
                    jumps[v] += 2;
                } else {
                    dist[v] = Math.min(dist[v], dist[u]-1);
                }

                if ( map[v].size() == 1) {
                    q.add(v);
                }

            }

            return 0;
        }
    }
}
