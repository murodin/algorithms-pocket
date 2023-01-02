import java.util.HashMap;
import java.util.Map;

public class WordPattern {
    /*
        Given a pattern and a string s, find if s follows the same pattern.
        Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.

        Example 1.
        Input: pattern = "abba", s = "dog cat cat dog"
        Output: true
        Example 2.
        Input: pattern = "abba", s = "dog cat cat fish"
        Output: false
        Example 3:

        Input: pattern = "aaaa", s = "dog cat cat dog"
        Output: false


        Constraints:

        1 <= pattern.length <= 300
        pattern contains only lower-case English letters.
        1 <= s.length <= 3000
        s contains only lowercase English letters and spaces ' '.
        s does not contain any leading or trailing spaces.
        All the words in s are separated by a single space.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.wordPattern("abba", "dog cat cat dog"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static boolean wordPattern(String pattern, String s) {
            String[] arr = s.split(" ");
            if(arr.length != pattern.length()) return false;
            Map<Character, String> hs = new HashMap<Character, String>();
            for(int i=0; i<arr.length; i++){
                char c = pattern.charAt(i);
                String str = arr[i];
                if(hs.containsKey(c)){
                    if(!hs.get(c).equals(str))
                        return false;
                } else {
                    if(hs.containsValue(str)) return false;
                    hs.put(c, str);
                }
            }
            return true;
        }
    }
}
