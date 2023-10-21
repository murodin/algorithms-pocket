import java.util.PriorityQueue;

public class ConstrainedSubsequenceSum {
    /*
        Given an integer array nums and an integer k, return the maximum sum of a non-empty subsequence
        of that array such that for every two consecutive integers in the subsequence, nums[i] and nums[j], where i < j, the condition j - i <= k is satisfied.
        A subsequence of an array is obtained by deleting some number of elements (can be zero) from the array, leaving the remaining elements in their original order.

        Example 1:
        Input: nums = [10,2,-10,5,20], k = 2
        Output: 37
        Explanation: The subsequence is [10, 2, 5, 20].
        Example 2:
        Input: nums = [-1,-2,-3], k = 1
        Output: -1
        Explanation: The subsequence must be non-empty, so we choose the largest number.
        Example 3:
        Input: nums = [10,-2,-10,-5,20], k = 2
        Output: 23
        Explanation: The subsequence is [10, -2, -5, 20].


        Constraints:

        1 <= k <= nums.length <= 105
        -104 <= nums[i] <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.constrainedSubsetSum(new int[]{10,-2,-10,-5,20}, 2));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static int constrainedSubsetSum(int[] nums, int k) {
            PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
            heap.add(new int[] {nums[0], 0});
            int ans = nums[0];
            for (int i = 1; i < nums.length; i++) {
                while (true) {
                    assert heap.peek() != null;
                    if (!(i - heap.peek()[1] > k)) break;
                    heap.remove();
                }
                int curr = Math.max(0, heap.peek()[0]) + nums[i];
                ans = Math.max(ans, curr);
                heap.add(new int[] {curr, i});
            }
            return ans;
        }
    }
}
