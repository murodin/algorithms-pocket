import java.util.Arrays;
import java.util.PriorityQueue;

public class SingleThreadedCPU {
    /*
        You are given n tasks labeled from 0 to n - 1 represented by a 2D integer array tasks,
        where tasks[i] = [enqueueTime-i, processingTime-i] means that the ith task will be available to process at enqueueTime-i and will take processingTime-i to finish processing.
        You have a single-threaded CPU that can process at most one task at a time and will act in the following way:
        If the CPU is idle and there are no available tasks to process, the CPU remains idle.
        If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time.
        If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
        Once a task is started, the CPU will process the entire task without stopping.
        The CPU can finish a task then start a new one instantly.
        Return the order in which the CPU will process the tasks.

        Example 1.
        Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
        Output: [0,2,3,1]
        Explanation: The events go as follows:
        - At time = 1, task 0 is available to process. Available tasks = {0}.
        - Also at time = 1, the idle CPU starts processing task 0. Available tasks = {}.
        - At time = 2, task 1 is available to process. Available tasks = {1}.
        - At time = 3, task 2 is available to process. Available tasks = {1, 2}.
        - Also at time = 3, the CPU finishes task 0 and starts processing task 2 as it is the shortest. Available tasks = {1}.
        - At time = 4, task 3 is available to process. Available tasks = {1, 3}.
        - At time = 5, the CPU finishes task 2 and starts processing task 3 as it is the shortest. Available tasks = {1}.
        - At time = 6, the CPU finishes task 3 and starts processing task 1. Available tasks = {}.
        - At time = 10, the CPU finishes task 1 and becomes idle.
        Example 2.
        Input: tasks = [[7,10],[7,12],[7,5],[7,4],[7,2]]
        Output: [4,3,2,0,1]
        Explanation: The events go as follows:
        - At time = 7, all the tasks become available. Available tasks = {0,1,2,3,4}.
        - Also at time = 7, the idle CPU starts processing task 4. Available tasks = {0,1,2,3}.
        - At time = 9, the CPU finishes task 4 and starts processing task 3. Available tasks = {0,1,2}.
        - At time = 13, the CPU finishes task 3 and starts processing task 2. Available tasks = {0,1}.
        - At time = 18, the CPU finishes task 2 and starts processing task 0. Available tasks = {1}.
        - At time = 28, the CPU finishes task 0 and starts processing task 1. Available tasks = {}.
        - At time = 40, the CPU finishes task 1 and becomes idle.

        Constraints:

        tasks.length == n
        1 <= n <= 105
        1 <= enqueueTime-i, processingTime-i <= 109
     */

    public static void main(String[] args) {
        int[][] tasks = {{7,10},{7,12},{7,5},{7,4},{7,2}};
        System.out.println("Solution: " + Arrays.toString(Solution.getOrder(tasks)));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static int[] getOrder(int[][] tasks1) {
            int n = tasks1.length;

            int[][] tasks = new int[n][3];

            //tasks ->0 = enqueueTimei 1 = processingTimei, 2 -> location
            for(int i = 0; i < n; i++) {
                tasks[i] = new int[]{tasks1[i][0], tasks1[i][1], i};
            }

            //sort tasks based on enqueue time
            Arrays.sort(tasks, (a, b) -> a[0]-b[0]);
            // [[1,2, 0],[2,4, 1],[3,2, 2],[4,1, 3]]

            // create min heap where we sort the items based on processing time.
            // if processing time is equal then sort based on location of the item
            // If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
            PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
                if(a[1] == b[1]) {
                    return a[2] - b[2];
                } else {
                    return a[1] - b[1];
                }
            });

            // intital time when CPU will start processing the first task
            int time = tasks[0][0];

            // contain the order in which CPU will process the tasks
            int[] taskOrderResult = new int[n];

            int i = 0; // index on tasks array
            int index = 0; // index for result
            // initially pq is empty so we will start with index 0
            while(!minHeap.isEmpty() || i < n) {
                // if the current time is greater than or equal to enqueue time in the tasks list
                // then add to pq because cpu can process them based on the conditions such as
                // shortest processing time and the one with smallest index when processing time is equal
                while(i < n && time >= tasks[i][0]) {
                    minHeap.add(tasks[i++]);
                }

                if(!minHeap.isEmpty()) {
                    int[] task = minHeap.remove();
                    taskOrderResult[index++] = task[2]; // the 2nd element is index of the process
                    time += task[1]; // increase the current time by processing time as cpu process the entire task without stopping. so the time cpu is done with current task will be current time + processing time of the task
                } else {
                    time = tasks[i][0]; //set the current time to processing time as cpu process the entire task without stopping. so the time cpu is done with current task will be  processing time of the task
                }
            }

            return taskOrderResult;
        }
    }
}
