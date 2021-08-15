import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrickWall {

    /*
        There is a rectangular brick wall in front of you with n rows of bricks.
        The ith row has some number of bricks each of the same height (i.e., one unit) but they can be of different widths. The total width of each row is the same.
        Draw a vertical line from the top to the bottom and cross the least bricks.
        If your line goes through the edge of a brick, then the brick is not considered as crossed.
        You cannot draw a line just along one of the two vertical edges of the wall, in which case the line will obviously cross no bricks.
        Given the 2D array wall that contains the information about the wall, return the minimum number of crossed bricks after drawing such a vertical line.

        Example.
        Input: wall = [[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
        Output: 2

     */

    public static void main(String[] args) {
        List<List<Integer>> testWall = List.of(
            List.of(1,2,2,1),
            List.of(3,1,2),
            List.of(1,3,2),
            List.of(2,4),
            List.of(3,1,2),
            List.of(1,3,1,1)
        );

        System.out.println("Min. Number of Crossed Bricks: " + Solution.leastBricks(testWall));
    }

    static class Solution {
        public static int leastBricks(List<List<Integer>> wall) {
            int untouched =0;
            Map<Integer,Integer> map =new HashMap<>();

            for(List<Integer> row : wall){
                int end=0;
                for(int brick=0 ; brick < row.size() - 1 ; brick++){
                    end += row.get(brick);
                    map.put(end, map.getOrDefault(end,0) + 1);
                    untouched = Math.max(untouched,map.get(end));
                }
            }

            return wall.size() - untouched;
        }
    }
}
