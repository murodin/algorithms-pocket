import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicateII {
    /*
        Given an integer array nums and an integer k, return true if there are two distinct indices i and j in the array
        such that nums[i] == nums[j] and abs(i - j) <= k.

        Example 1.
        Input: nums = [1,2,3,1], k = 3
        Output: true
        Example 2.
        Input: nums = [1,0,1,1], k = 1
        Output: true
        Example 3.
        Input: nums = [1,2,3,1,2,3], k = 2
        Output: false


        Constraints:

        1 <= nums.length <= 105
        -109 <= nums[i] <= 109
        0 <= k <= 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.containsNearbyDuplicate(new int[]{1,0,1,1}, 2));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static boolean containsNearbyDuplicate(int[] nums, int k) {
            if (k == 0) return false;
            Map<Integer, Integer> hashMap = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int integer = nums[i];
                if (hashMap.containsKey(integer) && i - hashMap.get(integer) <= k)
                    return true;
                hashMap.put(integer, i);
            }
            return false;
        }
    }
}
