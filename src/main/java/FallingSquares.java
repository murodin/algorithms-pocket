import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FallingSquares {
    /*
        There are several squares being dropped onto the X-axis of a 2D plane.
        You are given a 2D integer array positions where positions[i] = [lefti, sideLengthi] represents the ith square with a side l
        ength of sideLengthi that is dropped with its left edge aligned with X-coordinate lefti.
        Each square is dropped one at a time from a height above any landed squares.
        It then falls downward (negative Y direction) until it either lands on the top side of another square or on the X-axis.
        A square brushing the left/right side of another square does not count as landing on it. Once it lands, it freezes in place and cannot be moved.
        After each square is dropped, you must record the height of the current tallest stack of squares.
        Return an integer array ans where ans[i] represents the height described above after dropping the ith square.



        Example 1:
        Input: positions = [[1,2],[2,3],[6,1]]
        Output: [2,5,5]
        Explanation:
        After the first drop, the tallest stack is square 1 with a height of 2.
        After the second drop, the tallest stack is squares 1 and 2 with a height of 5.
        After the third drop, the tallest stack is still squares 1 and 2 with a height of 5.
        Thus, we return an answer of [2, 5, 5].
        Example 2:
        Input: positions = [[100,100],[200,100]]
        Output: [100,100]
        Explanation:
        After the first drop, the tallest stack is square 1 with a height of 100.
        After the second drop, the tallest stack is either square 1 or square 2, both with heights of 100.
        Thus, we return an answer of [100, 100].
        Note that square 2 only brushes the right side of square 1, which does not count as landing on it.


        Constraints:

        1 <= positions.length <= 1000
        1 <= lefti <= 108
        1 <= sideLengthi <= 106
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.fallingSquares(new int[][]{{100, 100}, {200, 100}}));
    }

    // Time: O(N^2LogN)
    // Space: O(N)
    static class Solution {
        public static List<Integer> fallingSquares(int[][] positions) {
            int[] ends = new int[positions.length*2];
            for(int i=0; i<positions.length; i++) {
                ends[i * 2] = positions[i][0];
                ends[i*2+1] = positions[i][0]+positions[i][1];
            }
            Arrays.sort(ends);
            int[] ceilings = new int[ends.length-1];
            int maxAll = 0;

            List<Integer> results = new ArrayList<>();
            for (int[] position : positions) {
                int X = position[0];
                int x = Arrays.binarySearch(ends, X);
                assert x >= 0;
                int maxCeiling = 0;
                int Y = X + position[1];
                for (int y = x; ends[y] < Y; y++)
                    maxCeiling = Math.max(maxCeiling, ceilings[y]);
                maxCeiling += (Y - X);
                for (int y = x; ends[y] < Y; y++)
                    ceilings[y] = maxCeiling;
                maxAll = Math.max(maxAll, maxCeiling);
                results.add(maxAll);
            }
            return results;
        }
    }
}
