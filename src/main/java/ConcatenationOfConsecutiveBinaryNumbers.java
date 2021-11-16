public class ConcatenationOfConsecutiveBinaryNumbers {

    /*
        Given an integer n, return the decimal value of the binary string formed by concatenating the binary representations of 1 to n in order, modulo 10^9 + 7.

        Example 1.
        Input: n = 1
        Output: 1
        Explanation: "1" in binary corresponds to the decimal value 1.
        Example 2.
        Input: n = 3
        Output: 27
        Explanation: In binary, 1, 2, and 3 corresponds to "1", "10", and "11".
        After concatenating them, we have "11011", which corresponds to the decimal value 27.
        Example 3.
        Input: n = 12
        Output: 505379714
        Explanation: The concatenation results in "1101110010111011110001001101010111100".
        The decimal value of that is 118505380540.
        After modulo 10^9 + 7, the result is 505379714.
     */

    public static void main(String[] args) {
        System.out.println("Solution I: " + Solution_I.concatenatedBinary(12));
        System.out.println("Solution II: " + Solution_II.concatenatedBinary(12));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_I {
        public static int concatenatedBinary(int n) {
            long res = 0;
            int mod = 1_000_000_007;
            for(int i = 1; i<=n; i++){
                String binaryString = Integer.toBinaryString(i);
                res = (res<<binaryString.length())%mod;
                res = (res+i)%mod;
            }
            return (int)res;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static int concatenatedBinary(int n) {
            long res = 0;
            int mod = 1_000_000_007;
            int size=0;
            for(int i = 1; i<=n; i++){
                if((i&(i-1))==0) size++;
                res = (res<<size | i) %mod;
            }
            return (int)res;
        }
    }
}
