public class MagicSquaresInGrid {
    /*
        A 3 x 3 magic square is a 3 x 3 grid filled with distinct numbers from 1 to 9 such that each row, column, and both diagonals all have the same sum.
        Given a row x col grid of integers, how many 3 x 3 "magic square" subgrids are there?  (Each subgrid is contiguous).

        Example 1:


        Input: grid = [[4,3,8,4],[9,5,1,9],[2,7,6,2]]
        Output: 1
        Explanation:
        The following subgrid is a 3 x 3 magic square:

        while this one is not:

        In total, there is only one magic square inside the given grid.
        Example 2:

        Input: grid = [[8]]
        Output: 0


        Constraints:

        row == grid.length
        col == grid[i].length
        1 <= row, col <= 10
        0 <= grid[i][j] <= 15
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numMagicSquaresInside(new int[][]{{4,3,8,4},{9,5,1,9},{2,7,6,2}}));
    }

    // Time: O(5^N)
    // Space: O(1)
    static class Solution {
        public static int numMagicSquaresInside(int[][] grid) {
            int cnt=0;
            for(int i=0;i<=grid.length-3;i++)
                for(int j=0;j<=grid[0].length-3;j++)
                    if(helper(i,j,grid)) cnt++;
            return cnt;
        }

        private static boolean helper(int x, int y, int[][] grid){
            if(grid[x+1][y+1]!=5) return false;
            int[] valid=new int[16];
            for(int i=x;i<=x+2;i++)
                for(int j=y;j<=y+2;j++)
                    valid[grid[i][j]]++;

            for (int v = 1; v <= 9; v++)
                if (valid[v] != 1) return false;

            if((grid[x][y]+grid[x][y+1]+grid[x][y+2])!=15)         return false;
            if((grid[x][y]+grid[x+1][y+1]+grid[x+2][y+2])!=15)     return false;
            if((grid[x][y]+grid[x+1][y]+grid[x+2][y])!=15)         return false;
            if((grid[x+2][y]+grid[x+2][y+1]+grid[x+2][y+2])!=15)   return false;
            if((grid[x][y+2]+grid[x+1][y+2]+grid[x+2][y+2])!=15)   return false;
            if((grid[x][y+2]+grid[x+1][y+1]+grid[x+2][y])!=15)     return false;
            return true;
        }
    }
}
