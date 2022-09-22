public class ReverseWordsInAStringIII {
    /*
        Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

        Example 1.
        Input: s = "Let's take LeetCode contest"
        Output: "s'teL ekat edoCteeL tsetnoc"
        Example 2.
        Input: s = "God Ding"
        Output: "doG gniD"

        Constraints:

        1 <= s.length <= 5 * 104
        s contains printable ASCII characters.
        s does not contain any leading or trailing spaces.
        There is at least one word in s.
        All the words in s are separated by a single space.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.reverseWords("Let's take LeetCode contest"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        // Approach:
        // Using two pointers, 'start' and 'end', each time 'end' reaches a space, we reverse the words before.
        // To reverse, we convert the String 's' to a char array, then write a method to reverse the char in the array.
        public static String reverseWords(String s) {
            // Convert s into char array.
            char[] chars = s.toCharArray();
            int start = 0;
            int end;
            for (end = 0; end <= chars.length; end++) {
                // When 'end' reaches a space character ' ', reverse the words from 'start' to 'end' - 1 index in 'chars'.
                if (end == chars.length || chars[end] == ' ') {
                    reverse(chars, start, end - 1);
                    // reset the start for the next word.
                    start = end + 1;
                }
            }
            return new String(chars);
        }

        // A private method to reverse the char in the array using the 'start' and 'end' pointer.
        private static void reverse(char[] c, int start, int end) {
            while (start < end) {
                char tmp = c[end];
                c[end] = c[start];
                c[start] = tmp;
                start++;
                end--;
            }
        }
    }
}
