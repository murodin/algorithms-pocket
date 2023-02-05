import java.util.Arrays;

public class PermutationInString {
    /*
        Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
        In other words, return true if one of s1's permutations is the substring of s2.

        Example 1.
        Input: s1 = "ab", s2 = "eidbaooo"
        Output: true
        Explanation: s2 contains one permutation of s1 ("ba").
        Example 2.
        Input: s1 = "ab", s2 = "eidboaoo"
        Output: false

        Constraints:

        1 <= s1.length, s2.length <= 104
        s1 and s2 consist of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.checkInclusion("ab", "eidbaooo"));
    }

    // Time: O(N^2)
    // Space: O(1)
    static class Solution {
        public static boolean checkInclusion(String s1, String s2) {
            int[] cnt = new int[26];
            for(char x: s1.toCharArray()){
                cnt[x - 'a']++;
            }
            int n1 = s1.length(), n2 = s2.length();
            for(int i = 0; i < n2 - n1 + 1; i++){
                int[] cnt2 = new int[26];
                for(int j = i; j < i + n1; j++){
                    cnt2[s2.charAt(j) - 'a']++;
                }
                if(Arrays.equals(cnt, cnt2)) return true;
            }
            return false;
        }
    }
}
