import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumIIInputArrayIsSorted {

    /*
        Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
        find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
        Return the indices of the two numbers, index1 and index2, added by one as an integer array [index1, index2] of length 2.
        The tests are generated such that there is exactly one solution. You may not use the same element twice.

        Your solution must use only constant extra space.

        Example 1.
        Input: numbers = [2,7,11,15], target = 9
        Output: [1,2]
        Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
        Example 2.
        Input: numbers = [2,3,4], target = 6
        Output: [1,3]
        Explanation: The sum of 2 and 4 is 6. Therefore, index1 = 1, index2 = 3. We return [1, 3].
        Example 3.
        Input: numbers = [-1,0], target = -1
        Output: [1,2]
        Explanation: The sum of -1 and 0 is -1. Therefore, index1 = 1, index2 = 2. We return [1, 2].


        Constraints:

        2 <= numbers.length <= 3 * 104
        -1000 <= numbers[i] <= 1000
        numbers is sorted in non-decreasing order.
        -1000 <= target <= 1000
        The tests are generated such that there is exactly one solution.
     */

    public static void main(String[] args) {
        System.out.println("Solution I: " + Arrays.toString(Solution_I.twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println("Solution II: " + Arrays.toString(Solution_II.twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println("Solution III: " + Arrays.toString(Solution_III.twoSum(new int[]{2, 7, 11, 15}, 9)));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution_I {
        public static int[] twoSum(int[] numbers, int target) {
            int len = numbers.length;
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < len; i++) {
                if (map.containsKey(target - numbers[i])) {
                    return new int[]{map.get(target - numbers[i]), i + 1};
                }
                map.putIfAbsent(numbers[i], i + 1);
            }
            return new int[0];
        }
    }

    // Time: O(NLogN)
    // Space: O(1)
    static class Solution_II {
        public static int[] twoSum(int[] numbers, int target) {
            for (int i = 0; i < numbers.length; ++i) {
                int low = i + 1;
                int high = numbers.length - 1;
                while (low <= high) {
                    int mid = (high - low) / 2 + low;
                    if (numbers[mid] == target - numbers[i]) {
                        return new int[]{i + 1, mid + 1};
                    } else if (numbers[mid] > target - numbers[i]) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
            }
            return new int[]{-1, -1};
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_III {
        public static int[] twoSum(int[] numbers, int target) {
            int len = numbers.length;
            int left = 0;
            int right = len - 1;
            while (left < right) {
                if (numbers[left] + numbers[right] > target) {
                    right--;
                } else if (numbers[left] + numbers[right] < target) {
                    left++;
                } else {
                    return new int[]{left + 1, right + 1};
                }
            }
            return new int[]{-1, -1};
        }
    }
}
