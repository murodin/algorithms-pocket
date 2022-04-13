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
        System.out.println("Solution-I:");
        Arrays.stream(Solution_I.generateMatrix(matrixSize))
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

        System.out.println("Solution-II:");
        Arrays.stream(Solution_II.generateMatrix(matrixSize))
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

        System.out.println("Solution-III:");
        Arrays.stream(Solution_III.generateMatrix(matrixSize))
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

        System.out.println("Solution-IV:");
        Arrays.stream(Solution_IV.generateMatrix(matrixSize))
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
    static class Solution_I {
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

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static int[][] generateMatrix(int n) {
            var matrix = new int[n][n];

            for (int firstRow = 0, firstCol = 0, lastRow = n - 1, lastCol = n - 1, val = 1;
                 firstRow <= lastRow && firstCol <= lastCol;
                 firstCol++) {
                // first row
                for (var i = firstCol; i <= lastCol; matrix[firstRow][i++] = val++);
                // last col
                for (var i = ++firstRow; i <= lastRow; matrix[i++][lastCol] = val++);

                if (firstRow > lastRow || firstCol > --lastCol)
                    break;

                // last row
                for (var i = lastCol; i >= firstCol; matrix[lastRow][i--] = val++);
                // first col
                for (var i = --lastRow; i >= firstRow; matrix[i--][firstCol] = val++);
            }
            return matrix;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_III {
        static int[][] dir = {{0,1},{1,0},{0,-1},{-1,0}};
        public static int[][] generateMatrix(int n) {
            //The length and width of generated matrix will be nxn
            int[][] ans = new int[n][n];

            //Traverse through the matrix until we hit a block and chage directions
            int currDirection = 0;
            int xAxis = 0;
            int yAxis = 0;
            int count = 1;

            while(xAxis >= 0 && xAxis < n && yAxis >= 0 && yAxis < n && ans[yAxis][xAxis] == 0){
                //Assigns count and increments
                ans[yAxis][xAxis] = count++;
                int newX = xAxis + dir[currDirection][1];
                int newY = yAxis + dir[currDirection][0];
                //If either x or y gets blocked then change direction
                if(newX >= 0 && newX < n && newY >= 0 && newY < n && ans[newY][newX] == 0){
                    xAxis = newX;
                    yAxis = newY;
                }else{
                    //change directions and add
                    currDirection = (currDirection + 1) % 4;
                    xAxis += dir[currDirection][1];
                    yAxis += dir[currDirection][0];
                }
            }

            return ans;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_IV{
        public static int[][] generateMatrix(int n)  {
            int matrix[][] = new int[n][n];
            int index = 1;
            int row = 0,col = 0;
            int maxRow = n-1,maxCol = n-1;
            int minRow = 0,minCol = 0;

            while(index<=n*n) {
                for(col=minCol,row=minRow;col<=maxCol && index<=n*n;col++) {
                    matrix[row][col] = index++;
                }
                minRow++;

                for(row=minRow,col=maxCol;row<=maxRow  && index<=n*n;row++) {
                    matrix[row][col] = index++;
                }
                maxCol--;

                for(col=maxCol,row=maxRow;col>=minCol && index<=n*n;col--) {
                    matrix[row][col] = index++;
                }
                maxRow--;

                for(row=maxRow,col=minCol;row>=minRow && index<=n*n;row--) {
                    matrix[row][col] = index++;
                }
                minCol++;
            }
            return matrix;
        }
    }
}
