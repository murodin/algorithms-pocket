import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimizeTheTotalPriceOfTheTrips {
    /*
        There exists an undirected and unrooted tree with n nodes indexed from 0 to n - 1.
        You are given the integer n and a 2D integer array edges of length n - 1, where edges[i] = [ai, bi]
        indicates that there is an edge between nodes ai and bi in the tree.

        Each node has an associated price. You are given an integer array price, where price[i] is the price of the ith node.

        The price sum of a given path is the sum of the prices of all nodes lying on that path.

        Additionally, you are given a 2D integer array trips, where trips[i] = [starti, endi]
        indicates that you start the ith trip from the node starti and travel to the node endi by any path you like.

        Before performing your first trip, you can choose some non-adjacent nodes and halve the prices.

        Return the minimum total price sum to perform all the given trips.



        Example 1:


        Input: n = 4, edges = [[0,1],[1,2],[1,3]], price = [2,2,10,6], trips = [[0,3],[2,1],[2,3]]
        Output: 23
        Explanation: The diagram above denotes the tree after rooting it at node 2. The first part shows the initial tree and the second part shows the tree after choosing nodes 0, 2, and 3, and making their price half.
        For the 1st trip, we choose path [0,1,3]. The price sum of that path is 1 + 2 + 3 = 6.
        For the 2nd trip, we choose path [2,1]. The price sum of that path is 2 + 5 = 7.
        For the 3rd trip, we choose path [2,1,3]. The price sum of that path is 5 + 2 + 3 = 10.
        The total price sum of all trips is 6 + 7 + 10 = 23.
        It can be proven, that 23 is the minimum answer that we can achieve.
        Example 2:


        Input: n = 2, edges = [[0,1]], price = [2,2], trips = [[0,0]]
        Output: 1
        Explanation: The diagram above denotes the tree after rooting it at node 0. The first part shows the initial tree and the second part shows the tree after choosing node 0, and making its price half.
        For the 1st trip, we choose path [0]. The price sum of that path is 1.
        The total price sum of all trips is 1. It can be proven, that 1 is the minimum answer that we can achieve.


        Constraints:

        1 <= n <= 50
        edges.length == n - 1
        0 <= ai, bi <= n - 1
        edges represents a valid tree.
        price.length == n
        price[i] is an even integer.
        1 <= price[i] <= 1000
        1 <= trips.length <= 100
        0 <= starti, endi <= n - 1
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minimumTotalPrice(
                4, new int[][]{{0,1},{1,2},{1,3}},
                new int[]{2, 2, 10, 6},
                new int[][]{{0,3},{2,1},{2,3}}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {
            List<Integer>[] tree = buildTree(n, edges);

            int[] visitCount = new int[n];
            for(int[] trip : trips) {
                travel(trip[0], -1, trip[1], tree, visitCount);
            }

            int[] cost = new int[n];
            int[][] calculated = new int[n][2];
            for(int i = 0; i < n; i++) {
                Arrays.fill(calculated[i], -1);
                cost[i] = price[i] * visitCount[i];
            }

            return minPrice(trips[0][0], -1, tree, 1, cost, calculated);
        }

        private static int minPrice(int node, int parent, List<Integer>[] tree, int canHalf, int[] cost, int[][] calculated) {
            if (calculated[node][canHalf] != -1) {
                return calculated[node][canHalf];
            }

            int minPrice;

            int noHalfCurrentNodePrice = cost[node];
            for(int next : tree[node]) {
                if (next != parent) {
                    noHalfCurrentNodePrice += minPrice(next, node, tree, 1, cost, calculated);
                }
            }

            int halfCurrentNodePrice = Integer.MAX_VALUE;
            if (canHalf == 1) {
                halfCurrentNodePrice = cost[node] / 2;

                for(int next : tree[node]) {
                    if (next != parent) {
                        halfCurrentNodePrice += minPrice(next, node, tree, 0, cost, calculated);
                    }
                }
            }

            calculated[node][canHalf] = Math.min(noHalfCurrentNodePrice, halfCurrentNodePrice);

            return calculated[node][canHalf];
        }

        private static boolean travel(int node, int parent, int target, List<Integer>[] tree, int[] visitCount) {
            if (node == target) {
                visitCount[node]++;
                return true;
            }

            boolean found = false;
            for(int next : tree[node]) {
                if (parent != next) {
                    found = travel(next, node, target, tree, visitCount);
                }
                if (found) {
                    break;
                }
            }
            if (found) {
                visitCount[node]++;
            }

            return found;
        }

        private static List<Integer>[] buildTree(int n, int[][] edges) {
            List<Integer>[] tree = new ArrayList[n];
            for(int i = 0; i < n; i++) {
                tree[i] = new ArrayList<Integer>();
            }

            for(int[] edge : edges) {
                tree[edge[0]].add(edge[1]);
                tree[edge[1]].add(edge[0]);
            }

            return tree;
        }
    }
}
