public class FindFirstPalindromicStringInTheArray {
    /*
        Given an array of strings words, return the first palindromic string in the array. If there is no such string, return an empty string "".
        A string is palindromic if it reads the same forward and backward.


        Example 1:
        Input: words = ["abc","car","ada","racecar","cool"]
        Output: "ada"
        Explanation: The first string that is palindromic is "ada".
        Note that "racecar" is also palindromic, but it is not the first.
        Example 2:
        Input: words = ["notapalindrome","racecar"]
        Output: "racecar"
        Explanation: The first and only string that is palindromic is "racecar".
        Example 3:
        Input: words = ["def","ghi"]
        Output: ""
        Explanation: There are no palindromic strings, so the empty string is returned.


        Constraints:

        1 <= words.length <= 100
        1 <= words[i].length <= 100
        words[i] consists only of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.firstPalindrome(new String[]{"notapalindrome","racecar"}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static String firstPalindrome(String[] words) {
            for (String s : words) {
                if (isPalindrome(s)) return s;
            }
            return "";
        }


        private static boolean isPalindrome(String s) {
            int left = 0;
            int right = s.length() - 1;

            while (left < right) {
                if (s.charAt(left) != s.charAt(right)) {
                    return false;
                }
                left++;
                right--;
            }

            return true;
        }
    }

}
