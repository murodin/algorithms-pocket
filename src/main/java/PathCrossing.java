import javafx.util.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PathCrossing {
    /*
        Given a string path, where path[i] = 'N', 'S', 'E' or 'W',
        each representing moving one unit north, south, east, or west, respectively. You start at the origin (0, 0) on a 2D plane and walk on the path specified by path.
        Return true if the path crosses itself at any point, that is, if at any time you are on a location you have previously visited. Return false otherwise.

        Example 1:
        Input: path = "NES"
        Output: false
        Explanation: Notice that the path doesn't cross any point more than once.
        Example 2:
        Input: path = "NESWW"
        Output: true
        Explanation: Notice that the path visits the origin twice.


        Constraints:

        1 <= path.length <= 104
        path[i] is either 'N', 'S', 'E', or 'W'.
     */
    public static void main(String[] args) {
        System.out.println(Solution.isPathCrossing("NE"));

        System.out.println("Solution: " + Solution.isPathCrossing("NESWW"));

    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static boolean isPathCrossing(String path) {
            Map<Character, Pair<Integer, Integer>> moves = new HashMap();
            moves.put('N', new Pair(0, 1));
            moves.put('S', new Pair(0, -1));
            moves.put('W', new Pair(-1, 0));
            moves.put('E', new Pair(1, 0));
            Set<Pair<Integer, Integer>> visited = new HashSet();
            visited.add(new Pair(0, 0));
            int x = 0;
            int y = 0;
            for (Character c : path.toCharArray()) {
                Pair<Integer, Integer> curr = moves.get(c);
                int dx = curr.getKey();
                int dy = curr.getValue();
                x += dx;
                y += dy;
                Pair<Integer, Integer> pair = new Pair(x, y);
                if (visited.contains(pair)) {
                    return true;
                }
                visited.add(pair);
            }
            return false;
        }
    }
}
