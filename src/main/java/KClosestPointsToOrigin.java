import java.util.Arrays;
import java.util.Random;

public class KClosestPointsToOrigin {
    /*
        Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
        The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
        You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

        Example 1.
        Input: points = [[1,3],[-2,2]], k = 1
        Output: [[-2,2]]
        Explanation:
        The distance between (1, 3) and the origin is sqrt(10).
        The distance between (-2, 2) and the origin is sqrt(8).
        Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
        We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
        Example 2.
        Input: points = [[3,3],[5,-1],[-2,4]], k = 2
        Output: [[3,3],[-2,4]]
        Explanation: The answer [[-2,4],[3,3]] would also be accepted.


        Constraints:

        1 <= k <= points.length <= 104
        -104 < xi, yi < 104
     */

    public static void main(String[] args) {
        int[][] points = {
                {3,3},
                {5,-1},
                {-2,4}
        };
        int k = 2;
        System.out.println("Solution: " + Arrays.deepToString(Solution.kClosest(points, k)));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        static Random random = new Random();
        public static int[][] kClosest(int[][] points, int k) {
            int l = 0;
            int r = points.length - 1;
            while (l < r) {
                int p = partition(points, l, r);
                if (p == k) break;
                if (p < k) l = p + 1;
                else r = p - 1;
            }
            return Arrays.copyOf(points, k);
        }

        static int partition(int[][] points, int l, int r) {
            int p = l;
            int pivotIndex = l + random.nextInt(r - l);
            int pivotDistance = distance(points[pivotIndex]);
            swap(points, pivotIndex, r);
            for (int i = l; i < r; i++) {
                if (distance(points[i]) <= pivotDistance) {
                    swap(points, i, p);
                    p++;
                }
            }
            swap(points, p, r);
            return p;
        }

        static int distance(int[] p) {
            return p[0] * p[0] + p[1] * p[1];
        }

        static void swap(int[][] points, int l, int r) {
            int[] temp = points[l];
            points[l] = points[r];
            points[r] = temp;
        }
    }
}
