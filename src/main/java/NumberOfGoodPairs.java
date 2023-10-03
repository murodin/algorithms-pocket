public class NumberOfGoodPairs {
    /*
        Given an array of integers nums, return the number of good pairs.
        A pair (i, j) is called good if nums[i] == nums[j] and i < j.


        Example 1:

        Input: nums = [1,2,3,1,1,3]
        Output: 4
        Explanation: There are 4 good pairs (0,3), (0,4), (3,4), (2,5) 0-indexed.
        Example 2:

        Input: nums = [1,1,1,1]
        Output: 6
        Explanation: Each pair in the array are good.
        Example 3:

        Input: nums = [1,2,3]
        Output: 0


        Constraints:

        1 <= nums.length <= 100
        1 <= nums[i] <= 100
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numIdenticalPairs(new int[]{1,2,3,1,1,3}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int numIdenticalPairs(int[] nums) {
            int ans = 0;
            int count[] = new int[101];

            for(int n:nums) {
                count[n]++;
            }
            for(int n:count) {
                ans+=(n*(n-1))/2;
            }
            return ans;
        }
    }
}
