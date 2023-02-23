import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class MinimumCostToHireKWorkers {
    /*
        There are n workers. You are given two integer arrays quality and wage where quality[i] is the quality of the ith worker and wage[i] is the minimum wage expectation for the ith worker.
        We want to hire exactly k workers to form a paid group. To hire a group of k workers, we must pay them according to the following rules:
        Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
        Every worker in the paid group must be paid at least their minimum wage expectation.
        Given the integer k, return the least amount of money needed to form a paid group satisfying the above conditions. Answers within 10-5 of the actual answer will be accepted.

        Example 1.
        Input: quality = [10,20,5], wage = [70,50,30], k = 2
        Output: 105.00000
        Explanation: We pay 70 to 0th worker and 35 to 2nd worker.
        Example 2.
        Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], k = 3
        Output: 30.66667
        Explanation: We pay 4 to 0th worker, 13.33333 to 2nd and 3rd workers separately.


        Constraints:

        n == quality.length == wage.length
        1 <= k <= n <= 104
        1 <= quality[i], wage[i] <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.mincostToHireWorkers(new int[]{3,1,10,10,1}, new int[]{4,8,2,2,7}, 3));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static double mincostToHireWorkers(int[] quality, int[] wage, int K) {
            if (K <= 0 || quality == null || quality.length == 0 || wage == null || wage.length == 0) return 0.0;
            int n = quality.length;
            if (wage.length != n) return 0;
            double[][] ratio = new double[n][2];
            for (int i = 0; i < n; i++) {
                ratio[i][0] = (double) wage[i] / quality[i];
                ratio[i][1] = quality[i];
            }

            Arrays.sort(ratio, (a, b) -> Double.compare(a[0], b[0]));
            PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            double sum = 0;
            double min = Double.MAX_VALUE;

            for (double[] e : ratio) {
                sum += e[1];
                maxHeap.offer(e[1]);
                if (maxHeap.size() > K)
                    sum -= maxHeap.poll();
                if (maxHeap.size() == K)
                    min = Math.min(min, e[0] * sum);
            }
            return min;
        }
    }
}
