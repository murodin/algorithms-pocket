import java.util.Map;
import java.util.TreeMap;

public class ReductionOperationsToMakeTheArrayElementsEqual {
    /*
        Given an integer array nums, your goal is to make all elements in nums equal. To complete one operation, follow these steps:
        Find the largest value in nums. Let its index be i (0-indexed) and its value be largest. If there are multiple elements with the largest value, pick the smallest i.
        Find the next largest value in nums strictly smaller than largest. Let its value be nextLargest.
        Reduce nums[i] to nextLargest.
        Return the number of operations to make all elements in nums equal.

        Example 1:
        Input: nums = [5,1,3]
        Output: 3
        Explanation: It takes 3 operations to make all elements in nums equal:
        1. largest = 5 at index 0. nextLargest = 3. Reduce nums[0] to 3. nums = [3,1,3].
        2. largest = 3 at index 0. nextLargest = 1. Reduce nums[0] to 1. nums = [1,1,3].
        3. largest = 3 at index 2. nextLargest = 1. Reduce nums[2] to 1. nums = [1,1,1].
        Example 2:
        Input: nums = [1,1,1]
        Output: 0
        Explanation: All elements in nums are already equal.
        Example 3:
        Input: nums = [1,1,2,2,3]
        Output: 4
        Explanation: It takes 4 operations to make all elements in nums equal:
        1. largest = 3 at index 4. nextLargest = 2. Reduce nums[4] to 2. nums = [1,1,2,2,2].
        2. largest = 2 at index 2. nextLargest = 1. Reduce nums[2] to 1. nums = [1,1,1,2,2].
        3. largest = 2 at index 3. nextLargest = 1. Reduce nums[3] to 1. nums = [1,1,1,1,2].
        4. largest = 2 at index 4. nextLargest = 1. Reduce nums[4] to 1. nums = [1,1,1,1,1].


        Constraints:

        1 <= nums.length <= 5 * 104
        1 <= nums[i] <= 5 * 104
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.reductionOperations(new int[]{1,1,2,2,3}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int reductionOperations(int[] nums) {
            TreeMap<Integer, Integer> frequencies = new TreeMap<>();
            for (int n : nums) {
                frequencies.put(n, 1 + frequencies.getOrDefault(n, 0));
            }
            int operations = 0, freqOfNextLargest = 0;
            while (frequencies.size() > 1) {
                Map.Entry<Integer, Integer> e = frequencies.pollLastEntry();
                freqOfNextLargest += e.getValue();
                operations += freqOfNextLargest;
            }
            return operations;
        }
    }
}
