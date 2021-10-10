public class BrokenCalculator {

    /*
        There is a broken calculator that has the integer startValue on its display initially. In on operation you can:
        multiply the number on the display by 2, or
        subtract 1 from the number on the display.
        Given two integers startValue and target, return the minimum number of operations needed to display target on the calculator.

        Example 1.
        Input: startValue = 2, target = 3
        Output: 2
        Explanation: Use double operation and then decrement operation {2 -> 4 -> 3}.
        Example 2.
        Input: startValue = 5, target = 8
        Output: 2
        Explanation: Use decrement and then double {5 -> 4 -> 8}.
        Example 3.
        Input: startValue = 3, target = 10
        Output: 3
        Explanation: Use double, decrement and double {3 -> 6 -> 5 -> 10}.
        Example 4.
        Input: startValue = 1024, target = 1
        Output: 1023
        Explanation: Use decrement operations 1023 times.
     */

    public static void main(String[] args) {
        int start = 5, target = 8;
        System.out.println("Solution I: " + Solution_I.brokenCalc(start, target));
        System.out.println("Solution II: " + Solution_II.brokenCalc(start, target));
    }

    // Time: O(LogN + N), N ~ Y
    // Space: O(1)
    static class Solution_I {
        public static int brokenCalc(int X, int Y) {
            if(X>=Y) return X-Y;

            if(Y%2==0){
                return 1 + brokenCalc(X,Y/2);
            }

            return 1 + brokenCalc(X,Y+1);
        }
    }

    // Time: O(LogN), N ~ Y
    // Space: O(1)
    static class Solution_II {
        public static int brokenCalc(int X, int Y) {
            int result=0;

            while(Y>X){
                result++;
                if(Y%2==0){
                    Y/=2;
                } else{
                    Y++;
                }
            }

            return result+(X-Y);
        }
    }
}
