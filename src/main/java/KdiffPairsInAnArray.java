import java.util.HashMap;
import java.util.Map;

public class KdiffPairsInAnArray {
    /*
        Given an array of integers nums and an integer k, return the number of unique k-diff pairs in the array.
        A k-diff pair is an integer pair (nums[i], nums[j]), where the following are true:

        0 <= i, j < nums.length
        i != j
        nums[i] - nums[j] == k
        Notice that |val| denotes the absolute value of val.

        Example 1.
        Input: nums = [3,1,4,1,5], k = 2
        Output: 2
        Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
        Although we have two 1s in the input, we should only return the number of unique pairs.
        Example 2.
        Input: nums = [1,2,3,4,5], k = 1
        Output: 4
        Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
        Example 3:

        Input: nums = [1,3,1,5,4], k = 0
        Output: 1
        Explanation: There is one 0-diff pair in the array, (1, 1).
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findPairs(new int[]{1,2,3,4,5}, 1));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int findPairs(int[] nums, int k) {
            Map<Integer, Integer> cnt = new HashMap<>();
            for (int x : nums) {
                cnt.put(x, cnt.getOrDefault(x, 0) + 1);
            }
            int res = 0;
            for (int x : cnt.keySet()) {
                if ((k > 0 && cnt.containsKey(x + k)) || (k == 0 && cnt.get(x) > 1)) {
                    res++;
                }
            }
            return res;
        }
    }
}
