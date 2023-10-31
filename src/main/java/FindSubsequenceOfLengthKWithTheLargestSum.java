import java.util.Arrays;

public class FindSubsequenceOfLengthKWithTheLargestSum {
    /*
        You are given an integer array nums and an integer k. You want to find a subsequence of nums of length k that has the largest sum.
        Return any such subsequence as an integer array of length k.
        A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.


        Example 1:

        Input: nums = [2,1,3,3], k = 2
        Output: [3,3]
        Explanation:
        The subsequence has the largest sum of 3 + 3 = 6.
        Example 2:

        Input: nums = [-1,-2,3,4], k = 3
        Output: [-1,3,4]
        Explanation:
        The subsequence has the largest sum of -1 + 3 + 4 = 6.
        Example 3:

        Input: nums = [3,4,3,3], k = 2
        Output: [3,4]
        Explanation:
        The subsequence has the largest sum of 3 + 4 = 7.
        Another possible subsequence is [4, 3].


        Constraints:

        1 <= nums.length <= 1000
        -105 <= nums[i] <= 105
        1 <= k <= nums.length
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.maxSubsequence(new int[]{3, 4, 3, 3}, 2)));
    }

    // Time: O(NLogN)
    // Space: O(1)
    static class Solution {
        public static int[] maxSubsequence(int[] nums, int k) {
            int n = nums.length;
            int[] index = new int[n];
            for (int i = 0; i < n; ++i) {
                index[i] = i;
            }
            int lo = 0, hi = n - 1;
            while (lo < hi) {
                int idx = quickSelect(nums, index, lo, hi);
                if (idx < k) {
                    lo = idx + 1;
                }else {
                    hi = idx;
                }
            }
            int kthVal = nums[index[k - 1]], freqOfkthVal = 0;
            for (int i : Arrays.copyOf(index, k)) {
                freqOfkthVal += nums[i] == kthVal ? 1 : 0;
            }
            int[] seq = new int[k];
            int i = 0;
            for (int num : nums) {
                if (num > kthVal || num == kthVal && freqOfkthVal-- > 0) {
                    seq[i++] = num;
                }
            }
            return seq;
        }
        private static int quickSelect(int[] nums, int[] index, int lo, int hi) {
            int pivot = index[lo];
            while (lo < hi) {
                while (lo < hi && nums[index[hi]] <= nums[pivot]) {
                    --hi;
                }
                index[lo] = index[hi];
                while (lo < hi && nums[index[lo]] >= nums[pivot]) {
                    ++lo;
                }
                index[hi] = index[lo];
            }
            index[lo] = pivot;
            return lo;
        }
    }
}
