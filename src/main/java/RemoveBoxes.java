public class RemoveBoxes {
    /*
        You are given several boxes with different colors represented by different positive numbers.
        You may experience several rounds to remove boxes until there is no box left.
        Each time you can choose some continuous boxes with the same color (i.e., composed of k boxes, k >= 1), remove them and get k * k points.
        Return the maximum points you can get.

        Example 1.
        Input: boxes = [1,3,2,2,2,3,4,3,1]
        Output: 23
        Explanation:
        [1, 3, 2, 2, 2, 3, 4, 3, 1]
        ----> [1, 3, 3, 4, 3, 1] (3*3=9 points)
        ----> [1, 3, 3, 3, 1] (1*1=1 points)
        ----> [1, 1] (3*3=9 points)
        ----> [] (2*2=4 points)
        Example 2.
        Input: boxes = [1,1,1]
        Output: 9
        Example 3.
        Input: boxes = [1]
        Output: 1


        Constraints:

        1 <= boxes.length <= 100
        1 <= boxes[i] <= 100
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.removeBoxes(new int[]{1,3,2,2,2,3,4,3,1}));
    }

    // Time: O(N^4)
    // Space: O(N^3)
    static class Solution {
        static int[][][] memo;
        public static int removeBoxes(int[] boxes) {
            int n = boxes.length;
            memo = new int[n][n][n];
            return dp(boxes, 0, n - 1, 0);
        }
        static int dp(int[] boxes, int l, int r, int k) {
            if (l > r) return 0;
            if (memo[l][r][k] > 0) return memo[l][r][k];
            int lOrg = l, kOrg = k;

            while (l+1 <= r && boxes[l] == boxes[l+1]) {
                l += 1;
                k += 1;
            }
            int ans = (k+1) * (k+1) + dp(boxes, l+1, r, 0);
            for (int m = l+1; m <= r; ++m)
                if (boxes[m] == boxes[l])
                    ans = Math.max(ans, dp(boxes, m, r, k+1) + dp(boxes, l+1, m-1, 0));
            return memo[lOrg][r][kOrg] = ans;
        }
    }
}
