import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PossibleBipartition {
    /*
        We want to split a group of n people (labeled from 1 to n)
        into two groups of any size. Each person may dislike some other people, and they should not go into the same group.
        Given the integer n and the array dislikes where dislikes[i] = [ai, bi]
        indicates that the person labeled ai does not like the person labeled bi, return true if it is possible to split everyone into two groups in this way.

        Example 1.
        Input: n = 4, dislikes = [[1,2],[1,3],[2,4]]
        Output: true
        Explanation: group1 [1,4] and group2 [2,3].
        Example 2.
        Input: n = 3, dislikes = [[1,2],[1,3],[2,3]]
        Output: false
        Example 3.
        Input: n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
        Output: false


        Constraints:

        1 <= n <= 2000
        0 <= dislikes.length <= 104
        dislikes[i].length == 2
        1 <= dislikes[i][j] <= n
        ai < bi
        All the pairs of dislikes are unique.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.possibleBipartition(4, new int[][]{{1,2},{1,3},{2,4}}));
    }

    // Time: O(V+E)
    // Space: O(N)
    static class Solution {
        static int n;
        static Map<Integer, List<Integer>> graph;
        public static boolean possibleBipartition(int n, int[][] dislikes) {
            if (dislikes == null || dislikes.length == 0)
                return true;
            graph = new HashMap<>();
            for(int[] dislik: dislikes){
                int hater = dislik[0];
                int hatee = dislik[1];
                graph.putIfAbsent(hatee, new ArrayList<>());
                graph.putIfAbsent(hater, new ArrayList<>());
                graph.get(hater).add(hatee);
                graph.get(hatee).add(hater);
            }

            int[] color = new int[1+n];
            for(int i = 1; i <= n; i++){
                if (color[i] == 0){
                    if (!dfs(i, color, color[i]))
                        return false;
                }
            }
            return true;
        }
        private static boolean dfs(int u, int[] color, int curColor){
            if (curColor == 0){
                color[u] = 1;
            } else {
                color[u] = curColor;
            }

            if (!graph.containsKey(u))
                return true;
            for(int adjVert: graph.get(u)){
                if (color[adjVert] == color[u]){
                    return false;
                }
                if (color[adjVert] != 0)
                    continue;
                if (!dfs(adjVert, color, -1*color[u]))
                    return false;
            }
            return true;
        }

    }

}
