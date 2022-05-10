import java.util.ArrayList;
import java.util.List;

public class CombinationSumIII {
    /*
        Find all valid combinations of k numbers that sum up to n such that the following conditions are true:
        Only numbers 1 through 9 are used.
        Each number is used at most once.
        Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

        Example 1.
        Input: k = 3, n = 7
        Output: [[1,2,4]]
        Explanation:
        1 + 2 + 4 = 7
        There are no other valid combinations.
        Example 2.
        Input: k = 3, n = 9
        Output: [[1,2,6],[1,3,5],[2,3,4]]
        Explanation:
        1 + 2 + 6 = 9
        1 + 3 + 5 = 9
        2 + 3 + 4 = 9
        There are no other valid combinations.
        Example 3.
        Input: k = 4, n = 1
        Output: []
        Explanation: There are no valid combinations.
        Using 4 different numbers in the range [1,9], the smallest sum we can get is 1+2+3+4 = 10 and since 10 > 1, there are no valid combination.


        Constraints:

        2 <= k <= 9
        1 <= n <= 60
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.combinationSum3(3, 9));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static List<List<Integer>> combinationSum3(int k, int n) {
            List<List<Integer>> ans = new ArrayList<>();
            List<Integer> dS = new ArrayList<>();
            helper(n, k, ans, dS, 0, 1, 0);

            return ans;
        }

        public static void helper(int n, int k, List<List<Integer>> ans, List<Integer> dS, int sum, int index, int count){
            if(sum == n && count == k){
                ans.add(new ArrayList<>(dS));
                return;
            }

            if(sum > n || index > 9 || count == k)
                return;

            for(int i = index; i <= 9; ++i){
                sum += i;
                if(sum > n)
                    return;
                dS.add(i);
                helper(n, k, ans, dS, sum, i + 1, count + 1);
                dS.remove(dS.size() - 1);
                sum -= i;
            }
        }
    }
}
