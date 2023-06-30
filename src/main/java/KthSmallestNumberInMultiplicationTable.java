public class KthSmallestNumberInMultiplicationTable {
    /*
        Nearly everyone has used the Multiplication Table. The multiplication table of size m x n is an integer matrix mat where mat[i][j] == i * j (1-indexed).

        Given three integers m, n, and k, return the kth smallest element in the m x n multiplication table.

        Example 1:


        Input: m = 3, n = 3, k = 5
        Output: 3
        Explanation: The 5th smallest number is 3.
        Example 2:


        Input: m = 2, n = 3, k = 6
        Output: 6
        Explanation: The 6th smallest number is 6.


        Constraints:

        1 <= m, n <= 3 * 104
        1 <= k <= m * n
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findKthNumber(3, 3, 5));
    }

    // Time: O(LogN)
    // Space: O(1)
    static class Solution {
        public static int findKthNumber(int m, int n, int k) {
            int lo=0, hi=m*n;
            while (lo < hi) {
                int mid=(lo+hi)/2, count=0;
                for (int i=1; i<=m; i++)
                    count += Math.min(n, mid / i);
                if (count >= k)
                    hi = mid;
                else
                    lo = mid+1;
            }
            return lo;
        }
    }
}
