public class PaintHouseIII {
    /*
        There is a row of m houses in a small city, each house must be painted with one of the n colors (labeled from 1 to n),
        some houses that have been painted last summer should not be painted again.
        A neighborhood is a maximal group of continuous houses that are painted with the same color.

        For example: houses = [1,2,2,3,3,2,1,1] contains 5 neighborhoods [{1}, {2,2}, {3,3}, {2}, {1,1}].
        Given an array houses, an m x n matrix cost and an integer target where:

        houses[i]: is the color of the house i, and 0 if the house is not painted yet.
        cost[i][j]: is the cost of paint the house i with the color j + 1.
        Return the minimum cost of painting all the remaining houses in such a way that there are exactly target neighborhoods. If it is not possible, return -1.

        Example 1.
        Input: houses = [0,0,0,0,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
        Output: 9
        Explanation: Paint houses of this way [1,2,2,1,1]
        This array contains target = 3 neighborhoods, [{1}, {2,2}, {1,1}].
        Cost of paint all houses (1 + 1 + 1 + 1 + 5) = 9.
        Example 2.
        Input: houses = [0,2,1,2,0], cost = [[1,10],[10,1],[10,1],[1,10],[5,1]], m = 5, n = 2, target = 3
        Output: 11
        Explanation: Some houses are already painted, Paint the houses of this way [2,2,1,2,2]
        This array contains target = 3 neighborhoods, [{2,2}, {1}, {2,2}].
        Cost of paint the first and last house (10 + 1) = 11.
        Example 3.
        Input: houses = [3,1,2,3], cost = [[1,1,1],[1,1,1],[1,1,1],[1,1,1]], m = 4, n = 3, target = 3
        Output: -1
        Explanation: Houses are already painted with a total of 4 neighborhoods [{3},{1},{2},{3}] different of target = 3.


        Constraints:

        m == houses.length == cost.length
        n == cost[i].length
        1 <= m <= 100
        1 <= n <= 20
        1 <= target <= m
        0 <= houses[i] <= n
        1 <= cost[i][j] <= 104
     */

    public static void main(String[] args) {
        int[] houses = {3,1,2,3};
        int[][] cost = {{1,1,1},{1,1,1},{1,1,1},{1,1,1}};
        int m = 4, n = 3, target = 3;
        System.out.println("Solution: " + Solution.minCost(houses, cost, m, n, target));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        private static Integer[][][] cache = new Integer[101][21][101]; // memorization
        private static int MAX = (int)1e8;
        private static int m, n;

        public static int minCost(int[] houses, int[][] cost, int m, int n, int target) {
            Solution.m = m;
            Solution.n = n;
            int res = dfs(houses, cost, 0, 0, target);
            return res >= MAX ? -1 : res;
        }

        private static int dfs(int[] houses, int[][] cost, int houseIdx, int prevColor, int target) {
            // you can't use target <= 0 since when target equals 0, houseIdx may not be m
            if (houseIdx == m || target < 0)
                return target == 0 ? 0 : MAX;

            if (cache[houseIdx][prevColor][target] != null)
                return cache[houseIdx][prevColor][target];

            if (houses[houseIdx] != 0) { // already painted
                int curColor = houses[houseIdx];
                // don't need to add cost of paint
                cache[houseIdx][prevColor][target] = dfs(houses, cost, houseIdx + 1, curColor, target - (prevColor == curColor ? 0 : 1));
                return cache[houseIdx][prevColor][target];
            }

            int res = MAX;
            // try every color and find the minimum cost
            for (int curColor = 1; curColor <= n; curColor++) {
                // need to add cost of paint
                res = Math.min(res, dfs(houses, cost, houseIdx + 1, curColor, target - (prevColor == curColor ? 0 : 1)) + cost[houseIdx][curColor - 1]);
            }
            cache[houseIdx][prevColor][target] = res;
            return res;
        }
    }
}
