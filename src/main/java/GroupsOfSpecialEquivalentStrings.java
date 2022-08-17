import java.util.Arrays;
import java.util.HashSet;

public class GroupsOfSpecialEquivalentStrings {
    /*
        You are given an array of strings of the same length words.
        In one move, you can swap any two even indexed characters or any two odd indexed characters of a string words[i].
        Two strings words[i] and words[j] are special-equivalent if after any number of moves, words[i] == words[j].
        For example, words[i] = "zzxy" and words[j] = "xyzz" are special-equivalent because we may make the moves "zzxy" -> "xzzy" -> "xyzz".
        A group of special-equivalent strings from words is a non-empty subset of words such that:
        Every pair of strings in the group are special equivalent, and
        The group is the largest size possible (i.e., there is not a string words[i] not in the group such that words[i] is special-equivalent to every string in the group).
        Return the number of groups of special-equivalent strings from words.

        Example 1.
        Input: words = ["abcd","cdab","cbad","xyzz","zzxy","zzyx"]
        Output: 3
        Explanation:
        One group is ["abcd", "cdab", "cbad"], since they are all pairwise special equivalent, and none of the other strings is all pairwise special equivalent to these.
        The other two groups are ["xyzz", "zzxy"] and ["zzyx"].
        Note that in particular, "zzxy" is not special equivalent to "zzyx".
        Example 2.
        Input: words = ["abc","acb","bac","bca","cab","cba"]
        Output: 3


        Constraints:

        1 <= words.length <= 1000
        1 <= words[i].length <= 20
        words[i] consist of lowercase English letters.
        All the strings are of the same length.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.numSpecialEquivGroups(new String[]{"abcd", "cdab", "cbad", "xyzz", "zzxy", "zzyx"}));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static int numSpecialEquivGroups(String[] words) {
            HashSet<String> set = new HashSet<>();
            for (String s : words) {
                String odd = "";
                String even = "";

                for (int i = 0; i < s.length(); i++) {
                    if (i % 2 == 0)
                        odd += s.charAt(i);
                    else
                        even += s.charAt(i);
                }

                char od[] = odd.toCharArray();
                char ev[] = even.toCharArray();

                Arrays.sort(od);
                Arrays.sort(ev);
                String t = String.valueOf(od) + String.valueOf(ev);
                set.add(t);
            }
            return set.size();
        }
    }
}
