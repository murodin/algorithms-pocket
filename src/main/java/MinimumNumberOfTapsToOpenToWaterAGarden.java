public class MinimumNumberOfTapsToOpenToWaterAGarden {
    /*
        There is a one-dimensional garden on the x-axis.
        The garden starts at the point 0 and ends at the point n. (i.e The length of the garden is n).
        There are n + 1 taps located at points [0, 1, ..., n] in the garden.

        Given an integer n and an integer array ranges of length n + 1
        where ranges[i] (0-indexed) means the i-th tap can water the area [i - ranges[i], i + ranges[i]] if it was open.

        Return the minimum number of taps that should be open to water the whole garden, If the garden cannot be watered return -1.

        Example 1.
        Input: n = 5, ranges = [3,4,1,1,0,0]
        Output: 1
        Explanation: The tap at point 0 can cover the interval [-3,3]
        The tap at point 1 can cover the interval [-3,5]
        The tap at point 2 can cover the interval [1,3]
        The tap at point 3 can cover the interval [2,4]
        The tap at point 4 can cover the interval [4,4]
        The tap at point 5 can cover the interval [5,5]
        Opening Only the second tap will water the whole garden [0,5]
        Example 2.
        Input: n = 3, ranges = [0,0,0,0]
        Output: -1
        Explanation: Even if you activate all the four taps you cannot water the whole garden.


        Constraints:

        1 <= n <= 104
        ranges.length == n + 1
        0 <= ranges[i] <= 100
     */

    public static void main(String[] args) {
        int[] ranges = {3,4,1,1,0,0};
        System.out.println("Min. Numbers of Taps: " + Solution_I.minTaps(5, ranges));
        System.out.println("Min. Numbers of Taps: " + Solution_II.minTaps(5, ranges));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_I{
        public static int minTaps(int n, int[] ranges) {
            int[] arr = new int[n+1];
            for (int i = 0; i < n+1 ; i++) {
                arr[i] = i;
                int start = Math.max(0, i-ranges[i]), end = Math.min(i+ranges[i], n);
                arr[start] = Math.max(arr[start], end);
            }
            int cnt = 0;
            int right = arr[0];
            int next = 0;
            for (int i = 0; i < n + 1; i++) {
                next = Math.max(arr[i], next);
                if (i == right) {
                    cnt++;
                    right = next;
                }
            }
            return right == n ? cnt : -1;
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_II {
        public static int minTaps(int n, int[] ranges) {
            int[] dp = new int[ranges.length]; // dp[i] will tell min taps require to water upto index i

            for(int i = 1; i < dp.length; i++) {
                dp[i] = ranges.length + 1; // make all values (not first, because taps require to water index 0 is 0, by above definitition of dp[]) to Infinite
            }

            for(int i = 0; i < ranges.length; i++) { // all the elements in ranges
                // find range [start, end]
                int start = Math.max(i - ranges[i], 0); // the MAX minima upto where the tap can water (if less than 0, then MAX is 0)
                int end = Math.min(i + ranges[i], ranges.length - 1); // the MIN maxima upto which a tap can water is 'n'

                for(int j = start; j <= end; j++) { // range upto which we can water from tap at index 'start'
                    dp[j] = Math.min(dp[j], dp[start] + 1); // every index (within range) from start can be reached by 1 tap
                }
            }

            return dp[ranges.length - 1] == ranges.length + 1 ? -1 : dp[ranges.length - 1];
        }
    }
}
