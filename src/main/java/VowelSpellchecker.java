import java.util.*;

public class VowelSpellchecker {

    /*
        Given a wordlist, we want to implement a spellchecker that converts a query word into a correct word.
        For a given query word, the spell checker handles two categories of spelling mistakes:
        Capitalization: If the query matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the case in the wordlist.
        Example: wordlist = ["yellow"], query = "YellOw": correct = "yellow"
        Example: wordlist = ["Yellow"], query = "yellow": correct = "Yellow"
        Example: wordlist = ["yellow"], query = "yellow": correct = "yellow"
        Vowel Errors: If after replacing the vowels ('a', 'e', 'i', 'o', 'u') of the query word with any vowel individually,
        it matches a word in the wordlist (case-insensitive), then the query word is returned with the same case as the match in the wordlist.
        Example: wordlist = ["YellOw"], query = "yollow": correct = "YellOw"
        Example: wordlist = ["YellOw"], query = "yeellow": correct = "" (no match)
        Example: wordlist = ["YellOw"], query = "yllw": correct = "" (no match)
        In addition, the spell checker operates under the following precedence rules:

        When the query exactly matches a word in the wordlist (case-sensitive), you should return the same word back.
        When the query matches a word up to capitlization, you should return the first such match in the wordlist.
        When the query matches a word up to vowel errors, you should return the first such match in the wordlist.
        If the query has no matches in the wordlist, you should return the empty string.
        Given some queries, return a list of words answer, where answer[i] is the correct word for query = queries[i].

        Example 1.
        Input: wordlist = ["KiTe","kite","hare","Hare"], queries = ["kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"]
        Output: ["kite","KiTe","KiTe","Hare","hare","","","KiTe","","KiTe"]
        Example 2.
        Input: wordlist = ["yellow"], queries = ["YellOw"]
        Output: ["yellow"]
     */

    public static void main(String[] args) {
        String[] wordList = {"KiTe","kite","hare","Hare"}, queries = {"kite","Kite","KiTe","Hare","HARE","Hear","hear","keti","keet","keto"};
        System.out.println("Solution: " + Arrays.toString(Solution.spellchecker(wordList, queries)));
    }

    // Time: O(M+N)
    // Space: O(M+N)
    static class Solution {
        public static String[] spellchecker(String[] wordList, String[] queries) {
            Set<String> exact = new HashSet<>();
            Map<String, String> caseInsensitive = new HashMap<>();
            Map<String, String> consonant = new HashMap<>();

            for(String word : wordList) {
                exact.add(word);
                caseInsensitive.putIfAbsent(word.toLowerCase(), word);
                consonant.putIfAbsent(deVowel(word.toLowerCase()), word);
            }
            String[] result = new String[queries.length];
            int i = 0;
            for(String query : queries) {
                if(exact.contains(query))
                    result[i] = query;
                else if(caseInsensitive.containsKey(query.toLowerCase()))
                    result[i] = caseInsensitive.get(query.toLowerCase());
                else if(consonant.containsKey(deVowel(query.toLowerCase())))
                    result[i] = consonant.get(deVowel(query.toLowerCase()));
                else
                    result[i] ="";
                i++;
            }

            return result;
        }

        public static String deVowel(String word) {
            StringBuilder sb = new StringBuilder();
            for(char c : word.toCharArray()) {
                sb.append(isVowel(c) ? "*" : c);
            }
            return sb.toString();
        }

        public static boolean isVowel(char c) {
            return (c == 'a' || c=='e' || c=='i' || c=='o' || c=='u');
        }
    }

}
