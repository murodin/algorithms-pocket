public class BackspaceStringCompare {
    /*
        Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.
        Note that after backspacing an empty text, the text will continue empty.

        Example 1.
        Input: s = "ab#c", t = "ad#c"
        Output: true
        Explanation: Both s and t become "ac".
        Example 2.
        Input: s = "ab##", t = "c#d#"
        Output: true
        Explanation: Both s and t become "".
        Example 3:

        Input: s = "a#c", t = "b"
        Output: false
        Explanation: s becomes "c" while t becomes "b".


        Constraints:

        1 <= s.length, t.length <= 200
        s and t only contain lowercase letters and '#' characters.


        Follow up: Can you solve it in O(n) time and O(1) space?
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.backspaceCompare("ab#c", "ad#c"));
    }

    // Time: (M+N), where M and N length of strings
    // Space: O(1)
    static class Solution {
        private static int move(String s, int cur) {
            int cnt = 0;
            while(cur >= 0) {
                if (s.charAt(cur--) == '#')
                    cnt++;
                else {
                    if (cnt == 0) return cur + 1;
                    cnt--;
                }
            }
            return cur;
        }

        public static boolean backspaceCompare(String s, String t) {
            int i = s.length() - 1;
            int j = t.length() - 1;
            while(true) {
                i = move(s, i);
                j = move(t, j);

                if (i < 0 || j < 0)
                    break;

                if (s.charAt(i--) != t.charAt(j--))
                    return false;
            }
            return i == j;
        }
    }
}
