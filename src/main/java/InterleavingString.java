public class InterleavingString {
    /*
        Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
        An interleaving of two strings s and t is a configuration where they are divided into non-empty substrings such that:

        s = s1 + s2 + ... + sn
        t = t1 + t2 + ... + tm
        |n - m| <= 1
        The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
        Note: a + b is the concatenation of strings a and b.


        Example 1.
        Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
        Output: true
        Example 2.
        Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
        Output: false
        Example 3.
        Input: s1 = "", s2 = "", s3 = ""
        Output: true


        Constraints:

        0 <= s1.length, s2.length <= 100
        0 <= s3.length <= 200
        s1, s2, and s3 consist of lowercase English letters.
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }

    // Time: O(N)
    // Space: O(N)

    static class Solution {
        private static boolean[][] invalid;
        private static char[] c1;
        private static char[] c2;
        private static char[] c3;

        public static boolean isInterleave(String s1, String s2, String s3) {
            c1 = s1.toCharArray();
            c2 = s2.toCharArray();
            c3 = s3.toCharArray();

            int m = s1.length(),n=s2.length();

            if(m+n != s3.length())
                return false;

            invalid = new boolean[m+1][n+1];

            return dfs(0,0,0);
        }

        private static boolean dfs(int i, int j, int k){
            if(invalid[i][j])
                return false;

            if(k == c3.length)
                return true;

            boolean valid = i<c1.length && c1[i] == c3[k] && dfs(i+1,j,k+1) ||
                            j<c2.length && c2[j] == c3[k] && dfs(i,j+1,k+1);

            if(!valid)
                invalid[i][j] = true;

            return valid;
        }
    }
}
