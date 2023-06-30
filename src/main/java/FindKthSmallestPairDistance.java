import java.util.Arrays;

public class FindKthSmallestPairDistance {
    /*
        The distance of a pair of integers a and b is defined as the absolute difference between a and b.

        Given an integer array nums and an integer k, return the kth smallest distance among all the pairs nums[i] and nums[j] where 0 <= i < j < nums.length.



        Example 1:

        Input: nums = [1,3,1], k = 1
        Output: 0
        Explanation: Here are all the pairs:
        (1,3) -> 2
        (1,1) -> 0
        (3,1) -> 2
        Then the 1st smallest distance pair is (1,1), and its distance is 0.
        Example 2:

        Input: nums = [1,1,1], k = 2
        Output: 0
        Example 3:

        Input: nums = [1,6,1], k = 3
        Output: 5


        Constraints:

        n == nums.length
        2 <= n <= 104
        0 <= nums[i] <= 106
        1 <= k <= n * (n - 1) / 2
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.smallestDistancePair(new int[]{1,6,1}, 3));
    }

    // Time: O(NLogN)
    // Space: O(1)
    static class Solution {
        private static int upperBound(int[] a, int low, int high, int key) {
            if (a[high] <= key) return high + 1;
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (key >= a[mid]) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }

        private static int countPairs(int[] a, int mid) {
            int n = a.length, res = 0;
            for (int i = 0; i < n; i++) {
                res += upperBound(a, i, n - 1, a[i] + mid) - i - 1;
            }
            return res;
        }

        public static int smallestDistancePair(int[] a, int k) {
            int n = a.length;
            Arrays.sort(a);
            int low = a[1] - a[0];
            for (int i = 1; i < n - 1; i++)
                low = Math.min(low, a[i + 1] - a[i]);

            int high = a[n - 1] - a[0];
            while (low < high) {
                int mid = low + (high - low) / 2;
                if (countPairs(a, mid) < k)
                    low = mid + 1;
                else
                    high = mid;
            }

            return low;
        }
    }
}
