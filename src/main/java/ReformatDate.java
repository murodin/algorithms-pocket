public class ReformatDate {
    /*
        Given a date string in the form Day Month Year, where:
        Day is in the set {"1st", "2nd", "3rd", "4th", ..., "30th", "31st"}.
        Month is in the set {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}.
        Year is in the range [1900, 2100].
        Convert the date string to the format YYYY-MM-DD, where:

        YYYY denotes the 4 digit year.
        MM denotes the 2 digit month.
        DD denotes the 2 digit day.


        Example 1:
        Input: date = "20th Oct 2052"
        Output: "2052-10-20"
        Example 2:
        Input: date = "6th Jun 1933"
        Output: "1933-06-06"
        Example 3:
        Input: date = "26th May 1960"
        Output: "1960-05-26"


        Constraints:

        The given dates are guaranteed to be valid, so no error handling is necessary.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.reformatDate("26th May 1960"));
    }

    // Time: O(1)
    // Space: O(1)
    static class Solution {
        public static String reformatDate(String date) {
            String[] str = date.split(" ");
            StringBuilder res = new StringBuilder();
            res.append(str[2]);
            String month = str[1].equals("Jan") ? "01"
                    : str[1].equals("Feb") ? "02"
                    : str[1].equals( "Mar" )? "03"
                    : str[1].equals("Apr") ? "04"
                    : str[1].equals("May") ? "05"
                    : str[1].equals("Jun") ? "06"
                    : str[1].equals("Jul") ? "07"
                    : str[1].equals("Aug") ? "08"
                    : str[1].equals("Sep") ? "09"
                    : str[1].equals("Oct") ? "10"
                    : str[1].equals("Nov") ? "11"
                    : "12";
            res.append("-");
            res.append(month);
            res.append("-");
            if(str[0].length() == 3) {
                res.append("0");
                res.append(str[0].charAt(0));
            } else {
                res.append(str[0].charAt(0));
                res.append(str[0].charAt(1));
            }
            return res.toString();
        }
    }
}
