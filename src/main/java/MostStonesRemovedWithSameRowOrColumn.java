import java.util.HashMap;
import java.util.Map;

public class MostStonesRemovedWithSameRowOrColumn {
    /*
        On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
        A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
        Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.

        Example 1:

        Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
        Output: 5
        Explanation: One way to remove 5 stones is as follows:
        1. Remove stone [2,2] because it shares the same row as [2,1].
        2. Remove stone [2,1] because it shares the same column as [0,1].
        3. Remove stone [1,2] because it shares the same row as [1,0].
        4. Remove stone [1,0] because it shares the same column as [0,0].
        5. Remove stone [0,1] because it shares the same row as [0,0].
        Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
        Example 2:

        Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
        Output: 3
        Explanation: One way to make 3 moves is as follows:
        1. Remove stone [2,2] because it shares the same row as [2,0].
        2. Remove stone [2,0] because it shares the same column as [0,0].
        3. Remove stone [0,2] because it shares the same row as [0,0].
        Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
        Example 3:

        Input: stones = [[0,0]]
        Output: 0
        Explanation: [0,0] is the only stone on the plane, so you cannot remove it.


        Constraints:

        1 <= stones.length <= 1000
        0 <= xi, yi <= 104
        No two stones are at the same coordinate point.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.removeStones(
                    new int[][]{{0,0},{0,2},{1,1},{2,0},{2,2}}
                ));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        static int count = 0;
        public static int removeStones(int[][] stones) {
            Map<String, String> parent = new HashMap<>();
            count = stones.length;
            // init Union Find
            for (int[] stone : stones) {
                String s = stone[0] + " " + stone[1];
                parent.put(s, s);
            }
            for (int[] s1 : stones) {
                String ss1 = s1[0] + " " + s1[1];
                for (int[] s2 : stones) {
                    if (s1[0] == s2[0] || s1[1] == s2[1]) { // in the same column or row
                        String ss2 = s2[0] + " " + s2[1];
                        union(parent, ss1, ss2);
                    }
                }
            }
            return stones.length - count;
        }
        private static void union(Map<String, String> parent, String s1, String s2) {
            String r1 = find(parent, s1), r2 = find(parent, s2);
            if (r1.equals(r2)) {
                return;
            }
            parent.put(r1, r2);
            count--;
        }
        private static String find(Map<String, String> parent, String s) {
            if (!parent.get(s).equals(s)) {
                parent.put(s, find(parent, parent.get(s)));
            }
            return parent.get(s);
        }
    }
}
