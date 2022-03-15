import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals {

    /*
        Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
        and return an array of the non-overlapping intervals that cover all the intervals in the input.

        Example 1.
        Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
        Output: [[1,6],[8,10],[15,18]]
        Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
        Example 2.
        Input: intervals = [[1,4],[4,5]]
        Output: [[1,5]]
        Explanation: Intervals [1,4] and [4,5] are considered overlapping.

        Constraints:

        1 <= intervals.length <= 104
        intervals[i].length == 2
        0 <= starti <= endi <= 104
     */

    public static void main(String[] args) {
        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        Arrays.stream(Solution.merge(intervals)).forEach(arr -> System.out.println(Arrays.toString(arr)));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, (a,b) -> a[0] - b[0]);
            List<int[]> res = new ArrayList<>();
            res.add(intervals[0]);
            int j = 0;

            for(int i=1; i<intervals.length; i++) {
                int[] interval = res.get(j);

                if(intervals[i][0] <= interval[1]) {
                    interval[1] = Math.max(intervals[i][1], interval[1]);
                } else {
                    res.add(intervals[i]);
                    j++;
                }
            }

            int[][] arr = new int[j+1][2];
            for(int i=0; i<j+1; i++)
                arr[i] = res.get(i);
            return arr;
        }
    }
}
