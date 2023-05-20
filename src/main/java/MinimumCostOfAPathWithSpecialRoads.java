import java.util.*;

public class MinimumCostOfAPathWithSpecialRoads {
    /*
        You are given an array start where start = [startX, startY] represents your initial position (startX, startY) in a 2D space.
        You are also given the array target where target = [targetX, targetY] represents your target position (targetX, targetY).
        The cost of going from a position (x1, y1) to any other position in the space (x2, y2) is |x2 - x1| + |y2 - y1|.
        There are also some special roads. You are given a 2D array specialRoads where specialRoads[i] = [x1i, y1i, x2i, y2i, costi]
        indicates that the ith special road can take you from (x1i, y1i) to (x2i, y2i) with a cost equal to costi. You can use each special road any number of times.

        Return the minimum cost required to go from (startX, startY) to (targetX, targetY).



        Example 1:

        Input: start = [1,1], target = [4,5], specialRoads = [[1,2,3,3,2],[3,4,4,5,1]]
        Output: 5
        Explanation: The optimal path from (1,1) to (4,5) is the following:
        - (1,1) -> (1,2). This move has a cost of |1 - 1| + |2 - 1| = 1.
        - (1,2) -> (3,3). This move uses the first special edge, the cost is 2.
        - (3,3) -> (3,4). This move has a cost of |3 - 3| + |4 - 3| = 1.
        - (3,4) -> (4,5). This move uses the second special edge, the cost is 1.
        So the total cost is 1 + 2 + 1 + 1 = 5.
        It can be shown that we cannot achieve a smaller total cost than 5.
        Example 2:

        Input: start = [3,2], target = [5,7], specialRoads = [[3,2,3,4,4],[3,3,5,5,5],[3,4,5,6,6]]
        Output: 7
        Explanation: It is optimal to not use any special edges and go directly from the starting to the ending position with a cost |5 - 3| + |7 - 2| = 7.


        Constraints:

        start.length == target.length == 2
        1 <= startX <= targetX <= 105
        1 <= startY <= targetY <= 105
        1 <= specialRoads.length <= 200
        specialRoads[i].length == 5
        startX <= x1i, x2i <= targetX
        startY <= y1i, y2i <= targetY
        1 <= costi <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minimumCost(
                new int[]{1,1},
                new int[]{4,5},
                new int[][]{{1,2,3,3,2}, {3,4,4,5,1}}
        ));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static int minimumCost(int[] start, int[] target, int[][] specialRoads) {
            List<int[]> filteredRoads = new ArrayList<>();
            for (int[] road : specialRoads) {
                int a = road[0], b = road[1], c = road[2], d = road[3], cost = road[4];
                if (cost < Math.abs(a - c) + Math.abs(b - d)) {
                    filteredRoads.add(new int[]{a, b, c, d, cost});
                }
            }

            Map<List<Integer>, Integer> dist = new HashMap<>();
            dist.put(Arrays.asList(start[0], start[1]), 0);
            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
            heap.offer(new int[]{0, start[0], start[1]});

            while (!heap.isEmpty()) {
                int[] curr = heap.poll();
                int currdist = curr[0], x = curr[1], y = curr[2];
                for (int[] road : filteredRoads) {
                    int a = road[0], b = road[1], c = road[2], d = road[3], cost = road[4];
                    if (dist.getOrDefault(Arrays.asList(c, d), Integer.MAX_VALUE) > currdist + Math.abs(x - a) + Math.abs(y - b) + cost) {
                        dist.put(Arrays.asList(c, d), currdist + Math.abs(x - a) + Math.abs(y - b) + cost);
                        heap.offer(new int[]{dist.get(Arrays.asList(c, d)), c, d});
                    }
                }
            }

            int res = Math.abs(target[0] - start[0]) + Math.abs(target[1] - start[1]);
            for (int[] road : filteredRoads) {
                int a = road[0], b = road[1], c = road[2], d = road[3], cost = road[4];
                res = Math.min(res, dist.getOrDefault(Arrays.asList(c, d), Integer.MAX_VALUE) + Math.abs(target[0] - c) + Math.abs(target[1] - d));
            }

            return res;
        }
    }
}
