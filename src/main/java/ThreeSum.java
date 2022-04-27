import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ThreeSum {
    /*
        Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
        Notice that the solution set must not contain duplicate triplets.

        Example 1.
        Input: nums = [-1,0,1,2,-1,-4]
        Output: [[-1,-1,2],[-1,0,1]]
        Example 2.
        Input: nums = []
        Output: []
        Example 3:

        Input: nums = [0]
        Output: []


        Constraints:

        0 <= nums.length <= 3000
        -105 <= nums[i] <= 105
     */

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};

        Solution_I.threeSum(nums).
                stream()
                .map(List::copyOf)
                .forEach(System.out::println);

        Solution_II.threeSum(nums).
                stream()
                .map(List::copyOf)
                .forEach(System.out::println);
    }

    // Time: O(N^2LogN)
    // Space: O(N)
    static class Solution_I {
        public static List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> result = new ArrayList();
            int i = 0;
            while (i < nums.length){
                int j = i+1;
                int k = nums.length -1;
                int desired = -nums[i];
                while (j < k) {
                    if (nums[k] + nums[j] == desired) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        j++;
                        while (j < nums.length && nums[j] == nums[j-1]) {
                            j++;
                        }
                        k--;
                    } else if (nums[k] + nums[j] > desired) {
                        k--;
                    } else {
                        j++;
                    }
                }
                i++;
                while (i < nums.length && nums[i] == nums[i-1]) {
                    i++;
                }
            }
            return result;
        }
    }

    // Time: O(N^2LogN)
    // Space: O(N)
    static class Solution_II {
        public static List<List<Integer>> threeSum(int[] nums) {
            Arrays.sort(nums);
            List<List<Integer>> res = new LinkedList<>();
            for (int i = 0; i < nums.length-2; i++) {
                if (i == 0 || (i > 0 && nums[i] != nums[i-1])) {
                    int lo = i+1, hi = nums.length-1, sum = 0 - nums[i];
                    while (lo < hi) {
                        if (nums[lo] + nums[hi] == sum) {
                            res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                            while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                            while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                            lo++; hi--;
                        } else if (nums[lo] + nums[hi] < sum) lo++;
                        else hi--;
                    }
                }
            }
            return res;
        }
    }
}
