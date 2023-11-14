import java.util.Arrays;

public class ExecutionOfAllSuffixInstructionsStayingInAGrid {
    /*
        There is an n x n grid, with the top-left cell at (0, 0) and the bottom-right cell at (n - 1, n - 1).
        You are given the integer n and an integer array startPos where startPos = [startrow, startcol] indicates that a robot is initially at cell (startrow, startcol).

        You are also given a 0-indexed string s of length m where s[i] is the ith instruction for the robot: 'L' (move left), 'R' (move right), 'U' (move up),
        and 'D' (move down).

        The robot can begin executing from any ith instruction in s. It executes the instructions one by one towards the end of s but it stops
        if either of these conditions is met:

        The next instruction will move the robot off the grid.
        There are no more instructions left to execute.
        Return an array answer of length m where answer[i] is the number of instructions the robot can execute if the robot begins executing from the ith instruction in s.



        Example 1:


        Input: n = 3, startPos = [0,1], s = "RRDDLU"
        Output: [1,5,4,3,1,0]
        Explanation: Starting from startPos and beginning execution from the ith instruction:
        - 0th: "RRDDLU". Only one instruction "R" can be executed before it moves off the grid.
        - 1st:  "RDDLU". All five instructions can be executed while it stays in the grid and ends at (1, 1).
        - 2nd:   "DDLU". All four instructions can be executed while it stays in the grid and ends at (1, 0).
        - 3rd:    "DLU". All three instructions can be executed while it stays in the grid and ends at (0, 0).
        - 4th:     "LU". Only one instruction "L" can be executed before it moves off the grid.
        - 5th:      "U". If moving up, it would move off the grid.
        Example 2:


        Input: n = 2, startPos = [1,1], s = "LURD"
        Output: [4,1,0,0]
        Explanation:
        - 0th: "LURD".
        - 1st:  "URD".
        - 2nd:   "RD".
        - 3rd:    "D".
        Example 3:


        Input: n = 1, startPos = [0,0], s = "LRUD"
        Output: [0,0,0,0]
        Explanation: No matter which instruction the robot begins execution from, it would move off the grid.


        Constraints:

        m == s.length
        1 <= n, m <= 500
        startPos.length == 2
        0 <= startrow, startcol < n
        s consists of 'L', 'R', 'U', and 'D'.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.executeInstructions(2, new int[]{1, 1}, "LURD")));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int[] executeInstructions(int n, int[] startPos, String s) {
            int[] arr = new int[s.length()];
            for(int i=0;i<s.length();i++){
                arr[i] = execution(s.substring(i),n,startPos);
            }
            return arr;
        }

        private static int execution(String str, int n, int[] arr){
            int move = 0;
            int row = arr[0];
            int col = arr[1];
            for(int i=0;i<str.length();i++){
                if(str.charAt(i)=='R'){
                    if(col < n-1){
                        move++;
                        col++;
                    }
                    else return move;
                } else if(str.charAt(i)=='D'){
                    if(row < n-1){
                        move++;
                        row++;
                    }
                    else return move;
                } else if(str.charAt(i)=='L'){
                    if(col > 0){
                        move++;
                        col--;
                    }
                    else return move;
                } else if(str.charAt(i)=='U'){
                    if(row > 0){
                        move++;
                        row--;
                    }
                    else return move;
                }
            }
            return move;
        }
    }
}
