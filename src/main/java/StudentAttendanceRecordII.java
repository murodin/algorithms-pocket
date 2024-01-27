public class StudentAttendanceRecordII {
    /*
        An attendance record for a student can be represented as a string where each character signifies whether the student was absent,
        late, or present on that day. The record only contains the following three characters:
            'A': Absent.
            'L': Late.
            'P': Present.
            Any student is eligible for an attendance award if they meet both of the following criteria:

            The student was absent ('A') for strictly fewer than 2 days total.
            The student was never late ('L') for 3 or more consecutive days.
        Given an integer n, return the number of possible attendance records of length n that make a student eligible for an attendance award.
        The answer may be very large, so return it modulo 109 + 7.

        Example 1:
        Input: n = 2
        Output: 8
        Explanation: There are 8 records with length 2 that are eligible for an award:
        "PP", "AP", "PA", "LP", "PL", "AL", "LA", "LL"
        Only "AA" is not eligible because there are 2 absences (there need to be fewer than 2).
        Example 2:
        Input: n = 1
        Output: 3
        Example 3:

        Input: n = 10101
        Output: 183236316


        Constraints:

        1 <= n <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.checkRecord(2));
    }

    // Time: O(N)
    // Space: O(N^3)
    static class Solution {
        static private final int MOD = 1000000007;
        public static int checkRecord(int n) {
            return (int)(count(n, 0, 0, new Long[n + 1][2][3]) % MOD);
        }

        private static long count(int n, int absentCount, int consecutiveLate, Long[][][] memo){
            if(absentCount == 2 || consecutiveLate == 3) return 0;
            if(n == 0) return 1;

            if(memo[n][absentCount][consecutiveLate] != null)
                return memo[n][absentCount][consecutiveLate];

            long present = count(n - 1, absentCount, 0, memo) % MOD;
            long absent = count(n - 1, absentCount + 1, 0, memo) % MOD;
            long late = count(n - 1, absentCount, consecutiveLate + 1, memo) % MOD;

            return memo[n][absentCount][consecutiveLate] = (present + absent + late) % MOD;
        }
    }
}
