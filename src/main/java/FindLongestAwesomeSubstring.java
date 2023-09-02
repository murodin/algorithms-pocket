import java.util.Arrays;

public class FindLongestAwesomeSubstring {
    /*
        You are given a string s. An awesome substring is a non-empty substring of s such that we can make any number of swaps in order to make it a palindrome.

        Return the length of the maximum length awesome substring of s.

        Example 1:

        Input: s = "3242415"
        Output: 5
        Explanation: "24241" is the longest awesome substring, we can form the palindrome "24142" with some swaps.
        Example 2:

        Input: s = "12345678"
        Output: 1
        Example 3:

        Input: s = "213123"
        Output: 6
        Explanation: "213123" is the longest awesome substring, we can form the palindrome "231132" with some swaps.


        Constraints:

        1 <= s.length <= 105
        s consists only of digits.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.longestAwesome("3242415"));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static int longestAwesome(String s) {
            int res = 0;
            int cur = 0;
            int n = s.length();
            int[] seen = new int[1024];
            Arrays.fill(seen, n);
            seen[0] = -1;
            for (int i = 0; i < n; ++i) {
                cur ^= 1 << (s.charAt(i) - '0');
                for (int a = 0; a < 10; ++a)
                    res = Math.max(res, i - seen[cur ^ (1 << a)]);
                res = Math.max(res, i - seen[cur]);
                seen[cur] = Math.min(seen[cur], i);
            }
            return res;
        }
    }
}
