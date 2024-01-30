public class StudentAttendanceRecordI {
    /*
        You are given a string s representing an attendance record for a student where each character signifies whether the student was absent,
        late, or present on that day. The record only contains the following three characters:

        'A': Absent.
        'L': Late.
        'P': Present.
        The student is eligible for an attendance award if they meet both of the following criteria:

        The student was absent ('A') for strictly fewer than 2 days total.
        The student was never late ('L') for 3 or more consecutive days.
        Return true if the student is eligible for an attendance award, or false otherwise.

        Example 1:
        Input: s = "PPALLP"
        Output: true
        Explanation: The student has fewer than 2 absences and was never late 3 or more consecutive days.
        Example 2:
        Input: s = "PPALLL"
        Output: false
        Explanation: The student was late 3 consecutive days in the last 3 days, so is not eligible for the award.


        Constraints:

        1 <= s.length <= 1000
        s[i] is either 'A', 'L', or 'P'.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.checkRecord("PPALLP"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static boolean checkRecord(String s) {
            int a=0;
            int l=0;
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)=='A') {
                    a++;
                    if(l<3)
                        l=0;
                }
                else if(s.charAt(i)=='L'){
                    l++;
                }
                else {
                    if (l < 3)
                        l = 0;
                }
            }
            return a < 2 && l < 3;
        }
    }
}
