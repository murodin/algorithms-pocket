public class ReachingPoints {
    /*
        Given four integers sx, sy, tx, and ty, return true if it is possible to convert the point (sx, sy) to the point (tx, ty) through some operations, or false otherwise.
        The allowed operation on some point (x, y) is to convert it to either (x, x + y) or (x + y, y).

        Example 1:

        Input: sx = 1, sy = 1, tx = 3, ty = 5
        Output: true
        Explanation:
        One series of moves that transforms the starting point to the target is:
        (1, 1) -> (1, 2)
        (1, 2) -> (3, 2)
        (3, 2) -> (3, 5)
        Example 2:

        Input: sx = 1, sy = 1, tx = 2, ty = 2
        Output: false
        Example 3:

        Input: sx = 1, sy = 1, tx = 1, ty = 1
        Output: true


        Constraints:

        1 <= sx, sy, tx, ty <= 109
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.reachingPoints(1, 1, 3, 5));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static boolean reachingPoints(int sx, int sy, int tx, int ty) {
            while (sx < tx && sy < ty)
                if (tx < ty) ty %= tx;
                else tx %= ty;
            return sx == tx && sy <= ty && (ty - sy) % sx == 0 ||
                    sy == ty && sx <= tx && (tx - sx) % sy == 0;
        }
    }
}
