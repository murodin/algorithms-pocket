import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumIntervalToIncludeEachQuery {
    /*
        You are given a 2D integer array intervals, where intervals[i] = [lefti, righti] describes the ith interval starting at lefti and ending at righti (inclusive).
        The size of an interval is defined as the number of integers it contains, or more formally righti - lefti + 1.
        You are also given an integer array queries. The answer to the jth query is the size of the smallest interval i such that lefti <= queries[j] <= righti.
        If no such interval exists, the answer is -1.
        Return an array containing the answers to the queries.

        Example 1:

        Input: intervals = [[1,4],[2,4],[3,6],[4,4]], queries = [2,3,4,5]
        Output: [3,3,1,4]
        Explanation: The queries are processed as follows:
        - Query = 2: The interval [2,4] is the smallest interval containing 2. The answer is 4 - 2 + 1 = 3.
        - Query = 3: The interval [2,4] is the smallest interval containing 3. The answer is 4 - 2 + 1 = 3.
        - Query = 4: The interval [4,4] is the smallest interval containing 4. The answer is 4 - 4 + 1 = 1.
        - Query = 5: The interval [3,6] is the smallest interval containing 5. The answer is 6 - 3 + 1 = 4.
        Example 2:

        Input: intervals = [[2,3],[2,5],[1,8],[20,25]], queries = [2,19,5,22]
        Output: [2,-1,4,6]
        Explanation: The queries are processed as follows:
        - Query = 2: The interval [2,3] is the smallest interval containing 2. The answer is 3 - 2 + 1 = 2.
        - Query = 19: None of the intervals contain 19. The answer is -1.
        - Query = 5: The interval [2,5] is the smallest interval containing 5. The answer is 5 - 2 + 1 = 4.
        - Query = 22: The interval [20,25] is the smallest interval containing 22. The answer is 25 - 20 + 1 = 6.


        Constraints:

        1 <= intervals.length <= 105
        1 <= queries.length <= 105
        intervals[i].length == 2
        1 <= lefti <= righti <= 107
        1 <= queries[j] <= 107
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.minInterval(new int[][]{{1, 4}, {2, 4}, {3, 6}, {4, 4}}, new int[]{2, 3, 4, 5})));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static int[] minInterval(int[][] intervals, int[] queries) {
            int numQuery = queries.length;
            //append index in query
            int[][] queriesWithIndex = new int[numQuery][2];
            for(int i = 0; i < numQuery; i++){
                queriesWithIndex[i] = new int[]{queries[i], i};
            }
            Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
            Arrays.sort(queriesWithIndex, (a, b) -> (a[0] - b[0]));
            //sort interval in increasing order of size
            PriorityQueue<int[]> minHeap = new PriorityQueue<int[]>((a, b) -> ((a[1] - a[0]) - (b[1] - b[0])));

            int[] result = new int[numQuery];
            int j = 0;
            for(int i = 0; i < queries.length; i++){
                int queryVal = queriesWithIndex[i][0];
                int queryIndex = queriesWithIndex[i][1];
                //add all the interval which start is less or equal than current query value
                while(j < intervals.length && intervals[j][0] <= queryVal){
                    minHeap.add(intervals[j]);
                    j++;
                }
                //remove all the smallest size interval which end val is less than current query value
                while(!minHeap.isEmpty() && minHeap.peek()[1] < queryVal){
                    minHeap.remove();
                }
                //now if heap is empty it means there is no interval which satisfy query val
                result[queryIndex] = minHeap.isEmpty() ? -1 : (minHeap.peek()[1] - minHeap.peek()[0] + 1);
            }

            return result;
        }
    }
}

