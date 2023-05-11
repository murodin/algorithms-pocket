import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    /*
        Given an m x n matrix, return all elements of the matrix in spiral order.

        Example 1.
        Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
        Output: [1,2,3,6,9,8,7,4,5]
        Example 2.
        Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
        Output: [1,2,3,4,8,12,11,10,9,5,6,7]


        Constraints:

        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 10
        -100 <= matrix[i][j] <= 100
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.spiralOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static List<Integer> spiralOrder(int[][] matrix) {
            List<Integer> arr=new ArrayList<>();
            int left=0, right=matrix[0].length;
            int top=0, bottom=matrix.length;

            while(left<right && bottom>top){
                for(int i=left;i<right;i++){
                    arr.add(matrix[top][i]);
                }
                top++;
                for(int i=top;i<bottom;i++){
                    arr.add(matrix[i][right-1]);
                }
                right--;
                if(!(left<right && bottom>top))
                    break;
                for(int i=right-1;i>left-1;i--){
                    arr.add(matrix[bottom-1][i]);
                }
                bottom--;
                for(int i=bottom-1;i>top-1;i--){
                    arr.add(matrix[i][left]);
                }
                left++;

            }
            return arr;
        }
    }
}
