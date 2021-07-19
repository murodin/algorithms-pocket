import java.util.ArrayList;
import java.util.List;

public class AmbiguousCoordinates {

    /*
        We had some 2-dimensional coordinates, like "(1, 3)" or "(2, 0.5)". Then, we removed all commas, decimal points, and spaces and ended up with the string s.
        For example, "(1, 3)" becomes s = "(13)" and "(2, 0.5)" becomes s = "(205)".
        Return a list of strings representing all possibilities for what our original coordinates could have been.

        Our original representation never had extraneous zeroes, so we never started with numbers like "00", "0.0", "0.00", "1.0", "001", "00.01",
        or any other number that can be represented with fewer digits.
        Also, a decimal point within a number never occurs without at least one digit occurring before it, so we never started with numbers like ".1".

        The final answer list can be returned in any order. All coordinates in the final answer have exactly one space between them (occurring after the comma.)

        Example:
        Input: s = "(123)"
        Output: ["(1, 2.3)","(1, 23)","(1.2, 3)","(12, 3)"]
     */

    public static void main(String[] args) {
        String testString = "(123)";
        System.out.println("Coordinates:" + Solution.ambiguousCoordinates(testString));
    }

    static class Solution {
        static List<String> result = new ArrayList<>();
        public static List<String> ambiguousCoordinates(String s) {
            s = s.substring(1, s.length()-1);
            // Breaking the String x,y form (0123) -> (0, 123)
            for(int i = 1; i<s.length(); i++) {
                helper(s.substring(0,i), s.substring(i));
            }
            return result;
        }

        private static void helper(String x, String y) {
            List<String> dotx = putDot(x);
            List<String> doty = putDot(y);

            for(String dx: dotx) {
                if(isValid(dx)) {
                    for(String dy: doty) {
                        if(isValid(dy)) {
                            result.add("("+dx+", "+dy+")");
                        }
                    }
                }
            }
        }

        private static List<String> putDot(String s) {
            List<String> res = new ArrayList<>();
            res.add(s);
            for(int i=1; i<s.length(); i++) {
                res.add(s.substring(0,i) + "."+s.substring(i));
            }
            System.out.println("Output of putDot:" + res);
            return res;
        }

        private static boolean isValid(String s) {
            if(s.contains(".")) {
                String[] part = s.split("\\.");
                if(!part[0].equals("0") && part[0].startsWith("0")) return false;
                else return !part[1].endsWith("0");
            } else {
                if(s.equals("0")) return true;
                else return !s.startsWith("0");
            }
        }
    }
}
