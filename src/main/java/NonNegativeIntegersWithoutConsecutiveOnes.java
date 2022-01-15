public class NonNegativeIntegersWithoutConsecutiveOnes {
    /*
        Given a positive integer n, return the number of the integers in the range [0, n] whose binary representations do not contain consecutive ones.

        Example 1.
        Input: n = 5
        Output: 5
        Explanation:
        Here are the non-negative integers <= 5 with their corresponding binary representations:
        0 : 0
        1 : 1
        2 : 10
        3 : 11
        4 : 100
        5 : 101
        Among them, only integer 3 disobeys the rule (two consecutive ones) and the other 5 satisfy the rule.
        Example 2.
        Input: n = 1
        Output: 2
        Example 3:

        Input: n = 2
        Output: 3
     */

    public static void main(String[] args) {
        System.out.println("Non Negative Integers Without Consecutive Ones: " + Solution.findIntegers(5));
    }

    // Time: O(LogN)
    // Space: O(LogN)
    static class Solution {
        static int[] dp;
        public static int findIntegers(int n) {
            int ones = helper(n);
            return n - ones + 1;
        }

        static int helper(int n){
            double pow = Math.log(n)/Math.log(2);
            if(pow == (int)pow) return dp[(int)pow];
            pow = Math.floor(pow);
            int ans = dp[(int) pow];

            int lowestNumber = 1<<(int)pow;

            int left = n - lowestNumber;
            if(left>=lowestNumber/2) ans+= dp[(int)pow - 1] + (left - lowestNumber/2 + 1);
            else ans+=helper(left);
            return ans;
        }

        static{
            dp = new int[33];
            dp[0] = 0;
            dp[1] = 0;
            int val = 1;
            for(int i=2;i<33;i++){
                dp[i] = dp[i-1] + dp[i-2] + val;
                val=val<<1;
            }
        }
    }
}
