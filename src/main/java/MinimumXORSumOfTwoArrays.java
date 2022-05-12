public class MinimumXORSumOfTwoArrays {
    /*
        You are given two integer arrays nums1 and nums2 of length n.
        The XOR sum of the two integer arrays is (nums1[0] XOR nums2[0]) + (nums1[1] XOR nums2[1]) + ... + (nums1[n - 1] XOR nums2[n - 1]) (0-indexed).
        For example, the XOR sum of [1,2,3] and [3,2,1] is equal to (1 XOR 3) + (2 XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4.
        Rearrange the elements of nums2 such that the resulting XOR sum is minimized.

        Return the XOR sum after the rearrangement.

        Example 1.
        Input: nums1 = [1,2], nums2 = [2,3]
        Output: 2
        Explanation: Rearrange nums2 so that it becomes [3,2].
        The XOR sum is (1 XOR 3) + (2 XOR 2) = 2 + 0 = 2.
        Example 2.
        Input: nums1 = [1,0,3], nums2 = [5,3,4]
        Output: 8
        Explanation: Rearrange nums2 so that it becomes [5,4,3].
        The XOR sum is (1 XOR 5) + (0 XOR 4) + (3 XOR 3) = 4 + 4 + 0 = 8.


        Constraints:

        n == nums1.length
        n == nums2.length
        1 <= n <= 14
        0 <= nums1[i], nums2[i] <= 107
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minimumXORSum(new int[] {1,0,3}, new int[] {5,3,4}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        static Integer[][] memo;
        public static int minimumXORSum(int[] nums1, int[] nums2) {
            memo = new Integer[nums1.length][1 << nums1.length];
            return dfs(nums1, nums2, 0, 0);
        }

        static int dfs(int[] nums1, int[] nums2, int idx, int mask) {
            if (idx == nums1.length) return 0;
            if (memo[idx][mask] != null) return memo[idx][mask];
            int res = Integer.MAX_VALUE;
            for (int i = 0; i < nums2.length; i++)
                if ((mask & (1 << i)) == 0)
                    res = Math.min(res,
                            (nums1[idx] ^ nums2[i]) + dfs(nums1, nums2, idx + 1, mask | (1 << i)));
            return memo[idx][mask] = res;
        }
    }
}
