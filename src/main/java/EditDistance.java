public class EditDistance {

    /*
        Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
        You have the following three operations permitted on a word:

        Insert a character
        Delete a character
        Replace a character


        Example 1.
        Input: word1 = "horse", word2 = "ros"
        Output: 3
        Explanation:
        horse -> rorse (replace 'h' with 'r')
        rorse -> rose (remove 'r')
        rose -> ros (remove 'e')
        Example 2.
        Input: word1 = "intention", word2 = "execution"
        Output: 5
        Explanation:
        intention -> inention (remove 't')
        inention -> enention (replace 'i' with 'e')
        enention -> exention (replace 'n' with 'x')
        exention -> exection (replace 'n' with 'c')
        exection -> execution (insert 'u')


        Constraints:

        0 <= word1.length, word2.length <= 500
        word1 and word2 consist of lowercase English letters.
     */

    public static void main(String[] args) {
        System.out.println("Solution I: " + Solution_I.minDistance("horse", "ros"));
        System.out.println("Solution I: " + Solution_II.minDistance("horse", "ros"));
    }

    // Time: O(NxM)
    // Space: O(N)
    static class Solution_I {
        public static int minDistance(String word1, String word2) {
            int len1 = word1.length();
            int len2 = word2.length();
            if (len1 > len2) return minDistance(word2, word1);
            int[] dp = new int[len1 + 1];
            for(int i = 1; i <= len1; i++) {
                dp[i] = i;
            }
            int prev = 0;
            for(int j = 0; j < len2; j++) {
                prev = dp[0];
                dp[0] = j + 1;
                for(int i = 1; i <= len1; i++) {
                    int tmp = Math.min(dp[i] + 1, prev + (word1.charAt(i - 1) == word2.charAt(j) ? 0 : 1));
                    prev = dp[i];
                    dp[i] = Math.min(tmp, dp[i - 1] + 1);
                }
            }
            return dp[len1];
        }
    }

    // Time: O(NxM)
    // Space: O(MxN)
    static class Solution_II {
        public static int minDistance(String word1, String word2) {
            if (word1 == null || word2 == null) return 0;

            if (word1.length() == 0) return word2.length();
            if (word2.length() == 0) return word1.length();

            int length1 = word1.length();
            int length2 = word2.length();

            int[][] min_operations = new int[length1 + 1][length2 + 1];

            for (int i = 0; i <= length1; i++) {
                for (int j = 0; j <= length2; j++) {
                    if (i == 0 && j == 0) {
                        min_operations[i][j] = 0;

                    } else if(i == 0) {
                        min_operations[i][j] = j;

                    } else if(j == 0) {
                        min_operations[i][j] = i;

                    } else {
                        char ch1 = word1.charAt(i - 1);
                        char ch2 = word2.charAt(j - 1);

                        if (ch1 != ch2) {
                            int replace = min_operations[i - 1][j - 1];
                            int insert = min_operations[i][j - 1];
                            int delete = min_operations[i - 1][j];
                            min_operations[i][j] = Math.min(insert, Math.min(delete, replace)) + 1;

                        } else {
                            min_operations[i][j] = min_operations[i - 1][j - 1];

                        }
                    }
                }
            }

            return min_operations[length1][length2];
        }

    }
}
