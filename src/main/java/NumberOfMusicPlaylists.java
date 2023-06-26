public class NumberOfMusicPlaylists {
    /*
        Your music player contains n different songs. You want to listen to goal songs (not necessarily different) during your trip.
         To avoid boredom, you will create a playlist so that:

        Every song is played at least once.
        A song can only be played again only if k other songs have been played.
        Given n, goal, and k, return the number of possible playlists that you can create. Since the answer can be very large, return it modulo 109 + 7.

        Example 1:

        Input: n = 3, goal = 3, k = 1
        Output: 6
        Explanation: There are 6 possible playlists: [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], and [3, 2, 1].
        Example 2:

        Input: n = 2, goal = 3, k = 0
        Output: 6
        Explanation: There are 6 possible playlists: [1, 1, 2], [1, 2, 1], [2, 1, 1], [2, 2, 1], [2, 1, 2], and [1, 2, 2].
        Example 3:

        Input: n = 2, goal = 3, k = 1
        Output: 2
        Explanation: There are 2 possible playlists: [1, 2, 1] and [2, 1, 2].


        Constraints:

        0 <= k < n <= goal <= 100
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numMusicPlaylists(2, 3, 0));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        static long mod = (long)1e9 + 7;
        public static int numMusicPlaylists(int N, int L, int K) {
            long[][] dp = new long[N + 1][L + 1];
            for (int i = K + 1; i <= N; ++i)
                for (int j = i; j <= L; ++j)
                    if ((i == j) || (i == K + 1))
                        dp[i][j] = factorial(i);
                    else
                        dp[i][j] = (dp[i - 1][j - 1] * i + dp[i][j - 1] * (i - K))  % mod;
            return (int) dp[N][L];
        }

        static long factorial(int n) {
            return n > 0 ? factorial(n - 1) * n % mod : 1;
        }
    }
}
