public class ElementAppearingMoreThanTwentyFivePercentInSortedArray {
    /*
        Given an integer array sorted in non-decreasing order, there is exactly one integer in the array that occurs more than 25% of the time, return that integer.

        Example 1:
        Input: arr = [1,2,2,6,6,6,6,7,10]
        Output: 6
        Example 2:
        Input: arr = [1,1]
        Output: 1


        Constraints:

        1 <= arr.length <= 104
        0 <= arr[i] <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findSpecialInteger(new int[]{1,2,2,6,6,6,6,7,10}));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int findSpecialInteger(int[] arr) {
            int size = arr.length;
            int qtr = size / 4;
            int cnt = 1;
            int p = arr[0];
            for (int i = 1 ; i < arr.length ; i++) {
                if ( p == arr[i]) cnt++;
                else cnt = 1;
                if (cnt > qtr) return arr[i];
                p = arr[i];
            }
            return p;
        }
    }
}
