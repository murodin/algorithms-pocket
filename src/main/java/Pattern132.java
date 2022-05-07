import java.util.ArrayDeque;
import java.util.Deque;

public class Pattern132 {
    /*
        Given an array of n integers nums, a 132 pattern is a subsequence of three integers nums[i], nums[j] and nums[k]
        such that i < j < k and nums[i] < nums[k] < nums[j].
        Return true if there is a 132 pattern in nums, otherwise, return false.

        Example 1.
        Input: nums = [1,2,3,4]
        Output: false
        Explanation: There is no 132 pattern in the sequence.
        Example 2.
        Input: nums = [3,1,4,2]
        Output: true
        Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
        Example 3:

        Input: nums = [-1,3,2,0]
        Output: true
        Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].


        Constraints:

        n == nums.length
        1 <= n <= 2 * 105
        -109 <= nums[i] <= 109
     */

    public static void main(String[] args) {
        int[] nums = {3,1,4,2};
        System.out.println("Solution I: " + Solution_I.find132pattern(nums));
        System.out.println("Solution II: " + Solution_II.find132pattern(nums));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_I {
        public static boolean find132pattern(int[] nums) {
            int min_of_range = Integer.MAX_VALUE;
            int max_of_range = Integer.MIN_VALUE;
            int left = nums[0]; // left of three elements

            for(int i = 1; i < nums.length; i++) {
                if(nums[i] >= min_of_range && nums[i] <= max_of_range)
                    return true;
                int middle = nums[i];
                // we need to find right in range [left+1, right-1] (inclusive)
                if(left + 1 <= middle - 1) { // at least one element is there to be right
                    if(left+1 < min_of_range) min_of_range = left+1;
                    if(middle-1 > max_of_range) max_of_range = middle-1;
                }
                left = Math.min(left, nums[i]);
            }
            return false;
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_II {
        public static boolean find132pattern(int[] nums) {
            int len = nums.length;
            if (len < 3) {
                return false;
            }
            Deque<Integer> stk = new ArrayDeque<>();
            int k = -1;
            for (int i = len - 1; i >= 0; i--) {
                if (k > -1 && nums[k] > nums[i]) {
                    return true;
                }
                while (!stk.isEmpty() && nums[i] > nums[stk.peek()])
                    k = stk.pop();
                stk.push(i);
            }
            return false;
        }
    }
}
