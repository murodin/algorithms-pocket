public class CheckIfItIsAStraightLine {
    /*
        You are given an array coordinates, coordinates[i] = [x, y], where [x, y] represents the coordinate of a point.
        Check if these points make a straight line in the XY plane.

        Example 1:

        Input: coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
        Output: true
        Example 2:

        Input: coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
        Output: false

        Constraints:

        2 <= coordinates.length <= 1000
        coordinates[i].length == 2
        -10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
        coordinates contains no duplicate point.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.checkStraightLine(new int[][]{{1,1}, {2,2}, {3,4}, {4,5}, {5,6}, {7,7}}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static boolean checkStraightLine(int[][] c) {
            int xMove = c[1][0] - c[0][0];
            int yMove = c[1][1] - c[0][1];
            for(int i=1; i<c.length; i++){
                int x = c[i][0] - c[i-1][0];
                int y = c[i][1] - c[i-1][1];
                if(y * xMove != x * yMove) return false;
            }
            return true;
        }
    }
}
