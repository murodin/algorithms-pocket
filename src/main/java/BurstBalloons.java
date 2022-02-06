public class BurstBalloons {

    /*
        You are given n balloons, indexed from 0 to n - 1.
        Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.
        If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins.
        If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
        Return the maximum coins you can collect by bursting the balloons wisely.

        Example 1.
        Input: nums = [3,1,5,8]
        Output: 167
        Explanation:
        nums = [3,1,5,8] --> [3,5,8] --> [3,8] --> [8] --> []
        coins =  3*1*5    +   3*5*8   +  1*3*8  + 1*8*1 = 167
        Example 2.
        Input: nums = [1,5]
        Output: 10

        Constraints:

        n == nums.length
        1 <= n <= 300
        0 <= nums[i] <= 100

     */

    public static void main(String[] args) {
        int[] test= {3,1,5,8};
        System.out.println("Burst Balloons: " + Solution.maxCoins(test));
    }

    // Time: O()
    // Space: O()
    static class Solution {
        public static int maxCoins(int[] nums) {
            int n = nums.length + 2;
            int[] arr = new int[n];
            for(int i=1; i<n-1;i++)
                arr[i] = nums[i-1];

            arr[0] = 1;
            arr[n-1] = 1;

            int[][] dp = new int[n][n];

            // length 1 to n-1
            for(int len=1; len<n-1; len++) {
                // start 1 to n - len
                for(int start=1; start<n-len; start++){
                    int end=start+len-1;
                    for(int k=start; k<=end; k++)
                        dp[start][end] = Math.max(dp[start][end], dp[start][k-1]+arr[start-1]*arr[k]*arr[end+1]+dp[k+1][end]);
                }
            }
            return dp[1][n-2];
        }
    }
}
