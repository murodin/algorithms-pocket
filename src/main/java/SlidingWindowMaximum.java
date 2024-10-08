import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindowMaximum {

    /*
        You are given an array of integers nums,
        there is a sliding window of size k which is moving from the very left of the array to the very right.
        You can only see the k numbers in the window. Each time the sliding window moves right by one position.

        Return the max sliding window.

        Example 1.
        Input: nums = [1,3,-1,-3,5,3,6,7], k = 3
        Output: [3,3,5,5,6,7]
        Explanation:
        Window position                Max
        ---------------               -----
        [1  3  -1] -3  5  3  6  7       3
         1 [3  -1  -3] 5  3  6  7       3
         1  3 [-1  -3  5] 3  6  7       5
         1  3  -1 [-3  5  3] 6  7       5
         1  3  -1  -3 [5  3  6] 7       6
         1  3  -1  -3  5 [3  6  7]      7
        Example 2.
        Input: nums = [1], k = 1
        Output: [1]

        Constraints:

        1 <= nums.length <= 105
        -104 <= nums[i] <= 104
        1 <= k <= nums.length
     */

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println("Sliding Window Max: " + Arrays.toString(Solution.maxSlidingWindow(nums, 3)));
    }

    // Time: O(N)
    // Space: O(K)
    static class Solution {
        public static int[] maxSlidingWindow(int[] nums, int k) {
            int n = nums.length;
            Deque<Integer> deque = new ArrayDeque<>();
            for (int i = 0; i < k; i++) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.getLast()]) {
                    deque.removeLast();
                }
                // save the index
                deque.addLast(i);
            }
            int[] res = new int[n - k + 1];
            res[0] = nums[deque.getFirst()];
            for (int i = k; i < n; i++) {
                while (!deque.isEmpty() && nums[i] >= nums[deque.getLast()]) {
                    deque.removeLast();
                }
                deque.addLast(i);
                // remove the element which is out of the window
                if (deque.getFirst() == i - k) {
                    deque.removeFirst();
                }
                res[i - k + 1] = nums[deque.getFirst()];
            }
            return res;
        }
    }
}
