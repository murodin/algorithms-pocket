public class MaximumNumberOfVowelsInASubstringOfGivenLength {
    /*
        Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.

        Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

        Example 1:

        Input: s = "abciiidef", k = 3
        Output: 3
        Explanation: The substring "iii" contains 3 vowel letters.
        Example 2:

        Input: s = "aeiou", k = 2
        Output: 2
        Explanation: Any substring of length 2 contains 2 vowels.
        Example 3:

        Input: s = "leetcode", k = 3
        Output: 2
        Explanation: "lee", "eet" and "ode" contain 2 vowels.


        Constraints:

        1 <= s.length <= 105
        s consists of lowercase English letters.
        1 <= k <= s.length
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxVowels("leetcode", 3));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int maxVowels(String s, int k) {
            int n = s.length();
            int maxVowels = 0;
            int count = 0;

            int[] vowels = new int[128];
            vowels['a'] = 1;
            vowels['e'] = 1;
            vowels['i'] = 1;
            vowels['o'] = 1;
            vowels['u'] = 1;

            for (int i = 0; i < k; i++) {
                count += vowels[s.charAt(i)];
            }

            maxVowels = count;
            for (int i = k; i < n; i++) {
                count += vowels[s.charAt(i)] - vowels[s.charAt(i - k)];
                maxVowels = Math.max(maxVowels, count);
                if (maxVowels == k) {
                    return maxVowels;
                }
            }
            return maxVowels;
        }
    }
}
