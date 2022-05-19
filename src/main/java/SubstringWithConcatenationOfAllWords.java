import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SubstringWithConcatenationOfAllWords {

    /*
        You are given a string s and an array of strings words of the same length.
        Return all starting indices of substring(s) in s that is a concatenation of each word in words exactly once, in any order, and without any intervening characters.
        You can return the answer in any order.

        Example 1.
        Input: s = "barfoothefoobarman", words = ["foo","bar"]
        Output: [0,9]
        Explanation: Substrings starting at index 0 and 9 are "barfoo" and "foobar" respectively.
        The output order does not matter, returning [9,0] is fine too.
        Example 2.
        Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
        Output: []
        Example 3.
        Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
        Output: [6,9,12]

        Constraints:

        1 <= s.length <= 104
        s consists of lower-case English letters.
        1 <= words.length <= 5000
        1 <= words[i].length <= 30
     */

    public static void main(String[] args) {
        String s = "barfoofoobarthefoobarman";
        String[] words = {"bar","foo","the"};
        System.out.println("Solution-I: " + Solution_I.findSubstring(s, words));
        System.out.println("Solution-II: " + Solution_II.findSubstring(s, words));
    }

    // Time: O(N*M)
    // Space: O(M)
    static class Solution_I{
        public static List<Integer> findSubstring(String s, String[] words) {
            List<Integer> res = new ArrayList<>();
            if(s.length() == 0 || words.length == 0){
                return res;
            }
            int numberOfWords = words.length;
            int wordLength = words[0].length();
            if (numberOfWords * wordLength > s.length()) {
                return res;
            }

            HashMap<String, Integer> wordCounter = new HashMap<>();
            for(String word: words){
                if(!wordCounter.containsKey(word)){
                    wordCounter.put(word, 1);
                }else{
                    wordCounter.put(word, wordCounter.get(word) + 1);
                }
            }

            for(int k = 0; k < words[0].length(); k++){
                HashMap<String, Integer> subStringCounter = new HashMap<>();
                int i = k;
                int j;
                for(j = k; j <= s.length() - wordLength && i <= s.length() - numberOfWords * wordLength; j += wordLength){
                    String subString = s.substring(j, j + wordLength);
                    if(!wordCounter.containsKey(subString)){
                        subStringCounter.clear();
                        i = j + wordLength;
                        continue;
                    }

                    if(!subStringCounter.containsKey(subString)){
                        subStringCounter.put(subString, 1);
                    }else{
                        subStringCounter.put(subString, subStringCounter.get(subString) + 1);
                    }

                    while(subStringCounter.get(subString) > wordCounter.get(subString)){
                        String tempSubString = s.substring(i, i + wordLength);
                        if(subStringCounter.get(tempSubString) == 1){
                            subStringCounter.remove(tempSubString);
                        }else{
                            subStringCounter.put(tempSubString, subStringCounter.get(tempSubString) - 1);
                        }
                        i += wordLength;
                    }

                    if(j - i + wordLength == numberOfWords * wordLength){
                        res.add(i);
                    }
                }
            }
            return res;
        }
    }

    // Time: O(N*M)
    // Space: O(M)
    static class Solution_II {
        public static List<Integer> findSubstring(String s, String[] words) {
            var inputMap = new HashMap<String, Integer>();
            var currMap = new HashMap<String, Integer>();
            var result = new ArrayList<Integer>();

            var totalWords = words.length;
            int start = 0, count = 0, wordLen = words[0].length();

            // Store frequency of input words
            for (String word: words) {
                inputMap.put(word, inputMap.getOrDefault(word, 0) + 1);
            }

            for (int i = 0; i < wordLen; i++) {
                // reset the current stats
                start = i;
                count = 0;
                currMap.clear();

                for (int j = i; j + wordLen <= s.length(); j += wordLen) {
                    String currWord = s.substring(j, j + wordLen);

                    // if input map has the word, increment word count, store word freq in currMap
                    if (inputMap.containsKey(currWord)) {

                        currMap.put(currWord, currMap.getOrDefault(currWord, 0) + 1);

                        // if curr word freq is not more than the input freq
                        if (currMap.get(currWord) <= inputMap.get(currWord)) {
                            count++;
                        }

                        // Until we have curr word freq is not more than the input freq
                        while (currMap.get(currWord) > inputMap.get(currWord)) {
                            String wordToRemove = s.substring(start, start + wordLen);
                            currMap.put(wordToRemove, currMap.get(wordToRemove) - 1);
                            start = start + wordLen;
                            if (currMap.get(wordToRemove) < inputMap.get(wordToRemove)) {
                                count--;
                            }
                        }

                        // if we reach the total word count of input
                        if (count == totalWords) {
                            result.add(start);
                            // discard the first word of the complete finding and reduce count
                            // because we can start finding another occurence
                            String temp = s.substring(start, start + wordLen);
                            currMap.put(temp, currMap.get(temp) - 1);
                            start = start + wordLen;
                            count--;
                        }

                    } else {
                        // reset the current findings.
                        count = 0;
                        start = j + wordLen; // discard curr word and move start to next word
                        currMap.clear(); // because we did not find complete substring, clear curr frequecies
                    }
                }
            }
            return result;

        }
    }
}
