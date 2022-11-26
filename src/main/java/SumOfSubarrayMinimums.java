public class SumOfSubarrayMinimums {
    /*
        Given an array of integers arr, find the sum of min(b), where b ranges over every (contiguous) subarray of arr.
        Since the answer may be large, return the answer modulo 109 + 7.

        Example 1.
        Input: arr = [3,1,2,4]
        Output: 17
        Explanation:
        Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
        Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
        Sum is 17.
        Example 2.
        Input: arr = [11,81,94,43,3]
        Output: 444


        Constraints:

        1 <= arr.length <= 3 * 104
        1 <= arr[i] <= 3 * 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.sumSubarrayMins(new int[] {3,1,2,4}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int sumSubarrayMins(int[] arr) {
            final int modulo = 1_000_000_007;

            int[] stack = new int[arr.length + 1];
            int idxStack = 0;

            long res = 0;
            for (int i = 1; i <= arr.length; i++) {
                while (idxStack >= 0 && (i == arr.length || arr[stack[idxStack]] > arr[i])){
                    int idx = stack[idxStack--];//pop
                    int leftIdx = idxStack < 0 ? -1 : stack[idxStack];
                    res += (long) arr[idx] * (i - idx) * (idx - leftIdx);
                }
                stack[++idxStack] = i;//push
            }
            return (int) (res % modulo);
        }
    }
}
