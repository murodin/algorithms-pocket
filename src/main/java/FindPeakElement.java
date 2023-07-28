public class FindPeakElement {
    /*
        A peak element is an element that is strictly greater than its neighbors.
        Given a 0-indexed integer array nums, find a peak element, and return its index. If the array contains multiple peaks, return the index to any of the peaks.
        You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is always considered to be strictly greater than a neighbor that is outside the array.
        You must write an algorithm that runs in O(log n) time.

        Example 1:

        Input: nums = [1,2,3,1]
        Output: 2
        Explanation: 3 is a peak element and your function should return the index number 2.
        Example 2:

        Input: nums = [1,2,1,3,5,6,4]
        Output: 5
        Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.


        Constraints:

        1 <= nums.length <= 1000
        -231 <= nums[i] <= 231 - 1
        nums[i] != nums[i + 1] for all valid i.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findPeakElement(new int[]{1,2,1,3,5,6,4}));
    }

    // Time: O(LogN)
    // Space: O(1)
    static class Solution {
        public static int findPeakElement(int[] nums) {
            int n = nums.length;
            if(n == 1 || nums[0] > nums[1]) return 0;
            if(nums[n - 1] > nums[n - 2]) return n - 1;
            int l = 0;
            int r = n - 1;
            while(l <= r) {
                int m = (l + r)/2;
                int minus = m == 0 ? Integer.MIN_VALUE : nums[m-1];
                int plusUltra = m == n - 1 ? Integer.MIN_VALUE : nums[m+1];
                if(minus < nums[m] && plusUltra < nums[m]) return m;
                if(minus < nums[m]) l = m + 1;
                else r = m - 1;
            }

            return l;
        }
    }
}
