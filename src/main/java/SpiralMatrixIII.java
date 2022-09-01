import java.util.Arrays;

public class SpiralMatrixIII {
    /*
        You start at the cell (rStart, cStart) of an rows x cols grid facing east.
        The northwest corner is at the first row and column in the grid, and the southeast corner is at the last row and column.
        You will walk in a clockwise spiral shape to visit every position in this grid.
         Whenever you move outside the grid's boundary, we continue our walk outside the grid (but may return to the grid boundary later.).
         Eventually, we reach all rows * cols spaces of the grid.
         Return an array of coordinates representing the positions of the grid in the order you visited them.

        Example 1.
        Input: rows = 1, cols = 4, rStart = 0, cStart = 0
        Output: [[0,0],[0,1],[0,2],[0,3]]
        Example 2.
        Input: rows = 5, cols = 6, rStart = 1, cStart = 4
        Output: [[1,4],[1,5],[2,5],[2,4],[2,3],[1,3],[0,3],[0,4],[0,5],[3,5],[3,4],[3,3],[3,2],[2,2],[1,2],[0,2],[4,5],[4,4],[4,3],[4,2],[4,1],[3,1],[2,1],[1,1],[0,1],[4,0],[3,0],[2,0],[1,0],[0,0]]


        Constraints:

        1 <= rows, cols <= 100
        0 <= rStart < rows
        0 <= cStart < cols
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.deepToString(Solution.spiralMatrixIII(1, 4, 0, 0)));
    }

    // Time:
    // Space:
    static class Solution {
        public static int[][] spiralMatrixIII(int rows, int cols, int rs, int cs) {
            int top=rs,down=rs,left=cs,right=cs,dir=0;
            int[][] ans =new int[rows*cols][2];
            int c=1;
            ans[0][0]=rs;
            ans[0][1]=cs;
            while(c<rows*cols){
                if(dir==0){
                    for(int i=left+1;i<=right+1;i++){
                        if(i<cols && i>=0 && top<rows && top>=0){
                            ans[c][0]=top;
                            ans[c][1]=i;
                            c++;
                            if(c>=rows*cols)break;
                        }
                    }
                    right+=1;
                } else if(dir==1){
                    for(int i=top+1;i<=down+1;i++){
                        if(i<rows && i>=0&& right<cols&& right>=0){
                            ans[c][0]=i;
                            ans[c][1]=right;
                            c++;
                            if(c>=rows*cols)break;
                        }
                    }
                    down+=1;
                } else if(dir==2){
                    for(int i=right-1;i>=left-1;i--){
                        if(i<cols && i>=0&& down<rows&& down>=0){
                            ans[c][0]=down;
                            ans[c][1]=i;
                            c++;
                            if(c>=rows*cols)break;
                        }
                    }
                    left-=1;
                } else if(dir==3){
                    for(int i=down-1;i>=top-1;i--){
                        if(i<rows && i>=0 && left<cols&& left>=0){
                            ans[c][0]=i;
                            ans[c][1]=left;
                            c++;
                            if(c>=rows*cols)break;
                        }
                    }
                    top-=1;
                }
                dir=(dir+1)%4;
            }
            return ans;
        }
    }
}
