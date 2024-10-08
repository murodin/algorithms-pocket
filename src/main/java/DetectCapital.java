public class DetectCapital {
    /*
        We define the usage of capitals in a word to be right when one of the following cases holds:
        All letters in this word are capitals, like "USA".
        All letters in this word are not capitals, like "leetcode".
        Only the first letter in this word is capital, like "Google".
        Given a string word, return true if the usage of capitals in it is right.

        Example 1.
        Input: word = "USA"
        Output: true
        Example 2.
        Input: word = "FlaG"
        Output: false

        Constraints:

        1 <= word.length <= 100
        word consists of lowercase and uppercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.detectCapitalUse("USA"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static boolean detectCapitalUse(String word) {
            if(word.length() == 0 || word.length() == 1) return true;
            if(Character.isUpperCase(word.charAt(0))) {
                boolean isFirstCharacter = Character.isUpperCase(word.charAt(1));
                for(int i = 2; i < word.length(); i++){
                    boolean currentCharState = Character.isUpperCase(word.charAt(i));
                    if(currentCharState != isFirstCharacter) return false;
                }
            } else {
                for(int i = 1; i < word.length(); i++)
                    if(Character.isUpperCase(word.charAt(i))) return false;
            }
            return true;
        }
    }
}
