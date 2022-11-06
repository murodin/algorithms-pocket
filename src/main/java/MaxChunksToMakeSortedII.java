import java.util.Arrays;

public class MaxChunksToMakeSortedII {
    /*
        You are given an integer array arr.
        We split arr into some number of chunks (i.e., partitions), and individually sort each chunk. After concatenating them, the result should equal the sorted array.
        Return the largest number of chunks we can make to sort the array.

        Example 1.
        Input: arr = [5,4,3,2,1]
        Output: 1
        Explanation:
        Splitting into two or more chunks will not return the required result.
        For example, splitting into [5, 4], [3, 2, 1] will result in [4, 5, 1, 2, 3], which isn't sorted.
        Example 2.
        Input: arr = [2,1,3,4,4]
        Output: 4
        Explanation:
        We can split into two chunks, such as [2, 1], [3, 4, 4].
        However, splitting into [2, 1], [3], [4], [4] is the highest number of chunks possible.

        Constraints:

        1 <= arr.length <= 2000
        0 <= arr[i] <= 108
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxChunksToSorted(new int[] {2,1,3,4,4}));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static int maxChunksToSorted(int[] arr) {
            int[] sorted = arr.clone();
            Arrays.sort(sorted);
            int res = 0, sum1 = 0, sum2 = 0;
            for (int i = 0; i < arr.length; i++) {
                sum1 += arr[i];
                sum2 += sorted[i];
                if (sum1 == sum2) res += 1;
            }
            return res;
        }
    }
}
