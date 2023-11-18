import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FindUniqueBinaryString {
    /*
        Given an array of strings nums containing n unique binary strings each of length n, return a binary string of length n that does not appear in nums.
        If there are multiple answers, you may return any of them.

        Example 1:

        Input: nums = ["01","10"]
        Output: "11"
        Explanation: "11" does not appear in nums. "00" would also be correct.
        Example 2:

        Input: nums = ["00","01"]
        Output: "11"
        Explanation: "11" does not appear in nums. "10" would also be correct.
        Example 3:

        Input: nums = ["111","011","001"]
        Output: "101"
        Explanation: "101" does not appear in nums. "000", "010", "100", and "110" would also be correct.


        Constraints:

        n == nums.length
        1 <= n <= 16
        nums[i].length == n
        nums[i] is either '0' or '1'.
        All the strings of nums are unique.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findDifferentBinaryString(new String[]{"111","011","001"}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        static Set<String> st = new HashSet<>();
        static String ans = "";
        static void dfs(String s, int n) {
            if (s.length() == n) {
                if (!st.contains(s) && ans.equals("")) {
                    ans = s;
                }
                return;
            }
            dfs(s + '0', n);
            dfs(s + '1', n);
        }

        static public String findDifferentBinaryString(String[] nums) {
            Collections.addAll(st, nums);
            int n = nums[0].length();
            dfs("", n);
            return ans;
        }
    }
}
