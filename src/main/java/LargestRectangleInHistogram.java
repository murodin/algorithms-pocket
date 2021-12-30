import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleInHistogram {
    /*
        Given an array of integers heights representing the histogram's bar height
         where the width of each bar is 1, return the area of the largest rectangle in the histogram.

        Example 1.
        Input: heights = [2,1,5,6,2,3]
        Output: 10
        Explanation: The above is a histogram where width of each bar is 1.
        The largest rectangle is shown in the red area, which has an area = 10 units.
        Example 2.
        Input: heights = [2,4]
        Output: 4


        Constraints:

        1 <= heights.length <= 105
        0 <= heights[i] <= 104

     */

    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        System.out.println("Largest rectangle area: " + Solution_I.largestRectangleArea(heights));
        System.out.println("Largest rectangle area: " + Solution_II.largestRectangleArea(heights));

    }

    // Time: O(N) average
    // Space: O(N)
    static class Solution_I {
        public static int largestRectangleArea(int[] heights) {
            int n = heights.length;
            if(n == 0) return 0;

            int maxArea = 0;
            int[] left = new int[n];
            int[] right = new int[n];
            left[0] = -1;
            right[n-1] = n;

            for(int i = 1; i < n; i++) {
                int prev = i-1;
                while (prev >= 0 && heights[prev] >= heights[i]) {
                    prev = left[prev];
                }
                left[i] = prev;
            }

            for(int i = n-2; i >= 0; i--) {
                int prev = i+1;
                while (prev < n && heights[prev] >= heights[i]) {
                    prev = right[prev];
                }
                right[i] = prev;
            }

            System.out.println("rigth:" + Arrays.toString(right));

            for(int i = 0; i < n; i++) {
                int width = right[i] - left[i] -1;
                maxArea = Math.max(maxArea, heights[i]*width);
            }

            return maxArea;
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_II {
        public static int largestRectangleArea(int[] heights) {
            int n = heights.length;
            if(n == 0) return 0;

            int maxArea = 0;
            Stack<Integer> s = new Stack<>();

            for(int i = 0; i <= n; i++) {
                int currHeight = i == n ? 0: heights[i];
                // currHeight > heights[top] ? push(i): pop & find area
                while (!s.isEmpty() && currHeight < heights[s.peek()]) {
                    int top = s.pop();
                    int width = s.isEmpty() ? i: i-s.peek()-1;
                    int area = heights[top]*width;
                    maxArea = Math.max(area, maxArea);
                }
                s.push(i);
            }
            return maxArea;
        }
    }
}
