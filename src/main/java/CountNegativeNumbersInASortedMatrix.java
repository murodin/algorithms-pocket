public class CountNegativeNumbersInASortedMatrix {
    /*
        Given a m x n matrix grid which is sorted in non-increasing order both row-wise and column-wise, return the number of negative numbers in grid.
        Example 1:

        Input: grid = [[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]
        Output: 8
        Explanation: There are 8 negatives number in the matrix.
        Example 2:

        Input: grid = [[3,2],[1,0]]
        Output: 0


        Constraints:

        m == grid.length
        n == grid[i].length
        1 <= m, n <= 100
        -100 <= grid[i][j] <= 100


        Follow up: Could you find an O(n + m) solution?
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.countNegatives(new int[][]{{3,2}, {1, 0}}));
    }

    // Time: O(M+N)
    // Space: O(N)
    static class Solution {
        public static int countNegatives(int[][] grid) {
            int rows = grid.length;
            int columns = grid[0].length;
            int i = rows - 1;
            int j = 0;

            int result = 0;
            while(i>=0 && j<columns) {
                if(grid[i][j]<0) {
                    i--;
                    result += columns - j;
                } else {
                    j++;
                }
            }
            return result;
        }
    }
}
