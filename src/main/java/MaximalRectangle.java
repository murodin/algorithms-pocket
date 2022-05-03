import java.util.Stack;

public class MaximalRectangle {
    /*
        Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

        Example 1.
        Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
        Output: 6
        Explanation: The maximal rectangle is shown in the above picture.
        Example 2.
        Input: matrix = [["0"]]
        Output: 0
        Example 3:

        Input: matrix = [["1"]]
        Output: 1


        Constraints:

        rows == matrix.length
        cols == matrix[i].length
        1 <= row, cols <= 200
        matrix[i][j] is '0' or '1'.
     */

    public static void main(String[] args) {
        char [][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println("Maximal Rect - I: " + Solution_I.maximalRectangle(matrix));
        System.out.println("Maximal Rect - II: " + Solution_II.maximalRectangle(matrix));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution_I {
        public static int maximalRectangle(char[][] matrix) {
            if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

            int[] height = new int[matrix[0].length];
            for(int i = 0; i < matrix[0].length; i ++){
                if(matrix[0][i] == '1') height[i] = 1;
            }
            int result = largestInLine(height);
            for(int i = 1; i < matrix.length; i ++){
                resetHeight(matrix, height, i);
                result = Math.max(result, largestInLine(height));
            }

            return result;
        }

        static void resetHeight(char[][] matrix, int[] height, int idx){
            for(int i = 0; i < matrix[0].length; i ++){
                if(matrix[idx][i] == '1') height[i] += 1;
                else height[i] = 0;
            }
        }

        static int largestInLine(int[] height) {
            if(height == null || height.length == 0) return 0;
            int len = height.length;
            Stack<Integer> s = new Stack<Integer>();
            int maxArea = 0;
            for(int i = 0; i <= len; i++){
                int h = (i == len ? 0 : height[i]);
                if(s.isEmpty() || h >= height[s.peek()]){
                    s.push(i);
                } else{
                    int tp = s.pop();
                    maxArea = Math.max(maxArea, height[tp] * (s.isEmpty() ? i : i - 1 - s.peek()));
                    i--;
                }
            }
            return maxArea;
        }
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution_II {
        public static int maximalRectangle(char[][] matrix) {
            int maxarea = Integer.MIN_VALUE;
            int [][]matrix1 = new int[matrix.length][matrix[0].length];
            for(int i=0; i<matrix.length; i++) {
                int[] histogram = new int[matrix[0].length];
                for(int j=0;j<matrix[0].length;j++) {
                    if(i==0)
                        matrix1[i][j] = (matrix[i][j]=='1')?1:0;
                    else
                        matrix1[i][j] = (matrix[i][j]=='1')?1+(matrix1[i-1][j]):0;


                    histogram[j]=matrix1[i][j];

                }
                maxarea=Math.max(maxarea,findLargestRect(histogram));
            }

            return maxarea;
        }

        public static int findLargestRect(int[] heights) {
            Stack<Integer>stack=new Stack<Integer>();
            int maxarea=Integer.MIN_VALUE;
            int area=-1,top=-1;
            for(int i=0;i<heights.length;) {
                if(stack.isEmpty() || heights[stack.peek()]<=heights[i])
                    stack.push(i++);
                else {
                    top=stack.peek();
                    stack.pop();
                    if(stack.isEmpty())
                        area=heights[top]*i;
                    else
                        area=heights[top]*(i-stack.peek()-1);
                    maxarea=Math.max(area,maxarea);
                }

            }
            int i=heights.length;
            while(!stack.isEmpty()) {
                top=stack.peek();
                stack.pop();
                if(stack.isEmpty())
                    area=heights[top]*i;
                else
                    area=heights[top]*(i-stack.peek()-1);
                maxarea=Math.max(area,maxarea);
            }
            return maxarea;
        }
    }
}
