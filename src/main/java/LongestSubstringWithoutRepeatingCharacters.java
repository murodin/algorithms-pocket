import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    /*
        Given a string s, find the length of the longest substring without repeating characters.

        Example 1.
        Input: s = "abcabcbb"
        Output: 3
        Explanation: The answer is "abc", with the length of 3.
        Example 2.
        Input: s = "bbbbb"
        Output: 1
        Explanation: The answer is "b", with the length of 1.
        Example 3.
        Input: s = "pwwkew"
        Output: 3
        Explanation: The answer is "wke", with the length of 3.
        Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
        Example 4.
        Input: s = ""
        Output: 0

        Constraints.
            0 <= s.length <= 5 * 104
            s consists of English letters, digits, symbols and spaces.
     */

    public static void main(String[] args) {
        System.out.println("Longest Substring Without Repeating Characters:" + Solution.lengthOfLongestSubstring("abcabcbb"));
    }

    // Time: O(N)
    // Space: O(Min(M,N)), where M: char set, N: len(substring)
    static class Solution {
        public static int lengthOfLongestSubstring(String s) {
            Map<Character,Integer> map=new HashMap<>();
            int start=0,len=0;

            for(int end=0;end<s.length();end++){
                char c=s.charAt(end);

                if(map.containsKey(c)){
                    if(start<=map.get(c)){
                        start=map.get(c)+1;
                    }
                }

                len=Math.max(len,end-start+1);
                map.put(c,end);
            }
            return len;
        }
    }
}
