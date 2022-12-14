public class MinimumFallingPathSumII {
    /*
        Given an n x n integer matrix grid, return the minimum sum of a falling path with non-zero shifts.
        A falling path with non-zero shifts is a choice of exactly one element from each row of grid such that no two elements
        chosen in adjacent rows are in the same column.


        Example 1.
        Input: arr = [[1,2,3],[4,5,6],[7,8,9]]
        Output: 13
        Explanation:
        The possible falling paths are:
        [1,5,9], [1,5,7], [1,6,7], [1,6,8],
        [2,4,8], [2,4,9], [2,6,7], [2,6,8],
        [3,4,8], [3,4,9], [3,5,7], [3,5,9]
        The falling path with the smallest sum is [1,5,7], so the answer is 13.
        Example 2.
        Input: grid = [[7]]
        Output: 7


        Constraints:

        n == grid.length == grid[i].length
        1 <= n <= 200
        -99 <= grid[i][j] <= 99
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minFallingPathSum(new int[][]{{1,2,3},{4,5,6},{7,8,9}}));
    }

    // Time: O(MxN)
    // Space: O(1)
    static class Solution {
        public static int minFallingPathSum(int[][] grid) {
            int n = grid.length;
            int min = 100,secondMin = 100;
            // find min and secondMin element in the first row
            for(int j=0;j<n;++j){
                if(min > grid[0][j]){
                    secondMin = min;
                    min = grid[0][j];
                }
                else if(secondMin > grid[0][j]) secondMin = grid[0][j];
            }

            for(int i=1;i<n;++i){
                int t1 = Integer.MAX_VALUE,t2 = Integer.MAX_VALUE; // temp min,secondMin
                for(int j=0;j<n;++j){
                    grid[i][j] += grid[i-1][j] == min ? secondMin  : min;
                    if(t1 > grid[i][j]){
                        t2 = t1;
                        t1 = grid[i][j];
                    }
                    else if(t2 > grid[i][j]) t2 = grid[i][j];
                }
                min = t1; secondMin  = t2;
            }
            return min;
        }
    }
}
