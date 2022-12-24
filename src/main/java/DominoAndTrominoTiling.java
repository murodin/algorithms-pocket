public class DominoAndTrominoTiling {
    /*
        You have two types of tiles: a 2 x 1 domino shape and a tromino shape. You may rotate these shapes.
        Given an integer n, return the number of ways to tile an 2 x n board. Since the answer may be very large, return it modulo 109 + 7.
        In a tiling, every square must be covered by a tile.
        Two tilings are different if and only if there are two 4-directionally adjacent cells on the board such that exactly one of the tilings has both squares occupied by a tile.

        Example 1.
        Input: n = 3
        Output: 5
        Explanation: The five different ways are show above.
        Example 2.
        Input: n = 1
        Output: 1


        Constraints:

        1 <= n <= 1000
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numTilings(3));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int numTilings(int n) {
            if(n==1)
                return 1;
            else if(n==2)
                return 2;
            int mod = 1000000007;

            int [] dp=new int[n+1];

            dp[1]=1;
            dp[2]=2;
            dp[3]=5;
            for(int i =4;i<=n;i++){
                dp[i]=(2*dp[i-1]%mod+dp[i-3]%mod)%mod;
            }
            return dp[n];
        }
    }
}
