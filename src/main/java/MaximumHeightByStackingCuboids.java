import java.util.Arrays;
import java.util.Comparator;

public class MaximumHeightByStackingCuboids {

    /*
        Given n cuboids where the dimensions of the ith cuboid is cuboids[i] = [widthi, lengthi, heighti] (0-indexed). Choose a subset of cuboids and place them on each other.
        You can place cuboid i on cuboid j if widthi <= widthj and lengthi <= lengthj and heighti <= heightj. You can rearrange any cuboid's dimensions by rotating it to put it on another cuboid.
        Return the maximum height of the stacked cuboids.

        Example 1.
        Input: cuboids = [[50,45,20],[95,37,53],[45,23,12]]
        Output: 190
        Explanation:
        Cuboid 1 is placed on the bottom with the 53x37 side facing down with height 95.
        Cuboid 0 is placed next with the 45x20 side facing down with height 50.
        Cuboid 2 is placed next with the 23x12 side facing down with height 45.
        The total height is 95 + 50 + 45 = 190.
        Example 2.
        Input: cuboids = [[38,25,45],[76,35,3]]
        Output: 76
        Explanation:
        You can't place any of the cuboids on the other.
        We choose cuboid 1 and rotate it so that the 35x3 side is facing down and its height is 76.
        Example 3.
        Input: cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
        Output: 102
        Explanation:
        After rearranging the cuboids, you can see that all cuboids have the same dimension.
        You can place the 11x7 side down on all cuboids so their heights are 17.
        The maximum height of stacked cuboids is 6 * 17 = 102.


        Constraints:

        n == cuboids.length
        1 <= n <= 100
        1 <= widthi, lengthi, heighti <= 100
     */

    public static void main(String[] args) {
        int[][] cuboids = {{7,11,17},{7,17,11},{11,7,17},{11,17,7},{17,7,11},{17,11,7}};
        System.out.println("Solution I: " + Solution_I.maxHeight(cuboids));
        System.out.println("Solution II: " + Solution_II.maxHeight(cuboids));
    }

    // Time: O(N^2)
    // Space: O(N) -> dp
    static class Solution_I {
        public static int maxHeight(int[][] cuboids) {
            // sort each cuboid dimensions
            for(int[] cuboid : cuboids) {
                Arrays.sort(cuboid);
            }
            // sort the cuboids from largest to smallest order
            Arrays.sort(cuboids, new Comparator<int[]>() {
                public int compare(int[] a, int[] b) {
                    if(a[0] != b[0]){
                        return b[0] - a[0];
                    }
                    if(a[1] != b[1]) {
                        return b[1] - a[1];
                    }

                    return b[2] - a[2];
                }
            });
            int[] dp = new int[cuboids.length]; // dp[i] means max height upto cuboid 'i' (row i)
            int res = 0;
            for(int i = 0; i < cuboids.length; i++) {
                dp[i] = cuboids[i][2]; // we chose the largest side of cuboid to be height to maximize it
                for(int j = 0; j < i; j++) {
                    if(cuboids[j][0] >= cuboids[i][0] && cuboids[j][1] >= cuboids[i][1] && cuboids[j][2] >= cuboids[i][2]) {
                        dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]);
                    }
                }
                res = Math.max(res, dp[i]);
            }
            return res;
        }
    }

    // Time: O(N^2)
    // Space: O(N) -> dp
    static class Solution_II {
        public static int maxHeight(int[][] cuboids) {
            // height will be maximum of length,breadth and height.
            for(int [] box : cuboids)
                Arrays.sort(box);
            // sort cuboids in increasing order of their heights.
            // if same height sort them in increasing order of their breadth.
            // if even breadth is also same, sort them in increasing order of their height.
            Arrays.sort(cuboids,new Comparator<>(){
                @Override
                public int compare(int[] a,int[] b){
                    if(a[2]!=b[2])return a[2] - b[2];
                    if(a[1]!=b[1])return a[1]-b[1];
                    return a[0] - b[0];
                }
            });
            int n = cuboids.length;
            int[] dp = new int[n];
            int maxH = 0;
            for(int i=0;i<n;i++){
                dp[i] = cuboids[i][2];
                for(int j=0;j<i;j++)
                    if(cuboids[i][0]>=cuboids[j][0] && cuboids[i][1]>=cuboids[j][1] && cuboids[i][2]>=cuboids[j][2] )
                        dp[i] = Math.max(dp[i],cuboids[i][2] + dp[j]);
                maxH = Math.max(maxH,dp[i]);
            }
            return maxH;

        }
    }
}
