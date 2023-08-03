import java.util.HashSet;
import java.util.Set;

public class SplitAStringIntoTheMaxNumberOfUniqueSubstrings {
    /*
        Given a string s, return the maximum number of unique substrings that the given string can be split into.
        You can split string s into any list of non-empty substrings, where the concatenation of the substrings forms the original string.
        However, you must split the substrings such that all of them are unique.

        A substring is a contiguous sequence of characters within a string.

        Example 1:

        Input: s = "ababccc"
        Output: 5
        Explanation: One way to split maximally is ['a', 'b', 'ab', 'c', 'cc']. Splitting like ['a', 'b', 'a', 'b', 'c', 'cc'] is not valid
        as you have 'a' and 'b' multiple times.
        Example 2:

        Input: s = "aba"
        Output: 2
        Explanation: One way to split maximally is ['a', 'ba'].
        Example 3:

        Input: s = "aa"
        Output: 1
        Explanation: It is impossible to split the string any further.


        Constraints:

        1 <= s.length <= 16

        s contains only lower case English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxUniqueSplit("aa"));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int maxUniqueSplit(String s) {
            Set<String> set = new HashSet<>();
            return dfs(set, 0, s);
        }

        private static int dfs(Set<String> set, int idx, String s) {
            if (idx >= s.length()) return 0;
            int res = -1;  // did not find method to split;
            for (int i = idx + 1; i <= s.length(); i++) {
                String sub = s.substring(idx, i);
                if (!set.add(sub)) continue; //already contains sub
                int next = dfs(set, i, s);
                if (next >= 0) res = Math.max(res, next + 1);
                set.remove(sub);
            }
            return res;
        }
    }
}
