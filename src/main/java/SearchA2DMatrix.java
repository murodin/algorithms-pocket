public class SearchA2DMatrix {

    /*
        Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
        Integers in each row are sorted from left to right.
        The first integer of each row is greater than the last integer of the previous row.

        Example 1.
        Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
        Output: true
        Example 2.
        Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
        Output: false
     */

    public static void main(String[] args) {
        int[][] testArray = {{1, 2, 3}, {4, 6, 7}, {10, 25, 32}};
        System.out.println("Find the 25: " + Solution.searchMatrix(testArray, 25));
        System.out.println("Find the 15: " + Solution.searchMatrix(testArray, 15));
    }

    // Time: O(Log(rows*cols))
    // Space: O(1)
    static class Solution {
        public static boolean searchMatrix(int[][] n, int target) {
            if(n.length ==  0) return false;
            int rows = n.length, cols = n[0].length;

            int left = 0, right = rows*cols -1;
            while(left <= right) {
                int midPoint = right + (left - right)/2;
                int midPointElement = n[midPoint/cols][midPoint%cols];

                if(midPointElement == target) return true;
                else if(midPointElement > target) right = midPoint - 1;
                else left = midPoint + 1;
            }
            return false;
        }
    }
}
