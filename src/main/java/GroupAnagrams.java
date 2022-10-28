import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    /*
        Given an array of strings strs, group the anagrams together. You can return the answer in any order.
        An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase, typically using all the original letters exactly once.

        Example 1.
        Input: strs = ["eat","tea","tan","ate","nat","bat"]
        Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
        Example 2.
        Input: strs = [""]
        Output: [[""]]
        Example 3.
        Input: strs = ["a"]
        Output: [["a"]]


        Constraints:

        1 <= strs.length <= 104
        0 <= strs[i].length <= 100
        strs[i] consists of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.groupAnagrams(new String[]{"eat","tea","tan","ate","nat","bat"}));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static List<List<String>> groupAnagrams(String[] strings) {
            HashMap<String, List<String>> groups = new HashMap<>();
            for (String s : strings) {
                // [1] compute letter frequencies
                char[] hash = new char[26];
                s.chars().forEachOrdered(ch -> hash[ch-'a'] += 1);
                // [2] smart way to obtain a hashable key for a map
                String hashKey = String.valueOf(hash);
                // [3] update group
                if (!groups.containsKey(hashKey))
                    groups.put(hashKey, new ArrayList<>());
                groups.get(hashKey).add(s);
            }

            // [4] nice one-liner to extract a list of values from map
            return new ArrayList<>(groups.values());
        }
    }
}
