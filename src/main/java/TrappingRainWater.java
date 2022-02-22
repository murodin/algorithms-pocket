import java.util.Stack;

public class TrappingRainWater {

    /*
        Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.

        Example 1.
        Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
        Output: 6
        Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
        Example 2.
        Input: height = [4,2,0,3,2,5]
        Output: 9

        Constraints:

        n == height.length
        1 <= n <= 2 * 104
     */

    public static void main(String[] args) {
        int[] height = {4,2,0,3,2,5};
        System.out.println("Solution I: " + Solution_I.trap(height));
        System.out.println("Solution II: " + Solution_II.trap(height));
        System.out.println("Solution III: " + Solution_III.trap(height));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static int trap(int[] height) {
            int n = height.length;
            int[] leftmax = new int[n];
            int[] rightmax = new int[n];
            int water = 0;

            for(int i = 0; i<n; i++) {
                if(i == 0) {
                    leftmax[0] = height[0];
                    rightmax[n-1] = height[n-1];
                } else {
                    leftmax[i] = Math.max(height[i], leftmax[i-1]);
                    rightmax[n-i-1] = Math.max(height[n-i-1], rightmax[n-i]);
                }
            }

            for(int i = 0; i<n; i++) {
                water += Math.min(leftmax[i], rightmax[i]) - height[i];
            }

            return water;
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_II {
        public  static int trap(int[] height) {
            Stack<Integer> stack = new Stack();
            int curr = 0, water = 0;

            while (curr < height.length) {
                while (!stack.isEmpty() && height[curr] > height[stack.peek()]) {
                    int top = stack.pop();
                    if(stack.isEmpty()) break;
                    int d = curr - stack.peek() - 1;
                    int fill = d * (Math.min(height[curr], height[stack.peek()]) - height[top]);
                    water += fill;
                }
                stack.push(curr++);
            }
            return water;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_III {
        public  static int trap(int[] height) {
           int i = 0, j = height.length - 1, maxLeft = 0, maxRight = 0, water = 0;

           while (i<j) {
               if(height[i] <= height[j]) {
                   maxLeft = Math.max(maxLeft, height[i]);
                   water += maxLeft - height[i];
                   i++;
               } else {
                   maxRight = Math.max(maxRight, height[j]);
                   water += maxRight - height[j];
                   j--;
               }
           }
           return water;
        }
    }
}
