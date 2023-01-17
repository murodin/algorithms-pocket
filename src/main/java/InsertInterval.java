import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertInterval {
    /*
        You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and
        intervals is sorted in ascending order by starti.
        You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
        Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and
        intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

        Return intervals after the insertion.

        Example 1.
        Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
        Output: [[1,5],[6,9]]
        Example 2.
        Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
        Output: [[1,2],[3,10],[12,16]]
        Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].


        Constraints:

        0 <= intervals.length <= 104
        intervals[i].length == 2
        0 <= starti <= endi <= 105
        intervals is sorted by starti in ascending order.
        newInterval.length == 2
        0 <= start <= end <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.deepToString(Solution.insert(new int[][]{{1, 3}, {6, 9}}, new int[]{2, 5})));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int[][] insert(int[][] intervals, int[] newInterval) {
            List<int[]> list = new ArrayList<>();
            for (int[] interval : intervals) {
                if (interval[1] < newInterval[0]) {
                    list.add(interval);
                } else if (interval[0] > newInterval[1]) {
                    list.add(new int[] {newInterval[0], newInterval[1]});
                    newInterval = interval;
                } else {
                    newInterval[0] = Math.min(newInterval[0], interval[0]);
                    newInterval[1] = Math.max(newInterval[1], interval[1]);
                }
            }
            list.add(newInterval);
            int[][] res = new int[list.size()][2];
            for (int i = 0; i < list.size(); i++) {
                res[i] = list.get(i);
            }
            return res;
        }
    }
}
