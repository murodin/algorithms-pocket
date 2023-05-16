public class ShortestCommonSupersequence {
    /*
        Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.

        A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.



        Example 1:

        Input: str1 = "abac", str2 = "cab"
        Output: "cabac"
        Explanation:
        str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
        str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
        The answer provided is the shortest such string that satisfies these properties.
        Example 2:

        Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
        Output: "aaaaaaaa"


        Constraints:

        1 <= str1.length, str2.length <= 1000
        str1 and str2 consist of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.shortestCommonSupersequence("abac", "cab"));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        public static String shortestCommonSupersequence(String s1, String s2) {
            // find the LCS of s1 and s2
            int m = s1.length(),n=s2.length();
            int[][] dp = new int[m+1][n+1];
            for(int i=1;i<=m;i++){
                for(int j=1;j<=n;j++){
                    if(s1.charAt(i-1)==s2.charAt(j-1)){
                        dp[i][j]=1+dp[i-1][j-1];
                    }else dp[i][j]=Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
            StringBuilder sb = new StringBuilder();
            int i=m,j=n;
            while(i>0 && j>0){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    sb.append(s1.charAt(i-1));
                    i--;
                    j--;
                }else if(dp[i-1][j]>dp[i][j-1]){//shrink s1
                    sb.append(s1.charAt(i-1));
                    i--;
                }else{
                    sb.append(s2.charAt(j-1));
                    j--;
                }
            }
            while(i>0) {
                sb.append(s1.charAt(i-1));
                i--;
            }
            while(j>0){
                sb.append(s2.charAt(j-1));
                j--;
            }
            return sb.reverse().toString();
        }
    }
}
