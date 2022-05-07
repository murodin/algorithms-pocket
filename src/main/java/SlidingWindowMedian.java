import java.util.Arrays;
import java.util.PriorityQueue;

public class SlidingWindowMedian {
    /*
        The median is the middle value in an ordered integer list. If the size of the list is even,
         there is no middle value. So the median is the mean of the two middle values.

        For examples, if arr = [2,3,4], the median is 3.
        For examples, if arr = [1,2,3,4], the median is (2 + 3) / 2 = 2.5.
        You are given an integer array nums and an integer k. There is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

        Return the median array for each window in the original array. Answers within 10-5 of the actual value will be accepted.

        Example 1.
        Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
        Output: [1.00000,-1.00000,-1.00000,3.00000,5.00000,6.00000]
        Explanation:
        Window position                Median
        ---------------                -----
        [1  3  -1] -3  5  3  6  7        1
         1 [3  -1  -3] 5  3  6  7       -1
         1  3 [-1  -3  5] 3  6  7       -1
         1  3  -1 [-3  5  3] 6  7        3
         1  3  -1  -3 [5  3  6] 7        5
         1  3  -1  -3  5 [3  6  7]       6
        Example 2.
        Input: nums = [1,2,3,4,2,3,1,4,2], k = 3
        Output: [2.00000,3.00000,3.00000,3.00000,2.00000,3.00000,2.00000]


        Constraints:

        1 <= k <= nums.length <= 105
        -231 <= nums[i] <= 231 - 1
     */

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,2,3,1,4,2};
        System.out.println("Sliding Window Median: " + Arrays.toString(Solution.medianSlidingWindow(nums, 3)));
    }

    // Time: O(N * logK + (N-K) * K)
    // Space: O(K) except the output.
    static class Solution {
        private static PriorityQueue<Integer> left;  // max heap
        private static PriorityQueue<Integer> right;  // min heap
        public static double[] medianSlidingWindow(int[] nums, int k) {
            left = new PriorityQueue<>(k/2+2, (a, b) -> Integer.compare(b, a));
            right = new PriorityQueue<>(k/2+2);
            double[] res = new double[nums.length - k + 1];
            for (int i = 0; i < nums.length; i++) {
                add(nums[i]);
                int j = i - k + 1 ;
                if (j >= 0) {
                    res[j] = getMedian();
                    remove(nums[j]);
                }
            }
            return res;
        }

        private static double getMedian() {
            if (left.size() == right.size()) {
                return ((double) left.peek() + right.peek()) / 2.0;
            } else if (left.size() > right.size()) {
                return left.peek();
            } else {
                return right.peek();
            }
        }

        private static void add(int num) {
            if (left.isEmpty() || left.peek() >= num) {
                left.add(num);
            } else {
                right.add(num);
            }
            rebalance();
        }

        private static void remove(int num) {
            if (num <= left.peek()) {
                left.remove(num);
            } else {
                right.remove(num);
            }
            rebalance();
        }

        private static void rebalance() {
            if (left.size() > right.size() + 1) {
                right.add(left.poll());
            } else if (left.size() < right.size()) {
                left.add(right.poll());
            }
        }
    }
}
