import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {
    /*
        Given a set of distinct positive integers nums, return the largest subset answer such that every pair (answer[i], answer[j]) of elements in this subset satisfies:

        answer[i] % answer[j] == 0, or
        answer[j] % answer[i] == 0
        If there are multiple solutions, return any of them.



        Example 1:

        Input: nums = [1,2,3]
        Output: [1,2]
        Explanation: [1,3] is also accepted.
        Example 2:

        Input: nums = [1,2,4,8]
        Output: [1,2,4,8]


        Constraints:

        1 <= nums.length <= 1000
        1 <= nums[i] <= 2 * 109
        All the integers in nums are unique.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.largestDivisibleSubset(new int[]{1,2,3}));
    }

    // Time: O(MxN)
    // Space: O(N)
    static class Solution {
        public static List<Integer> largestDivisibleSubset(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n];
            Arrays.fill(dp, 1);
            Arrays.sort(nums);

            int maxSize = 1, maxIndex = 0;
            for (int i = 1; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[i] % nums[j] == 0) {
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                        if (dp[i] > maxSize) {
                            maxSize = dp[i];
                            maxIndex = i;
                        }
                    }
                }
            }

            List<Integer> result = new ArrayList<>();
            int num = nums[maxIndex];
            for (int i = maxIndex; i >= 0; i--) {
                if (num % nums[i] == 0 && dp[i] == maxSize) {
                    result.add(nums[i]);
                    num = nums[i];
                    maxSize--;
                }
            }

            return result;
        }
    }
}
