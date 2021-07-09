import java.util.Arrays;

public class MinimumMovesToEqualArrayElementsII {

    /*
        Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
        In one move, you can increment or decrement an element of the array by 1.
        Test cases are designed so that the answer will fit in a 32-bit integer.
     */

    public static void main(String[] args) {
        int[] testArrays = {1, 0, 0, 8, 6};
        System.out.println("Min moves:" + Solution.minMoves2(testArrays));
        System.out.println("Min moves Optimum:" + Solution.minMoves2Optimum(testArrays));
    }

    static class Solution {
        // Time: O(nlogn)
        // Space: O(1)
        public static int minMoves2(int[] nums) {
            int n = nums.length;

            Arrays.sort(nums);

            int median = n%2 == 0 ? nums[n/2]: nums[(n-1)/2];
            int res = 0;

            for(int num: nums) {
                res += Math.abs(num - median);
            }
            return res;
        }

        // Time: O(nlogn)
        // Space: O(1)
        public static int minMoves2Optimum(int[] nums) {
            int n = nums.length;

            Arrays.sort(nums);

            int medianIndex = n%2 == 0 ? n/2: (n-1)/2;
            int res = 0;

            for(int i=0, j=n-1; i<=medianIndex; i++, j--) {
                res += nums[j]-nums[i];
            }
            /* Alternative code fragment to above
                int i=0, j=n-1
                int res=0;
                while(i<j) {
                    res += nums[j]-nums[i];
                    i++;
                    j--;
                }
             */
            return res;
        }
    }
}
