import java.util.Arrays;

public class SortArrayByParity {

    /*
        Given an integer array nums, move all the even integers at the beginning of the array followed by all the odd integers.
        Return any array that satisfies this condition.

        Example 1.
        Input: nums = [3,1,2,4]
        Output: [2,4,3,1]
        Explanation: The outputs [4,2,3,1], [2,4,1,3], and [4,2,1,3] would also be accepted.
        Example 2.
        Input: nums = [0]
        Output: [0]
     */

    public static void main(String[] args) {
        int[] nums = {3,1,2,4};
        System.out.println("Solution I: " + Arrays.toString(Solution_I.sortArrayByParity(nums)));
        System.out.println("Solution II: " + Arrays.toString(Solution_II.sortArrayByParity(nums)));
        System.out.println("Solution III: " + Arrays.toString(Solution_III.sortArrayByParity(nums)));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_I {
        public static int[] sortArrayByParity(int[] nums) {
            int left = 0;
            for(int i=0; i<nums.length; i++){
                if(nums[i]%2 == 0){
                    swap(nums, left, i);
                    left++;
                }
            }
            return nums;
        }

        static public void swap(int[] nums, int i, int j){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static int[] sortArrayByParity(int[] nums) {
            int i =0;
            int j= nums.length-1;
            while(i<j) {
                if(nums[i]%2!=0 && nums[j]%2==0) {
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                    i++;
                    j--;
                }

                if(nums[i]%2==0){
                    i++;
                }

                if(nums[j]%2!=0){
                    j--;
                }
            }
            return nums;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_III {
        public static int[] sortArrayByParity(int[] nums) {
            int odd = nums.length-1;
            int even = 0;
            while(odd>=even){
                if(nums[even]%2==0){
                    even++;
                }
                else{
                    int t = nums[even];
                    nums[even]= nums[odd];
                    nums[odd] = t;
                    odd--;
                }
            }
            return nums;
        }
    }
}
