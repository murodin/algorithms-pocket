public class LongestSubstringWithAtLeastKRepeatingCharacters {

    /*
        Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.

        Example 1.
        Input: s = "aaabb", k = 3
        Output: 3
        Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
        Example 2.
        Input: s = "ababbc", k = 2
        Output: 5
        Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.


        Constraints:

        1 <= s.length <= 104
        s consists of only lowercase English letters.
        1 <= k <= 105
     */

    public static void main(String[] args) {
        System.out.println("Longest substring with at least 3 repeating chars: " + Solution.longestSubstring("aaabb", 3));
        System.out.println("Longest substring with at least 2 repeating chars: " + Solution.longestSubstring("ababbc", 2));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int longestSubstring(String s, int k) {
            return helper(s.toCharArray(), 0, s.length(), k);
        }

        static int helper(char[] ch, int start, int end, int k) {
            if(end - start < k) return 0;

            int[] count = new int[26];

            for(int i=start; i<end; i++)
                count[ch[i] -'a']++;

            for(int i=start; i<end; i++) {
                if(count[ch[i] -'a'] < k) {
                    int j = i + 1;
                    while (j<end && count[ch[i] -'a'] < k ) j++;
                    return Math.max(helper(ch, start, i, k), helper(ch, j, end, k));
                }
            }
            return end-start;
        }
    }

}
