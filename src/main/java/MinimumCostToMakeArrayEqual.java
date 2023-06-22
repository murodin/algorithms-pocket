public class MinimumCostToMakeArrayEqual {
    /*
        You are given two 0-indexed arrays nums and cost consisting each of n positive integers.

        You can do the following operation any number of times:

        Increase or decrease any element of the array nums by 1.
        The cost of doing one operation on the ith element is cost[i].

        Return the minimum total cost such that all the elements of the array nums become equal.



        Example 1:

        Input: nums = [1,3,5,2], cost = [2,3,1,14]
        Output: 8
        Explanation: We can make all the elements equal to 2 in the following way:
        - Increase the 0th element one time. The cost is 2.
        - Decrease the 1st element one time. The cost is 3.
        - Decrease the 2nd element three times. The cost is 1 + 1 + 1 = 3.
        The total cost is 2 + 3 + 3 = 8.
        It can be shown that we cannot make the array equal with a smaller cost.
        Example 2:

        Input: nums = [2,2,2,2,2], cost = [4,2,8,1,3]
        Output: 0
        Explanation: All the elements are already equal, so no operations are needed.


        Constraints:

        n == nums.length == cost.length
        1 <= n <= 105
        1 <= nums[i], cost[i] <= 106
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minCost(new int[]{1,3,5,2}, new int[]{2,3,1,14}));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static long minCost(int[] nums, int[] cost) {
            int left = nums[0];
            int right = nums[0];
            for (int i : nums) {
                left = Math.min(left, i);
                right = Math.max(right, i);
            }
            long ans = 0;
            while (left < right) {
                int mid = (left + right) / 2;
                long cost1 = helper(nums, cost, mid);
                long cost2 = helper(nums, cost, mid + 1);
                if (cost1 > cost2) {
                    left = mid + 1;
                    ans = cost2;
                } else {
                    right = mid;
                    ans = cost1;
                }

            }
            return ans;
        }

        public static long helper(int[] nums, int[] cost, int all) {
            long totalCost = 0L;
            for (int i = 0; i < nums.length; i++) {
                totalCost += (long) Math.abs(nums[i] - all) * cost[i];
            }
            return totalCost;
        }
    }
}
