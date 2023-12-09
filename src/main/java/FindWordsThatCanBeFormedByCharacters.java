public class FindWordsThatCanBeFormedByCharacters {
    /*
        You are given an array of strings words and a string chars.
        A string is good if it can be formed by characters from chars (each character can only be used once).
        Return the sum of lengths of all good strings in words.

        Example 1:
        Input: words = ["cat","bt","hat","tree"], chars = "atach"
        Output: 6
        Explanation: The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
        Example 2:
        Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
        Output: 10
        Explanation: The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.


        Constraints:

        1 <= words.length <= 1000
        1 <= words[i].length, chars.length <= 100
        words[i] and chars consist of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.countCharacters(new String[]{"cat","bt","hat","tree"}, "atach"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static int countCharacters(String[] words, String chars) {
            int[] counts = new int[26];
            for(int i=0; i<chars.length(); i++){
                counts[chars.charAt(i)-'a']++;
            }
            int res = 0 ;
            for(String s : words) {
                if(canForm(s, counts))
                    res += s.length();
            }
            return res;
        }

        private static boolean canForm(String word, int[] counts) {
            int[] c = new int[26];
            for(int i = 0; i<word.length(); i++) {
                int x = word.charAt(i)-'a';
                c[x]++;
                if(c[x] > counts[x])
                    return false;
            }
            return true;
        }
    }
}
