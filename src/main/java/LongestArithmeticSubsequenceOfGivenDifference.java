import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSubsequenceOfGivenDifference {
    /*
        Given an integer array arr and an integer difference, return the length of the longest subsequence in arr which is an arithmetic sequence
        such that the difference between adjacent elements in the subsequence equals difference.
        A subsequence is a sequence that can be derived from arr by deleting some or no elements without changing the order of the remaining elements.

        Example 1:

        Input: arr = [1,2,3,4], difference = 1
        Output: 4
        Explanation: The longest arithmetic subsequence is [1,2,3,4].
        Example 2:

        Input: arr = [1,3,5,7], difference = 1
        Output: 1
        Explanation: The longest arithmetic subsequence is any single element.
        Example 3:

        Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
        Output: 4
        Explanation: The longest arithmetic subsequence is [7,5,3,1].


        Constraints:

        1 <= arr.length <= 105
        -104 <= arr[i], difference <= 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.longestSubsequence(new int[]{1,5,7,8,5,3,4,2,1}, -2));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int longestSubsequence(int[] arr, int difference) {
            Map<Integer, Integer> m = new HashMap<>();
            int mx = 0;
            for (int c : arr) {
                if (m.containsKey(c - difference))
                    m.put(c, m.get(c - difference) + 1);
                else
                    m.put(c, 1);
                mx = Math.max(mx, m.get(c));
            }
            return mx;
        }
    }
}
