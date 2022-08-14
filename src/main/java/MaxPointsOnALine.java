import java.util.HashMap;

public class MaxPointsOnALine {
    /*
        Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.

        Example 1.
        Input: points = [[1,1],[2,2],[3,3]]
        Output: 3
        Example 2.
        Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
        Output: 4


        Constraints:

        1 <= points.length <= 300
        points[i].length == 2
        -104 <= xi, yi <= 104
        All the points are unique.
     */

    public static void main(String[] args) {
        int[][] points = { {1,1}, {3,2}, {5,3}, {4,1}, {2,3}, {1,4}};
        System.out.println("Solution: " + Solution.maxPoints(points));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int maxPoints(int[][] points) {
            int max = 0;
            for(int i = 0; i < points.length; ++i){
                int vertical = 0;
                int horizontal = 0;
                HashMap<Double,Integer> map = new HashMap();
                for(int j = 0; j<points.length; ++j){
                    if(i != j){
                        if(points[i][0] == points[j][0]){
                            vertical++;
                            max = Math.max(vertical,max);
                        } else if(points[i][1] == points[j][1]){
                            horizontal++;
                            max = Math.max(horizontal,max);
                        } else{
                            double gradient = (points[i][1] - points[j][1])*1.00/(points[i][0] - points[j][0]);
                            max = Math.max(max,map.getOrDefault(gradient,0)+1);
                            map.put(gradient,map.getOrDefault(gradient,0)+1);}
                    }
                }
            }
            return max + 1;
        }
    }
}
