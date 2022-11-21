public class RectangleArea {
    /*
        Given the coordinates of two rectilinear rectangles in a 2D plane, return the total area covered by the two rectangles.
        The first rectangle is defined by its bottom-left corner (ax1, ay1) and its top-right corner (ax2, ay2).
        The second rectangle is defined by its bottom-left corner (bx1, by1) and its top-right corner (bx2, by2).

        Example 1:

        Rectangle Area
        Input: ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2
        Output: 45
        Example 2:

        Input: ax1 = -2, ay1 = -2, ax2 = 2, ay2 = 2, bx1 = -2, by1 = -2, bx2 = 2, by2 = 2
        Output: 16


        Constraints:

        -104 <= ax1 <= ax2 <= 104
        -104 <= ay1 <= ay2 <= 104
        -104 <= bx1 <= bx2 <= 104
        -104 <= by1 <= by2 <= 104
     */
    public static void main(String[] args) {
        int ax1 = -3, ay1 = 0, ax2 = 3, ay2 = 4, bx1 = 0, by1 = -1, bx2 = 9, by2 = 2;
        System.out.println("Solution: " + Solution.computeArea(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2));
    }

    // Time: O(1)
    // Space: O(1)
    static class Solution {
        public static int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2, int by2) {
            int a1=(ax2-ax1)*(ay2-ay1);//area of rectangle 1
            int a2=(bx2-bx1)*(by2-by1);//area of rectangle 2

            //co-ordinates of intersecting rectangle
            int x5 = Math.max(ax1, bx1);
            int y5 = Math.max(ay1, by1);
            int x6 = Math.min(ax2, bx2);
            int y6 = Math.min(ay2, by2);

            int x=x6-x5;
            int y=y6-y5;
            int a3=x*y;//area of intersecting rectangle
            if(x<0 || y<0){//means both rectangle don't intersects
                a3=0;
            }
            return a1+a2-a3;
        }
    }
}
