import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximumAveragePassRatio {
    /*
        There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array classes, where classes[i] = [passi, totali].
        You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam.
        You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to.
        You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.
        The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class.
        The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.

        Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10-5 of the actual answer will be accepted.


        Example 1:

        Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
        Output: 0.78333
        Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.
        Example 2:

        Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
        Output: 0.53485


        Constraints:

        1 <= classes.length <= 105
        classes[i].length == 2
        1 <= passi <= totali <= 105
        1 <= extraStudents <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: "  + Solution.maxAverageRatio(new int[][]{{1,2},{3,5},{2,2}}, 2));
    }

    // Time: O(MLogN+NLogN)
    // Space: O(N)
    static class Solution {
        public static double maxAverageRatio(int[][] classes, int extraStudents) {
            PriorityQueue<double[]> maxHeap = new PriorityQueue<>(Comparator.comparingDouble(o -> -o[0]));
            for (int[] c : classes) {
                double a = c[0], b = c[1];
                maxHeap.offer(new double[]{profit(a, b), a, b});
            }
            while (extraStudents-- > 0) {
                double[] top = maxHeap.poll();
                assert top != null;
                double a = top[1], b = top[2];
                maxHeap.offer(new double[]{profit(a+1, b+1), a+1, b+1});
            }
            double ans = 0.0d;
            while (!maxHeap.isEmpty()) {
                double[] top = maxHeap.poll();
                double a = top[1], b = top[2];
                ans += a/b;
            }
            return ans / classes.length;
        }
        static double profit(double a, double b) {
            return (a + 1) / (b + 1) - a / b;
        }
    }
}
