import java.util.Arrays;

public class FirstMissingPositive {

    /*
        Given an unsorted integer array nums, return the smallest missing positive integer.
        You must implement an algorithm that runs in O(n) time and uses constant extra space.

        Example 1.
        Input: nums = [1,2,0]
        Output: 3
        Example 2.
        Input: nums = [3,4,-1,1]
        Output: 2
        Example 3.
        Input: nums = [7,8,9,11,12]
        Output: 1

        Constraints:

        1 <= nums.length <= 5 * 105
        -231 <= nums[i] <= 231 - 1
     */

    public static void main(String[] args) {
        int[] nums = {7,8,9,11,12};
        System.out.println("Solution I: " + Solution_I.firstMissingPositive(nums));
        System.out.println("Solution II: " + Solution_II.firstMissingPositive(nums));
        System.out.println("Solution III: " + Solution_III.firstMissingPositive(nums));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_I {
        public static int firstMissingPositive(int[] nums) {

            for(int i=0; i<nums.length; i++)
                if(nums[i] < 0)
                    nums[i] = 0;

            for(int i=0; i<nums.length; i++) {
                int val = Math.abs(nums[i]);
                if(val > 0 && val <= nums.length) {
                    if(nums[val - 1] > 0)
                        nums[val - 1] *= -1;
                    else if(nums[val - 1] == 0)
                        nums[val - 1] = -(nums.length + 1);
                }
            }

            for(int i=0; i<nums.length; i++)
                if(nums[i] >= 0)
                    return i+1;

            return nums.length + 1;
        }
    }

    // Time: O(N*LogN)
    // Space: O(1)
    static class Solution_II {
        public static int firstMissingPositive(int[] nums) {
            int l = nums.length;
            Arrays.sort(nums);
            int ans = 1;
            for (int num : nums) {
                if (num == ans)
                    ans++;
            }
            return ans;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_III {
        public static int firstMissingPositive(int[] nums) {
            int i=0;
            while(i<nums.length) {
                int correct=nums[i]-1;  //for sorted // index=element;
                if(nums[i]>0 && nums[i]<=nums.length && nums[i]!=nums[correct]) {
                    swap(nums,i,correct);
                }  else {
                    i++;
                }
            }
            // search for first missing number
            for (int index = 0; index <nums.length ; index++) {
                if (nums[index] != index+1)
                    return index+1;
            }
            //case 2
            return nums.length+1; // if all elements sorted from 1-n return n+1
        }

        public static void swap(int[] a, int first, int second) {
            int temp=a[first];
            a[first]=a[second];
            a[second]=temp;
        }
    }
}
