import java.util.Arrays;

public class MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts {

    /*
        You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:
        horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
        verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
        Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts.
        Since the answer can be a large number, return this modulo 109 + 7.

        Example 1.
        Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
        Output: 4
        Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
        Example 2.
        Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
        Output: 6
        Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.
        Example 3.
        Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
        Output: 9


        Constraints:

        2 <= h, w <= 109
        1 <= horizontalCuts.length <= min(h - 1, 105)
        1 <= verticalCuts.length <= min(w - 1, 105)
        1 <= horizontalCuts[i] < h
        1 <= verticalCuts[i] < w
        All the elements in horizontalCuts are distinct.
        All the elements in verticalCuts are distinct.
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxArea(5, 4, new int[] {1,2,4}, new int[] {1, 3}));
    }

    // Time: O(NLogN)
    // Space: O(1)
    static class Solution {
        public static int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
            // Max Difference between any two horizontal cuts * Max Difference between any two vertical cuts will give the answer
            Arrays.sort(horizontalCuts); // to get consecutive cuts
            Arrays.sort(verticalCuts);
            long mod = (long)1e9+7;
            long a = 0,b = 0; // a = max diff of horizontal cuts b = max diff of vertical cuts
            int prev =0; // two edges of cake (0 - first cut)
            a = Math.max(a,h-horizontalCuts[horizontalCuts.length-1]); // and (edge - last cut) of the cake
            b = Math.max(b,w-verticalCuts[verticalCuts.length-1]);
            for(int i:horizontalCuts){
                a = Math.max(a,i-prev);
                prev = i;
            }
            prev = 0;
            for(int i:verticalCuts){
                b = Math.max(b,i-prev);
                prev = i;
            }
            return (int)((a*b)%mod);
        }
    }
}
