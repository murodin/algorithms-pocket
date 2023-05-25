import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RectangleAreaII {
    /*
        You are given a 2D array of axis-aligned rectangles. Each rectangle[i] = [xi1, yi1, xi2, yi2] denotes the
        ith rectangle where (xi1, yi1) are the coordinates of the bottom-left corner, and (xi2, yi2) are the coordinates of the top-right corner.
        Calculate the total area covered by all rectangles in the plane. Any area covered by two or more rectangles should only be counted once.
        Return the total area. Since the answer may be too large, return it modulo 109 + 7.

        Example 1:


        Input: rectangles = [[0,0,2,2],[1,0,2,3],[1,0,3,1]]
        Output: 6
        Explanation: A total area of 6 is covered by all three rectangles, as illustrated in the picture.
        From (1,1) to (2,2), the green and red rectangles overlap.
        From (1,0) to (2,3), all three rectangles overlap.
        Example 2:

        Input: rectangles = [[0,0,1000000000,1000000000]]
        Output: 49
        Explanation: The answer is 1018 modulo (109 + 7), which is 49.


        Constraints:

        1 <= rectangles.length <= 200
        rectanges[i].length == 4
        0 <= xi1, yi1, xi2, yi2 <= 109
        xi1 <= xi2
        yi1 <= yi2
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.rectangleArea(new int[][]{{0,0,2,2},{1,0,2,3},{1,0,3,1}}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int rectangleArea(int[][] rectangles) {
            int mod = (int)(1e9 + 7);
            Set<Integer> x = new TreeSet<>();
            List<int[]> L = new ArrayList<>();
            for (int[] r : rectangles) {
                x.add(r[0]);
                x.add(r[2]);
                L.add(new int[]{r[1], r[0], r[2], 1});
                L.add(new int[]{r[3], r[0], r[2], -1});
            }
            List<Integer> unique = new ArrayList<>(x);
            Map<Integer, Integer> xMap = IntStream.range(0, unique.size()).boxed().collect(Collectors.toMap(unique::get, i -> i));
            int[] cnt = new int[unique.size()];
            Collections.sort(L, (a, b) -> a[0] == b[0] ?
                    a[1] == b[1] ? a[2] == b[2] ? Integer.compare(a[3], b[3]) :
                            Integer.compare(a[2], b[2]) :
                            Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
            long curY = 0;
            long curXSum = 0;
            long area = 0;
            for (int[] l : L) {
                long y = (long)l[0], x1 = (long)l[1], x2 = (long)l[2], sig = (long)l[3];
                area = (area + (y - curY) * curXSum) % mod;
                curY = y;
                for (int i = xMap.get((int)x1); i < xMap.get((int)x2); i++) {
                    cnt[i] += (int)sig;
                }
                curXSum = 0;
                for (int i = 0; i < unique.size(); i++) {
                    if (cnt[i] > 0) {
                        curXSum += unique.get(i + 1) - unique.get(i);
                    }
                }
            }
            return (int)area;
        }
    }
}
