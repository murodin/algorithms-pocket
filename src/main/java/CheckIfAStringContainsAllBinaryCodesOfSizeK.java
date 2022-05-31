public class CheckIfAStringContainsAllBinaryCodesOfSizeK {

    /*
        Given a binary string s and an integer k, return true if every binary code of length k is a substring of s. Otherwise, return false.

        Example 1.
        Input: s = "00110110", k = 2
        Output: true
        Explanation: The binary codes of length 2 are "00", "01", "10" and "11". They can be all found as substrings at indices 0, 1, 3 and 2 respectively.
        Example 2.
        Input: s = "0110", k = 1
        Output: true
        Explanation: The binary codes of length 1 are "0" and "1", it is clear that both exist as a substring.
        Example 3.
        Input: s = "0110", k = 2
        Output: false
        Explanation: The binary code "00" is of length 2 and does not exist in the array.


        Constraints:

        1 <= s.length <= 5 * 105
        s[i] is either '0' or '1'.
        1 <= k <= 20
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.hasAllCodes("00110110", 2));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        final static int base = '0';
        public static boolean hasAllCodes(String s, int k) {
            if (s.length() < k) return false;
            int total = 1 << k;
            boolean[] map = new boolean[total];
            int mask = total - 1;
            int value = Integer.parseInt(s.substring(0, k), 2);
            map[value] = true;
            --total;
            for (int i = k; i < s.length(); i++) {
                value = (value << 1) + (s.charAt(i) - base);
                value &= mask;
                if (!map[value]) {
                    map[value] = true;
                    if (--total == 0) return true;
                }
            }
            return false;
        }
    }
}
