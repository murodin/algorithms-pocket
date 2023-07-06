import java.util.*;

public class SingleNumberIII {
    /*
        Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice.
        Find the two elements that appear only once. You can return the answer in any order.
        You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.

        Example 1:

        Input: nums = [1,2,1,3,2,5]
        Output: [3,5]
        Explanation:  [5, 3] is also a valid answer.
        Example 2:

        Input: nums = [-1,0]
        Output: [-1,0]
        Example 3:

        Input: nums = [0,1]
        Output: [1,0]


        Constraints:

        2 <= nums.length <= 3 * 104
        -231 <= nums[i] <= 231 - 1
        Each integer in nums will appear twice, only two integers will appear once.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.singleNumber(new int[]{1, 2, 1, 3, 2, 5})));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int[] singleNumber(int[] nums) {
            Map<Integer,Integer> map = new HashMap<>();
            List<Integer> l = new ArrayList<>();
            for (int num : nums) {
                if (!map.containsKey(num)) {
                    map.put(num, 1);
                } else {
                    int k = map.get(num);
                    map.put(num, k + 1);
                }
            }
            for(Map.Entry<Integer,Integer> entry: map.entrySet()){
                int value = entry.getValue();
                if(value == 1){
                    l.add(entry.getKey());
                }
            }
            int[] res = new int[l.size()];
            for(int i = 0; i < l.size(); i++){
                res[i] = l.get(i);
            }
            return res;
        }
    }
}
