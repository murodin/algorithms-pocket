import java.util.*;

public class MaximumRowsCoveredByColumns {
    /*
        You are given a 0-indexed m x n binary matrix matrix and an integer numSelect, which denotes the number of distinct columns you must select from matrix.
        Let us consider s = {c1, c2, ...., cnumSelect} as the set of columns selected by you. A row row is covered by s if:
        For each cell matrix[row][col] (0 <= col <= n - 1) where matrix[row][col] == 1, col is present in s or,
        No cell in row has a value of 1.
        You need to choose numSelect columns such that the number of rows that are covered is maximized.

        Return the maximum number of rows that can be covered by a set of numSelect columns.

        Example 1:
        Input: matrix = [[0,0,0],[1,0,1],[0,1,1],[0,0,1]], numSelect = 2
        Output: 3
        Explanation: One possible way to cover 3 rows is shown in the diagram above.
        We choose s = {0, 2}.
        - Row 0 is covered because it has no occurrences of 1.
        - Row 1 is covered because the columns with value 1, i.e. 0 and 2 are present in s.
        - Row 2 is not covered because matrix[2][1] == 1 but 1 is not present in s.
        - Row 3 is covered because matrix[2][2] == 1 and 2 is present in s.
        Thus, we can cover three rows.
        Note that s = {1, 2} will also cover 3 rows, but it can be shown that no more than three rows can be covered.
        Example 2:
        Input: matrix = [[1],[0]], numSelect = 1
        Output: 2
        Explanation: Selecting the only column will result in both rows being covered since the entire matrix is selected.
        Therefore, we return 2.


        Constraints:

        m == matrix.length
        n == matrix[i].length
        1 <= m, n <= 12
        matrix[i][j] is either 0 or 1.
        1 <= numSelect <= n
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maximumRows(new int[][]{{1}, {0}}, 1));
    }

    // Time: O(2^N)
    // Space: O(N)
    static class Solution {
        static int result;
        public static int maximumRows(int[][] mat, int cols) {
            int m = mat.length;
            int n = mat[0].length;
            result = -1;
            if(cols == n) return m;
            Map<Integer, Set<Integer>> columnIndexToRowHavingOnes = new HashMap<>();
            for(int i = 0; i < n; i++) columnIndexToRowHavingOnes.put(i, new HashSet<>());
            for(int i = 0; i < m; i++){
                for(int j = 0; j < n; j++){
                    if(mat[i][j] == 1) {
                        Set<Integer> set = columnIndexToRowHavingOnes.get(j);
                        set.add(i);
                        columnIndexToRowHavingOnes.put(j, set);
                    }
                }
            }
            getAnswer(0, new ArrayList<Integer>(), cols, columnIndexToRowHavingOnes, m, n);
            return result;
        }

        private static void getAnswer(int index, ArrayList<Integer> colChoosen, int cols, Map<Integer, Set<Integer>> columnIndexToRowHavingOnes, int m, int n){
            if(colChoosen.size() == cols){
                Set<Integer> rowNotCoveredByChoosenColumns = new HashSet<>();
                for(int i = 0; i < n; i++){
                    if(!colChoosen.contains(i)){
                        rowNotCoveredByChoosenColumns.addAll(columnIndexToRowHavingOnes.get(i));
                    }
                }
                result = Math.max(result, m - rowNotCoveredByChoosenColumns.size());
                return;
            }

            if(index == n) return;
            colChoosen.add(index);
            getAnswer(index + 1, colChoosen, cols, columnIndexToRowHavingOnes, m, n);
            colChoosen.remove(colChoosen.size() - 1);
            getAnswer(index + 1, colChoosen, cols, columnIndexToRowHavingOnes, m, n);

        }
    }
}
