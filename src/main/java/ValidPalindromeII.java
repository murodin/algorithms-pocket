public class ValidPalindromeII {

    /*
        Given a string s, return true if the s can be palindrome after deleting at most one character from it.

        Example 1.
        Input: s = "aba"
        Output: true
        Example 2.
        Input: s = "abca"
        Output: true
        Explanation: You could delete the character 'c'.
        Example 3:

        Input: s = "abc"
        Output: false

        Constraints:

        1 <= s.length <= 105
        s consists of lowercase English letters.
     */

    public static void main(String[] args) {
        System.out.println("Valid Palindrome: " + Solution.validPalindrome("abca"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static boolean validPalindrome(String s) {
            int i = 0;
            int j = s.length() - 1;

            while(i <= j){
                if(s.charAt(i) == s.charAt(j)){
                    i++;
                    j--;
                }
                else return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
            return true;
        }
        private static boolean isPalindrome(String s, int i, int j){
            while(i <= j){
                if(s.charAt(i) == s.charAt(j)){
                    i++;
                    j--;
                }
                else return false;
            }
            return true;
        }
    }
}
