import java.util.stream.IntStream;

public class FindTheShortestSuperstring {
    /*
        Given an array of strings words, return the smallest string that contains each string in words as a substring.
        If there are multiple valid strings of the smallest length, return any of them.

        You may assume that no string in words is a substring of another string in words.



        Example 1:

        Input: words = ["alex","loves","leetcode"]
        Output: "alexlovesleetcode"
        Explanation: All permutations of "alex","loves","leetcode" would also be accepted.
        Example 2:

        Input: words = ["catg","ctaagt","gcta","ttca","atgcatc"]
        Output: "gctaagttcatgcatc"


        Constraints:

        1 <= words.length <= 12
        1 <= words[i].length <= 20
        words[i] consists of lowercase English letters.
        All the strings of words are unique.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.shortestSuperstring(new String[]{"catg","ctaagt","gcta","ttca","atgcatc"}));
    }

    // Time: O(2^N * N^2)
    // Space: O(2^N * N + N^2)
    static class Solution {
        public static String shortestSuperstring(String[] words) {
            int n = words.length;
            int[][] discount = new int[n][n];
            for (int i = 0; i < n; i++){
                for (int j = 0; j < n; j++){
                    for (int k = 0; k < words[i].length()&&i!=j; k++) {
                        if (words[j].startsWith(words[i].substring(k))) {
                            discount[i][j] = words[i].length()-k;
                            break;
                        }
                    }
                }
            }
            int[][] dp = new int[1<<n][n];
            int[][] path = new int[1<<n][n];
            for (int i = 0; i < 1<<n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < n && (i&1<<j)>0; k++) {
                        if ((i&1 << k) == 0 && dp[i][j] + discount[j][k] >= dp[i|1 << k][k]){
                            dp[i|1<<k][k] = dp[i][j]+discount[j][k];
                            path[i|1<<k][k] = j;
                        }
                    }
                }
            }
            int m = (1<<n)-1, idx = n; // build the path from end to start
            int end = IntStream.range(0,n).reduce((a, b)->dp[(1<<n)-1][a]>dp[(1<<n)-1][b]?a:b).getAsInt();
            String[] ans = new String[n];
            while(m > 0){
                ans[--idx] = words[end].substring((m&(m-1))==0?0:discount[path[m][end]][end]);
                m ^= 1 << end;
                end = path[m|1 << end][end];
            }
            return String.join("",ans);
        }
    }
}
