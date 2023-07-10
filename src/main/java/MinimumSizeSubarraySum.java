public class MinimumSizeSubarraySum {
    /*
        Given an array of positive integers nums and a positive integer target,
        return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.

        Example 1:

        Input: target = 7, nums = [2,3,1,2,4,3]
        Output: 2
        Explanation: The subarray [4,3] has the minimal length under the problem constraint.
        Example 2:

        Input: target = 4, nums = [1,4,4]
        Output: 1
        Example 3:

        Input: target = 11, nums = [1,1,1,1,1,1,1,1]
        Output: 0


        Constraints:

        1 <= target <= 109
        1 <= nums.length <= 105
        1 <= nums[i] <= 104


        Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minSubArrayLen(4, new int[]{1,4,4}));
    }

    // Time: O(NLogN)
    // Space: O(1)
    static class Solution {
        public static int minSubArrayLen(int target, int[] A) {
            int i = 0; int j = 0;
            int sum = 0; int ans = 1000000;
            while(j<A.length){
                sum+=A[j];
                while(sum>=target){
                    ans = Math.min(ans, j-i+1);
                    sum -= A[i];
                    i++;
                }
                j++;
            }

            return ans==1000000?0:ans;
        }
    }
}
