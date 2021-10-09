import java.util.Arrays;

public class ShortestUnsortedContinuousSubarray {

    /*
        Given an integer array nums, you need to find one continuous subarray that if you only sort this subarray in ascending order, then the whole array will be sorted in ascending order.
        Return the shortest such subarray and output its length.

        Example 1.
        Input: nums = [2,6,4,8,10,9,15]
        Output: 5
        Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the whole array sorted in ascending order.
        Example 2.
        Input: nums = [1,2,3,4]
        Output: 0
        Example 3.
        Input: nums = [1]
        Output: 0

     */

    public static void main(String[] args) {
        int[] testNums = {2,6,4,8,10,9,15};
        System.out.println("Solution I: " + Solution_I.findUnsortedSubarray(testNums));
        System.out.println("Solution II: " + Solution_II.findUnsortedSubarray(testNums));
    }

    // Time: O(NlogN)
    // Space: O(N)
    static class Solution_I {
        public static int findUnsortedSubarray(int[] nums) {
            int[] arr= new int[nums.length];
            System.arraycopy(nums,0,arr,0,nums.length);
            Arrays.sort(arr);
            int start = 0, end = nums.length-1;
            for(;start < nums.length; start++) {
                if(nums[start] != arr[start]) break;
            }
            if(start >= nums.length-1) return 0;
            for(; end>=0; end--){
                if(nums[end] != arr[end]) break;
            }

            return end-start+1;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static int findUnsortedSubarray(int[] nums) {
            int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            int n = nums.length;

            for (int i = 1; i < n; i++) {
                //Decreasing Value
                if (nums[i] < nums[i - 1]) {
                    min = Math.min(min, nums[i]);
                }
            }

            for (int i = n - 2; i >= 0; i--) {
                //Increasing value
                if (nums[i] > nums[i + 1]) {
                    max = Math.max(max, nums[i]);
                }
            }
            if (min == Integer.MAX_VALUE && max == Integer.MIN_VALUE) return 0;

            int start = 0, end = n - 1;

            for (; start < n; start++) {
                if (nums[start] > min) break;
            }

            for (; end >= 0; end--) {
                if (nums[end] < max) break;
            }

            return end - start + 1;
        }
    }
}
