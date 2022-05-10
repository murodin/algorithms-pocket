public class MinimumSwapsToMakeSequencesIncreasing {
    /*
        You are given two integer arrays of the same length nums1 and nums2. In one operation, you are allowed to swap nums1[i] with nums2[i].
        For example, if nums1 = [1,2,3,8], and nums2 = [5,6,7,4], you can swap the element at i = 3 to obtain nums1 = [1,2,3,4] and nums2 = [5,6,7,8].
        Return the minimum number of needed operations to make nums1 and nums2 strictly increasing. The test cases are generated so that the given input always makes it possible.

        An array arr is strictly increasing if and only if arr[0] < arr[1] < arr[2] < ... < arr[arr.length - 1].

        Example 1.
        Input: nums1 = [1,3,5,4], nums2 = [1,2,3,7]
        Output: 1
        Explanation:
        Swap nums1[3] and nums2[3]. Then the sequences are:
        nums1 = [1, 3, 5, 7] and nums2 = [1, 2, 3, 4]
        which are both strictly increasing.
        Example 2.
        Input: nums1 = [0,3,5,8,9], nums2 = [2,1,4,6,9]
        Output: 1


        Constraints:

        2 <= nums1.length <= 105
        nums2.length == nums1.length
        0 <= nums1[i], nums2[i] <= 2 * 105
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minSwap(new int[] {1,3,5,4}, new int[] {1,2,3,7}));
    }

    static class Solution {
        public static int minSwap(int[] nums1, int[] nums2) {
            int[][] dp = new int[nums1.length][2];
            //dp[0][0] = 0; if we do not swap, then nb of swap is 0
            dp[0][1] = 1; // if we swap the first, then nb of swap is 1
            for (int i=1; i<nums1.length; i++){
                int a1 = nums1[i-1];
                int a2 = nums1[i];
                int b1 = nums2[i-1];
                int b2 = nums2[i];
                dp[i][0] = Integer.MAX_VALUE;
                dp[i][1] = Integer.MAX_VALUE;
                if (a1<a2 && b1 <b2){
                    if (a1<b2 && b1<a2){
                        dp[i][0] = Math.min(Math.min(dp[i-1][0], dp[i-1][1]), dp[i][0]);
                        dp[i][1] = Math.min(Math.min(dp[i-1][0]+1,dp[i-1][1]+1), dp[i][1]);
                    }else{
                        dp[i][0] = Math.min(dp[i-1][0], dp[i][0]);
                        dp[i][1] = Math.min(dp[i-1][1]+1, dp[i][1]);
                    }
                }else{
                    dp[i][0] = Math.min(dp[i-1][1], dp[i][0]);
                    dp[i][1] = Math.min(dp[i-1][0]+1, dp[i][1]);
                }
            }
            return Math.min(dp[nums1.length-1][0], dp[nums1.length-1][1]);
        }
    }
}
