import java.util.*;

public class KHighestRankedItemsWithinAPriceRange {
    /*
        You are given a 0-indexed 2D integer array grid of size m x n that represents a map of the items in a shop. The integers in the grid represent the following:
        0 represents a wall that you cannot pass through.
        1 represents an empty cell that you can freely move to and from.
        All other positive integers represent the price of an item in that cell. You may also freely move to and from these item cells.
        It takes 1 step to travel between adjacent grid cells.
        You are also given integer arrays pricing and start where pricing = [low, high] and start = [row, col] indicates that you start at the position (row, col) and are interested only in items with a price in the range of [low, high] (inclusive). You are further given an integer k.

        You are interested in the positions of the k highest-ranked items whose prices are within the given price range. The rank is determined by the first of these criteria that is different:

        Distance, defined as the length of the shortest path from the start (shorter distance has a higher rank).
        Price (lower price has a higher rank, but it must be in the price range).
        The row number (smaller row number has a higher rank).
        The column number (smaller column number has a higher rank).
        Return the k highest-ranked items within the price range sorted by their rank (highest to lowest). If there are fewer than k reachable items within the price range, return all of them.

        Example 1.
        Input: grid = [[1,2,0,1],[1,3,0,1],[0,2,5,1]], pricing = [2,5], start = [0,0], k = 3
        Output: [[0,1],[1,1],[2,1]]
        Explanation: You start at (0,0).
        With a price range of [2,5], we can take items from (0,1), (1,1), (2,1) and (2,2).
        The ranks of these items are:
        - (0,1) with distance 1
        - (1,1) with distance 2
        - (2,1) with distance 3
        - (2,2) with distance 4
        Thus, the 3 highest ranked items in the price range are (0,1), (1,1), and (2,1).
        Example 2.
        Input: grid = [[1,2,0,1],[1,3,3,1],[0,2,5,1]], pricing = [2,3], start = [2,3], k = 2
        Output: [[2,1],[1,2]]
        Explanation: You start at (2,3).
        With a price range of [2,3], we can take items from (0,1), (1,1), (1,2) and (2,1).
        The ranks of these items are:
        - (2,1) with distance 2, price 2
        - (1,2) with distance 2, price 3
        - (1,1) with distance 3
        - (0,1) with distance 4
        Thus, the 2 highest ranked items in the price range are (2,1) and (1,2).
        Example 3.
        Input: grid = [[1,1,1],[0,0,1],[2,3,4]], pricing = [2,3], start = [0,0], k = 3
        Output: [[2,1],[2,0]]
        Explanation: You start at (0,0).
        With a price range of [2,3], we can take items from (2,0) and (2,1).
        The ranks of these items are:
        - (2,1) with distance 5
        - (2,0) with distance 6
        Thus, the 2 highest ranked items in the price range are (2,1) and (2,0).
        Note that k = 3 but there are only 2 reachable items within the price range.


        Constraints:

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 105
        1 <= m * n <= 105
        0 <= grid[i][j] <= 105
        pricing.length == 2
        2 <= low <= high <= 105
        start.length == 2
        0 <= row <= m - 1
        0 <= col <= n - 1
        grid[row][col] > 0
        1 <= k <= m * n
     */

    public static void main(String[] args) {
       int[][] grid = {
               {1,1,1},
               {0,0,1},
               {2,3,4}
        };
       int[] pricing = {2,3};
       int[] start = {0,0};
       int k = 3;

        System.out.println("Solution: " + Solution.highestRankedKItems(grid, pricing, start, k));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        static class SolComparator implements Comparator<int[]> {
            public int compare(int[] a, int[] b) {
                return comparer(a, b, 0);
            }

            public int comparer(int[] a, int[] b, int i) {
                if(i == 4)
                    return 0;
                if(a[i] > b[i])
                    return 1;
                if(a[i] < b[i])
                    return -1;
                return comparer(a, b, i + 1);
            }

        }

        public static List<List<Integer>> highestRankedKItems(int[][] grid, int[] pricing, int[] start, int k) {
            int m = grid.length;
            int n = grid[0].length;

            boolean[][] visited = new boolean[m][n];

            PriorityQueue<int[]> pq = new PriorityQueue<>(new SolComparator());

            pq.add(new int[]{0, grid[start[0]][start[1]], start[0], start[1]});
            visited[start[0]][start[1]] = true;

            List<List<Integer>> ans = new ArrayList<>();

            while(!pq.isEmpty()) {
                int[] p = pq.poll();
                if(p[1] != 1) {
                    if(p[1] >= pricing[0] && p[1] <= pricing[1])
                        ans.add(Arrays.asList(p[2], p[3]));
                }
                if(ans.size() == k)
                    break;
                int r = p[2];
                int c = p[3];
                int currDist = p[0];

                if(r - 1 >= 0 && grid[r-1][c] != 0 && !visited[r-1][c]) {
                    visited[r-1][c] = true;
                    pq.add(new int[]{currDist + 1, grid[r-1][c], r - 1, c});
                }

                if(c - 1 >= 0 && grid[r][c-1] != 0 && !visited[r][c-1]) {
                    visited[r][c-1] = true;
                    pq.add(new int[]{currDist + 1, grid[r][c-1], r, c - 1});
                }

                if(r + 1 < m && grid[r+1][c] != 0 && !visited[r+1][c]) {
                    visited[r+1][c] = true;
                    pq.add(new int[]{currDist + 1, grid[r+1][c], r+1, c});
                }

                if(c + 1 < n && grid[r][c+1] != 0 && !visited[r][c+1]) {
                    visited[r][c+1] = true;
                    pq.add(new int[]{currDist + 1, grid[r][c+1], r, c + 1});
                }

            }
            return ans;
        }
    }
}
