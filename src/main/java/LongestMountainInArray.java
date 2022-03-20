public class LongestMountainInArray {

    /*
        You may recall that an array arr is a mountain array if and only if:

        arr.length >= 3
        There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
        arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
        arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
        Given an integer array arr, return the length of the longest subarray, which is a mountain. Return 0 if there is no mountain subarray.

        Example 1.
        Input: arr = [2,1,4,7,3,2,5]
        Output: 5
        Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
        Example 2:

        Input: arr = [2,2,2]
        Output: 0
        Explanation: There is no mountain.

        Constraints:

        1 <= arr.length <= 104
        0 <= arr[i] <= 104

        Follow up:

        Can you solve it using only one pass?
        Can you solve it in O(1) space?
     */

    public static void main(String[] args) {
        int[] arr = {2,1,4,7,3,2,5};
        System.out.println("Solution: " + Solution.longestMountain(arr));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int longestMountain(int[] arr) {
            int res = 0;
            int n = arr.length;

            for(int i = 1; i < n; i++) {
                int count = 1;
                boolean flag = false;
                int j = i;

                while (j<n && arr[j] > arr[j-1]) {
                    j++;
                    count++;
                }

                while (i!= j && j<n && arr[j] < arr[j-1]) {
                    j++;
                    count++;
                    flag = true;
                }

                if(i != j && flag && count > 2) {
                    res = Math.max(res, count);
                    j--;
                }
                i=j;
            }

            return res;
        }
    }
}
