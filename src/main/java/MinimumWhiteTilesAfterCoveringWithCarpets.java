public class MinimumWhiteTilesAfterCoveringWithCarpets {
    /*
        You are given a 0-indexed binary string floor, which represents the colors of tiles on a floor:
        floor[i] = '0' denotes that the ith tile of the floor is colored black.
        On the other hand, floor[i] = '1' denotes that the ith tile of the floor is colored white.
        You are also given numCarpets and carpetLen. You have numCarpets black carpets, each of length carpetLen tiles.
        Cover the tiles with the given carpets such that the number of white tiles still visible is minimum. Carpets may overlap one another.

        Return the minimum number of white tiles still visible.

        Example 1:
        Input: floor = "10110101", numCarpets = 2, carpetLen = 2
        Output: 2
        Explanation:
        The figure above shows one way of covering the tiles with the carpets such that only 2 white tiles are visible.
        No other way of covering the tiles with the carpets can leave less than 2 white tiles visible.
        Example 2:
        Input: floor = "11111", numCarpets = 2, carpetLen = 3
        Output: 0
        Explanation:
        The figure above shows one way of covering the tiles with the carpets such that no white tiles are visible.
        Note that the carpets are able to overlap one another.


        Constraints:

        1 <= carpetLen <= floor.length <= 1000
        floor[i] is either '0' or '1'.
        1 <= numCarpets <= 1000
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minimumWhiteTiles("10110101" ,2, 2));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        public static int minimumWhiteTiles(String s, int nc, int l) {
            int n = s.length();
            int[][] dp = new int[n + 1][nc + 1];
            for (int i = 1; i <= n; ++i) {
                for (int k = 0; k <= nc; ++k) {
                    int jump = dp[i - 1][k] + s.charAt(i - 1) - '0';
                    int cover = k > 0 ? dp[Math.max(i - l, 0)][k - 1] : 1000;
                    dp[i][k] = Math.min(cover, jump);
                }
            }
            return dp[n][nc];
        }
    }
}
