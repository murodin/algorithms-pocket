import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {

    /*
        Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
        Each number in candidates may only be used once in the combination.
        Note: The solution set must not contain duplicate combinations.

        Example 1.
        Input: candidates = [10,1,2,7,6,1,5], target = 8
        Output:
        [
        [1,1,6],
        [1,2,5],
        [1,7],
        [2,6]
        ]
        Example 2.
        Input: candidates = [2,5,2,1,2], target = 5
        Output:
        [
        [1,2,2],
        [5]
        ]

        Constraints:
        1 <= candidates.length <= 100
        1 <= candidates[i] <= 50
        1 <= target <= 30
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.combinationSum2(new int[] {2,5,2,1,2}, 5));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        static private final List<List<Integer>> combinations = new ArrayList<>();
        public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
            Arrays.sort(candidates);
            findCombination(0, target, 0, candidates, new ArrayList<>());
            return combinations;
        }

        private static void findCombination(int index, int target, int runningSum, int[] candidates, List<Integer> combination) {
            if (target == runningSum) {
                combinations.add(new ArrayList<>(combination));
                return;
            }

            for (int i = index; i < candidates.length; i++) {
                if (runningSum + candidates[i] <= target) {
                    combination.add(candidates[i]);
                    findCombination(i + 1, target, runningSum + candidates[i], candidates, combination); // look ahead
                    combination.remove(combination.size() - 1);
                    while (i < candidates.length - 1 && candidates[i] == candidates[i + 1]) {
                        i++;
                    }
                }
            }
        }
    }
}
