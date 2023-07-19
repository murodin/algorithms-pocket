import java.util.Arrays;

public class FindMinimumTimeToFinishAllJobs {
    /*
        You are given an integer array jobs, where jobs[i] is the amount of time it takes to complete the ith job.
        There are k workers that you can assign jobs to. Each job should be assigned to exactly one worker.
        The working time of a worker is the sum of the time it takes to complete all jobs assigned to them.
        Your goal is to devise an optimal assignment such that the maximum working time of any worker is minimized.
        Return the minimum possible maximum working time of any assignment.



        Example 1:

        Input: jobs = [3,2,3], k = 3
        Output: 3
        Explanation: By assigning each person one job, the maximum time is 3.
        Example 2:

        Input: jobs = [1,2,4,7,8], k = 2
        Output: 11
        Explanation: Assign the jobs the following way:
        Worker 1: 1, 2, 8 (working time = 1 + 2 + 8 = 11)
        Worker 2: 4, 7 (working time = 4 + 7 = 11)
        The maximum working time is 11.


        Constraints:

        1 <= k <= jobs.length <= 12
        1 <= jobs[i] <= 107
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minimumTimeRequired(new int[]{1,2,4,7,8}, 2));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int minimumTimeRequired(int[] jobs, int k) {
            Arrays.sort(jobs);
            int n = jobs.length;
            int left = jobs[n - 1];
            int right = jobs[n - 1] * n;
            while (left < right) {
                int[] cap = new int[k];
                int mid = left + (right - left) / 2;
                Arrays.fill(cap, mid);
                if (dfs(jobs, cap, n - 1, k, mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }

        private static boolean dfs(int[] jobs, int[] cap, int i, int k, int x) {
            if (i == -1) {
                return true;
            }
            for (int j = 0; j < k; j++) {
                if (cap[j] >= jobs[i]) {
                    cap[j] -= jobs[i];
                    if (dfs(jobs, cap, i - 1, k, x)) {
                        return true;
                    }
                    cap[j] += jobs[i];
                }
                if (cap[j] == x) {
                    break;
                }
            }
            return false;
        }
    }
}
