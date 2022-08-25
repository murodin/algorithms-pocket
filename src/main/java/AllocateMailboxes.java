import java.util.Arrays;

public class AllocateMailboxes {
    /*
        Given the array houses where houses[i] is the location of the ith house along a street and an integer k, allocate k mailboxes in the street.
        Return the minimum total distance between each house and its nearest mailbox.
        The test cases are generated so that the answer fits in a 32-bit integer.

        Example 1.
        Input: houses = [1,4,8,10,20], k = 3
        Output: 5
        Explanation: Allocate mailboxes in position 3, 9 and 20.
        Minimum total distance from each houses to nearest mailboxes is |3-1| + |4-3| + |9-8| + |10-9| + |20-20| = 5
        Example 2.
        Input: houses = [2,3,5,12,18], k = 2
        Output: 9
        Explanation: Allocate mailboxes in position 3 and 14.
        Minimum total distance from each houses to nearest mailboxes is |2-3| + |3-3| + |5-3| + |12-14| + |18-14| = 9.

        Constraints:

        1 <= k <= houses.length <= 100
        1 <= houses[i] <= 104
        All the integers of houses are unique.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minDistance(new int[]{2,3,5,12,18}, 2));
    }

    // Time:: O(k * N^2), where N is the number of houses
    // Space: O(N^2)
    static class Solution {
        public static int minDistance(int[] houses, int k) {
            Arrays.sort(houses);

            // place[i][j] is the distance to place a box to cover house i to j
            int[][] place = new int[houses.length][houses.length];
            for (int i = 0; i < houses.length; ++i) {
                for (int j = i + 1; j < houses.length; ++j) {
                    place[i][j] = placeMailBox(houses, i, j);
                }
            }

            // distance[i][j] is the distance to place j boxes to cover house i to n - 1
            int[][] distance = new int[houses.length][k + 1];
            // iterate over i
            for (int i = houses.length - 1; i >= 0; --i) {
                distance[i][1] = place[i][houses.length - 1];

                // iterate over j
                for (int j = Math.max(2, k - i); j <= houses.length - i && j <= k; ++j) {
                    distance[i][j] = Integer.MAX_VALUE;
                    // iterate over the range the first box cover
                    for (int m = i; m < houses.length - (j - 1); ++m) {
                        distance[i][j] = Math.min(distance[i][j], place[i][m] + distance[m + 1][j - 1]);
                    }
                }
            }

            return distance[0][k];
        }

        private static int placeMailBox(int[] houses, int start, int end) {
            // median is the best position to place mail box
            int median = (start + end) / 2;
            int distance = 0;
            for (int i = start; i <= end; ++i) {
                if (i <= median) {
                    distance += houses[median] - houses[i];
                } else {
                    distance += houses[i] - houses[median];
                }
            }

            return distance;
        }
    }
}
