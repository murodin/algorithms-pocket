import java.util.Arrays;

public class SpiralMatrixIV {
    /*
        You are given two integers m and n, which represent the dimensions of a matrix.

        You are also given the head of a linked list of integers.

        Generate an m x n matrix that contains the integers in the linked list presented in spiral order (clockwise), starting from the top-left of the matrix.
        If there are remaining empty spaces, fill them with -1.

        Return the generated matrix.



        Example 1:


        Input: m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
        Output: [[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
        Explanation: The diagram above shows how the values are printed in the matrix.
        Note that the remaining spaces in the matrix are filled with -1.
        Example 2:


        Input: m = 1, n = 4, head = [0,1,2]
        Output: [[0,1,2,-1]]
        Explanation: The diagram above shows how the values are printed from left to right in the matrix.
        The last space in the matrix is set to -1.


        Constraints:

        1 <= m, n <= 105
        1 <= m * n <= 105
        The number of nodes in the list is in the range [1, m * n].
        0 <= Node.val <= 1000
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    // Time: O(N^2)
    // Space: O(N^2)
    /**
     * Definition for singly-linked list.
     **/
    public class ListNode {
         int val;
         ListNode next;
         ListNode() {}
         ListNode(int val) { this.val = val; }
         ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    static class Solution {
        public static int[][] spiralMatrix(int m, int n, ListNode head) {
            int[][] arr = new int[m][n];
            for(int[] row: arr)   Arrays.fill(row,-1);
            int top = 0, left = 0, right = n-1, bottom = m-1;
            while(head != null){
                for(int i=left; i<=right && head != null; i++){
                    arr[top][i] = head.val;
                    head = head.next;
                }
                top++;
                for(int i=top; i<=bottom && head != null; i++){
                    arr[i][right] = head.val;
                    head = head.next;
                }
                right--;
                for(int i=right; i>=left && head != null; i--){
                    arr[bottom][i] = head.val;
                    head = head.next;
                }
                bottom--;
                for(int i=bottom; i>=top && head != null; i--){
                    arr[i][left] = head.val;
                    head = head.next;
                }
                left++;
            }
            return arr;
        }
    }
}
