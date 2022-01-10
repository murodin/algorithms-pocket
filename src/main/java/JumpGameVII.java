public class JumpGameVII {
    /*
        You are given a 0-indexed binary string s and two integers minJump and maxJump.
        In the beginning, you are standing at index 0, which is equal to '0'.
        You can move from index i to index j if the following conditions are fulfilled:

        i + minJump <= j <= min(i + maxJump, s.length - 1), and
        s[j] == '0'.
        Return true if you can reach index s.length - 1 in s, or false otherwise.


        Example 1.
        Input: s = "011010", minJump = 2, maxJump = 3
        Output: true
        Explanation:
        In the first step, move from index 0 to index 3.
        In the second step, move from index 3 to index 5.
        Example 2.
        Input: s = "01101110", minJump = 2, maxJump = 3
        Output: false

        Constraints:

        2 <= s.length <= 105
        s[i] is either '0' or '1'.
        s[0] == '0'
        1 <= minJump <= maxJump < s.length
     */

    public static void main(String[] args) {
        String s = "011010";
        System.out.println("Can Reach: " + Solution.canReach(s, 2, 3));
    }

    // Time:O(N)
    // Space: O(N)
    static class Solution {
        public static boolean canReach(String s, int minJump, int maxJump) {
            int n = s.length();
            boolean[] dp = new boolean[n];
            char[] ch = s.toCharArray();

            dp[0] = ch[0]=='0';

            int reachable = 0;

            for(int i = 1;i<n;i++){

                if(i>=minJump){
                    reachable += dp[i-minJump]? 1 : 0;
                }

                if(i>maxJump){
                    reachable -= dp[i-maxJump-1]? 1 : 0;
                }

                dp[i] = reachable>0 && ch[i] == '0';
            }

            return dp[n-1];
        }
    }
}
