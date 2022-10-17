public class CheckIfTheSentenceIsPangram {
    /*
        A pangram is a sentence where every letter of the English alphabet appears at least once.
        Given a string sentence containing only lowercase English letters, return true if sentence is a pangram, or false otherwise.

        Example 1.
        Input: sentence = "thequickbrownfoxjumpsoverthelazydog"
        Output: true
        Explanation: sentence contains at least one of every letter of the English alphabet.
        Example 2.
        Input: sentence = "leetcode"
        Output: false

        Constraints:

        1 <= sentence.length <= 1000
        sentence consists of lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution:" + Solution.checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution {
        public static boolean checkIfPangram(String sentence) {
            // Created count array to take count of every character.
            int[] count = new int[26];
            // Fill our count array
            for(int i = 0; i<sentence.length(); i++)
                count[sentence.charAt(i) - 'a']++;
            // Check in count array that every element is present or not !
            for(int i : count)
                if(i < 1) return false;

            return true;
        }
    }
}
