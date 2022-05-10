import java.util.ArrayList;
import java.util.List;

public class GuessTheWord {
    /*
        This is an interactive problem.
        You are given an array of unique strings wordlist where wordlist[i] is 6 letters long, and one word in this list is chosen as secret.
        You may call Master.guess(word) to guess a word. The guessed word should have type string and must be from the original list with 6 lowercase letters.
        This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.
        Also, if your guess is not in the given wordlist, it will return -1 instead.
        For each test case, you have exactly 10 guesses to guess the word. At the end of any number of calls,
        if you have made 10 or fewer calls to Master.guess and at least one of these guesses was secret, then you pass the test case.

        Example 1.
        Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"], numguesses = 10
        Output: You guessed the secret word correctly.
        Explanation:
        master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
        master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
        master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
        master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
        master.guess("abcczz") returns 4, because "abcczz" has 4 matches.
        We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
        Example 2.
        Input: secret = "hamada", wordlist = ["hamada","khaled"], numguesses = 10
        Output: You guessed the secret word correctly.


        Constraints:

        1 <= wordlist.length <= 100
        wordlist[i].length == 6
        wordlist[i] consist of lowercase English letters.
        All the strings of wordlist are unique.
        secret exists in wordlist.
        numguesses == 10
     */

    public static void main(String[] args) {
        System.out.println("Done.");
    }

    interface Master {
        public int guess(String word);
    }

    static class Solution_I {
        private static String getMostCommonWord(String[] wordlist){
            int[][] count = new int[wordlist[0].length()][26];
            int matches = 0;
            for(String word: wordlist){
                char[] ch = word.toCharArray();
                for(int i = 0; i < 6; i++){
                    count[i][ch[i] - 'a']++;
                }
            }
            int best_score = Integer.MIN_VALUE;
            int curr_score = 0;
            String common = "";
            for(String word: wordlist){
                char[] ch = word.toCharArray();
                for(int i = 0; i < 6; i++){
                    curr_score += count[i][ch[i] - 'a'];
                }
                if(curr_score > best_score){
                    best_score = curr_score;
                    common = word;
                }
                curr_score = 0;
            }

            return common;
        }

        public static void findSecretWord(String[] wordlist, Master master) {
            for(int i = 0, matches = 0; i < 10; i ++){
                String guess = getMostCommonWord(wordlist);
                matches = master.guess(guess);
                if(matches == 6)
                    return;
                List<String> candidates = new ArrayList<>();
                for(String word: wordlist){
                    if(word != guess){
                        if(matches == getMatches(guess, word)){
                            candidates.add(word);
                        }
                    }
                }
                wordlist = candidates.toArray(new String[0]);
            }
        }

        private static int getMatches(String word1, String word2){
            int matches = 0;
            for(int i = 0; i < word1.length(); i ++){
                if(word1.charAt(i) == word2.charAt(i)){
                    matches ++;
                }
            }
            return matches;
        }
    }

    static class Solution_II {
        /* We need to score words based on how frequent the characters are in the given word
            so ex:
            We are storing frequencies cumulatively for all words in the wordlist
            if a's frequency is 3 and z's frequency is 6 and all other letter's frequency is 1
            aazzzz gives a score of (3x2+6x4) = 30 due to repeating characters and abcdef gives a score of (3+1+1+1+1+6 )= 13
            by scoring we have a better chance of guessing the secret

            The for words from wordlist
                if guessvalue is 6 we found the secret
                if the guessvalue is not 6 then we need to remove words from our words list that dont match the pattern
                so if aabbcc gives us 3 matches and wordlist contains xvdecv which gives us 1 match we remove it
                without calling guessword

            todo:
            Needs a helper to modify wordlist
            Needs a scoring function
        */
        public static void findSecretWord(String[] wordlist, Master master) {
            int attempts = 0;
            ArrayList<String> arr = new ArrayList<>();

            for(String s : wordlist){
                arr.add(s);
            }

            while(attempts < 10){
                String word = getWord(arr.toArray(new String[arr.size()]));
                int guessVal = master.guess(word);

                if(guessVal == 6) return;
                arr.removeIf(str -> helper(str,word) != guessVal);      // remove words from word list that dont match current guess value
                attempts++;
            }
        }

        private static String getWord(String[] wordlist){
            int [][]count = new int[wordlist[0].length()][26];

            for(String str : wordlist){ // Store frequencies for every letter in the wordlist here by adding all letter frequencies into a 2d array
                char [] ch = str.toCharArray();
                for(int i = 0; i < ch.length; i++){
                    count[i][ch[i] - 'a']++;
                }
            }

            int highestScore = Integer.MIN_VALUE;
            int score = 0;
            String curr = "";

            for(String str : wordlist){ // Score words based on the letter frequencies
                char [] ch = str.toCharArray();
                for(int i = 0; i < ch.length; i++){
                    score += count[i][ch[i] - 'a'];
                }

                if(score > highestScore){
                    highestScore = score;
                    curr = str;
                }

                score = 0;
            }

            return curr;
        }

        private static int helper(String s, String t){     // to get the guess value of other words, so if our current guess value is 3 any value != 3 is removed
            int value = 0;
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == t.charAt(i)){
                    value++;
                }
            }

            return value;
        }
    }
}
