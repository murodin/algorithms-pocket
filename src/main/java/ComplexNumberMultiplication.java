public class ComplexNumberMultiplication {
    /*
        A complex number can be represented as a string on the form "real+imaginaryi" where:

        real is the real part and is an integer in the range [-100, 100].
        imaginary is the imaginary part and is an integer in the range [-100, 100].
        i2 == -1.
        Given two complex numbers num1 and num2 as strings, return a string of the complex number that represents their multiplications.

        Example 1:
        Input: num1 = "1+1i", num2 = "1+1i"
        Output: "0+2i"
        Explanation: (1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i, and you need convert it to the form of 0+2i.
        Example 2:
        Input: num1 = "1+-1i", num2 = "1+-1i"
        Output: "0+-2i"
        Explanation: (1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i, and you need convert it to the form of 0+-2i.


        Constraints:

        num1 and num2 are valid complex numbers.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.complexNumberMultiply("1+1i", "1+1i"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static String complexNumberMultiply(String num1, String num2) {
            String[] one = num1.split("\\+");
            String[] two = num2.split("\\+");
            int a = Integer.parseInt(one[0]);
            int b = Integer.parseInt(one[1].replace("i",""));
            int c = Integer.parseInt(two[0]);
            int d = Integer.parseInt(two[1].replace("i",""));
            return a * c - b * d +"+"+ (b * c + a * d) +"i";
        }
    }
}
