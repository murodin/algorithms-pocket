import java.util.HashSet;
import java.util.Set;

public class ValidSquare {

    /*
        Given the coordinates of four points in 2D space p1, p2, p3 and p4, return true if the four points construct a square.
        The coordinate of a point pi is represented as [xi, yi]. The input is not given in any order.
        A valid square has four equal sides with positive length and four equal angles (90-degree angles).

        Example 1.
        Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,1]
        Output: true
        Example 2.
        Input: p1 = [0,0], p2 = [1,1], p3 = [1,0], p4 = [0,12]
        Output: false
        Example 3.
        Input: p1 = [1,0], p2 = [-1,0], p3 = [0,1], p4 = [0,-1]
        Output: true

        Constraints:

        p1.length == p2.length == p3.length == p4.length == 2
        -104 <= xi, yi <= 104
     */

    public static void main(String[] args) {
        int[] p1 = {0,0}, p2 = {1,1}, p3 = {1,0}, p4 = {0,1};
        System.out.println("Valid Square - expected True: " + Solution.validSquare(p1, p2, p3, p4));
        int[] p5 = {0,0}, p6 = {1,1}, p7 = {1,0}, p8 = {0,8};
        System.out.println("Valid Square - expected False:" + Solution.validSquare(p5, p6, p7, p8));
    }

    // Time: O(1)
    // Space: O(1)
    static class Solution {
        public static boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
            Set<Integer> set = new HashSet<>();

            if(!helper(p1, p2, set) || !helper(p1, p3, set) || !helper(p1, p4, set) ||
                    !helper(p2, p3, set) || !helper(p2, p3, set) || !helper(p3, p4, set)) return false;

            return set.size() == 2;
        }

        private static boolean helper(int[] e, int[] s, Set<Integer> set) {
            if(e[0] == s[0] && e[1] == s[1]) return false;
            int dx = e[0] - s[0];
            int dy = e[1] - s[1];

            int dist = dx*dx + dy*dy;
            set.add(dist);
            return true;
        }
    }
}
