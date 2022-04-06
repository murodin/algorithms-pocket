public class SplitArrayLargestSum {
    /*
        Given an array nums which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
        Write an algorithm to minimize the largest sum among these m subarrays.

        Example 1.
        Input: nums = [7,2,5,10,8], m = 2
        Output: 18
        Explanation:
        There are four ways to split nums into two subarrays.
        The best way is to split it into [7,2,5] and [10,8],
        where the largest sum among the two subarrays is only 18.
        Example 2.
        Input: nums = [1,2,3,4,5], m = 2
        Output: 9
        Example 3:

        Input: nums = [1,4,4], m = 3
        Output: 4

        Constraints:

        1 <= nums.length <= 1000
        0 <= nums[i] <= 106
        1 <= m <= min(50, nums.length)
     */

    public static void main(String[] args) {
        System.out.println("Split Array Largest Sum: " + Solution.splitArray(new int[] {1,2,3,4,5}, 2));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        static int[] nums;
        public static int splitArray(int[] numbers, int m) {
            nums = numbers;
            int low = 0, high = 0, min = Integer.MAX_VALUE;
            for(int i=0;i<nums.length;i++){
                low = Math.max(low, nums[i]);
                high += nums[i];
            }
            while(low <= high) {
                int mid = (low + high) / 2;
                if(required_no_of_chunks(mid, m)){
                    min = Math.min(min, mid);
                    high = mid - 1;
                }
                else low = mid + 1;
            }
            return min;
        }

        private static boolean required_no_of_chunks(int mid, int m){
            int chunks = 0, i=0;
            while(i < nums.length){
                int val = 0;
                while(i < nums.length && nums[i] + val <= mid) val += nums[i++];
                chunks++;
            }
            return chunks <= m;
        }
    }
}
