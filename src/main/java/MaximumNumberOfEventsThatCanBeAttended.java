import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximumNumberOfEventsThatCanBeAttended {
    /*
        You are given an array of events where events[i] = [startDayi, endDayi]. Every event i starts at startDayi and ends at endDayi.
        You can attend an event i at any day d where startTimei <= d <= endTimei. You can only attend one event at any time d.

        Return the maximum number of events you can attend.

        Example 1:
        Input: events = [[1,2],[2,3],[3,4]]
        Output: 3
        Explanation: You can attend all the three events.
        One way to attend them all is as shown.
        Attend the first event on day 1.
        Attend the second event on day 2.
        Attend the third event on day 3.
        Example 2:
        Input: events= [[1,2],[2,3],[3,4],[1,2]]
        Output: 4


        Constraints:

        1 <= events.length <= 105
        events[i].length == 2
        1 <= startDayi <= endDayi <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxEvents(new int[][]{{1,2},{2,3},{3,4}}));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static int maxEvents(int[][] A) {
            PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
            Arrays.sort(A, Comparator.comparingInt(a -> a[0]));
            int i = 0, res = 0, n = A.length;
            for (int d = 1; d <= 100000; ++d) {
                while (!pq.isEmpty() && pq.peek() < d)
                    pq.poll();
                while (i < n && A[i][0] == d)
                    pq.offer(A[i++][1]);
                if (!pq.isEmpty()) {
                    pq.poll();
                    ++res;
                }
            }
            return res;
        }
    }
}
