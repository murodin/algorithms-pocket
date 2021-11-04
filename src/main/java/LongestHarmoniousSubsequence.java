import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LongestHarmoniousSubsequence {

    /*
        We define a harmonious array as an array where the difference between its maximum value and its minimum value is exactly 1.
        Given an integer array nums, return the length of its longest harmonious subsequence among all its possible subsequences.
        A subsequence of array is a sequence that can be derived from the array by deleting some or no elements without changing the order of the remaining elements.

        Example 1.
        Input: nums = [1,3,2,2,5,2,3,7]
        Output: 5
        Explanation: The longest harmonious subsequence is [3,2,2,2,3].
        Example 2.
        Input: nums = [1,2,3,4]
        Output: 2
        Example 3.
        Input: nums = [1,1,1,1]
        Output: 0
     */

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 2, 5, 2, 3, 7};
        System.out.println("Solution I: " + Solution_I.findLHS(nums));
        System.out.println("Solution II: " + Solution_II.findLHS(nums));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static int findLHS(int[] nums) {
            int result = 0;
            Map<Integer, Integer> count= new HashMap<>();
            for(int i : nums){
                count.put(i, count.getOrDefault(i,0)+1);
            }
            for(int i : count.keySet()){
                if(count.containsKey(i+1)){
                    result = Math.max(result, count.get(i)+count.get(i+1));
                }
            }
            return result;
        }
    }

    // Time: O(NLogN)
    // Space: O(1)
    static class Solution_II {
        public static int findLHS(int[] nums) {
            Arrays.sort(nums);
            int left =0, right =1;
            int result = 0;
            while(right < nums.length){
                int diff = nums[right] - nums[left];
                if(diff == 1){
                    result = Math.max(result, right - left + 1);
                }
                // 2 2 3
                if(diff <= 1){
                    right++;
                }
                else {
                    left++;
                }
            }
            return result;
        }
    }
}
