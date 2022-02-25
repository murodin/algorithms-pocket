public class PairsOfSongsWithTotalDurationDivisibleBy60 {

    /*
        You are given a list of songs where the ith song has a duration of time[i] seconds.

        Return the number of pairs of songs for which their total duration in seconds is divisible by 60.
        Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.

        Example 1.
        Input: time = [30,20,150,100,40]
        Output: 3
        Explanation: Three pairs have a total duration divisible by 60:
        (time[0] = 30, time[2] = 150): total duration 180
        (time[1] = 20, time[3] = 100): total duration 120
        (time[1] = 20, time[4] = 40): total duration 60
        Example 2.
        Input: time = [60,60,60]
        Output: 3
        Explanation: All three pairs have a total duration of 120, which is divisible by 60.

        Constraints:

        1 <= time.length <= 6 * 104
        1 <= time[i] <= 500
     */

    public static void main(String[] args) {
        int[] time1 = {30,20,150,100,40};
        System.out.println("Solution: " + Solution.numPairsDivisibleBy60(time1));

        int[] time2 = {60,60,60};
        System.out.println("Solution: " + Solution.numPairsDivisibleBy60(time2));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int numPairsDivisibleBy60(int[] time) {
            int[] count = new int[60];

            for(int t:time)
                count[t%60]++;

            int pair = 0;

            for(int i=1; i<30; i++)
                pair += count[i]*count[60-i];

            pair += sumOfN(count[0]-1) + sumOfN(count[30]-1);
            return pair;
        }

        static int sumOfN(int n) {
            return n*(n+1)/2;
        }
    }
}
