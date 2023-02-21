public class SingleElementInASortedArray {
    /*
        You are given a sorted array consisting of only integers where every element appears exactly twice, except for one element which appears exactly once.
        Return the single element that appears only once.
        Your solution must run in O(log n) time and O(1) space.

        Example 1.
        Input: nums = [1,1,2,3,3,4,4,8,8]
        Output: 2
        Example 2.
        Input: nums = [3,3,7,7,10,11,11]
        Output: 10


        Constraints:

        1 <= nums.length <= 105
        0 <= nums[i] <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.singleNonDuplicate(new int[]{3,3,7,7,10,11,11}));
    }

    // Time: O(LogN)
    // Space: O(1)
    static class Solution {
        public static int singleNonDuplicate(int[] nums) {
            if(nums.length==1) return nums[0];
            int low = 0;
            int high = nums.length-1;
            if (nums[0]!= nums[1]) return nums[0];
            if (nums[high]!= nums[high- 1]) return nums[high];
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (nums[mid] != nums[mid - 1] && nums[mid] != nums[mid + 1])
                    return nums[mid];
                else if ((nums[mid] == nums[mid + 1] && mid % 2 == 0) || (nums[mid] == nums[mid - 1] && mid % 2 != 0))
                    low = mid + 1;
                else
                    high = mid - 1;
            }
            return nums[low];
        }
    }
}
