import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseSchedule {
    /*
        There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
        You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

        For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
        Return true if you can finish all courses. Otherwise, return false.



        Example 1:

        Input: numCourses = 2, prerequisites = [[1,0]]
        Output: true
        Explanation: There are a total of 2 courses to take.
        To take course 1 you should have finished course 0. So it is possible.
        Example 2:

        Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
        Output: false
        Explanation: There are a total of 2 courses to take.
        To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.


        Constraints:

        1 <= numCourses <= 2000
        0 <= prerequisites.length <= 5000
        prerequisites[i].length == 2
        0 <= ai, bi < numCourses
        All the pairs prerequisites[i] are unique.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.canFinish(2, new int[][]{{1,0}}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static boolean canFinish(int numCourses, int[][] prerequisites) {
            Map<Integer, List<Integer>> mp = new HashMap<>();
            for (int[] pre : prerequisites) {
                if (!mp.containsKey(pre[0])) {
                    mp.put(pre[0], new ArrayList<>());
                }
                mp.get(pre[0]).add(pre[1]);
            }

            int[] bl = new int[numCourses];
            for (int[] pre : prerequisites) {
                int a = pre[0];
                if (bl[a] == 0) {
                    if (test(a, mp, bl)) {
                        return false;
                    }
                }
            }
            return true;
        }

        private static boolean test(int ind, Map<Integer, List<Integer>> mp, int[] bl) {
            if (bl[ind] == 1) {
                return true;
            }
            if (bl[ind] == 2) {
                return false;
            }
            bl[ind] = 1;

            if (mp.containsKey(ind)) {
                for (int i : mp.get(ind)) {
                    if (test(i, mp, bl)) {
                        return true;
                    }
                }
            }
            bl[ind] = 2;
            return false;
        }
    }

}
