import java.util.HashMap;
import java.util.Map;

public class FourSumII {
    /*
        Given four integer arrays nums1, nums2, nums3, and nums4 all of length n, return the number of tuples (i, j, k, l) such that:
        0 <= i, j, k, l < n
        nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0

        Example 1.
        Input: nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
        Output: 2
        Explanation:
        The two tuples are:
        1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
        2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
        Example 2.
        Input: nums1 = [0], nums2 = [0], nums3 = [0], nums4 = [0]
        Output: 1

        Constraints:

        n == nums1.length
        n == nums2.length
        n == nums3.length
        n == nums4.length
        1 <= n <= 200
        -228 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 228
     */
    public static void main(String[] args) {
        int[] nums1 = {1,2}, nums2 = {-2,-1}, nums3 = {-1,2}, nums4 = {0,2};
        System.out.println("4 Sum Count: " + Solution.fourSumCount(nums1, nums2, nums3, nums4));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
            Map<Integer, Integer> sumMap = new HashMap<>();

            for(int a: nums1)
                for (int b: nums2) {
                    int sum = a + b;
                    sumMap.put(-sum, sumMap.getOrDefault(-sum, 0) + 1);
                }

            int count = 0;
            for(int c: nums3)
                for (int d: nums4) {
                    int sum = c + d;
                    count += sumMap.getOrDefault(sum, 0);
                }

            return count;
        }
    }
}
