import java.util.HashSet;
import java.util.Set;

public class VerbalArithmeticPuzzle {
    /*
        Given an equation, represented by words on the left side and the result on the right side.
        You need to check if the equation is solvable under the following rules:

        Each character is decoded as one digit (0 - 9).
        No two characters can map to the same digit.
        Each words[i] and result are decoded as one number without leading zeros.
        Sum of numbers on the left side (words) will equal to the number on the right side (result).
        Return true if the equation is solvable, otherwise return false.

        Example 1:

        Input: words = ["SEND","MORE"], result = "MONEY"
        Output: true
        Explanation: Map 'S'-> 9, 'E'->5, 'N'->6, 'D'->7, 'M'->1, 'O'->0, 'R'->8, 'Y'->'2'
        Such that: "SEND" + "MORE" = "MONEY" ,  9567 + 1085 = 10652
        Example 2:

        Input: words = ["SIX","SEVEN","SEVEN"], result = "TWENTY"
        Output: true
        Explanation: Map 'S'-> 6, 'I'->5, 'X'->0, 'E'->8, 'V'->7, 'N'->2, 'T'->1, 'W'->'3', 'Y'->4
        Such that: "SIX" + "SEVEN" + "SEVEN" = "TWENTY" ,  650 + 68782 + 68782 = 138214
        Example 3:

        Input: words = ["LEET","CODE"], result = "POINT"
        Output: false
        Explanation: There is no possible mapping to satisfy the equation, so we return false.
        Note that two different characters cannot map to the same digit.


        Constraints:

        2 <= words.length <= 5
        1 <= words[i].length, result.length <= 7
        words[i], result contain only uppercase English letters.
        The number of different characters used in the expression is at most 10.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.isSolvable( new String[] {"SIX","SEVEN","SEVEN"}, "TWENTY"));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        private static final int[] POW_10 = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000};
        public static boolean isSolvable(String[] words, String result) {
            Set<Character> charSet = new HashSet<>();
            int[] charCount = new int[91];
            boolean[] nonLeadingZero = new boolean[91];
            for (String word : words) {
                char[] cs = word.toCharArray();
                for (int i = 0; i < cs.length; i++) {
                    if (i == 0 && cs.length > 1) nonLeadingZero[cs[i]] = true;
                    charSet.add(cs[i]);
                    charCount[cs[i]] += POW_10[cs.length - i - 1];
                }
            }
            char[] cs = result.toCharArray();
            for (int i = 0; i < cs.length; i++) {
                if (i == 0 && cs.length > 1) nonLeadingZero[cs[i]] = true;
                charSet.add(cs[i]);
                charCount[cs[i]] -= POW_10[cs.length - i - 1];
            }
            boolean[] used = new boolean[10];
            char[] charList = new char[charSet.size()];
            int i = 0;
            for (char c : charSet) charList[i++] = c;
            return backtracking(used, charList, nonLeadingZero, 0, 0, charCount);
        }

        private static boolean backtracking(boolean[] used, char[] charList, boolean[] nonLeadingZero, int step, int diff, int[] charCount) {
            if (step == charList.length) return diff == 0;
            for (int d = 0; d <= 9; d++) {
                char c = charList[step];
                if (!used[d] && (d > 0 || !nonLeadingZero[c])) {
                    used[d] = true;
                    if (backtracking(used, charList, nonLeadingZero, step + 1, diff + charCount[c] * d, charCount)) return true;
                    used[d] = false;
                }
            }
            return false;
        }
    }
}
