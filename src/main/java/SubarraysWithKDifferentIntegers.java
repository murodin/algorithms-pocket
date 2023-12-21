import java.util.HashMap;
import java.util.Map;

public class SubarraysWithKDifferentIntegers {
    /*
        Given an integer array nums and an integer k, return the number of good subarrays of nums.
        A good array is an array where the number of different integers in that array is exactly k.
        For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
        A subarray is a contiguous part of an array.

        Example 1:
        Input: nums = [1,2,1,2,3], k = 2
        Output: 7
        Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
        Example 2:
        Input: nums = [1,2,1,3,4], k = 3
        Output: 3
        Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].


        Constraints:

        1 <= nums.length <= 2 * 104
        1 <= nums[i], k <= nums.length
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.subarraysWithKDistinct(new int[]{1,2,1,2,3}, 2));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int subarraysWithKDistinct(int[] A, int K) {
            return atMostK(A, K) - atMostK(A, K - 1);
        }
        static int atMostK(int[] A, int K) {
            int i = 0, res = 0;
            Map<Integer, Integer> count = new HashMap<>();
            for (int j = 0; j < A.length; ++j) {
                if (count.getOrDefault(A[j], 0) == 0) K--;
                count.put(A[j], count.getOrDefault(A[j], 0) + 1);
                while (K < 0) {
                    count.put(A[i], count.get(A[i]) - 1);
                    if (count.get(A[i]) == 0) K++;
                    i++;
                }
                res += j - i + 1;
            }
            return res;
        }
    }
}
