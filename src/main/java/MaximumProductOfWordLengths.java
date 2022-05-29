public class MaximumProductOfWordLengths {
    /*
        Given a string array words, return the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. If no such two words exist, return 0.

        Example 1.
        Input: words = ["abcw","baz","foo","bar","xtfn","abcdef"]
        Output: 16
        Explanation: The two words can be "abcw", "xtfn".
        Example 2.
        Input: words = ["a","ab","abc","d","cd","bcd","abcd"]
        Output: 4
        Explanation: The two words can be "ab", "cd".
        Example 3.
        Input: words = ["a","aa","aaa","aaaa"]
        Output: 0
        Explanation: No such pair of words.


        Constraints:

        2 <= words.length <= 1000
        1 <= words[i].length <= 1000
        words[i] consists only of lowercase English letters."
     */

    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.maxProduct(new String[] {"abcw","baz","foo","bar","xtfn","abcdef"}));
    }

    // Time: O(N*K)
    // Space: O(N)
    static class Solution {
        public static int maxProduct(String[] words) {
            int n = words.length;
            int[] bitmask = new int[n];
            int max = 0;

            for(int i=0; i<n; i++) {
                // Calculate bitmask for current word
                for(int j=0; j<words[i].length(); j++) {
                    // index will be - for a -> 0, b -> 1, c -> 2 and so on
                    int index = words[i].charAt(j) - 'a';

                    // left shift 1 by index and OR it with the current bitmask
                    bitmask[i] |= (1 << index);
                }

                // Compare bitmask of current string with previous strings bitmask
                for(int j=0; j<i; j++) {
                /* If there is a 1 at the same index of current string {i} and {j},
                then bitmask of i and j string will result in a number greater than 0,
                else it will result in 0 */
                    if( (bitmask[i] & bitmask[j]) == 0 )
                        max = Math.max(max, words[i].length()*words[j].length());
                }
            }

            return max;
        }
    }
}
