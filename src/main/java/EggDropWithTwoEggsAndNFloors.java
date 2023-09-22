import java.util.Arrays;

public class EggDropWithTwoEggsAndNFloors {
    /*
        You are given two identical eggs and you have access to a building with n floors labeled from 1 to n.
        You know that there exists a floor f where 0 <= f <= n such that any egg dropped at a floor higher than f will break,
        and any egg dropped at or below floor f will not break.
        In each move, you may take an unbroken egg and drop it from any floor x (where 1 <= x <= n). If the egg breaks, you can no longer use it.
        However, if the egg does not break, you may reuse it in future moves.
        Return the minimum number of moves that you need to determine with certainty what the value of f is.

        Example 1:

        Input: n = 2
        Output: 2
        Explanation: We can drop the first egg from floor 1 and the second egg from floor 2.
        If the first egg breaks, we know that f = 0.
        If the second egg breaks but the first egg didn't, we know that f = 1.
        Otherwise, if both eggs survive, we know that f = 2.
        Example 2:

        Input: n = 100
        Output: 14
        Explanation: One optimal strategy is:
        - Drop the 1st egg at floor 9. If it breaks, we know f is between 0 and 8. Drop the 2nd egg starting from floor 1 and going up one at a time to find f within 8 more drops. Total drops is 1 + 8 = 9.
        - If the 1st egg does not break, drop the 1st egg again at floor 22. If it breaks, we know f is between 9 and 21. Drop the 2nd egg starting from floor 10 and going up one at a time to find f within 12 more drops. Total drops is 2 + 12 = 14.
        - If the 1st egg does not break again, follow a similar process dropping the 1st egg from floors 34, 45, 55, 64, 72, 79, 85, 90, 94, 97, 99, and 100.
        Regardless of the outcome, it takes at most 14 drops to determine f.


        Constraints:

        1 <= n <= 1000
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.twoEggDrop(2));
    }

    // Time: O(2^N)
    // Space: O(N)
    static class Solution {
        public static int twoEggDrop(int n) {
            int eggs = 2;
            int[][] dp = new int[n+1][3];
            for(int[] row : dp)
                Arrays.fill(row,-1);

            return solve(n,2,dp);
        }
        public static int solve(int f, int e, int[][] dp) {
            if(f<=2 || e==1) return f;
            if(dp[f][e] != -1) return dp[f][e];

            int min = f;

            for(int i=1; i<=f; ++i) {
                int Break = solve(i-1,e-1,dp);
                int notBreak = solve(f-i,e,dp);
                int worstCase = 1 + Math.max(Break,notBreak);
                min = Math.min(min,worstCase);
            }
            return dp[f][e] = min;
        }
    }
}
