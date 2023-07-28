import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumAreaRectangleII {
    /*
        You are given an array of points in the X-Y plane points where points[i] = [xi, yi].
        Return the minimum area of any rectangle formed from these points, with sides not necessarily parallel to the X and Y axes.
        If there is not any such rectangle, return 0.
        Answers within 10-5 of the actual answer will be accepted.

        Example 1:


        Input: points = [[1,2],[2,1],[1,0],[0,1]]
        Output: 2.00000
        Explanation: The minimum area rectangle occurs at [1,2],[2,1],[1,0],[0,1], with an area of 2.
        Example 2:


        Input: points = [[0,1],[2,1],[1,1],[1,0],[2,0]]
        Output: 1.00000
        Explanation: The minimum area rectangle occurs at [1,0],[1,1],[2,1],[2,0], with an area of 1.
        Example 3:


        Input: points = [[0,3],[1,2],[3,1],[1,3],[2,1]]
        Output: 0
        Explanation: There is no possible rectangle to form from these points.


        Constraints:

        1 <= points.length <= 50
        points[i].length == 2
        0 <= xi, yi <= 4 * 104
        All the given points are unique.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minAreaFreeRect(new int[][]{{1,2},{2,1},{1,0},{0,1}}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static double minAreaFreeRect(int[][] p) {
            Map<String, List<int[]>> map = new HashMap<>();
            for(int i = 0; i < p.length; i++) {
                for(int j = i + 1; j < p.length; j++) {
                    double x = (p[i][0] + p[j][0]) / 2.0;
                    double y = (p[i][1] + p[j][1]) / 2.0;
                    String key = x + ","+ y + "," + getDist(p, i, j);
                    map.putIfAbsent(key, new ArrayList<>());
                    map.get(key).add(new int[]{i, j});
                }
            }

            double res = Double.MAX_VALUE;
            for(List<int[]> l : map.values()) {
                for(int i = 0; i < l.size(); i++) {
                    for(int j = i + 1; j < l.size(); j++) {
                        res = Math.min(res, getArea(p, l.get(i), l.get(j)));
                    }
                }
            }
            return res == Double.MAX_VALUE ? 0 : res;
        }

        static int getDist(int[][] p, int i, int j) {
            return (p[i][0]-p[j][0])*(p[i][0]-p[j][0]) + (p[i][1]-p[j][1])*(p[i][1]-p[j][1]);
        }

        static double getArea(int[][] p, int[] a, int[] b) {
            int dis1 = getDist(p, a[0], b[0]);
            int dis2 = getDist(p, a[0], b[1]);
            return Math.sqrt(dis1) * Math.sqrt(dis2);
        }
    }
}
