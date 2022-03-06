public class PartitionEqualSubsetSum {

    /*
        Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

        Example 1.
        Input: nums = [1,5,11,5]
        Output: true
        Explanation: The array can be partitioned as [1, 5, 5] and [11].
        Example 2.
        Input: nums = [1,2,3,5]
        Output: false
        Explanation: The array cannot be partitioned into equal sum subsets.

        Constraints:

        1 <= nums.length <= 200
     */

    public static void main(String[] args) {
        System.out.println("Partition Equal Subset Sum(Should be True): " + Solution.canPartition(new int[] {1,5,11,5}));
        System.out.println("Partition Equal Subset Sum(Should be False): " + Solution.canPartition(new int[] {1,2,3,5}));
    }

    // Time: O(M*N), where N array length, M is sum/2 values in boolean dp
    // Space: O(M)
    static class Solution {
        public static boolean canPartition(int[] nums) {
            int sum = 0;
            for (int n : nums) sum += n;

            if(sum%2 != 0) return false;

            sum = sum/2;

            boolean[] dp = new boolean[sum+1];
            dp[0] = true;

            for(int n :nums) {
                for(int i = sum; i>=n; i--)
                    dp[i] = dp[i] | dp[i-n];
            }

            return dp[sum];
        }
    }
}
