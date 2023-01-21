import java.util.ArrayList;
import java.util.List;

public class NonDecreasingSubsequences {
    /*
        Given an integer array nums, return all the different possible non-decreasing subsequences of the given array with at least two elements. You may return the answer in any order.

        Example 1.
        Input: nums = [4,6,7,7]
        Output: [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]
        Example 2.
        Input: nums = [4,4,3,2,1]
        Output: [[4,4]]


        Constraints:

        1 <= nums.length <= 15
        -100 <= nums[i] <= 100
     */
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("Solution: " + s.findSubsequences(new int[]{4,6,7,7}));
    }

    // Time: O(2^N)
    // Space: O(2^N)
    static class Solution {
        int[] nums;
        List<List<Integer>> globalResult;
        int[] currentList;
        int currentListLength;

        public List<List<Integer>> findSubsequences(int[] nums) {
            this.nums = nums;
            this.currentList = new int[nums.length+1];
            globalResult = new ArrayList<>();
            backtrack(0, -1);

            return globalResult;
        }

        public void backtrack(int i, int l ) {
            if (i >= nums.length) {
                if (currentListLength >= 2) {
                    var result = new ArrayList<Integer>(currentListLength);
                    for(int j = 0; j < currentListLength; j++)
                        result.add(currentList[j]);
                    globalResult.add(result);
                }
                return;
            }
            if (l == -1 || nums[i] >= nums[l]) {
                currentList[currentListLength++] = nums[i];
                backtrack(i+1, i);
                currentListLength--;
            }
            if ((l == -1) || (nums[i] != nums[l]))
                backtrack(i+1, l); // backtrack excluding current number
        }
    }
}
