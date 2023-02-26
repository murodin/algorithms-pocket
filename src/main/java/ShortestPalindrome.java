public class ShortestPalindrome {
    /*
        You are given a string s. You can convert s to a palindrome by adding characters in front of it.
        Return the shortest palindrome you can find by performing this transformation.

        Example 1.
        Input: s = "aacecaaa"
        Output: "aaacecaaa"
        Example 2.
        Input: s = "abcd"
        Output: "dcbabcd"


        Constraints:

        0 <= s.length <= 5 * 104
        s consists of lowercase English letters only.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.shortestPalindrome("abcd"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static String shortestPalindrome(String s) {
            int i = 0, end = s.length() - 1, j = end; char chs[] = s.toCharArray();
            while(i < j) {
                if (chs[i] == chs[j]) {
                    i++; j--;
                } else {
                    i = 0; end--; j = end;
                }
            }
            return new StringBuilder(s.substring(end+1)).reverse().toString() + s;
        }
    }

}
