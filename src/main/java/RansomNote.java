public class RansomNote {
    /*
        Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.
        Each letter in magazine can only be used once in ransomNote.

        Example 1.
        Input: ransomNote = "a", magazine = "b"
        Output: false
        Example 2.
        Input: ransomNote = "aa", magazine = "ab"
        Output: false
        Example 3.
        Input: ransomNote = "aa", magazine = "aab"
        Output: true

        Constraints:

        1 <= ransomNote.length, magazine.length <= 105
        ransomNote and magazine consist of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.canConstruct("aa", "aab"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static boolean canConstruct(String ransomNote, String magazine) {
            int[] count = new int[26];
            for(char ch: magazine.toCharArray()) {
                count[ch - 'a']++;
            }

            for(char ch: ransomNote.toCharArray()) {
                int val = ch - 'a';
                if(count[val] <= 0) return false;
                count[val]--;
            }
            return true;
        }
    }
}
