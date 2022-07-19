import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    /*
        Given an integer numRows, return the first numRows of Pascal's triangle.
        In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:

        Example 1.
        Input: numRows = 5
        Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
        Example 2.
        Input: numRows = 1
        Output: [[1]]


        Constraints:

        1 <= numRows <= 30
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.generate(5));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static List<List<Integer>> generate(int numRows) {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            List<Integer> row, pre = null;
            for (int i = 0; i < numRows; ++i) {
                row = new ArrayList<Integer>();
                for (int j = 0; j <= i; ++j)
                    if (j == 0 || j == i)
                        row.add(1);
                    else {
                        assert pre != null;
                        row.add(pre.get(j - 1) + pre.get(j));
                    }
                pre = row;
                res.add(row);
            }
            return res;
        }
    }
}
