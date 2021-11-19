import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortTheMatrixDiagonally {

    /*
        A matrix diagonal is a diagonal line of cells starting from some cell in either the topmost row or leftmost column and
        going in the bottom-right direction until reaching the matrix's end.
        For example, the matrix diagonal starting from mat[2][0], where mat is a 6 x 3 matrix, includes cells mat[2][0], mat[3][1], and mat[4][2].
        Given an m x n matrix mat of integers, sort each matrix diagonal in ascending order and return the resulting matrix.

        Example 1.
        Input: mat = [[3,3,1,1],[2,2,1,2],[1,1,1,2]]
        Output: [[1,1,1,1],[1,2,2,2],[1,2,3,3]]
        Example 2.
        Input: mat = [[11,25,66,1,69,7],[23,55,17,45,15,52],[75,31,36,44,58,8],[22,27,33,25,68,4],[84,28,14,11,5,50]]
        Output: [[5,17,4,1,52,7],[11,11,25,45,8,69],[14,23,25,44,58,15],[22,27,31,36,50,66],[84,28,75,33,55,68]]
     */

    public static void main(String[] args) {
        int[][] mat = {
                    {11,25,66,1,69,7},
                    {23,55,17,45,15,52},
                    {75,31,36,44,58,8},
                    {22,27,33,25,68,4},
                    {84,28,14,11,5,50}
            };
        Solution_I.diagonalSort(mat);
        Arrays.stream(mat).forEach(a -> {
            System.out.println("Solution I : " + Arrays.toString(a));
        });

        Solution_II.diagonalSort(mat);
        Arrays.stream(mat).forEach(a -> {
            System.out.println("Solution II: " + Arrays.toString(a));
        });
    }

    // Time: O((M+N)*KLogK) where K=Min(M, N)
    // Space: O(K)
    static class Solution_I {
        public static int[][] diagonalSort(int[][] mat) {
            int m=mat.length;
            int n=mat[0].length;

            //row=0
            for(int col=0;col<n;col++){
                sort(mat,0,col,m,n);
            }

            //col=0
            for(int row=1;row<m;row++){
                sort(mat,row,0,m,n);
            }

            return mat;
        }

        static void sort(int[][] mat,int row,int col,int m,int n){
            List<Integer> values=new ArrayList<>();
            int r=row,c=col;
            while(r<m && c<n){
                values.add(mat[r][c]);
                r++;
                c++;
            }

            Collections.sort(values);

            r=row;
            c=col;
            int ind=0;
            while(r<m && c<n){
                mat[r][c]=values.get(ind++);
                r++;
                c++;
            }
        }
    }

    // Time: O((M+N)*K) where K=Min(M, N)
    // Space: O(1)
    static class Solution_II {
        public static int[][] diagonalSort(int[][] mat) {
            int m=mat.length;
            int n=mat[0].length;

            //row=0
            for(int col=0;col<n;col++){
                sort(mat,0,col,m,n);
            }

            //col=0
            for(int row=1;row<m;row++){
                sort(mat,row,0,m,n);
            }

            return mat;
        }

        static void sort(int[][] mat,int row,int col,int m,int n){
            int[] values=new int[101];
            int r=row,c=col;
            while(r<m && c<n){
                // values.add(mat[r][c]);
                values[mat[r][c]]++;
                r++;
                c++;
            }

            r=row;
            c=col;

            for(int i=1;i<101;i++){
                if(values[i]>0){
                    int count=values[i];
                    while(count-->0){
                        mat[r][c]=i;
                        r++;
                        c++;
                    }
                }
            }
        }
    }
}
