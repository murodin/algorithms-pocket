import java.util.Arrays;

public class RearrangeWordsInASentence {
    /*
        Given a sentence text (A sentence is a string of space-separated words) in the following format:
        First letter is in upper case.
        Each word in text are separated by a single space.
        Your task is to rearrange the words in text such that all words are rearranged in an increasing order of their lengths.
        If two words have the same length, arrange them in their original order.
        Return the new text following the format shown above.

        Example 1.
        Input: text = "Leetcode is cool"
        Output: "Is cool leetcode"
        Explanation: There are 3 words, "Leetcode" of length 8, "is" of length 2 and "cool" of length 4.
        Output is ordered by length and the new first word starts with capital letter.
        Example 2.
        Input: text = "Keep calm and code on"
        Output: "On and keep calm code"
        Explanation: Output is ordered as follows:
        "On" 2 letters.
        "and" 3 letters.
        "keep" 4 letters in case of tie order by position in original text.
        "calm" 4 letters.
        "code" 4 letters.
        Example 3.
      3.
        Input: text = "To be or not to be"
        Output: "To be or to be not"


        Constraints:

        text begins with a capital letter and then contains lowercase letters and single space between words.
        1 <= text.length <= 10^5
    */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.arrangeWords("Keep calm and code on"));
    }

    // Time: O(NLogN)
    // Space: O(N)
    static class Solution {
        public static String arrangeWords(String text) {
            String[] strArr = text.split(" ");
            Arrays.sort(strArr, (str1, str2) -> str1.length()-str2.length());
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < strArr.length; i++){
                if(i == 0){
                    sb.append(strArr[i].substring(0, 1).toUpperCase()).append(strArr[i].substring(1));
                } else{
                    sb.append(strArr[i].toLowerCase());
                }
                sb.append(" ");
            }
            return sb.toString().trim();
        }
    }
}
