import javafx.util.Pair;

import java.util.*;

public class MostProfitAssigningWork {
    /*
        You have n jobs and m workers. You are given three arrays: difficulty, profit, and worker where:
        difficulty[i] and profit[i] are the difficulty and the profit of the ith job, and
        worker[j] is the ability of jth worker (i.e., the jth worker can only complete a job with difficulty at most worker[j]).
        Every worker can be assigned at most one job, but one job can be completed multiple times.
        For example, if three workers attempt the same job that pays $1, then the total profit will be $3. If a worker cannot complete any job, their profit is $0.
        Return the maximum profit we can achieve after assigning the workers to the jobs.

        Example 1.
        Input: difficulty = [2,4,6,8,10], profit = [10,20,30,40,50], worker = [4,5,6,7]
        Output: 100
        Explanation: Workers are assigned jobs of difficulty [4,4,6,6] and they get a profit of [20,20,30,30] separately.
        Example 2.
        Input: difficulty = [85,47,57], profit = [24,66,99], worker = [40,25,25]
        Output: 0


        Constraints:

        n == difficulty.length
        n == profit.length
        m == worker.length
        1 <= n, m <= 104
        1 <= difficulty[i], profit[i], worker[i] <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxProfitAssignment(new int[]{2,4,6,8,10}, new int[]{10,20,30,40,50}, new int[]{4,5,6,7}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
            List<Pair<Integer, Integer>> jobs = new ArrayList<>();
            int N = profit.length, res = 0, i = 0, best = 0;
            for (int j = 0; j < N; ++j)
                jobs.add(new Pair<Integer, Integer>(difficulty[j], profit[j]));
            jobs.sort(Comparator.comparing(Pair::getKey));
            Arrays.sort(worker);
            for (int ability : worker) {
                while (i < N && ability >= jobs.get(i).getKey())
                    best = Math.max(jobs.get(i++).getValue(), best);
                res += best;
            }
            return res;
        }
    }
}
