public class FindFirstAndLastPositionOfElementInSortedArray {

    /*
        Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.
        If target is not found in the array, return [-1, -1].
        You must write an algorithm with O(log n) runtime complexity.

        Example 1:

        Input: nums = [5,7,7,8,8,10], target = 8
        Output: [3,4]
     */

    public static void main(String[] args) {
        int[] testNums = {5,7,7,8,8,10};
        int testTarget = 8;

        System.out.println("First and Last Indexes: " +
                Solution.searchRange(testNums, testTarget)[0] + " and " + Solution.searchRange(testNums, testTarget)[1]
                + " for target " + testTarget);
    }

    static class Solution {
        public static int[] searchRange(int[] nums, int target) {
            int[] res = new int[]{-1, -1};
            int start = 0, end = nums.length-1;
            if(nums.length == 0) return res;

            while (start < end) {
                int mid = start + (end-start)/2;
                if(nums[mid] >= target) end = mid;
                else start = mid + 1;
            }

            if(nums[start] != target) return res;
            res[0] = start;

            end = nums.length;
            while (start < end) {
                int mid = start + (end-start)/2;
                if(nums[mid] > target) end = mid;
                else start = mid + 1;
            }
            res[1] = start-1;
            return res;
        }
    }
}
