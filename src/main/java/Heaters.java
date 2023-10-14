import java.util.Arrays;

public class Heaters {
    /*
        Winter is coming! During the contest, your first job is to design a standard heater with a fixed warm radius to warm all the houses.
        Every house can be warmed, as long as the house is within the heater's warm radius range.
        Given the positions of houses and heaters on a horizontal line, return the minimum radius standard of heaters so that those heaters could cover all houses.
        Notice that all the heaters follow your radius standard, and the warm radius will the same.

        Example 1:
        Input: houses = [1,2,3], heaters = [2]
        Output: 1
        Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
        Example 2:
        Input: houses = [1,2,3,4], heaters = [1,4]
        Output: 1
        Explanation: The two heaters were placed at positions 1 and 4. We need to use a radius 1 standard, then all the houses can be warmed.
        Example 3:
        Input: houses = [1,5], heaters = [2]
        Output: 3


        Constraints:

        1 <= houses.length, heaters.length <= 3 * 104
        1 <= houses[i], heaters[i] <= 109
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findRadius(new int[]{1,5}, new int[]{2}));
    }

    // Time: O(NLogN)
    // Space: O(1)
    static class Solution {
        public static int findRadius(int[] houses, int[] heaters) {
            Arrays.sort(heaters);
            int result = Integer.MIN_VALUE;
            for (int house : houses) {
                int index = Arrays.binarySearch(heaters, house);
                if (index < 0) {
                    index = -(index + 1);
                }
                int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
                int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;

                result = Math.max(result, Math.min(dist1, dist2));
            }

            return result;
        }
    }
}
