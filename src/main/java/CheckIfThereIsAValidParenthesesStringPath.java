public class CheckIfThereIsAValidParenthesesStringPath {

    /*
           A parentheses string is a non-empty string consisting only of '(' and ')'. It is valid if any of the following conditions is true:
            It is ().
            It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
            It can be written as (A), where A is a valid parentheses string.
            You are given an m x n matrix of parentheses grid. A valid parentheses string path in the grid is a path satisfying all of the following conditions:

            The path starts from the upper left cell (0, 0).
            The path ends at the bottom-right cell (m - 1, n - 1).
            The path only ever moves down or right.
            The resulting parentheses string formed by the path is valid.
            Return true if there exists a valid parentheses string path in the grid. Otherwise, return false.

            Example 1.
            Input: grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
            Output: true
            Explanation: The above diagram shows two possible paths that form valid parentheses strings.
            The first path shown results in the valid parentheses string "()(())".
            The second path shown results in the valid parentheses string "((()))".
            Note that there may be other valid parentheses string paths.
            Example 2:.
            Input: grid = [[")",")"],["(","("]]
            Output: false
            Explanation: The two possible paths form the parentheses strings "))(" and ")((". Since neither of them are valid parentheses strings, we return false.


            Constraints:

            m == grid.length
            n == grid[i].length
            1 <= m, n <= 100
            grid[i][j] is either '(' or ')'.
     */

    public static void main(String[] args) {
        char[][] grid = {{'(','(','('},{')','(',')'},{'(','(',')'},{'(','(',')'}};
        System.out.println("Solution: " + Solution.hasValidPath(grid));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        static Boolean[][][] dp;
        public static boolean hasValidPath(char[][] grid) {
            int m = grid.length, n = grid[0].length;
            dp = new Boolean[101][101][201];  // [row][col][open-close]
            if(grid[0][0] == ')'){   // cannot start with ')'
                return false;
            }
            if(grid[m-1][n-1] == '('){  // cannot end with '('
                return false;
            }
            return solve(grid,0,0,m,n,0,0);
        }
        public static boolean solve(char[][] grid,int i,int j,int m,int n,int open,int close){
            if(grid[i][j] == '('){
                open++;
            }
            else{
                close++;
            }
            if(close > open){  // at any point if closeBracket count exceeds openBracket count then return false since this path can never lead to valid paranthesis string
                return false;
            }
            if(i == m-1 && j == n-1){  // on reaching bottom right cell if openCount == closeCount return true else return false
                return open == close;
            }
            if(dp[i][j][open-close] != null){  // check for precomputed overlapping subproblem
                return dp[i][j][open-close];
            }
            if(i == m-1){   // make sure to not go out of the grid in last row
                return dp[i][j][open-close] = solve(grid,i,j+1,m,n,open,close);
            }
            if(j == n-1){  // make sure to not go out of the grid in last col
                return dp[i][j][open-close] = solve(grid,i+1,j,m,n,open,close);
            }
            boolean op = solve(grid,i+1,j,m,n,open,close) || solve(grid,i,j+1,m,n,open,close); // we have two choices to move forward, [i+1][j] or [i][j+1]
            return dp[i][j][open-close] = op;
        }
    }
}
