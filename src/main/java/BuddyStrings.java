import java.util.HashSet;
import java.util.Set;

public class BuddyStrings {
    /*
        Given two strings s and goal, return true if you can swap two letters in s so the result is equal to goal, otherwise, return false.
        Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at s[i] and s[j].
        For example, swapping at indices 0 and 2 in "abcd" results in "cbad".

        Example 1:

        Input: s = "ab", goal = "ba"
        Output: true
        Explanation: You can swap s[0] = 'a' and s[1] = 'b' to get "ba", which is equal to goal.
        Example 2:

        Input: s = "ab", goal = "ab"
        Output: false
        Explanation: The only letters you can swap are s[0] = 'a' and s[1] = 'b', which results in "ba" != goal.
        Example 3:

        Input: s = "aa", goal = "aa"
        Output: true
        Explanation: You can swap s[0] = 'a' and s[1] = 'a' to get "aa", which is equal to goal.


        Constraints:

        1 <= s.length, goal.length <= 2 * 104
        s and goal consist of lowercase letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.buddyStrings("aa", "aa"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static boolean buddyStrings(String s, String goal) {
            int n = s.length();
            if (s.equals(goal)) {
                Set<Character> temp = new HashSet<>();
                for (char c : s.toCharArray()) {
                    temp.add(c);
                }
                return temp.size() < goal.length();
            }
            int i = 0;
            int j = n - 1;
            while (i < j && s.charAt(i) == goal.charAt(i)) {
                i++;
            }
            while (j >= 0 && s.charAt(j) == goal.charAt(j)) {
                j--;
            }
            if (i < j) {
                char[] sArr = s.toCharArray();
                char temp = sArr[i];
                sArr[i] = sArr[j];
                sArr[j] = temp;
                s = new String(sArr);
            }
            return s.equals(goal);
        }
    }
}
