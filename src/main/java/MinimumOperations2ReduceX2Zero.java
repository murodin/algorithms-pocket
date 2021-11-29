import java.util.HashMap;
import java.util.Map;

public class MinimumOperations2ReduceX2Zero {

    /*
        You are given an integer array nums and an integer x.
        In one operation, you can either remove the leftmost or the rightmost element from the array nums and subtract its value from x.
        Note that this modifies the array for future operations.

        Return the minimum number of operations to reduce x to exactly 0 if it is possible, otherwise, return -1.

        Example 1.
        Input: nums = [1,1,4,2,3], x = 5
        Output: 2
        Explanation: The optimal solution is to remove the last two elements to reduce x to zero.
        Example 2.
        Input: nums = [5,6,7,8,9], x = 4
        Output: -1
        Example 3.
        Input: nums = [3,2,20,1,1,3], x = 10
        Output: 5
        Explanation: The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
     */

    public static void main(String[] args) {
        System.out.println("Solution I: " + Solution_I.minOperations(new int[]{3,2,20,1,1,3}, 10));
        System.out.println("Solution II: " + Solution_II.minOperations(new int[]{3,2,20,1,1,3}, 10));
    }

    // Using Prefix Sum
    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static int minOperations(int[] nums, int x) {
            int target = -x;
            for(int i : nums) target+=i;
            if(target == 0) return nums.length;
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0,-1);
            int res = -1, sum = 0;

            for(int i = 0; i < nums.length; i++) {
                sum+=nums[i];
                if(map.containsKey(sum-target)) {
                    res = Math.max(res, i-map.get(sum-target));
                }
                map.put(sum, i);
            }

            return res==-1 ? -1 : nums.length - res;
        }
    }

    // Using Siding Window
    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static int minOperations(int[] nums, int x) {
            int target = -x;
            for(int i : nums) target+=i;
            if(target == 0) return nums.length;
            if(target < 0) return -1;
            int res = -1, sum = 0, left = 0;

            for(int i = 0; i < nums.length; i++) {
                sum+=nums[i];
                while(sum > target){
                    sum-=nums[left++];
                }
                if(sum == target){
                    res = Math.max(res, i-left+1);
                }
            }

            return res==-1 ? -1 : nums.length - res;
        }
    }
}
