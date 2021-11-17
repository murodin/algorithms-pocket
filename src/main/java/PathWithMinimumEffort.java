import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PathWithMinimumEffort {

    /*
        You are a hiker preparing for an upcoming hike.
        You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col).
        You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
        You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
        A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
        Return the minimum effort required to travel from the top-left cell to the bottom-right cell.

        Example 1.
        Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
        Output: 2
        Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
        This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
        Example 2.
        Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
        Output: 1
        Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
        Example 3.
        Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
        Output: 0
        Explanation: This route does not require any effort.
     */

    public static void main(String[] args) {
        int[][] heights = {
                    {1,2,2},
                    {3,8,2},
                    {5,3,5}
                };
        System.out.println("Solution I: " + Solution_I.minimumEffortPath(heights));
        System.out.println("Solution II: " + Solution_II.minimumEffortPath(heights));
    }

    // Dijkstra's
    // Time: O(MxNxLog(MxN))
    // Space: O(MxN)
    static class Solution_I {
        public static int minimumEffortPath(int[][] heights) {
            int m = heights.length;
            int n = heights[0].length;

            int[][] effort = new int[m][n];
            for(int i = 0; i < m; i++) Arrays.fill(effort[i], Integer.MAX_VALUE);

            //dist, row, col --> int[]
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a->a[0]));
            pq.offer(new int[]{0,0,0});

            int[][] dir = {{0,1}, {0,-1}, {1,0}, {-1,0}};
            while(!pq.isEmpty()){
                int[] min = pq.poll();
                int dist = min[0], row = min[1], col = min[2];
                if(dist > effort[row][col]) continue;
                if(row == m-1 && col == n-1) return dist;
                for(int[] d : dir){
                    int newRow = row+d[0];
                    int newCol = col+d[1];
                    if(newRow >= 0 && newRow < m && newCol >= 0 && newCol < n) {
                        int newDist = Math.max(dist, Math.abs(heights[newRow][newCol] - heights[row][col]));
                        if(newDist < effort[newRow][newCol]){
                            effort[newRow][newCol] = newDist;
                            pq.offer(new int[]{newDist, newRow, newCol});
                        }
                    }
                }
            }
            return 0;
        }
    }

    // Binary Search
    // Time: O((MxNxLog(end))
    // Space: O(MxN)
    static class Solution_II {
        static int[][] dir=new int[][]{{0,1},{1,0},{-1,0},{0,-1}};
        public static int minimumEffortPath(int[][] heights) {
            int start=0;
            int end =1000000;
            int m=heights.length;
            int n=heights[0].length;
            while(start<end){
                int mid=start+(end-start)/2;
                boolean[][] visited=new boolean[m][n];
                if(isPath(heights,0,0,visited,mid,heights[0][0])){
                    end=mid;
                }
                else{
                    start=mid+1;
                }
            }
            return start;
        }

        static boolean isPath(int[][] heights,int x,int y,boolean[][] visited,int k,int val){
            if(x<0||y<0||x>=heights.length||y>=heights[0].length||visited[x][y] || Math.abs(val-heights[x][y])>k){
                return false;
            }
            if(x==heights.length-1 && y==heights[0].length-1){
                return true;
            }
            visited[x][y]=true;
            for(int[] d:dir){
                if(isPath(heights,x+d[0],y+d[1],visited,k,heights[x][y])){
                    return true;
                }
            }

            return false;
        }
    }
}
