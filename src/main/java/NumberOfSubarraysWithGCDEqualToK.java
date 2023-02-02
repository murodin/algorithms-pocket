public class NumberOfSubarraysWithGCDEqualToK {
    /*
        Given an integer array nums and an integer k, return the number of subarrays of nums where the greatest common divisor of the subarray's elements is k.
        A subarray is a contiguous non-empty sequence of elements within an array.
        The greatest common divisor of an array is the largest integer that evenly divides all the array elements.

        Example 1.
        Input: nums = [9,3,1,2,6,3], k = 3
        Output: 4
        Explanation: The subarrays of nums where 3 is the greatest common divisor of all the subarray's elements are:
        - [9,3,1,2,6,3]
        - [9,3,1,2,6,3]
        - [9,3,1,2,6,3]
        - [9,3,1,2,6,3]
        Example 2.
        Input: nums = [4], k = 7
        Output: 0
        Explanation: There are no subarrays of nums where 7 is the greatest common divisor of all the subarray's elements.


        Constraints:

        1 <= nums.length <= 1000
        1 <= nums[i], k <= 109
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.subarrayGCD(new int[]{9,3,1,2,6,3}, 3));
    }

    // Time: O(N^3)
    // Space: O(1)
    static class Solution {
        public static int subarrayGCD(int[] nums, int k) {
            int ans = 0;
            for (int i = 0; i < nums.length; i++) {
                int currGcd = nums[i];
                if(currGcd == k) // if element is equal to k, increment answer
                    ans++;
                for (int j = i + 1; j < nums.length; j++) {
                    if(nums[j] < k) // if nums[j] < k gcd can never be equal to k for this subarray
                        break;
                    currGcd = gcd(nums[j], currGcd);
                    if (currGcd == k)
                        ans++;
                }
            }
            return ans;
        }
        private static int gcd(int a, int b) {
            if (b == 0)
                return a;
            return gcd(b, a % b);
        }
    }
}
