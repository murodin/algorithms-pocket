import java.util.Arrays;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class MaxSumOfRectangleNoLargerThanK {

    /*
        Given an m x n matrix and an integer k, return the max sum of a rectangle in the matrix such that its sum is no larger than k.
        It is guaranteed that there will be a rectangle with a sum no larger than k.

        Example 1.
        Input: matrix = [[1,0,1],[0,-2,3]], k = 2
        Output: 2
        Explanation: Because the sum of the blue rectangle [[0, 1], [-2, 3]] is 2, and 2 is the max number no larger than k (k = 2).
        Example 2.
        Input: matrix = [[2,2,-1]], k = 3
        Output: 3


        Constraints:

        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 100
        -100 <= matrix[i][j] <= 100
        -105 <= k <= 105


        Follow up: What if the number of rows is much larger than the number of columns?
     */

    public static void main(String[] args) {
        int[][] matrix =  {{2,2,-1}};
        System.out.println("Solution: " + Solution.maxSumSubmatrix(matrix, 2));
    }

    // Time: (Min(M, N))^2 * Max(M, N) * log(Max(M, N))
    // Space: O(MxN)
    static class Solution {
        public static int maxSumSubmatrix(int[][] matrix, int k) {
            //transpose the matrix, if its number of row is greater than colomn
            if(matrix.length > matrix[0].length)
                matrix = transpose(matrix);

            return  getMaxSumSubmatrix(matrix, k);
        }

        private static int getMaxSumSubmatrix(int[][] matrix, int k){
            int rows = matrix.length;
            int cols = matrix[0].length;

            //maxSum
            AtomicInteger maxSum = new AtomicInteger(Integer.MIN_VALUE);

            int[] rowSumForEachCol = new int[cols];

            for(int startRow = 0; startRow < rows; startRow++){
                Arrays.fill(rowSumForEachCol, 0);

                //add each column starting from startRow to last row,
                for(int row = startRow; row < rows; row++){
                    for(int col = 0; col < cols; col++){

                        //append the current column value in rowSumForEachCol[col]
                        rowSumForEachCol[col] += matrix[row][col];
                    }
                    //now update max subarray matrix sum
                    solve(rowSumForEachCol, k, maxSum);
                    //best result found, no need to check further
                    if(maxSum.get() == k) return maxSum.get();
                }
            }

            return maxSum.get();
        }

        private static void solve(int[] nums, int k, AtomicInteger maxSum){
            int maxSubArraySum = getMaxSubSumUsingKadans(nums);
            //best sub array found, not need to check further
            if(maxSubArraySum <= k){
                maxSum.set(Math.max(maxSum.get(), maxSubArraySum));
                return;
            }

            int sum = 0;
            //sorted sum of
            TreeSet<Integer> sortedSum = new TreeSet();
            sortedSum.add(0);

            for(int num : nums){
                sum += num;
                //find the x, which is >= (sum - k) from the previously sorted prefix sum
                Integer x = sortedSum.ceiling(sum - k);
                if(x != null){
                    //update the max sub array
                    maxSum.set(Math.max(maxSum.get(), sum - x));
                }
                //add current prefix sum
                sortedSum.add(sum);
            }
        }

        private static int getMaxSubSumUsingKadans(int[] nums){
            int maxSum = Integer.MIN_VALUE;
            int sum = 0;
            for(int num : nums){
                sum = Math.max(num, sum + num);
                maxSum = Math.max(maxSum, sum);
            }

            return maxSum;
        }

        private static int[][] transpose(int[][] matrix){
            //this method return the transpose of matrix
            int rows = matrix.length;
            int cols = matrix[0].length;
            int[][] newMatrix = new int[cols][rows];

            for(int i = 0; i < rows; i++){
                for(int j = 0; j < cols; j++){
                    newMatrix[j][i] = matrix[i][j];
                }
            }
            return newMatrix;
        }
    }
}
