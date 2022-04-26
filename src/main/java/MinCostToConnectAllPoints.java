public class MinCostToConnectAllPoints {
    /*
        You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].
        The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

        Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.

        Example 1.
        Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
        Output: 20
        Explanation:

        We can connect the points as shown above to get the minimum cost of 20.
        Notice that there is a unique path between every pair of points.
        Example 2.
        Input: points = [[3,12],[-2,5],[-4,1]]
        Output: 18

        Constraints:

        1 <= points.length <= 1000
        -106 <= xi, yi <= 106
        All pairs (xi, yi) are distinct.
     */

    public static void main(String[] args) {
        int[][] points = {{0,0}, {2,2}, {3,10}, {5,2}, {7,0}};
        System.out.println("Min Cost: " + Solution.minCostConnectPoints(points));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int minCostConnectPoints(int[][] points) {
            int len = points.length;
            // array that keep track of the shortest distance from mst to each node
            int[] disArr = new int[len];
            for (int i = 1; i < len; ++i) {
                disArr[i] = Integer.MAX_VALUE;
            }
            // visited[node] == true if node in mst
            boolean[] visited = new boolean[len];
            visited[0] = true;

            int numEdge = 0;
            // current node, used to update the disArr
            int cur = 0;
            // result
            int res = 0;

            while (numEdge++ < len - 1) {
                int minEdge = Integer.MAX_VALUE;
                int next = -1;
                for (int i = 0; i < len; ++i) {
                    // if the node i is not in mst
                    if (!visited[i]) {
                        // find the distance between cur and i
                        int dis = Math.abs(points[cur][0] - points[i][0]) + Math.abs(points[cur][1] - points[i][1]);
                        // update distance array
                        disArr[i] = Math.min(dis, disArr[i]);
                        // find the shortest edge
                        if (disArr[i] < minEdge) {
                            minEdge = disArr[i];
                            next = i;
                        }
                    }
                }
                cur = next;
                visited[cur] = true;
                res += minEdge;
            }

            return res;
        }
    }
}
