import java.util.ArrayList;
import java.util.List;

public class FindAllAnagramsInAString {
    /*
        Given two strings s and p, return an array of all the start indices of p's anagrams in s. You may return the answer in any order.
        An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

        Example 1.
        Input: s = "cbaebabacd", p = "abc"
        Output: [0,6]
        Explanation:
        The substring with start index = 0 is "cba", which is an anagram of "abc".
        The substring with start index = 6 is "bac", which is an anagram of "abc".
        Example 2.
        Input: s = "abab", p = "ab"
        Output: [0,1,2]
        Explanation:
        The substring with start index = 0 is "ab", which is an anagram of "ab".
        The substring with start index = 1 is "ba", which is an anagram of "ab".
        The substring with start index = 2 is "ab", which is an anagram of "ab".


        Constraints:

        1 <= s.length, p.length <= 3 * 104
        s and p consist of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findAnagrams("cbaebabacd", "abc"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static List<Integer> findAnagrams(String s, String p) {
            ArrayList<Integer> soln = new ArrayList<Integer>();
            if (s.length() == 0 || p.length() == 0 || s.length() < p.length()){
                return new ArrayList<Integer>();
            }
            int[] chars = new int[26];
            for (Character c : p.toCharArray()) {
                chars[c-'a']++;
            }
            int start = 0, end = 0, len = p.length(), diff = len;
            char temp;
            for (end = 0; end < len; end++) {
                temp = s.charAt(end);
                chars[temp-'a']--;
                if (chars[temp-'a'] >= 0){
                    diff--;
                }
            }
            if (diff == 0){
                soln.add(0);
            }
            while (end < s.length()) {
                temp = s.charAt(start);
                if (chars[temp-'a'] >= 0){
                    diff++;
                }
                chars[temp-'a']++;
                start++;
                temp = s.charAt(end);
                chars[temp-'a']--;
                if (chars[temp-'a'] >= 0){
                    diff--;
                }
                if (diff == 0){
                    soln.add(start);
                }
                end++;

            }
            return soln;
        }
    }
}
