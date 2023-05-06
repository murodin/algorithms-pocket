import java.util.Arrays;
import java.util.Comparator;

public class MaximumWhiteTilesCoveredByACarpet {
    /*
        You are given a 2D integer array tiles where tiles[i] = [li, ri] represents that every tile j in the range li <= j <= ri is colored white.
        You are also given an integer carpetLen, the length of a single carpet that can be placed anywhere.
        Return the maximum number of white tiles that can be covered by the carpet.

        Example 1.
        Input: tiles = [[1,5],[10,11],[12,18],[20,25],[30,32]], carpetLen = 10
        Output: 9
        Explanation: Place the carpet starting on tile 10.
        It covers 9 white tiles, so we return 9.
        Note that there may be other places where the carpet covers 9 white tiles.
        It can be shown that the carpet cannot cover more than 9 white tiles.
        Example 2.
        Input: tiles = [[10,11],[1,1]], carpetLen = 2
        Output: 2
        Explanation: Place the carpet starting on tile 10.
        It covers 2 white tiles, so we return 2.


        Constraints:

        1 <= tiles.length <= 5 * 104
        tiles[i].length == 2
        1 <= li <= ri <= 109
        1 <= carpetLen <= 109
        The tiles are non-overlapping.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maximumWhiteTiles(new int[][]{{10,11}, {1,1}}, 2));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static int maximumWhiteTiles(int[][] tiles, int carpetLen) {
            Arrays.sort(tiles, Comparator.comparingInt(i -> i[0]));
            int rv = 0, overlap = 0, ei = 0, prevEnd = 0;
            for (int si = 0; si < tiles.length; si++) {
                int st = tiles[si][0], end = st + carpetLen - 1;
                if (si > 0) {
                    overlap -= (Math.min(prevEnd, tiles[si-1][1]) - tiles[si-1][0] + 1);
                }
                if (ei-1 >= si && tiles[ei-1][1] > prevEnd) {
                    overlap += (Math.min(end, tiles[ei-1][1]) - prevEnd);
                }

                while (ei < tiles.length && tiles[ei][0] <= end) {
                    overlap += (Math.min(end, tiles[ei][1]) - tiles[ei][0] + 1);
                    ei++;
                }

                prevEnd = end;
                rv = Math.max(overlap, rv);
            }
            return rv;
        }
    }
}
