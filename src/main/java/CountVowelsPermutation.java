public class CountVowelsPermutation {
    /*
        Given an integer n, your task is to count how many strings of length n can be formed under the following rules:

        Each character is a lower case vowel ('a', 'e', 'i', 'o', 'u')
        Each vowel 'a' may only be followed by an 'e'.
        Each vowel 'e' may only be followed by an 'a' or an 'i'.
        Each vowel 'i' may not be followed by another 'i'.
        Each vowel 'o' may only be followed by an 'i' or a 'u'.
        Each vowel 'u' may only be followed by an 'a'.
        Since the answer may be too large, return it modulo 10^9 + 7.

        Example 1.
        Input: n = 1
        Output: 5
        Explanation: All possible strings are: "a", "e", "i" , "o" and "u".
        Example 2.
        Input: n = 2
        Output: 10
        Explanation: All possible strings are: "ae", "ea", "ei", "ia", "ie", "io", "iu", "oi", "ou" and "ua".
        Example 3:

        Input: n = 5
        Output: 68


        Constraints:

        1 <= n <= 2 * 10^4
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.countVowelPermutation(5));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int countVowelPermutation(int n) {
            // long dp of length n used to handle big test cases and using mod efficiently
            long []dp = new long[n];
            long mod = (long)1e9+7;
            // base case
            long ia = 1,ie = 1,ii = 1,io = 1,iu = 1;
            dp[0] = 5;

            for( int i=1; i<n; i++ ){
                // simply calculating current strings of length i by using previous string and current options values
                long tot = ((ia*1) + (ie*2) + (ii*4) + (io*2) + (iu*1))%mod;

                // finding out new values of strings ending with a specific character by using previous values
                long na = (ie+ii+iu)%mod;
                long ne = (ia + ii)%mod;
                long ni = (ie + io)%mod;
                long no = ii%mod;
                long nu = (ii+io)%mod;

                ia = na;
                ie = ne;
                ii = ni;
                io = no;
                iu = nu;

                // assigning value to dp
                dp[i] = tot;
            }

            return (int)dp[n-1];
        }
    }
}
