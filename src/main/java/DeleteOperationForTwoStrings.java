public class DeleteOperationForTwoStrings {

    /*
        Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.
        In one step, you can delete exactly one character in either string.

        Example 1.
        Input: word1 = "sea", word2 = "eat"
        Output: 2
        Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
     */

    public static void main(String[] args) {
        String testString1 = "eat", testString2 = "sea";
        System.out.println("Min Distance to Delete: " + Solution.minDistance_2(testString1, testString2));

    }

    // Time: O(MxN)
    // Space: O(MxN)
    static class Solution {

        public static int minDistance_2(String word1, String word2) {
            int[][] dp = new int[word1.length()+1][word2.length()+1];
            for(int i = 0; i <= word1.length(); i++) {
                for(int j = 0; j <= word2.length(); j++) {
                    if(i == 0 || j == 0) dp[i][j] = i +j;
                    else if(word1.charAt(i-1) == word2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
            return dp[word1.length()][word2.length()];
        }

        public static int minDistance(String word1, String word2) {
            int length = lcs(word1, word2);
            return word1.length() + word2.length() - 2*length;
        }

        private static int lcs(String word1, String word2) {
            int[][] dp = new int[word1.length()+1][word2.length()+1];
            for(int i = 0; i <= word1.length(); i++) {
                for(int j = 0; j <= word2.length(); j++) {
                    if(i == 0 || j == 0) continue;

                    if(word1.charAt(i-1) == word2.charAt(j-1)){
                        dp[i][j] = 1 + dp[i-1][j-1];
                    } else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
            return dp[word1.length()][word2.length()];
        }
    }
}
