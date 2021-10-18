import java.util.LinkedList;
import java.util.List;

public class LetterCasePermutation {

    /*
        Given a string s, we can transform every letter individually to be lowercase or uppercase to create another string.
        Return a list of all possible strings we could create. You can return the output in any order.

        Example 1.
        Input: s = "a1b2"
        Output: ["a1b2","a1B2","A1b2","A1B2"]
        Example 2.
        Input: s = "3z4"
        Output: ["3z4","3Z4"]
        Example 3.
        Input: s = "12345"
        Output: ["12345"]
        Example 4.
        Input: s = "0"
        Output: ["0"]
     */

    public static void main(String[] args) {
        System.out.println("Letter Case Permutations: " + Solution.letterCasePermutation("3z4"));
    }

    // Time: O(2^N)
    // Space: O(N*(2^N))
    static class Solution {
        public static List<String> letterCasePermutation(String S) {
            LinkedList<String> result = new LinkedList<>();
            result.add(S);
            int n = S.length();
            for(int i = n-1; i >= 0; i--) {
                char c = S.charAt(i);
                if(Character.isLetter(c)) {
                    int size = result.size();
                    while(size-- > 0){
                        String s = result.poll();
                        String left = s.substring(0,i);
                        String right = s.substring(i+1, n);
                        result.add(left + Character.toLowerCase(c) + right);
                        result.add(left + Character.toUpperCase(c) + right);
                    }
                }
            }
            return result;
        }
    }
}
