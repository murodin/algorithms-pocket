public class MissingNumber {

    /*
        Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.

        Example 1.
        Input: nums = [3,0,1]
        Output: 2
        Explanation: n = 3 since there are 3 numbers, so all numbers are in the range [0,3]. 2 is the missing number in the range since it does not appear in nums.
        Example 2.
        Input: nums = [0,1]
        Output: 2
        Explanation: n = 2 since there are 2 numbers, so all numbers are in the range [0,2]. 2 is the missing number in the range since it does not appear in nums.
        Example 3.
        Input: nums = [9,6,4,2,3,5,7,0,1]
        Output: 8
        Explanation: n = 9 since there are 9 numbers, so all numbers are in the range [0,9]. 8 is the missing number in the range since it does not appear in nums.
        Example 4.
        Input: nums = [0]
        Output: 1
        Explanation: n = 1 since there is 1 number, so all numbers are in the range [0,1]. 1 is the missing number in the range since it does not appear in nums.

     */

    public static void main(String[] args) {
        int[] testNums =  {9,6,4,2,3,5,7,0,1};

        System.out.println("Solution I:" + Solution_I.missingNumber(testNums));
        System.out.println("Solution II:" + Solution_II.missingNumber(testNums));
        System.out.println("Solution III:" + Solution_III.missingNumber(testNums));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_I {
        public static int missingNumber(int[] nums) {
            int xorarr=0;
            for(int i:nums){
                xorarr^=i;
            }

            int xorAll = 0;
            for(int i=0;i<=nums.length;i++){
                xorAll^=i;
            }

            return xorarr ^ xorAll;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static int missingNumber(int[] nums) {
            int xorAll = nums.length;
            for(int i=0;i<nums.length;i++){
                xorAll^=i^nums[i];
            }

            return xorAll;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_III {
        public static int missingNumber(int[] nums) {
            int sumArr=0;
            for(int i:nums){
                sumArr+=i;
            }

            int n=nums.length;
            int sumAll= n*(n+1)/2;

            return sumAll - sumArr;
        }
    }
}
