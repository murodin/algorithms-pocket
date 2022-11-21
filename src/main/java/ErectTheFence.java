import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class ErectTheFence {
    /*
        You are given an array trees where trees[i] = [xi, yi] represents the location of a tree in the garden.
        You are asked to fence the entire garden using the minimum length of rope as it is expensive. The garden is well fenced only if all the trees are enclosed.
        Return the coordinates of trees that are exactly located on the fence perimeter.

        Example 1.
        Input: points = [[1,1],[2,2],[2,0],[2,4],[3,3],[4,2]]
        Output: [[1,1],[2,0],[3,3],[2,4],[4,2]]
        Example 2.
        Input: points = [[1,2],[2,2],[4,2]]
        Output: [[4,2],[2,2],[1,2]]


        Constraints:

        1 <= points.length <= 3000
        points[i].length == 2
        0 <= xi, yi <= 100
        All the given points are unique.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.deepToString(Solution.outerTrees(new int[][]{
                {1, 1}, {2, 2}, {2, 0}, {2, 4}, {3, 3}, {4, 2}
        })));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        private static int orientation(int[] p,int[] q,int[] r){
            return (r[1]-q[1]) * (q[0]-p[0]) - ( (q[1]-p[1]) * (r[0]-q[0]));
        }
        public static int[][] outerTrees(int[][] trees) {
            Stack<int[]> upper= new Stack<>();
            Stack<int[]> lower= new Stack<>();
            Arrays.sort(trees,(p, q) ->  q[0]-p[0]==0 ? q[1]-p[1]: q[0]-p[0] );
            for (int[] tree : trees) {
                while (lower.size() >= 2 && orientation(lower.get(lower.size() - 2), lower.get(lower.size() - 1), tree) > 0) {
                    lower.pop();
                }
                while (upper.size() >= 2 && orientation(upper.get(upper.size() - 2), upper.get(upper.size() - 1), tree) < 0) {
                    upper.pop();
                }
                lower.push(tree);
                upper.push(tree);

            }
            Set<int[]> res= new HashSet<>(lower);
            res.addAll(upper);
            return res.toArray(new int[res.size()][]);

        }
    }
}
