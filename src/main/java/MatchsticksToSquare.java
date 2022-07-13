import java.util.Arrays;

public class MatchsticksToSquare {
    /*
        You are given an integer array matchsticks where matchsticks[i] is the length of the ith matchstick. You want to use all the matchsticks to make one square.
        You should not break any stick, but you can link them up, and each matchstick must be used exactly one time.
        Return true if you can make this square and false otherwise.

        Example 1.
        Input: matchsticks = [1,1,2,2,2]
        Output: true
        Explanation: You can form a square with length 2, one side of the square came two sticks with length 1.
        Example 2.
        Input: matchsticks = [3,3,3,3,4]
        Output: false
        Explanation: You cannot find a way to form a square with all the matchsticks.


        Constraints:

        1 <= matchsticks.length <= 15
        1 <= matchsticks[i] <= 108
     */
    public static void main(String[] args) {
        System.out.println("Solution - true: " + Solution.makesquare(new int[] {1,1,2,2,2}));
        System.out.println("Solution - false: " + Solution.makesquare(new int[] {3,3,3,3,4}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static boolean makesquare(int[] matchsticks) {
            int total = 0;
            for (int i : matchsticks) {
                total += i;
            }

            if (total % 4 != 0) return false; // if we cant make 4 equals sides then theres no way to make a square
            // sort the array and place the largest sides first. required optimization to not TLE
            Arrays.sort(matchsticks);
            return match(matchsticks, matchsticks.length - 1, 0, 0, 0, 0, total / 4);
        }

        public static boolean match(int[] matchsticks, int index, int top, int bottom, int left, int right, int target) {

            if (top == target && bottom == target && left == target && right == target) return true;

            if (top > target || bottom > target || left > target || right > target) return false;

            int val = matchsticks[index];

            boolean t = match(matchsticks, index - 1, top + val, bottom, left, right, target);
            if (t) return true;
            boolean b = match(matchsticks, index - 1, top, bottom + val, left, right, target);
            if (b) return true;
            boolean l = match(matchsticks, index - 1, top, bottom, left + val, right, target);
            if (l) return true;
            boolean r = match(matchsticks, index - 1, top, bottom, left, right + val, target);
            return r;
        }
    }
}
