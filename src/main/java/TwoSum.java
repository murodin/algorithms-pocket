import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    /*
        Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
        You may assume that each input would have exactly one solution, and you may not use the same element twice.
        You can return the answer in any order.

        Example 1:
        Input: nums = [2,7,11,15], target = 9
        Output: [0,1]
        Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
        Example 2.
        Input: nums = [3,2,4], target = 6
        Output: [1,2]
        Example 3.
        Input: nums = [3,3], target = 6
        Output: [0,1]


        Constraints:

        2 <= nums.length <= 104
        -109 <= nums[i] <= 109
        -109 <= target <= 109
        Only one valid answer exists.


        Follow-up: Can you come up with an algorithm that is less than O(n2) time complexity?
     */

    public static void main(String[] args) {
        int[] nums = {2,7,11,15};
        int target = 9;

        System.out.println("Two Sum: " + Arrays.toString(Solution.twoSum(nums, target)));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int[] twoSum(int[] nums, int target) {
            Map<Integer,Integer> map=new HashMap<>();
            for(int i=0;i<nums.length;i++){
                int r=nums[i];
                if(map.containsKey(target-r)){
                    return new int[]{map.get(target-r),i};
                }
                map.put(r,i);
            }
            throw new IllegalArgumentException("");
        }
    }
}
