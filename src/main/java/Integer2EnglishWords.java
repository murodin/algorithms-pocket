public class Integer2EnglishWords {

    /*
        Convert a non-negative integer num to its English words representation.
        Example 1.
        Input: num = 123
        Output: "One Hundred Twenty Three"
        Example 2.
        Input: num = 12345
        Output: "Twelve Thousand Three Hundred Forty Five"
        Example 3.
        Input: num = 1234567
        Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
        Example 4.
        Input: num = 1234567891
        Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
     */

    public static void main(String[] args) {
        System.out.println("1234567891 to English Words: " + Solution.numberToWords(1234567891));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        private final static String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};
        private final static String[] LESS_THAN_TWENTY = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
        };
        private final static String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

        public static String numberToWords(int num) {
            if(num == 0) return "Zero";

            StringBuilder sb = new StringBuilder();
            int index = 0;

            while(num > 0) {
                if(num%1000 != 0) {
                    StringBuilder tmp = new StringBuilder();
                    helper(tmp, num %1000);
                    sb.insert(0, tmp.append(THOUSANDS[index]).append(" "));
                }

                index++;
                num = num/1000;
            }

            return sb.toString().trim();
        }

        private final static void helper(StringBuilder sb, int num) {
            if(num == 0) return;
            else if(num < 20) {
                sb.append(LESS_THAN_TWENTY[num]).append(" ");
                return;
            } else if(num < 100) {
                sb.append(TENS[num / 10]).append(" ");
                helper(sb, num%10);
            } else {
                sb.append(LESS_THAN_TWENTY[num/100]).append(" Hundred ");
                helper(sb, num%100);
            }
        }
    }
}
