import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {
    /*
        Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:

        0 <= a, b, c, d < n
        a, b, c, and d are distinct.
        nums[a] + nums[b] + nums[c] + nums[d] == target
        You may return the answer in any order.

        Example 1.
        Input: nums = [1,0,-1,0,-2,2], target = 0
        Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
        Example 2.
        Input: nums = [2,2,2,2,2], target = 8
        Output: [[2,2,2,2]]


        Constraints:

        1 <= nums.length <= 200
        -109 <= nums[i] <= 109
        -109 <= target <= 109
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.fourSum(new int[] {1,0,-1,0,-2,2}, 0));
    }

    // Time: O(N * logN) + O(N^3) => O(N^3)
    // Space: O(1) - ignoring the output array
    static class Solution {
        public static List<List<Integer>> fourSum(int[] nums, int target) {
            List<List<Integer>> ans = new ArrayList<>();
            int n = nums.length;

            Arrays.sort(nums);

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    long target2 = (long) target - (long) nums[i] - (long) nums[j];
                    int lo = j + 1, hi = n - 1;
                    while (lo < hi) {
                        int twoSum = nums[lo] + nums[hi];
                        if (twoSum < target2) lo++;
                        else if (twoSum > target2) hi--;
                        else {
                            List<Integer> quad = Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]);
                            ans.add(quad);
                            while (lo < hi && nums[lo] == quad.get(2)) lo++;
                            while (lo < hi && nums[hi] == quad.get(3)) hi--;
                        }
                    }
                    while (j + 1 < n && nums[j] == nums[j + 1]) j++;
                }
                while (i + 1 < n && nums[i] == nums[i + 1]) i++;
            }
            return ans;
        }
    }
}
