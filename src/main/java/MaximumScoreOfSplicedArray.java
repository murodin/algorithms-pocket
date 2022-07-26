public class MaximumScoreOfSplicedArray {
    /*
        You are given two 0-indexed integer arrays nums1 and nums2, both of length n.
        You can choose two integers left and right where 0 <= left <= right < n and swap the subarray nums1[left...right] with the subarray nums2[left...right].
        For example, if nums1 = [1,2,3,4,5] and nums2 = [11,12,13,14,15] and you choose left = 1 and right = 2, nums1 becomes [1,12,13,4,5] and nums2 becomes [11,2,3,14,15].
        You may choose to apply the mentioned operation once or not do anything.
        The score of the arrays is the maximum of sum(nums1) and sum(nums2), where sum(arr) is the sum of all the elements in the array arr.
        Return the maximum possible score.
        A subarray is a contiguous sequence of elements within an array. arr[left...right] denotes the subarray that contains the elements of nums between indices left and right (inclusive).

        Example 1.
        Input: nums1 = [60,60,60], nums2 = [10,90,10]
        Output: 210
        Explanation: Choosing left = 1 and right = 1, we have nums1 = [60,90,60] and nums2 = [10,60,10].
        The score is max(sum(nums1), sum(nums2)) = max(210, 80) = 210.
        Example 2.
        Input: nums1 = [20,40,20,70,30], nums2 = [50,20,50,40,20]
        Output: 220
        Explanation: Choosing left = 3, right = 4, we have nums1 = [20,40,20,40,20] and nums2 = [50,20,50,70,30].
        The score is max(sum(nums1), sum(nums2)) = max(140, 220) = 220.
        Example 3:

        Input: nums1 = [7,11,13], nums2 = [1,1,1]
        Output: 31
        Explanation: We choose not to swap any subarray.
        The score is max(sum(nums1), sum(nums2)) = max(31, 3) = 31.


        Constraints:

        n == nums1.length == nums2.length
        1 <= n <= 105
        1 <= nums1[i], nums2[i] <= 104
     */

    public static void main(String[] args) {
        int[] nums1 = {20,40,20,70,30}, nums2 = {50,20,50,40,20};
        System.out.println("Solution: " + Solution.maximumsSplicedArray(nums1, nums2));
    }

    // Time:
    // Space:
    static class Solution {
        public static int maximumsSplicedArray(int[] nums1, int[] nums2) {
            int n = nums1.length;
            int minValue = 0, prefixSum1 = 0, prefixSum2 = 0;
            int sum1 = 0, sum2 = 0;

            for(int i = 0; i < n; i++) {
                sum1 += nums1[i];
                sum2 += nums2[i];
            }

            int ans = Math.max(sum1, sum2);

            for(int i = 0; i < n; i++) {
                prefixSum1 += nums1[i];
                prefixSum2 += nums2[i];

                int delta = prefixSum2 - prefixSum1 - minValue;

                ans = Math.max(ans, Math.max(sum1 + delta, sum2 - delta));
                minValue = Math.min(minValue, prefixSum2 - prefixSum1);
            }

            minValue = 0; prefixSum1 = 0; prefixSum2 = 0;
            for(int i = 0; i < n; i++) {
                prefixSum1 += nums1[i];
                prefixSum2 += nums2[i];

                int delta = prefixSum1 - prefixSum2 - minValue;

                ans = Math.max(ans, Math.max(sum1 - delta, sum2 + delta));
                minValue = Math.min(minValue, prefixSum1 - prefixSum2);
            }
            return ans;
        }
    }
}
