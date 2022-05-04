import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxNumberOfKSumPairs {

    /*
        You are given an integer array nums and an integer k.
        In one operation, you can pick two numbers from the array whose sum equals k and remove them from the array.
        Return the maximum number of operations you can perform on the array.

        Example 1.
        Input: nums = [1,2,3,4], k = 5
        Output: 2
        Explanation: Starting with nums = [1,2,3,4]:
        - Remove numbers 1 and 4, then nums = [2,3]
        - Remove numbers 2 and 3, then nums = []
        There are no more pairs that sum up to 5, hence a total of 2 operations.
        Example 2.
        Input: nums = [3,1,3,4,3], k = 6
        Output: 1
        Explanation: Starting with nums = [3,1,3,4,3]:
        - Remove the first two 3's, then nums = [1,4,3]
        There are no more pairs that sum up to 6, hence a total of 1 operation.
     */

    public static void main(String[] args) {
        System.out.println("Solution I: " + Solution_I.maxOperations(new int[]{3,1,3,4,3}, 6));
        System.out.println("Solution II: " + Solution_II.maxOperations(new int[]{3,1,3,4,3}, 6));
        System.out.println("Solution III: " + Solution_III.maxOperations(new int[]{3,1,3,4,3}, 6));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static int maxOperations(int[] nums, int k) {
            Map<Integer,Integer> map=new HashMap<>();

            for(int i:nums){
                map.put(i,map.getOrDefault(i,0)+1);
            }

            int count=0;
            for(int i:map.keySet()){
                if(map.containsKey(i) && map.containsKey(k-i)){
                    if(i!=k-i){
                        count+=Math.min(map.get(i),map.get(k-i));
                        map.put(i,0);
                        map.put(k-i,0);
                    }
                    else{
                        count+=Math.floor(map.get(i)/2);
                        map.put(i,0);
                    }
                }
            }

            return count;
        }
    }

    // Time: O(NLogN)
    // Space: O(1)
    static class Solution_II {
        public static int maxOperations(int[] nums, int k) {
            Arrays.sort(nums);

            int left=0,right=nums.length-1;
            int count=0;
            while(left<right){
                int sum=nums[left]+nums[right];

                if(sum==k){
                    left++;
                    right--;
                    count++;
                }
                else if(sum>k){
                    right--;
                }
                else{
                    left++;
                }
            }

            return count;
        }
    }

    // Time: O(NLogN)
    // Space: O(1)
    static class Solution_III {
        public static int maxOperations(int[] nums, int k) {
            Arrays.sort(nums);
            int low = 0, high = nums.length - 1, minOperations = 0;

            while(low < high){
                if(nums[low] + nums[high] == k){
                    low++;
                    high--;
                    minOperations++;
                }
                else if(nums[low] + nums[high] > k) high--;
                else low++;
            }

            return minOperations;
        }
    }
}
