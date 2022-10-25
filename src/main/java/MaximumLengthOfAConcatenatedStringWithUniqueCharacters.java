import java.util.ArrayList;
import java.util.List;

public class MaximumLengthOfAConcatenatedStringWithUniqueCharacters {
    /*
        You are given an array of strings arr. A string s is formed by the concatenation of a subsequence of arr that has unique characters.
        Return the maximum possible length of s.
        A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.

        Example 1.
        Input: arr = ["un","iq","ue"]
        Output: 4
        Explanation: All the valid concatenations are:
        - ""
        - "un"
        - "iq"
        - "ue"
        - "uniq" ("un" + "iq")
        - "ique" ("iq" + "ue")
        Maximum length is 4.
        Example 2.
        Input: arr = ["cha","r","act","ers"]
        Output: 6
        Explanation: Possible longest valid concatenations are "chaers" ("cha" + "ers") and "acters" ("act" + "ers").
        Example 3.
        Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
        Output: 26
        Explanation: The only string in arr has all 26 characters.


        Constraints:

        1 <= arr.length <= 16
        1 <= arr[i].length <= 26
        arr[i] contains only lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxLength(List.of("cha","r","act","ers")));
    }

    // Time: O(NxM); N where List size, M where is string char length.
    // Space: O(N)
    static class Solution {
        public static int maxLength(List<String> A) {
            List<Integer> dp = new ArrayList<>();
            dp.add(0);
            int res = 0;
            for (String s : A) {
                int a = 0, dup = 0;
                for (char c : s.toCharArray()) {
                    dup |= a & (1 << (c - 'a'));
                    a |= 1 << (c - 'a');
                }
                if (dup > 0) continue;
                for (int i = dp.size() - 1; i >= 0; --i) {
                    if ((dp.get(i) & a) > 0) continue;
                    dp.add(dp.get(i) | a);
                    res = Math.max(res, Integer.bitCount(dp.get(i) | a));
                }
            }
            return res;
        }
    }
}
