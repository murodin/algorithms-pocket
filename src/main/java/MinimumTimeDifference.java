import java.util.Arrays;
import java.util.List;

public class MinimumTimeDifference {
    /*
        Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.


        Example 1:

        Input: timePoints = ["23:59","00:00"]
        Output: 1
        Example 2:

        Input: timePoints = ["00:00","23:59","00:00"]
        Output: 0


        Constraints:

        2 <= timePoints.length <= 2 * 104
        timePoints[i] is in the format "HH:MM".
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findMinDifference(List.of("23:59","00:00")));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static int findMinDifference(List<String> timePoints) {
            int[] times = new int[timePoints.size()];
            int min = Integer.MAX_VALUE;
            for(int i = 0;i<timePoints.size();i++){
                String[] timeArr = timePoints.get(i).split(":");
                times[i] = Integer.parseInt(timeArr[0])*60  + Integer.parseInt(timeArr[1]);
            }
            Arrays.sort(times);
            for(int i = 1;i<times.length;i++){
                min = Math.min(times[i]-times[i-1],min);
            }
            int wrapAroundTime = Math.abs(times[times.length-1] - (times[0]+1440));
            min = Math.min(wrapAroundTime,min);
            return min;
        }
    }
}
