public class GlobalAndLocalInversions {

    /*
        You are given an integer array nums of length n which represents a permutation of all the integers in the range [0, n - 1].
        The number of global inversions is the number of the different pairs (i, j) where:

        0 <= i < j < n
        nums[i] > nums[j]
        The number of local inversions is the number of indices i where:

        0 <= i < n - 1
        nums[i] > nums[i + 1]
        Return true if the number of global inversions is equal to the number of local inversions.

        Example 1.
        Input: nums = [1,0,2]
        Output: true
        Explanation: There is 1 global inversion and 1 local inversion.

        Example 2.
        Input: nums = [1,2,0]
        Output: false
        Explanation: There are 2 global inversions and 1 local inversion.
     */

    public static void main(String[] args) {
        int[] test = {1,0,2};
        System.out.println("Result: " + Solution.isIdealPermutation(test));
    }

    static class Solution {
        // Time: O(N)
        // Space: O(1)
        public static boolean isIdealPermutation(int[] A) {
            for(int i = 0; i < A.length; i++) {
                if(Math.abs(A[i]-i) > 1) return false;
            }
            return true;
        }
    }
}
