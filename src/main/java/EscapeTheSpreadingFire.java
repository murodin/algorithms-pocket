import java.util.LinkedList;
import java.util.Queue;

public class EscapeTheSpreadingFire {
    /*
        You are given a 0-indexed 2D integer array grid of size m x n which represents a field. Each cell has one of three values:
        0 represents grass,
        1 represents fire,
        2 represents a wall that you and fire cannot pass through.
        You are situated in the top-left cell, (0, 0), and you want to travel to the safehouse at the bottom-right cell, (m - 1, n - 1).
        Every minute, you may move to an adjacent grass cell. After your move, every fire cell will spread to all adjacent cells that are not walls.
        Return the maximum number of minutes that you can stay in your initial position before moving while still safely reaching the safehouse.
        If this is impossible, return -1. If you can always reach the safehouse regardless of the minutes stayed, return 109.
        Note that even if the fire spreads to the safehouse immediately after you have reached it, it will be counted as safely reaching the safehouse.
        A cell is adjacent to another cell if the former is directly north, east, south, or west of the latter (i.e., their sides are touching).

        Example 1.
        Input: grid = [[0,2,0,0,0,0,0],[0,0,0,2,2,1,0],[0,2,0,0,1,2,0],[0,0,2,2,2,0,2],[0,0,0,0,0,0,0]]
        Output: 3
        Explanation: The figure above shows the scenario where you stay in the initial position for 3 minutes.
        You will still be able to safely reach the safehouse.
        Staying for more than 3 minutes will not allow you to safely reach the safehouse.
        Example 2.
        Input: grid = [[0,0,0,0],[0,1,2,0],[0,2,0,0]]
        Output: -1
        Explanation: The figure above shows the scenario where you immediately move towards the safehouse.
        Fire will spread to any cell you move towards and it is impossible to safely reach the safehouse.
        Thus, -1 is returned.
        Example 3.
        Input: grid = [[0,0,0],[2,2,0],[1,2,0]]
        Output: 1000000000
        Explanation: The figure above shows the initial grid.
        Notice that the fire is contained by walls and you will always be able to safely reach the safehouse.
        Thus, 109 is returned.


        Constraints:

        m == grid.length
        n == grid[i].length
        2 <= m, n <= 300
        4 <= m * n <= 2 * 104
        grid[i][j] is either 0, 1, or 2.
        grid[0][0] == grid[m - 1][n - 1] == 0
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maximumMinutes(new int[][]{{0,0,0},{2,2,0},{1,2,0}}));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        static int m,n;
        public static int maximumMinutes(int[][] grid) {
            m=grid.length;
            n=grid[0].length;
            int [][]fire = new int[grid.length][grid[0].length];
            int [][]people = new int[grid.length][grid[0].length];
            bfsFire(fire,grid);
            bfsPeople(people,grid);
            int firetime = fire[m-1][n-1];
            int peopletime = people[m-1][n-1];
            if(peopletime==0)
                return -1;
            if(firetime<0)
                return (int)1e9;

            int diff = firetime-peopletime;
            if(diff<0)
                return -1;
            int northdiff = fire[m-2][n-1] - people[m-2][n-1];
            int westdiff = fire[m-1][n-2] - people[m-1][n-2];
            if(northdiff > diff || westdiff > diff)
                return diff;
            return diff-1;

        }
        static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
        static void bfsFire(int[][] fire, int[][] grid){
            Queue<int[]> queue = new LinkedList<>();
            for(int i=0;i<m;i++)
                for(int j=0;j<n;j++){
                    fire[i][j]=-1;
                    if(grid[i][j]==1){
                        queue.offer(new int[]{i,j});
                        fire[i][j]=0;
                    }

                }
            int min=1;
            while(!queue.isEmpty()){
                int size = queue.size();
                while(size-->0){
                    int coor[] = queue.poll();
                    int row = coor[0];
                    int col = coor[1];
                    for(int i=0;i<4;i++){
                        int rowdash = row+dir[i][0];
                        int coldash = col+dir[i][1];

                        if(rowdash<0||rowdash>=m||coldash<0||coldash>=n||fire[rowdash][coldash]>=0
                                ||grid[rowdash][coldash]==2)
                            continue;
                        fire[rowdash][coldash] = min;
                        queue.offer(new int[]{rowdash,coldash});
                    }
                }
                min++;
            }
        }
        static void bfsPeople(int[][] people, int[][] grid){
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0,0});
            int min=1;
            while(!queue.isEmpty()){
                int size = queue.size();
                while(size-->0){
                    int coor[] = queue.poll();
                    int row = coor[0];
                    int col = coor[1];
                    for(int i=0;i<4;i++){
                        int rowdash = row+dir[i][0];
                        int coldash = col+dir[i][1];

                        if(rowdash<0||rowdash>=m||coldash<0||coldash>=n||people[rowdash][coldash]>0
                                ||grid[rowdash][coldash]==2)
                            continue;
                        people[rowdash][coldash] = min;
                        queue.offer(new int[]{rowdash,coldash});
                    }
                }
                min++;
            }
        }
    }

}
