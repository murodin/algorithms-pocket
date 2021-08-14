public class RotateImage {

    /*
        You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
        You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

        Example.
        Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
        Output: [[7,4,1],[8,5,2],[9,6,3]]
     */

    public static void main(String[] args) {
        int[][] testImage = {{1,2,3}, {4,5,6}, {7,8,9}};
        Solution.rotate(testImage);
        System.out.println("Rotate Image: " + testImage);
    }

    static class Solution {
        // Time: O(N*N)
        // Space: O(1)
        public static void rotate(int[][] matrix) {
            int n = matrix.length;
            // Transpose
            for(int i = 0; i < n; i++) {
                for(int j = i; j < n; j++) {
                    // Swap the matrix[i][j] with matrix[j][i]
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }

            // Reverse the rows
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n/2; j++) {
                    // Swap the matrix[i][j] with matrix[i][n-j-1]
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[i][n-j-1];
                    matrix[i][n-j-1] = temp;
                }
            }
        }

        // Time: O(N*N)
        // Space: O(1)
        public static void rotate_II(int[][] matrix) {
            int n = matrix.length;
            int layers = n/2;

            for(int layer = 0; layer < layers; layer++) {
                int start = layer;
                int end = n-1-layer;

                for(int i = start; i < end; i++) {

                    //top in temp
                    int temp = matrix[start][i];
                    //left in top
                    matrix[start][i] = matrix[n-1-i][start];
                    //bottom in left
                    matrix[n-1-i][start] = matrix[end][n-1-i];
                    //right in bottom
                    matrix[end][n-1-i] = matrix[i][end];
                    //top in right
                    matrix[i][end] = temp;
                }
            }
        }
    }
}
