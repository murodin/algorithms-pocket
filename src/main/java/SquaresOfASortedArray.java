import java.util.Arrays;

public class SquaresOfASortedArray {

    /*
        Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
        Example 1.
        Input: nums = [-4,-1,0,3,10]
        Output: [0,1,9,16,100]
        Explanation: After squaring, the array becomes [16,1,0,9,100].
        After sorting, it becomes [0,1,9,16,100].
        Example 2.
        Input: nums = [-7,-3,2,3,11]
        Output: [4,9,9,49,121]

        Constraints:

        1 <= nums.length <= 104
        -104 <= nums[i] <= 104
        nums is sorted in non-decreasing order.

        Follow up: Squaring each element and sorting the new array is very trivial, could you find an O(n) solution using a different approach?
     */

    public static void main(String[] args) {
        System.out.println("Solution I -> Sorted Squares: " + Arrays.toString(Solution_I.sortedSquares(new int[]{-4, 1, 0, 3, 10})));
        System.out.println("Solution II -> Sorted Squares: " + Arrays.toString(Solution_II.sortedSquares(new int[]{-4, 1, 0, 3, 10})));
    }

    // Time: O(NLogN)
    // Space: O(1)
    static class Solution_I {
        public static int[] sortedSquares(int[] nums) {
            for(int i=0; i<nums.length; i++)
                nums[i] *= nums[i];
            Arrays.sort(nums);
            return nums;
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_II {
        public static int[] sortedSquares(int[] nums) {
            int l=0, r=nums.length-1;
            int p = nums.length-1;
            int[] res = new int[nums.length];

            while (l<=r) {
                if (Math.abs(nums[l]) > Math.abs(nums[r])) {
                    res[p] = nums[l] * nums[l];
                    l++;
                } else {
                    res[p] = nums[r] * nums[r];
                    r--;
                }
                p--;
            }
            return res;
        }
    }
}
