public class ReverseVowelsOfAString {
    /*
        Given a string s, reverse only all the vowels in the string and return it.
        The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

        Example 1.
        Input: s = "hello"
        Output: "holle"
        Example 2.
        Input: s = "leetcode"
        Output: "leotcede"

        Constraints:

        1 <= s.length <= 3 * 105
        s consist of printable ASCII characters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.reverseVowels("leetcode"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static String reverseVowels(String s) {
            char[] chars = s.toCharArray();

            int left = 0;
            int right = s.length() - 1;

            while (left < right) {
                while (left < right && !isVowel(chars[left]))
                    left++;
                while (left < right && !isVowel(chars[right]))
                    right--;

                if (left < right) {
                    char temp = chars[left];
                    chars[left++] = chars[right];
                    chars[right--] = temp;
                }
            }
            return new String(chars);
        }

        private static boolean isVowel(char c) {
            return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                    || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
        }
    }
}
