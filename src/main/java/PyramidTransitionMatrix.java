import java.util.*;

public class PyramidTransitionMatrix {
    /*
        You are stacking blocks to form a pyramid. Each block has a color, which is represented by a single letter.
        Each row of blocks contains one less block than the row beneath it and is centered on top.

        To make the pyramid aesthetically pleasing, there are only specific triangular patterns that are allowed.
        A triangular pattern consists of a single block stacked on top of two blocks. The patterns are given as a list of three-letter strings allowed,
        where the first two characters of a pattern represent the left and right bottom blocks respectively, and the third character is the top block.

        For example, "ABC" represents a triangular pattern with a 'C' block stacked on top of an 'A' (left) and 'B' (right) block.

        Note that this is different from "BAC" where 'B' is on the left bottom and 'A' is on the right bottom.
        You start with a bottom row of blocks bottom, given as a single string, that you must use as the base of the pyramid.

        Given bottom and allowed, return true if you can build the pyramid all the way to the top such that every triangular pattern in the pyramid is in allowed,
        or false otherwise.



        Example 1:


        Input: bottom = "BCD", allowed = ["BCC","CDE","CEA","FFF"]
        Output: true
        Explanation: The allowed triangular patterns are shown on the right.
        Starting from the bottom (level 3), we can build "CE" on level 2 and then build "A" on level 1.
        There are three triangular patterns in the pyramid, which are "BCC", "CDE", and "CEA". All are allowed.
        Example 2:


        Input: bottom = "AAAA", allowed = ["AAB","AAC","BCD","BBE","DEF"]
        Output: false
        Explanation: The allowed triangular patterns are shown on the right.
        Starting from the bottom (level 4), there are multiple ways to build level 3, but trying all the possibilites, you will get always stuck before building level 1.


        Constraints:

        2 <= bottom.length <= 6
        0 <= allowed.length <= 216
        allowed[i].length == 3
        The letters in all input strings are from the set {'A', 'B', 'C', 'D', 'E', 'F'}.
        All the values of allowed are unique
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.pyramidTransition("BCD", List.of("BCC","CDE","CEA","FFF")));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static boolean pyramidTransition(String bottom, List<String> allowed) {
            Map<String, List<Character>> map = new HashMap<>();
            for (String s : allowed) {
                String key = s.substring(0, 2);
                map.putIfAbsent(key, new ArrayList<>());
                map.get(key).add(s.charAt(2));
            }
            return dfs(map, bottom + "#", new HashSet<>());
        }

        static boolean dfs(Map<String, List<Character>> map, String s, Set<String> memo) {
            if (s.length() == 1) return true;
            if (memo.contains(s)) return false;
            String key = s.substring(0, 2);
            if (key.charAt(1) == '#') return dfs(map, s.substring(2) + '#', memo);
            for (char c : map.getOrDefault(key, new ArrayList<>())) {
                if (dfs(map, s.substring(1) + c, memo)) return true;
            }
            memo.add(s);
            return false;
        }

    }
}
