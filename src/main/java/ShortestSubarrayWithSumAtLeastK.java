import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestSubarrayWithSumAtLeastK {
    /*
        Given an integer array nums and an integer k, return the length of the shortest non-empty subarray of nums with a sum of at least k.
        If there is no such subarray, return -1.
        A subarray is a contiguous part of an array.
        Example 1:

        Input: nums = [1], k = 1
        Output: 1
        Example 2:

        Input: nums = [1,2], k = 4
        Output: -1
        Example 3:

        Input: nums = [2,-1,2], k = 3
        Output: 3


        Constraints:

        1 <= nums.length <= 105
        -105 <= nums[i] <= 105
        1 <= k <= 109
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.shortestSubarray(new int[]{2,-1,2}, 3));
    }

    // Time: O(MN)
    // Space: O(N)
    static class Solution {
        public static int shortestSubarray(int[] A, int K) {
            int N = A.length, res = N + 1;
            long[] B = new long[N + 1];
            for (int i = 0; i < N; i++) B[i + 1] = B[i] + A[i];
            Deque<Integer> d = new ArrayDeque<>();
            for (int i = 0; i < N + 1; i++) {
                while (!d.isEmpty() && B[i] - B[d.getFirst()] >=  K)
                    res = Math.min(res, i - d.pollFirst());
                while (!d.isEmpty() && B[i] <= B[d.getLast()])
                    d.pollLast();
                d.addLast(i);
            }
            return res <= N ? res : -1;
        }
    }
}
