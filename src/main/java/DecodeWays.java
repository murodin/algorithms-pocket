import java.util.HashMap;

public class DecodeWays {
    /*
        A message containing letters from A-Z can be encoded into numbers using the following mapping:
        'A' -> "1"
        'B' -> "2"
        ...
        'Z' -> "26"
        To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways).
        For example, "11106" can be mapped into:

        "AAJF" with the grouping (1 1 10 6)
        "KJF" with the grouping (11 10 6)
        Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".

        Given a string s containing only digits, return the number of ways to decode it.

        The test cases are generated so that the answer fits in a 32-bit integer.

        Example 1.
        Input: s = "12"
        Output: 2
        Explanation: "12" could be decoded as "AB" (1 2) or "L" (12).
        Example 2.
        Input: s = "226"
        Output: 3
        Explanation: "226" could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
        Example 3.
        Input: s = "06"
        Output: 0
        Explanation: "06" cannot be mapped to "F" because of the leading zero ("6" is different from "06").

        Constraints:

        1 <= s.length <= 100
        s contains only digits and may contain leading zero(s).
     */

    public static void main(String[] args) {
        String s = "226";
        System.out.println("Solution I: " + Solution_I.numDecodings(s));
        System.out.println("Solution II: " + Solution_II.numDecodings(s));
        System.out.println("Solution III: " + Solution_III.numDecodings(s));
        System.out.println("Solution IV: " + Solution_IV.numDecodings(s));
    }

    // Time: O(2^N)
    // Space: O(1)
    static class Solution_I {
        static int result = 0;
        public static int numDecodings(String s) {
            find(s);
            return result;
        }

        private static void find(String s) {
            if(s.startsWith("0")) return;
            if(s.length() <= 1) {
                result++;
                return;
            }
            for(int i = 0; i<Math.min(2, s.length()); i++) {
                int val = Integer.parseInt(s.substring(0, i+1));
                if(val >= 1 && val <= 26) {
                    find(s.substring(i+1));
                }
            }
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_II {
        static int result = 0;
        static HashMap<String, Integer> map = null;
        public static int numDecodings(String s) {
            map = new HashMap<>();
            find(s);
            return result;
        }

        private static void find(String s) {
            if(s.startsWith("0")) return;
            if(s.length() <= 1) {
                result++;
                return;
            }

            if(map.containsKey(s)) {
                result += map.get(s);
                return;
            }
            for(int i = 0; i<Math.min(2, s.length()); i++) {
                int val = Integer.parseInt(s.substring(0, i+1));
                if(val >= 1 && val <= 26) {
                    find(s.substring(i+1));
                }
                map.put(s.substring(0, i+1), result);
            }
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_III {
        public static int numDecodings(String s) {
            if(s==null || s.length() == 0) return 0;

            int[] dp = new int[s.length()+1];
            dp[0] = 1;
            dp[1] = s.charAt(0) == '0'? 0:1;

            for(int i=2; i <= s.length(); i++) {
                int lenOne = Integer.parseInt(s.substring(i-1, i));
                int lenTwo = Integer.parseInt(s.substring(i-2, i));

                if(lenOne >= 1 && lenOne <= 9) {
                    dp[i] += dp[i-1];
                }

                if(lenTwo >= 10 && lenTwo <= 26) {
                    dp[i] += dp[i-2];
                }
            }
            return dp[s.length()];
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_IV {
        public static int numDecodings(String s) {
            if(s==null || s.length() == 0) return 0;
            if(s.charAt(0) == '0') return 0;

            int dp1 = 1;
            int dp2 = s.charAt(0) == '0'? 0:1;

            for(int i=2; i <= s.length(); i++) {
                int lenOne = Integer.parseInt(s.substring(i-1, i));
                int lenTwo = Integer.parseInt(s.substring(i-2, i));

                int dp = 0;
                if(lenOne >= 1 && lenOne <= 9) {
                    dp += dp1;
                }

                if(lenTwo >= 10 && lenTwo <= 26) {
                    dp += dp2;
                }

                dp2 = dp1;
                dp1 = dp;
            }
            return dp1;
        }
    }
}
