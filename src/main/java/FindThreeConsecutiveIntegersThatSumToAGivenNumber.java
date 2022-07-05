import java.util.Arrays;

public class FindThreeConsecutiveIntegersThatSumToAGivenNumber {
    /*
        Given an integer num, return three consecutive integers (as a sorted array) that sum to num.
        If num cannot be expressed as the sum of three consecutive integers, return an empty array.

        Example 1.
        Input: num = 33
        Output: [10,11,12]
        Explanation: 33 can be expressed as 10 + 11 + 12 = 33.
        10, 11, 12 are 3 consecutive integers, so we return [10, 11, 12].
        Example 2.
        Input: num = 4
        Output: []
        Explanation: There is no way to express 4 as the sum of 3 consecutive integers.


        Constraints:

        0 <= num <= 1015
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.sumOfThree(33)));
    }

    // Time: O(1)
    // Space: O(1)
    static class Solution {
        public static long[] sumOfThree(long num) {
            if(num % 3 != 0)
                return new long[0];
            long mid = num / 3;
            return new long[] {mid - 1, mid, mid + 1};
        }
    }
}
