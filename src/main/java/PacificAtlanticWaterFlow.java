import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacificAtlanticWaterFlow {

    /*
        There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean.
        The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.
        The island is partitioned into a grid of square cells.
        You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

        The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west
        if the neighboring cell's height is less than or equal to the current cell's height.
        Water can flow from any cell adjacent to an ocean into the ocean.
        Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.

        Example 1.
        Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
        Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
        Example 2.
        Input: heights = [[2,1],[1,2]]
        Output: [[0,0],[0,1],[1,0],[1,1]]
     */

    public static void main(String[] args) {
        int[][] testHeights = {{1,2,2,3,5}, {3,2,3,4,4}, {2,4,5,3,1}, {6,7,1,4,5}, {5,1,1,2,4}};
        System.out.println("Result:" + Solution.pacificAtlantic(testHeights));
        System.out.println("Result:" + Solution_II.pacificAtlantic(testHeights));
    }

    //BruteForce
    // Time: O(4^N*N^2)
    // Space: O(N^2)
    static class Solution {
        static int m,n;
        static int[][] matrix;
        public static List<List<Integer>> pacificAtlantic(int[][] mat) {
            List<List<Integer>> result=new ArrayList<>();
            if(mat.length==0 || mat[0].length==0) return result;
            m=mat.length;
            n=mat[0].length;
            matrix=mat;
            for(int i=0;i<m;i++){
                for(int j=0;j<n;j++){
                    //If both flags are true, add in result
                    if(reachPacific(i,j) && reachAtlantic(i,j)){
                        result.add(Arrays.asList(i,j));
                    }
                }
            }
            return result;
        }

        static boolean reachPacific(int r, int c){
            if(r==0 || c==0) return true;
            if(matrix[r][c]==-1) return false;
            int val=matrix[r][c];
            matrix[r][c]=-1;
            boolean flag=false;

            //Call DFS in all 4 Directions
            if(matrix[r-1][c]<=val){
                flag=reachPacific(r-1,c);
            }

            if(!flag && matrix[r][c-1]<=val){
                flag = reachPacific(r,c-1);
            }

            if(!flag && c+1<n && matrix[r][c+1]<=val){
                flag = reachPacific(r,c+1);
            }

            if(!flag && r+1<m && matrix[r+1][c]<=val){
                flag = reachPacific(r+1,c);
            }
            matrix[r][c]=val;
            return flag;
        }

        static boolean reachAtlantic(int r, int c){
            if(r==m-1 || c==n-1) return true;
            if(matrix[r][c]==-1) return false;
            int val=matrix[r][c];
            matrix[r][c]=-1;
            boolean flag=false;

            //Call DFS in all 4 Directions
            if(matrix[r+1][c]<=val){
                flag=reachAtlantic(r+1,c);
            }
            if(!flag &&  matrix[r][c+1]<=val){
                flag = reachAtlantic(r,c+1);
            }
            if(!flag && c-1>=0 &&  matrix[r][c-1]<=val){
                flag = reachAtlantic(r,c-1);
            }
            if(!flag &&  r-1>=0 && matrix[r-1][c]<=val){
                flag = reachAtlantic(r-1,c);
            }
            matrix[r][c]=val;
            return flag;
        }
    }

    //Optimized
    // Time: O(4^(N/4) + M*N)
    // Space: O(M*N)
    static class Solution_II {
        static int dir[][] = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        public static List<List<Integer>> pacificAtlantic(int[][] matrix) {
            List<List<Integer>> res = new ArrayList<>();
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
                return res;

            int row = matrix.length, col = matrix[0].length;
            boolean[][] pacific = new boolean[row][col];
            boolean[][] atlantic = new boolean[row][col];

            //DFS
            for(int i = 0; i < col; i++){
                dfs(matrix, 0, i, Integer.MIN_VALUE, pacific);
                dfs(matrix, row-1, i, Integer.MIN_VALUE, atlantic);
            }
            for(int i = 0; i < row; i++){
                dfs(matrix, i, 0, Integer.MIN_VALUE, pacific);
                dfs(matrix, i, col-1, Integer.MIN_VALUE, atlantic);
            }

            //preparing the result
            for(int i = 0; i < row; i++){
                for(int j = 0; j < col; j++) {
                    if(pacific[i][j] && atlantic[i][j]) {
                        res.add(Arrays.asList(i,j));
                    }
                }
            }

            return res;
        }

        public static void dfs(int[][] matrix, int i, int j, int prev, boolean[][] ocean){
            if(i < 0 || i >= ocean.length || j < 0 || j >= ocean[0].length) return;
            if(matrix[i][j] < prev || ocean[i][j]) return;
            ocean[i][j] = true;
            for(int[] d : dir){
                dfs(matrix, i+d[0], j+d[1], matrix[i][j], ocean);
            }
        }
    }
}
