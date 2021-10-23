import java.sql.Time;
import java.util.Arrays;

public class ValidAnagram {

    /*
        Given two strings s and t, return true if t is an anagram of s, and false otherwise.

        Example 1.
        Input: s = "anagram", t = "nagaram"
        Output: true
        Example 2.
        Input: s = "rat", t = "car"
        Output: false
     */

    public static void main(String[] args) {
        System.out.println("Solution I: " + Solution_I.isAnagram("anagram", "nagaram"));
        System.out.println("Solution II: " + Solution_I.isAnagram("anagram", "nagaram"));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution_I {
        public static boolean isAnagram(String s, String t) {
            if(s.length() != t.length()) return false;
            char[] sArr = s.toCharArray();
            char[] tArr = t.toCharArray();

            Arrays.sort(sArr);
            Arrays.sort(tArr);

            return Arrays.equals(sArr, tArr);
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static boolean isAnagram(String s, String t) {
            if(s.length() != t.length()) return false;
            int[] cnt = new int[26];

            for(char c : s.toCharArray()) {
                cnt[c-'a']++;
            }
            for(char c : t.toCharArray()) {
                if(cnt[c-'a'] == 0) return false;
                cnt[c-'a']--;
            }
            return true;
        }
    }
}
