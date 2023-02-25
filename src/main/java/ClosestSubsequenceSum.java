import java.util.ArrayList;
import java.util.Collections;

public class ClosestSubsequenceSum {
    /*
        You are given an integer array nums and an integer goal.
        You want to choose a subsequence of nums such that the sum of its elements is the closest possible to goal.
        That is, if the sum of the subsequence's elements is sum, then you want to minimize the absolute difference abs(sum - goal).
        Return the minimum possible value of abs(sum - goal).
        Note that a subsequence of an array is an array formed by removing some elements (possibly all or none) of the original array.

        Example 1.
        Input: nums = [5,-7,3,5], goal = 6
        Output: 0
        Explanation: Choose the whole array as a subsequence, with a sum of 6.
        This is equal to the goal, so the absolute difference is 0.
        Example 2.
        Input: nums = [7,-9,15,-2], goal = -5
        Output: 1
        Explanation: Choose the subsequence [7,-9,-2], with a sum of -4.
        The absolute difference is abs(-4 - (-5)) = abs(1) = 1, which is the minimum.
        Example 3.
        Input: nums = [1,2,3], goal = -7
        Output: 7


        Constraints:

        1 <= nums.length <= 40
        -107 <= nums[i] <= 107
        -109 <= goal <= 109
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.minAbsDifference(new int[]{7,-9,15,-2}, -5));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int minAbsDifference(int[] nums, int goal){
            int[] part1 = new int[nums.length/2];
            int[] part2 = new int[nums.length-part1.length];
            int k = 0;
            for(int i=0;i<part1.length;i++) {
                part1[i] = nums[i];
                k++;
            }
            for(int i=0 ; i<part2.length ; i++) {
                part2[i] = nums[k];
                k++;
            }
            ArrayList<Integer> sum1=new ArrayList<>();
            ArrayList<Integer> sum2=new ArrayList<>();
            addSum(sum1,part1,0,0);
            addSum(sum2,part2,0,0);

            Collections.sort(sum1);
            Collections.sort(sum2);

            int low  = 0;
            int high = sum2.size()-1;
            int res = Integer.MAX_VALUE;
            while(low < sum1.size() && high >= 0) {
                int sum = sum1.get(low)+sum2.get(high);
                res = Math.min(res,Math.abs(sum-goal));
                if(sum > goal) {
                    high--;
                } else if(sum==goal) {
                    return 0;
                } else {
                    low++;
                }
            }
            return res;
        }
        private static void addSum(ArrayList<Integer> sumlist, int[] part, int sum, int si) {
            if(si>=part.length) {
                sumlist.add(sum);
                return;
            }
            addSum(sumlist,part,sum+part[si],si+1);
            addSum(sumlist,part,sum,si+1);
        }
    }
}
