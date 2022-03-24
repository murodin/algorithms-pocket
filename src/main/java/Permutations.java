import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {

    /*
        Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

        Example 1.
        Input: nums = [1,2,3]
        Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        Example 2:.
        Input: nums = [0,1]
        Output: [[0,1],[1,0]]
        Example 3:

        Input: nums = [1]
        Output: [[1]]

        Constraints:

        1 <= nums.length <= 6
        -10 <= nums[i] <= 10
        All the integers of nums are unique.
     */

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Permutations.Solution.permute(nums)
                .forEach(System.out::println);
    }

    // Time: O(N!)
    // Space: O(1)
    static class Solution {
        public static List<List<Integer>> permute(int[] nums) {
                List<List<Integer>> res = new ArrayList<>();
                backtrack(res, nums, 0);
                return res;
        }

        private static void backtrack(List<List<Integer>> res, int[] nums, int start) {
            if(start == nums.length) res.add(toList(nums));
            else {
                for(int i = start; i < nums.length; i++) {
                    swap(start, i, nums);
                    backtrack(res, nums, start+1);
                    swap(start, i ,nums);
                }
            }
        }

        private static void swap(int i, int j, int[] nums) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }

        private static List<Integer> toList(int[] nums) {
            return Arrays.stream(nums)
                    .boxed()
                    .collect(Collectors.toList());
        }
    }
}
