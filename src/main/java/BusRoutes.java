import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

public class BusRoutes {
    /*
        You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

        For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
        You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel between bus stops by buses only.

        Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.



        Example 1:

        Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
        Output: 2
        Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
        Example 2:

        Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
        Output: -1


        Constraints:

        1 <= routes.length <= 500.
        1 <= routes[i].length <= 105
        All the values of routes[i] are unique.
        sum(routes[i].length) <= 105
        0 <= routes[i][j] < 106
        0 <= source, target < 106
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numBusesToDestination(new int[][]{{1,2,7},{3,6,7}}, 1, 6));
    }

    // Time: O(N^2LogN)
    // Space: O(N)
    static class Solution {
        public static int numBusesToDestination(int[][] routes, int S, int T) {
            int n = routes.length;
            HashMap<Integer, HashSet<Integer>> to_routes = new HashMap<>();
            for (int i = 0; i < routes.length; ++i) {
                for (int j : routes[i]) {
                    if (!to_routes.containsKey(j))
                        to_routes.put(j, new HashSet<Integer>());
                    to_routes.get(j).add(i);
                }
            }
            Queue<int[]> bfs = new ArrayDeque();
            bfs.offer(new int[] {S, 0});
            HashSet<Integer> seen = new HashSet<>();
            seen.add(S);
            boolean[] seen_routes = new boolean[n];
            while (!bfs.isEmpty()) {
                int stop = bfs.peek()[0], bus = bfs.peek()[1];
                bfs.poll();
                if (stop == T) return bus;
                for (int i : to_routes.get(stop)) {
                    if (seen_routes[i]) continue;
                    for (int j : routes[i]) {
                        if (!seen.contains(j)) {
                            seen.add(j);
                            bfs.offer(new int[] {j, bus + 1});
                        }
                    }
                    seen_routes[i] = true;
                }
            }
            return -1;
        }
    }
}
