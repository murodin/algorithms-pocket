import java.util.Arrays;

public class ParallelCoursesII {
    /*
        You are given an integer n, which indicates that there are n courses labeled from 1 to n.
        You are also given an array relations where relations[i] = [prevCoursei, nextCoursei], representing a prerequisite relationship
         between course prevCoursei and course nextCoursei: course prevCoursei has to be taken before course nextCoursei. Also, you are given the integer k.
        In one semester, you can take at most k courses as long as you have taken all the prerequisites in the previous semesters for the courses you are taking.
        Return the minimum number of semesters needed to take all courses. The testcases will be generated such that it is possible to take every course.

        Example 1.
        Input: n = 4, relations = [[2,1],[3,1],[1,4]], k = 2
        Output: 3
        Explanation: The figure above represents the given graph.
        In the first semester, you can take courses 2 and 3.
        In the second semester, you can take course 1.
        In the third semester, you can take course 4.
        Example 2.
        Input: n = 5, relations = [[2,1],[3,1],[4,1],[1,5]], k = 2
        Output: 4
        Explanation: The figure above represents the given graph.
        In the first semester, you can only take courses 2 and 3 since you cannot take more than two per semester.
        In the second semester, you can take course 4.
        In the third semester, you can take course 1.
        In the fourth semester, you can take course 5.


        Constraints:

        1 <= n <= 15
        1 <= k <= n
        0 <= relations.length <= n * (n-1) / 2
        relations[i].length == 2
        1 <= prevCoursei, nextCoursei <= n
        prevCoursei != nextCoursei
        All the pairs [prevCoursei, nextCoursei] are unique.
        The given graph is a directed acyclic graph.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minNumberOfSemesters(5, new int[][] {{2,1},{3,1},{4,1},{1,5}}, 2));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int minNumberOfSemesters(int n, int[][] relations, int k) {
            int[] prereq = new int[n];
            for (int[] req : relations) {
                prereq[req[1] - 1] |= 1 << (req[0] - 1);
            }
            int[] dp = new int[(1 << n)];
            Arrays.fill(dp, -1);
            return solve(0, n, k, prereq, dp);
        }

        private static int solve(int mask, int n, int k, int[] prereq, int[] dp) {
            if (mask == (1 << n) - 1) {
                return 0;
            }
            if (dp[mask] != -1) {
                return dp[mask];
            }
            int availableCourses = 0;
            for (int i = 0; i < n; i += 1) {
                if ((mask & prereq[i]) == prereq[i]) {
                    if( (mask & (1<<i))>0 )
                        continue;
                    availableCourses |= 1 << i;
                }
            }
            int best = Integer.MAX_VALUE / 2;
            for (int submask = availableCourses; submask > 0; submask = (submask - 1) & availableCourses) {
                if (Integer.bitCount(submask) <= k) {
                    best = Math.min(best, 1 + solve(mask | submask, n, k, prereq, dp));
                }
            }
            return dp[mask] = best;
        }
    }
}
