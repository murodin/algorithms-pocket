import java.util.Arrays;

public class JumpGameII {
    /*
        Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
        Each element in the array represents your maximum jump length at that position.
        Your goal is to reach the last index in the minimum number of jumps.
        You can assume that you can always reach the last index.

        Example 1.
        Input: nums = [2,3,1,1,4]
        Output: 2
        Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
     */

    public static void main(String[] args) {
        int[] testNums = {2, 3, 1, 1, 4};
        System.out.println("Jump Game: " + Solution.jump_2(testNums));
    }

    static class Solution {
        // Time: O(N^2)
        // Space: O(N)
        public static int jump_1(int[] nums) {
            int n = nums.length;
            int[] dp = new int[n];
            Arrays.fill(dp, Integer.MAX_VALUE);
            dp[n-1] = 0;

            for(int i=n-2; i>=0; i--) {
                int min = Integer.MAX_VALUE;
                for(int j=i+1; j<=Math.min(n-1, i+nums[i]); j++) {
                    min = Math.min(min, dp[j]);
                }

                if(min != Integer.MAX_VALUE)
                    dp[i] = min + 1;
            }
            return dp[0];
        }

        // Time: O(N)
        // Space: O(1)
        public static int jump_2(int[] nums) {
            int end = 0, farthest = 0;
            int jump = 0;

            for(int i=0; i<nums.length-1; i++) {
                farthest = Math.max(farthest, i + nums[i]);

                if(i == end) {
                    jump++;
                    end = farthest;
                }
            }

            return jump;
        }
    }
}
