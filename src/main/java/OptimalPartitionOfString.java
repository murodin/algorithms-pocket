import java.util.HashMap;
import java.util.Map;

public class OptimalPartitionOfString {
    /*
        Given a string s, partition the string into one or more substrings such that the characters in each substring are unique.
        That is, no letter appears in a single substring more than once.
        Return the minimum number of substrings in such a partition.
        Note that each character should belong to exactly one substring in a partition.

        Example 1.
        Input: s = "abacaba"
        Output: 4
        Explanation:
        Two possible partitions are ("a","ba","cab","a") and ("ab","a","ca","ba").
        It can be shown that 4 is the minimum number of substrings needed.
        Example 2.
        Input: s = "ssssss"
        Output: 6
        Explanation:
        The only valid partition is ("s","s","s","s","s","s").


        Constraints:

        1 <= s.length <= 105
        s consists of only English lowercase letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.partitionString("abacaba"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int partitionString(String s) {
            int idx = 0;
            int count = 0;
            Map<Character, Boolean> mp = new HashMap<>();
            while (idx < s.length()) {
                if (mp.containsKey(s.charAt(idx))) {
                    count++;
                    mp.clear();
                }
                mp.put(s.charAt(idx), true);
                idx++;
            }
            return count + 1;
        }
    }
}
