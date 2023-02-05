
public class MultiplyStrings {
    /*
        Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
        Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

        Example 1.
        Input: num1 = "2", num2 = "3"
        Output: "6"
        Example 2.
        Input: num1 = "123", num2 = "456"
        Output: "56088"


        Constraints:

        1 <= num1.length, num2.length <= 200
        num1 and num2 consist of digits only.
        Both num1 and num2 do not contain any leading zero, except the number 0 itself.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.multiply("123", "456"));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static String multiply(String num1, String num2) {
            if (num1 == null || num2 == null) {
                throw new IllegalArgumentException("Input numbers are invalid");
            }

            int m = num1.length();
            int n = num2.length();

            if (m == 0 || n == 0 || "0".equals(num1) || "0".equals(num2)) {
                return "0";
            }
            if ("1".equals(num1)) {
                return num2;
            }
            if ("1".equals(num2)) {
                return num1;
            }
            int[] result = new int[m + n];

            for (int i = m - 1; i >= 0; i--) {
                for (int j = n - 1; j >= 0; j--) {
                    int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                    product += result[i + j + 1];
                    result[i + j + 1] = product % 10;
                    result[i + j] += product / 10;
                }
            }
            StringBuilder sb = new StringBuilder();
            for (int r : result) {
                if (sb.length() == 0 && r == 0) {
                    continue;
                }
                sb.append(r);
            }

            return sb.toString();
        }
    }
}
