import java.util.Arrays;

public class LargestPerimeterTriangle {
    /*
        Given an integer array nums, return the largest perimeter of a triangle with a non-zero area, formed from three of these lengths.
        If it is impossible to form any triangle of a non-zero area, return 0.

        Example 1.
        Input: nums = [2,1,2]
        Output: 5
        Example 2.
        Input: nums = [1,2,1]
        Output: 0

        Constraints:

        3 <= nums.length <= 104
        1 <= nums[i] <= 106
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.largestPerimeter(new int[] {2,1,2}));
    }

    // Time: O(NLogN)
    // Space: O(1)
    static class Solution {
        public static int largestPerimeter(int[] nums) {
            //Sort the array first.
            Arrays.sort(nums);

            //Start traversing from back , so that we can get the largest value.
            for(int i = nums.length-1; i>1; i--){
                //Using triangle property to become valid sides
                // The sum of the length of the two sides of a triangle is greater than the length of the third side.
                if(nums[i] < nums[i-1] + nums[i-2])
                    return  nums[i] + nums[i-1]+ nums[i-2];
            }

            //If we didn't found anything we return 0.
            return 0;
        }
    }
}
