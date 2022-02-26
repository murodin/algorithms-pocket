import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SpiralMatrixII {

    /*
        Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

        Example 1.
        Input: n = 3
        Output: [[1,2,3],[8,9,4],[7,6,5]]
        Example 2.
        Input: n = 1
        Output: [[1]]

        Constraints:

        1 <= n <= 20
     */

    public static void main(String[] args) {
        int matrixSize = 3;
        AtomicInteger i = new AtomicInteger(0);
        Arrays.stream(Solution.generateMatrix(matrixSize))
                        .flatMapToInt(Arrays::stream)
                        .forEach(x -> {
                            if(i.get() == matrixSize - 1) {
                                System.out.println(x + " ");
                                i.set(0);
                            } else {
                                System.out.print(x + " ");
                                i.getAndIncrement();
                            }
                        });
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static int[][] generateMatrix(int n) {
            int c1 = 0, c2 = n-1;
            int r1 = 0, r2 = n-1;

            int[][] arr = new int[n][n];
            int val = 1;

            while (r1<=r2 && c1<=c2) {
                // move left to right
                for(int c=c1; c<=c2; c++) arr[r1][c] = val++;

                // move up to down
                for(int r=r1+1; r<=r2; r++) arr[r][c2] = val++;

                if(r1< r2 && c1 < c2) {
                    // move right to left
                    for(int c=c2-1; c>c1; c--) arr[r2][c] = val++;

                    // move down to up
                    for(int r=r2; r>r1; r--) arr[r][c1] = val++;
                }
                r1++;
                r2--;
                c1++;
                c2--;
            }

            return arr;
        }
    }
}
