import java.util.ArrayList;
import java.util.List;

public class CheckIfStringIsTransformableWithSubstringSortOperations {
    /*
        Given two strings s and t, transform string s into string t using the following operation any number of times:
        Choose a non-empty substring in s and sort it in place so the characters are in ascending order.
        For example, applying the operation on the underlined substring in "14234" results in "12344".
        Return true if it is possible to transform s into t. Otherwise, return false.

        A substring is a contiguous sequence of characters within a string.

        Example 1:

        Input: s = "84532", t = "34852"
        Output: true
        Explanation: You can transform s into t using the following sort operations:
        "84532" (from index 2 to 3) -> "84352"
        "84352" (from index 0 to 2) -> "34852"
        Example 2:

        Input: s = "34521", t = "23415"
        Output: true
        Explanation: You can transform s into t using the following sort operations:
        "34521" -> "23451"
        "23451" -> "23415"
        Example 3:

        Input: s = "12345", t = "12435"
        Output: false


        Constraints:

        s.length == t.length
        1 <= s.length <= 105
        s and t consist of only digits.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.isTransformable("34521", "23415"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static boolean isTransformable(String s, String t) {
            List<Integer>[] idx = new ArrayList[10];
            int[] pos = new int[10];
            for (int i = 0; i <= 9; ++i)
                idx[i] = new ArrayList<Integer>();
            for (int i = 0; i < s.length(); ++i)
                idx[s.charAt(i) - '0'].add(i);
            for (int i = 0; i < t.length(); ++i) {
                int d = t.charAt(i) - '0';
                if (pos[d] >= idx[d].size())
                    return false;
                for (int j = 0; j < d; ++j)
                    if (pos[j] < idx[j].size() && idx[j].get(pos[j]) < idx[d].get(pos[d]))
                        return false;
                ++pos[d];
            }
            return true;
        }
    }

}
