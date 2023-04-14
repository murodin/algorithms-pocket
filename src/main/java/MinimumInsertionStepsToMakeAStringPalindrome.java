public class MinimumInsertionStepsToMakeAStringPalindrome {
    /*
        Given a string s. In one step you can insert any character at any index of the string.
        Return the minimum number of steps to make s palindrome.
        A Palindrome String is one that reads the same backward as well as forward.


        Example 1:

        Input: s = "zzazz"
        Output: 0
        Explanation: The string "zzazz" is already palindrome we do not need any insertions.
        Example 2:

        Input: s = "mbadm"
        Output: 2
        Explanation: String can be "mbdadbm" or "mdbabdm".
        Example 3:

        Input: s = "leetcode"
        Output: 5
        Explanation: Inserting 5 characters the string becomes "leetcodocteel".


        Constraints:

        1 <= s.length <= 500
        s consists of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minInsertions("leetcode"));
    }

    // Time: O(N^2)
    // Space: O(N^2)
    static class Solution {
        public static int minInsertions(String a) {
            StringBuilder s = new StringBuilder(a);
            s.reverse();
            String b = s.toString();

            int n = a.length();
            int t[][] = new int[n+1][n+1];

            for(int i=1; i<=n; i++)
                for(int j=1; j<=n; j++)
                    if(a.charAt(i-1)==b.charAt(j-1)) t[i][j] = 1 + t[i-1][j-1];
                    else t[i][j] = Math.max(t[i-1][j], t[i][j-1]);
            return n-t[n][n];
        }
    }
}
