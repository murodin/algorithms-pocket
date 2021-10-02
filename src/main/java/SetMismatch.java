public class SetMismatch {

    /*
        You have a set of integers s, which originally contains all the numbers from 1 to n.
        Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.
        You are given an integer array nums representing the data status of this set after the error.
        Find the number that occurs twice and the number that is missing and return them in the form of an array.

        Example 1.
        Input: nums = [1,2,2,4]
        Output: [2,3]
        Example 2.
        Input: nums = [1,1]
        Output: [1,2]
     */

    public static void main(String[] args) {
        int[] tests = {1,2,2,4};
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static int[] findErrorNums(int[] nums) {
            int[] arr=new int[nums.length];
            int[] result=new int[2];

            for(int i:nums){
                arr[i-1]++;
            }

            for(int i=0;i<arr.length;i++){
                if(arr[i]==2){
                    result[0]=i+1;
                }
                if(arr[i]==0){
                    result[1]=i+1;
                }
            }
            return result;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static int[] findErrorNums(int[] nums) {
            int[] result=new int[2];

            for(int i:nums){
                if(nums[Math.abs(i)-1]<0){
                    result[0]=Math.abs(i);
                }
                else{
                    nums[Math.abs(i)-1] *=-1;
                }
            }

            for(int i=0;i<nums.length;i++){
                if(nums[i]>0){
                    result[1]=i+1;
                }
            }

            return result;
        }
    }
}
