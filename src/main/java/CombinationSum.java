import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    /*
        Given an array of distinct integers candidates and a target integer target,
        return a list of all unique combinations of candidates where the chosen numbers sum to target. You may return the combinations in any order.
        The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the frequency of at least one of the chosen numbers is different.
        It is guaranteed that the number of unique combinations that sum up to target is less than 150 combinations for the given input.

        Example 1.
        Input: candidates = [2,3,6,7], target = 7
        Output: [[2,2,3],[7]]
        Explanation:
        2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
        7 is a candidate, and 7 = 7.
        These are the only two combinations.
        Example 2.
        Input: candidates = [2,3,5], target = 8
        Output: [[2,2,2,2],[2,3,3],[3,5]]
        Example 3.
        Input: candidates = [2], target = 1
        Output: []


        Constraints:

        1 <= candidates.length <= 30
        1 <= candidates[i] <= 200
        All elements of candidates are distinct.
        1 <= target <= 500
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.combinationSum(new int[] {2,3,5}, 8));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static List<List<Integer>> combinationSum(int[] candidates, int target) {
            return helper(candidates, target, 0);
        }

        private static List<List<Integer>> helper(int[] candidates, int target, int lc) {
            if (target == 0) {
                List<List<Integer>> bans = new ArrayList<>();
                bans.add(new ArrayList<>());
                return bans;
            }

            List<List<Integer>> ans = new ArrayList<>();
            for (int i = lc; i < candidates.length; i++) {
                if (target - candidates[i] >= 0) {
                    List<List<Integer>> lans = helper(candidates, target - candidates[i], i);
                    if (lans.size() > 0) {
                        for (List<Integer> l : lans) {
                            l.add(candidates[i]);
                            ans.add(l);

                        }
                    }
                }
            }
            return ans;
        }
    }
}
