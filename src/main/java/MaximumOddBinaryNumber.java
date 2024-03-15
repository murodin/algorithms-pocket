public class MaximumOddBinaryNumber {
    /*
        You are given a binary string s that contains at least one '1'.
        You have to rearrange the bits in such a way that the resulting binary number is the maximum odd binary number that can be created from this combination.
        Return a string representing the maximum odd binary number that can be created from the given combination.
        Note that the resulting string can have leading zeros.

        Example 1:
        Input: s = "010"
        Output: "001"
        Explanation: Because there is just one '1', it must be in the last position. So the answer is "001".
        Example 2:
        Input: s = "0101"
        Output: "1001"
        Explanation: One of the '1's must be in the last position. The maximum number that can be made with the remaining digits is "100". So the answer is "1001".


        Constraints:

        1 <= s.length <= 100
        s consists only of '0' and '1'.
        s contains at least one '1'.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maximumOddBinaryNumber("0101"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static String maximumOddBinaryNumber(String s) {
            int one = 0, zero = 0;
            for(int i = 0; i< s.length() ; i++){
                if(s.charAt(i) == '1'){
                    one++;
                }
            }
            zero = s.length() - one;
            StringBuilder sb = new StringBuilder();
            sb.append("1".repeat(Math.max(0, one - 1)));
            sb.append("0".repeat(Math.max(0, zero)));
            sb.append("1");
            return sb.toString();

        }
    }
}
