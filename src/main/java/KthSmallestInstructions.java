public class KthSmallestInstructions {
    /*
        Bob is standing at cell (0, 0), and he wants to reach destination: (row, column). He can only travel right and down.
        You are going to help Bob by providing instructions for him to reach destination.

        The instructions are represented as a string, where each character is either:

        'H', meaning move horizontally (go right), or
        'V', meaning move vertically (go down).
        Multiple instructions will lead Bob to destination. For example, if destination is (2, 3), both "HHHVV" and "HVHVH" are valid instructions.

        However, Bob is very picky. Bob has a lucky number k, and he wants the kth lexicographically smallest instructions that will lead him to destination. k is 1-indexed.

        Given an integer array destination and an integer k, return the kth lexicographically smallest instructions that will take Bob to destination.



        Example 1:
        Input: destination = [2,3], k = 1
        Output: "HHHVV"
        Explanation: All the instructions that reach (2, 3) in lexicographic order are as follows:
        ["HHHVV", "HHVHV", "HHVVH", "HVHHV", "HVHVH", "HVVHH", "VHHHV", "VHHVH", "VHVHH", "VVHHH"].
        Example 2:
        Input: destination = [2,3], k = 2
        Output: "HHVHV"
        Example 3:
        Input: destination = [2,3], k = 3
        Output: "HHVVH"


        Constraints:

        destination.length == 2
        1 <= row, column <= 15
        1 <= k <= nCr(row + column, row), where nCr(a, b) denotes a choose b​​​​​.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.kthSmallestPath(new int[]{2,3}, 2));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        public static String kthSmallestPath(int[] destination, int k) {
            int ti = destination[0], tj = destination[1];
            int[][] dp = new int[ti+1][tj+1];
            for(int i = ti; i >= 0; i--) {
                for(int j = tj; j >= 0; j--) {
                    if(i == ti && j == tj) {
                        dp[i][j] = 1;
                    } else if(i == ti) {
                        dp[i][j] = dp[i][j+1];
                    } else if(j == tj) {
                        dp[i][j] = dp[i+1][j];
                    } else {
                        dp[i][j] = dp[i+1][j] + dp[i][j+1];
                    }
                }
            }
            StringBuilder sb = new StringBuilder();
            helper(dp, 0, 0, k, sb);
            return sb.toString();
        }

        private static void helper(int[][] dp, int i, int j, int k, StringBuilder sb){
            if(i == dp.length -1) {
                while(++j < dp[0].length)sb.append("H");
                return;
            }
            if(j == dp[0].length - 1) {
                while(++i < dp.length)sb.append("V");
                return;
            }
            if(dp[i][j+1] >= k) {
                sb.append("H");
                helper(dp, i, j+1, k, sb);
            } else {
                sb.append("V");
                helper(dp, i+1, j, k-dp[i][j+1], sb);
            }
        }
    }
}
