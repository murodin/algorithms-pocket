public class ExcelSheetColumnTitle {
    /*
        Given an integer columnNumber, return its corresponding column title as it appears in an Excel sheet.

        For example:

        A -> 1
        B -> 2
        C -> 3
        ...
        Z -> 26
        AA -> 27
        AB -> 28
        ...


        Example 1:

        Input: columnNumber = 1
        Output: "A"
        Example 2:

        Input: columnNumber = 28
        Output: "AB"
        Example 3:

        Input: columnNumber = 701
        Output: "ZY"


        Constraints:

        1 <= columnNumber <= 231 - 1
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.convertToTitle(28));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static String convertToTitle(int n) {
            if (n < 27) {
                return Character.toString((char) ('A' + (n - 1) % 26));
            }

            StringBuilder c = new StringBuilder();
            while (n > 0) {
                if (n % 26 == 0) {
                    c.append((char) ('A' + 25));
                    n -= 1;
                } else {
                    c.append((char) ('A' + n % 26 - 1));
                }
                n /= 26;
            }
            return c.reverse().toString();
        }
    }
}
