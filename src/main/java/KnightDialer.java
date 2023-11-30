import java.util.HashMap;
import java.util.Map;

public class KnightDialer {
    /*
        The chess knight has a unique movement, it may move two squares vertically and one square horizontally,
        or two squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of chess knight are shown in this diagaram:

        A chess knight can move as indicated in the chess diagram below:
        We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).
        Given an integer n, return how many distinct phone numbers of length n we can dial.
        You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.

        As the answer may be very large, return the answer modulo 109 + 7.

        Example 1:
        Input: n = 1
        Output: 10
        Explanation: We need to dial a number of length 1, so placing the knight over any numeric cell of the 10 cells is sufficient.
        Example 2:
        Input: n = 2
        Output: 20
        Explanation: All the valid number we can dial are [04, 06, 16, 18, 27, 29, 34, 38, 40, 43, 49, 60, 61, 67, 72, 76, 81, 83, 92, 94]
        Example 3:
        Input: n = 3131
        Output: 136006598
        Explanation: Please take care of the mod.


        Constraints:

        1 <= n <= 5000
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.knightDialer(3131));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static int knightDialer(int n) {
            if (n == 1) return 10;

            Map<Integer, int[]> moves = new HashMap<>();
            moves.put(0, new int[]{4, 6});
            moves.put(1, new int[]{6, 8});
            moves.put(2, new int[]{7, 9});
            moves.put(3, new int[]{4, 8});
            moves.put(4, new int[]{0, 3, 9});
            moves.put(6, new int[]{0, 1, 7});
            moves.put(7, new int[]{2, 6});
            moves.put(8, new int[]{1, 3});
            moves.put(9, new int[]{2, 4});

            int[] v = new int[10];
            for (int i = 0; i < 10; i++) {
                v[i] = 1;
            }

            for (int i = 2; i <= n; i++) {
                int[] tmp = new int[10];
                for (Map.Entry<Integer, int[]> entry : moves.entrySet()) {
                    int key = entry.getKey();
                    int[] val = entry.getValue();
                    for (int j : val) {
                        tmp[key] = (tmp[key] + v[j]) % 1000000007;
                    }
                }
                v = tmp;
            }

            int ans = 0;
            for (int i : v) {
                ans = (ans + i) % 1000000007;
            }
            return ans;
        }
    }
}
