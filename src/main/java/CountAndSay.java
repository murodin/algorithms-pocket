public class CountAndSay {
    /*
        The count-and-say sequence is a sequence of digit strings defined by the recursive formula:

        countAndSay(1) = "1"
        countAndSay(n) is the way you would "say" the digit string from countAndSay(n-1), which is then converted into a different digit string.
        To determine how you "say" a digit string, split it into the minimal number of substrings such that each substring contains exactly one unique digit.
        Then for each substring, say the number of digits, then say the digit. Finally, concatenate every said digit.

        For example, the saying and conversion for digit string "3322251":
        Given a positive integer n, return the nth term of the count-and-say sequence.

        Example 1.
        Input: n = 1
        Output: "1"
        Explanation: This is the base case.
        Example 2.
        Input: n = 4
        Output: "1211"
        Explanation:
        countAndSay(1) = "1"
        countAndSay(2) = say "1" = one 1 = "11"
        countAndSay(3) = say "11" = two 1's = "21"
        countAndSay(4) = say "21" = one 2 + one 1 = "12" + "11" = "1211"


        Constraints:

        1 <= n <= 30
     */
    public static void main(String[] args) {

    }

    // Time:
    // Space:
    static class Solution {
        public static String countAndSay(int n) {
            if (n <= 0) {
                throw new IllegalArgumentException("Invalid input");
            }

            StringBuilder sb = new StringBuilder("1");
            for (int i = 2; i <= n; i++) {
                sb = getNextState(sb);
            }
            return sb.toString();
        }

        private static StringBuilder getNextState(StringBuilder curSb) {
            StringBuilder nextSb = new StringBuilder();
            int len = curSb.length();
            int i = 0;

            while (i < len) {
                char c = curSb.charAt(i++);
                int count = 1;
                while (i < len && c == curSb.charAt(i)) {
                    count++;
                    i++;
                }
                nextSb.append(count).append(c);
            }

            return nextSb;
        }
    }
}
