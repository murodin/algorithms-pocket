import java.util.ArrayList;
import java.util.List;

public class MaximumWidthRamp {
    /*
        A ramp in an integer array nums is a pair (i, j) for which i < j and nums[i] <= nums[j]. The width of such a ramp is j - i.
        Given an integer array nums, return the maximum width of a ramp in nums. If there is no ramp in nums, return 0.

        Example 1:

        Input: nums = [6,0,8,2,1,5]
        Output: 4
        Explanation: The maximum width ramp is achieved at (i, j) = (1, 5): nums[1] = 0 and nums[5] = 5.
        Example 2:

        Input: nums = [9,8,1,0,1,9,4,0,4,1]
        Output: 7
        Explanation: The maximum width ramp is achieved at (i, j) = (2, 9): nums[2] = 1 and nums[9] = 1.


        Constraints:

        2 <= nums.length <= 5 * 104
        0 <= nums[i] <= 5 * 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxWidthRamp(new int[]{9,8,1,0,1,9,4,0,4,1}));
    }

    // Time: O(LogN)
    // Space: O(N)
    static class Solution {
        public static int maxWidthRamp(int[] A) {
            List<Integer> s = new ArrayList<>();
            int res = 0, n = A.length;
            for (int i = 0; i < n; ++i) {
                if (s.isEmpty() || A[i] < A[s.get(s.size() - 1)]) {
                    s.add(i);
                } else {
                    int left = 0, right = s.size() - 1, mid = 0;
                    while (left < right) {
                        mid = (left + right) / 2;
                        if (A[s.get(mid)] > A[i]) {
                            left = mid + 1;
                        } else {
                            right = mid;
                        }
                    }
                    res = Math.max(res, i - s.get(left));
                }
            }
            return res;
        }
    }
}
