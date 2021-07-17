import java.util.regex.Pattern;

public class ValidNumber {
    /*
        A valid number can be split up into these components (in order):
        A decimal number or an integer.
        (Optional) An 'e' or 'E', followed by an integer.
        A decimal number can be split up into these components (in order):

        (Optional) A sign character (either '+' or '-').
        One of the following formats:
        One or more digits, followed by a dot '.'.
        One or more digits, followed by a dot '.', followed by one or more digits.
        A dot '.', followed by one or more digits.
        An integer can be split up into these components (in order):

        (Optional) A sign character (either '+' or '-').
        One or more digits.
        For example, all the following are valid numbers:
        ["2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"],
        while the following are not valid numbers: ["abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"].

        Given a string s, return true if s is a valid number.
     */

    public static void main(String[] args) {
        String testNumber = "-90E3";
        System.out.println("-90E3 is Valid: " + Solution.isNumber(testNumber));
    }

    static class Solution {

        public static boolean isNumberRegex(String s) {
            // +-
            // 8.9e 9.e .6E-57
            // 1256e569
            String regex = "^[+-]?(((\\d+\\.\\d*)|(\\.\\d+))|\\d+)([eE][+-]?\\d+)?$";
            return Pattern.matches(regex, s);
        }

        public static boolean isNumber(String s) {
            boolean digitseen = false, eseen = false, dotseen = false;
            int countPlusMinus = 0;

            for(int i = 0; i<s.length(); i++) {
                char c = s.charAt(i);

                // digit
                if(Character.isDigit(c)) digitseen = true;

                // minus/plus
                else if(c == '+' || c == '-') {
                    if(countPlusMinus == 2) return false;
                    if(i > 0 && (s.charAt(i-1) != 'e' && s.charAt(i-1) != 'E')) return false;
                    if(i == s.length() -1) return false;
                    countPlusMinus++;
                }

                // dot
                else if(c == '.') {
                    if(eseen || dotseen) return false;
                    if(i == s.length()-1 && !digitseen) return false;
                    dotseen = true;
                }

                // e/E
                else if(c == 'e' || c == 'E') {
                    if(eseen || !digitseen || i == s.length()-1) return false;
                    eseen = true;
                }
                else return false;
            }
            return true;
        }
    }
}
