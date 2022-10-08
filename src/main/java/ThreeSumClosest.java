import java.util.Arrays;

public class ThreeSumClosest {
    /*
        Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
        Return the sum of the three integers.
        You may assume that each input would have exactly one solution.

        Example 1.
        Input: nums = [-1,2,1,-4], target = 1
        Output: 2
        Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
        Example 2.
        Input: nums = [0,0,0], target = 1
        Output: 0
        Explanation: The sum that is closest to the target is 0. (0 + 0 + 0 = 0).


        Constraints:

        3 <= nums.length <= 1000
        -1000 <= nums[i] <= 1000
        -104 <= target <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.threeSumClosest(new int[]{-1,2,1,-4}, 1));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int n = nums.length, diff = Integer.MAX_VALUE, res = 0;
            for(int i = 0; i < n; ++i){
                int first = nums[i];
                int start = i + 1;
                int end = n - 1;
                while(start < end){
                    if(nums[start] + nums[end] + first == target) return target;
                    else if(Math.abs(nums[start] + nums[end] + first - target) < diff){
                        diff = Math.abs(nums[start] + nums[end] + first - target);
                        res = nums[start] + nums[end] + first;
                    }
                    if(nums[start] + nums[end] + first < target) start++;
                    else end--;
                }
            }
            return res;
        }
    }
}
