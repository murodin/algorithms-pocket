public class HouseRobber {
    /*
        You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed,
        the only constraint stopping you from robbing each of them is that
        adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
        Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.


        Example 1.
        Input: nums = [1,2,3,1]
        Output: 4
        Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
        Total amount you can rob = 1 + 3 = 4.
        Example 2.
        Input: nums = [2,7,9,3,1]
        Output: 12
        Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
        Total amount you can rob = 2 + 9 + 1 = 12.


        Constraints:

        1 <= nums.length <= 100
        0 <= nums[i] <= 400
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.rob(new int[]{2,7,9,3,1}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int rob(int[] nums) {
            int temp, max, max1;
            if(nums.length == 1)
                return nums[0];
            if(nums.length == 2)
                return Math.max(nums[0], nums[1]);
            max = nums[0];
            max1 = nums[1];
            for(int i=2; i<nums.length; i++) {
                max1 = Math.max(max1, max);
                if(max+nums[i] >= max)
                    max += nums[i];
                temp = max;
                max = max1;
                max1 = temp;
            }
            return Math.max(max1, max);
        }
    }
}
