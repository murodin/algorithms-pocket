import java.util.Arrays;

public class LongestCommonPrefix {
    /*
        Write a function to find the longest common prefix string amongst an array of strings.
        If there is no common prefix, return an empty string "".

        Example 1:

        Input: strs = ["flower","flow","flight"]
        Output: "fl"
        Example 2:

        Input: strs = ["dog","racecar","car"]
        Output: ""
        Explanation: There is no common prefix among the input strings.


        Constraints:

        1 <= strs.length <= 200
        0 <= strs[i].length <= 200
        strs[i] consists of only lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.longestCommonPrefix(new String[]{"flower","flow","flight"}));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static String longestCommonPrefix(String[] v) {
            StringBuilder ans = new StringBuilder();
            Arrays.sort(v);
            String first = v[0];
            String last = v[v.length-1];
            for (int i=0; i<Math.min(first.length(), last.length()); i++) {
                if (first.charAt(i) != last.charAt(i)) {
                    return ans.toString();
                }
                ans.append(first.charAt(i));
            }
            return ans.toString();
        }
    }
}
