import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TwoBestNonOverlappingEvents {
    /*
        You are given a 0-indexed 2D integer array of events where events[i] = [startTimei, endTimei, valuei].
        The ith event starts at startTimei and ends at endTimei, and if you attend this event, you will receive a value of valuei.
        You can choose at most two non-overlapping events to attend such that the sum of their values is maximized.
        Return this maximum sum.
        Note that the start time and end time is inclusive: that is, you cannot attend two events where one of them starts and the other ends at the same time.
        More specifically, if you attend an event with end time t, the next event must start at or after t + 1.

        Example 1.
        Input: events = [[1,3,2],[4,5,2],[2,4,3]]
        Output: 4
        Explanation: Choose the green events, 0 and 1 for a sum of 2 + 2 = 4.
        Example 2.
        Example 1 Diagram
        Input: events = [[1,3,2],[4,5,2],[1,5,5]]
        Output: 5
        Explanation: Choose event 2 for a sum of 5.
        Example 3.
        Input: events = [[1,5,3],[1,5,1],[6,6,5]]
        Output: 8
        Explanation: Choose events 0 and 2 for a sum of 3 + 5 = 8.


        Constraints:

        2 <= events.length <= 105
        events[i].length == 3
        1 <= startTimei <= endTimei <= 109
        1 <= valuei <= 106
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxTwoEvents(new int[][]{{1,3,2},{4,5,2},{2,4,3}}));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static int maxTwoEvents(int[][] events) {
            int n = events.length;
            Arrays.sort(events, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
            int maxVal = 0, ans = 0;
            for(int[] e : events){
                int start = e[0];
                while(!queue.isEmpty()){
                    if(queue.peek()[0] >= start)
                        break;
                    int[] eve = queue.remove();
                    maxVal = Math.max(maxVal, eve[1]);
                }
                ans = Math.max(ans, e[2] + maxVal);
                queue.add(new int[]{e[1], e[2]});
            }
            return ans;
        }
    }
}
