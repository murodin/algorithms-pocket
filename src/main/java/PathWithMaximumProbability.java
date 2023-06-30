import java.util.*;

public class PathWithMaximumProbability {
    /*
        You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge
        connecting the nodes a and b with a probability of success of traversing that edge succProb[i].

        Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.

        If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.

        Example 1:

        Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
        Output: 0.25000
        Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
        Example 2:



        Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
        Output: 0.30000
        Example 3:



        Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
        Output: 0.00000
        Explanation: There is no path between 0 and 2.


        Constraints:

        2 <= n <= 10^4
        0 <= start, end < n
        start != end
        0 <= a, b < n
        a != b
        0 <= succProb.length == edges.length <= 2*10^4
        0 <= succProb[i] <= 1
        There is at most one edge between every two nodes.
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    // Time: O(NLogN)
    // Space: O(N)
    class Solution {
        public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
            List<List<Pair<Integer, Double>>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                adj.add(new ArrayList<>());
            }

            for (int i = 0; i < edges.length; i++) {
                int u = edges[i][0];
                int v = edges[i][1];
                double p = succProb[i];
                adj.get(u).add(new Pair<>(v, p));
                adj.get(v).add(new Pair<>(u, p));
            }

            double[] dist = new double[n];
            Arrays.fill(dist, 0.0);
            dist[start] = 1.0;

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);

            while (!queue.isEmpty()) {
                int curr = queue.poll();

                for (Pair<Integer, Double> pair : adj.get(curr)) {
                    int node = pair.getKey();
                    double prob = pair.getValue();
                    double newProb = dist[curr] * prob;

                    if (newProb > dist[node]) {
                        dist[node] = newProb;
                        queue.offer(node);
                    }
                }
            }

            return dist[end];
        }

        private class Pair<K, V> {
            private K key;
            private V value;

            public Pair(K key, V value) {
                this.key = key;
                this.value = value;
            }

            public K getKey() {
                return key;
            }

            public V getValue() {
                return value;
            }
        }
    }
}
