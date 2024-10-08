public class MinimumASCIIDeleteSumForTwoStrings {
    /*
        Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.

        Example 1.
        Input: s1 = "sea", s2 = "eat"
        Output: 231
        Explanation: Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
        Deleting "t" from "eat" adds 116 to the sum.
        At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.
        Example 2.
        Input: s1 = "delete", s2 = "leet"
        Output: 403
        Explanation: Deleting "dee" from "delete" to turn the string into "let",
        adds 100[d] + 101[e] + 101[e] to the sum.
        Deleting "e" from "leet" adds 101[e] to the sum.
        At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
        If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.


        Constraints:

        1 <= s1.length, s2.length <= 1000
        s1 and s2 consist of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minimumDeleteSum("delete", "leet"));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        public static int minimumDeleteSum(String s1, String s2) {
            int m = s1.length();
            int n = s2.length();
            int[][] dp = new int[m+1][n+1];
            for(int i=0;i<=m;i++){
                for(int j=0;j<=n;j++){
                    if(i==0 || j==0){
                        int a = 0;
                        for(int z=1;z<=Math.max(j,i);z++){
                            a += (i==0?s2.charAt(z-1):s1.charAt(z-1));
                        }
                        dp[i][j] = a;
                    } else if(s1.charAt(i-1)==s2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1];
                    } else{
                        dp[i][j] = Math.min(s1.charAt(i-1)+dp[i-1][j],s2.charAt(j-1)+dp[i][j-1]);
                        dp[i][j] = Math.min(dp[i][j],s1.charAt(i-1)+s2.charAt(j-1)+dp[i-1][j-1]);
                    }
                }
            }
            return dp[m][n];
        }
    }
}
