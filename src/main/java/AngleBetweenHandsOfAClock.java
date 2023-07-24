public class AngleBetweenHandsOfAClock {
    /*
        Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.
        Answers within 10-5 of the actual value will be accepted as correct.

        Example 1.
        Input: hour = 12, minutes = 30
        Output: 165
        Example 2.
        Input: hour = 3, minutes = 30
        Output: 75
        Example 3.
        Input: hour = 3, minutes = 15
        Output: 7.5


        Constraints:

        1 <= hour <= 12
        0 <= minutes <= 59
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.angleClock(3, 30));
    }

    // Time: O(1)
    // Space: O(1)
    static class Solution {
        public static double angleClock(int hour, int minutes) {
            double h = (hour%12 * 30) + ((double)minutes/60 * 30);
            double m = minutes * 6;

            double angle = Math.abs(m - h);
            if (angle > 180) angle = 360.0 - angle;

            return angle;
        }
    }
}
