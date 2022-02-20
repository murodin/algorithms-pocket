import java.util.Arrays;
import java.util.PriorityQueue;

public class KthSmallestPrimeFraction {

    /*
        You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are unique. You are also given an integer k.
        For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].
        Return the kth smallest fraction considered. Return your answer as an array of integers of size 2, where answer[0] == arr[i] and answer[1] == arr[j].

        Example 1.
        Input: arr = [1,2,3,5], k = 3
        Output: [2,5]
        Explanation: The fractions to be considered in sorted order are:
        1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
        The third fraction is 2/5.
        Example 2.
        Input: arr = [1,7], k = 1
        Output: [1,7]

        Constraints:

        2 <= arr.length <= 1000
        1 <= arr[i] <= 3 * 104
        arr[0] == 1
        arr[i] is a prime number for i > 0.
        All the numbers of arr are unique and sorted in strictly increasing order.
        1 <= k <= arr.length * (arr.length - 1) / 2
     */
    public static void main(String[] args) {
        int[] arr = {1,2,3,5};
        System.out.println("Solution 1: " + Arrays.toString(Solution_I.kthSmallestPrimeFraction(arr, 3)));
        System.out.println("Solution 1: " + Arrays.toString(Solution_II.kthSmallestPrimeFraction(arr, 3)));
    }

    // Time: (LogN*N^2)
    // Space: O(N)
    static class Solution_I {
        public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
            // max-heap
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
                double x = (double)b[0] / b[1];
                double y = (double)a[0] / a[1];
                if (x > y)
                    return 1;  // lambda exp. returns b(param of lambda exp), if the value is positive. Hence returned b.
                return -1;  // lambda exp. returns a(param of lambda exp), if the value is negative. Hence returned a.
            });

            for (int i = 0 ; i < arr.length ; i++) {
                for (int j = i+1 ; j < arr.length ; j++) {
                    pq.offer(new int[]{arr[i], arr[j]});
                    // Avoiding size greater than k
                    while (pq.size() > k) {
                        pq.poll();
                    }
                }
            }

            return pq.poll();
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_II {
        public static int[] kthSmallestPrimeFraction(int[] arr, int k) {
            int len = arr.length;
            int[] helper = new int[arr[len - 1] + 1];
            int num = 1;
            for (int i = 1; i < helper.length; i++) {
                if (i >= arr[num]) num++;
                helper[i] = num;
            }
            System.out.println("arr: " + Arrays.toString(arr));
            System.out.println("hepler: " + Arrays.toString(helper));

            double min = 0.0;
            double max = 1.0;
            int[] res = new int[2];
            while (true) {
                res[0] = 0;
                res[1] = 1;
                int count = 0;
                double mid = (min + max) / 2;
                for (int i = 1; i < len; i++) {
                    int v = (int) (mid * (double) arr[i]);
                    if (v == 0) continue;
                    count += helper[v];
                    if ((double) res[0] / (double) res[1] < (double) arr[helper[v] - 1] / (double) arr[i]) {
                        res[0] = arr[helper[v] - 1];
                        res[1] = arr[i];
                    }
                }
                if (count < k) {
                    min = mid;
                } else if (count > k) {
                    max = mid;
                } else return res;
            }
        }
    }
}
