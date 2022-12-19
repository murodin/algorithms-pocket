public class LongestCommonSubsequence {
    /*
        Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
        A subsequence of a string is a new string generated from the original string with some characters (can be none)
        deleted without changing the relative order of the remaining characters.
        For example, "ace" is a subsequence of "abcde".
        A common subsequence of two strings is a subsequence that is common to both strings.

        Example 1.
        Input: text1 = "abcde", text2 = "ace"
        Output: 3
        Explanation: The longest common subsequence is "ace" and its length is 3.
        Example 2.
        Input: text1 = "abc", text2 = "abc"
        Output: 3
        Explanation: The longest common subsequence is "abc" and its length is 3.
        Example 3.
        Input: text1 = "abc", text2 = "def"
        Output: 0
        Explanation: There is no such common subsequence, so the result is 0.


        Constraints:

        1 <= text1.length, text2.length <= 1000
        text1 and text2 consist of only lowercase English characters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.longestCommonSubsequence("abcde", "ace"));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        public static int longestCommonSubsequence(String text1, String text2) {
            char[] str1 = text1.toCharArray();
            char[] str2 = text2.toCharArray();
            int[][] temp = new int[str1.length + 1][str2.length + 1];
            int max = 0;
            for(int i=1; i < temp.length; i++) {
                for(int j=1; j< temp[i].length; j++) {
                    if(str1[i-1] == str2[j-1]) {
                        temp[i][j] = temp[i - 1][j - 1] + 1;
                    } else {
                        temp[i][j]=Math.max(temp[i][j-1], temp[i-1][j]);
                    }

                    if(temp[i][j] > max) {
                        max = temp[i][j];
                    }
                }
            }
            return max;
        }
    }
}
