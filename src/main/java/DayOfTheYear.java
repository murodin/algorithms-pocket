public class DayOfTheYear {
    /*
        Given a string date representing a Gregorian calendar date formatted as YYYY-MM-DD, return the day number of the year.

        Example 1:
        Input: date = "2019-01-09"
        Output: 9
        Explanation: Given date is the 9th day of the year in 2019.
        Example 2:
        Input: date = "2019-02-10"
        Output: 41


        Constraints:

        date.length == 10
        date[4] == date[7] == '-', and all other date[i]'s are digits
        date represents a calendar date between Jan 1st, 1900 and Dec 31th, 2019.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.dayOfYear("2019-02-10"));
    }

    // Time: O(N)
    // Space: O(1)
   static class Solution {
        static int[] days = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        static {
            for (int i = 1; i < days.length; i++) days[i] += days[i - 1];
        }
        public static int dayOfYear(String date) {
            String[] d = date.split("-");
            int year = Integer.parseInt(d[0]), month = Integer.parseInt(d[1]) - 1, day = Integer.parseInt(d[2]);
            return isLeapYear(year) && month > 1 ? days[month] + day + 1 : days[month] + day;
        }
        private static boolean isLeapYear(long year) {
            return ((year & 3) == 0) && ((year % 100) != 0 || (year % 400) == 0);
        }
    }
}
