import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SumOfDistances {
    /*
        You are given a 0-indexed integer array nums. There exists an array arr of length nums.length, where arr[i]
        is the sum of |i - j| over all j such that nums[j] == nums[i] and j != i. If there is no such j, set arr[i] to be 0.

        Return the array arr.

        Example 1:

        Input: nums = [1,3,1,1,2]
        Output: [5,0,3,4,0]
        Explanation:
        When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5.
        When i = 1, arr[1] = 0 because there is no other index with value 3.
        When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3.
        When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4.
        When i = 4, arr[4] = 0 because there is no other index with value 2.

        Example 2:

        Input: nums = [0,5,3]
        Output: [0,0,0]
        Explanation: Since each element in nums is distinct, arr[i] = 0 for all i.


        Constraints:

        1 <= nums.length <= 105
        0 <= nums[i] <= 109
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.distance(new int[]{1, 3, 1, 1, 2})));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static long[] distance(int[] nums) {
            long[] res = new long[nums.length];
            int[] count = new int[nums.length];
            Map<Integer,Integer> map = new HashMap<>();

            for(int i=0;i<nums.length;i++){
                if(map.containsKey(nums[i])){
                    int prev = map.get(nums[i]);
                    res[i] = res[prev]+ (long) count[prev] *(i-prev);
                    count[i] = count[prev]+1;
                } else {
                    count[i] = 1;
                }

                map.put(nums[i],i);
            }

            count = new int[nums.length];
            map = new HashMap<>();
            long[] resReverse = new long[nums.length];
            for(int i=nums.length-1;i>=0;i--){
                if(map.containsKey(nums[i])){
                    int prev = map.get(nums[i]);
                    resReverse[i] = resReverse[prev]+ (long) count[prev] *(prev-i);
                    count[i] = count[prev]+1;
                } else {
                    count[i] = 1;
                }
                map.put(nums[i],i);
            }

            for(int i=0;i<nums.length;i++){
                res[i] += resReverse[i];
            }
            return res;
        }
    }
}
