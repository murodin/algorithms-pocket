import java.util.HashMap;
import java.util.Map;

public class SubstringWithLargestVariance {
    /*
        The variance of a string is defined as the largest difference
        between the number of occurrences of any 2 characters present in the string. Note the two characters may or may not be the same.
        Given a string s consisting of lowercase English letters only, return the largest variance possible among all substrings of s.
        A substring is a contiguous sequence of characters within a string.

        Example 1.
        Input: s = "aababbb"
        Output: 3
        Explanation:
        All possible variances along with their respective substrings are listed below:
        - Variance 0 for substrings "a", "aa", "ab", "abab", "aababb", "ba", "b", "bb", and "bbb".
        - Variance 1 for substrings "aab", "aba", "abb", "aabab", "ababb", "aababbb", and "bab".
        - Variance 2 for substrings "aaba", "ababbb", "abbb", and "babb".
        - Variance 3 for substring "babbb".
        Since the largest possible variance is 3, we return it.
        Example 2.
        Input: s = "abcde"
        Output: 0
        Explanation:
        No letter occurs more than once in s, so the variance of every substring is 0.


        Constraints:

        1 <= s.length <= 104
        s consists of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.largestVariance("aababbb"));
    }

    // Time: O(N^2)
    // Space: O(N)
    static class Solution {
        public static int largestVariance(String s) {
            Map<Character, Integer> map = new HashMap<>();
            for(char c : s.toCharArray())
                map.put(c, map.getOrDefault(c, 0) + 1);

            int max=0;
            for(char c1 : map.keySet()) {
                for(char c2 : map.keySet()) {
                    if(c1 == c2)
                        continue;
                    int c1Freq=0, c2Freq=0;
                    int c1Remaining=map.get(c1);
                    for(char c : s.toCharArray()) {
                        if(c == c1) {
                            c1Freq++;
                            c1Remaining--;
                        }
                        if(c == c2)
                            c2Freq++;

                        if(c2Freq < c1Freq && c1Remaining > 0) {
                            c2Freq=0;
                            c1Freq=0;
                        }

                        if(c1Freq > 0 && c2Freq > 0)
                            max = Math.max(max, c2Freq-c1Freq);
                    }
                }
            }
            return max;
        }
    }
}
