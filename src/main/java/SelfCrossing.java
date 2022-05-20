public class SelfCrossing {
    /*
        You are given an array of integers distance.
        You start at point (0,0) on an X-Y plane and you move distance[0] meters to the north, then distance[1] meters to the west,
         distance[2] meters to the south, distance[3] meters to the east, and so on. In other words, after each move, your direction changes counter-clockwise.

        Return true if your path crosses itself, and false if it does not.

        Example 1.
        Input: distance = [2,1,1,2]
        Output: true
        Example 2.
        Input: distance = [1,2,3,4]
        Output: false
        Example 3.
        Input: distance = [1,1,1,1]
        Output: true


        Constraints:

        1 <= distance.length <= 105
        1 <= distance[i] <= 105
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.isSelfCrossing(new int[] {2,1,1,2}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static boolean isSelfCrossing(int[] x) {
            boolean arm = false;
            boolean leg = false;
            for (int i = 2; i < x.length; ++i) {
                int a = f(x, i - 2) - f(x, i - 4);
                int b = f(x, i - 2);

                if (arm && x[i] >= b)          return true;  // cross [i - 2]
                if (leg && x[i] >= a && a > 0) return true;  // cross [i - 4]

                if (x[i] < a)       arm = true;
                else if (x[i] <= b) leg = true;
            }

            return false;
        }
        private static int f(int[] x, int index) {
            return (index < 0) ? 0 : x[index];
        }
    }
}
