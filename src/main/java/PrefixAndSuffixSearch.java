import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PrefixAndSuffixSearch {

    /*
        Design a special dictionary with some words that searchs the words in it by a prefix and a suffix.

        Implement the WordFilter class:

        WordFilter(string[] words) Initializes the object with the words in the dictionary
        f(string prefix, string suffix) Returns the index of the word in the dictionary, which has the prefix prefix and the suffix suffix.
        If there is more than one valid index, return the largest of them. If there is no such word in the dictionary, return -1.

        Example 1.

        Input
        ["WordFilter", "f"]
        [[["apple"]], ["a", "e"]]
        Output
        [null, 0]

        Explanation
        WordFilter wordFilter = new WordFilter(["apple"]);
        wordFilter.f("a", "e"); // return 0, because the word at index 0 has prefix = "a" and suffix = 'e".

     */

    public static void main(String[] args) {
        String[][] testWords = {{"apple", "applet", "ape"}, {"a", "e"}};
        Solution.WordFilter wordFilter = new Solution.WordFilter(testWords[0]);
        System.out.println("Find words: " + wordFilter.f(testWords[1][0], testWords[1][1]));
    }

    static class Solution {
        static class Trie {
            public Trie[] t;
            List<Integer> index;

            public Trie() {
                this.t = new Trie[26];
                this.index = new ArrayList<>();
            }

            // insert
            public void insert(String word, int i) {
                Trie root = this;
                for(char c: word.toCharArray()) {
                    if(root.t[c-'a'] == null) {
                        root.t[c-'a'] = new Trie();
                    }
                    root = root.t[c-'a'];
                    root.index.add(i);
                }
            }

            // startsWith
            public List<Integer> startsWith(String word) {
                Trie root = this;
                for(char c: word.toCharArray()) {
                    if(root.t[c-'a'] == null) {
                        return new ArrayList<>();
                    }
                    root = root.t[c-'a'];
                }
                return  root.index;
            }
        }

        // Total Time: O(n*k + Q(n+k))
        // Space: O(n*k)
        static class WordFilter {
            static Trie prefix = null;
            static Trie suffix = null;

            // Time: O(n*k)
            public WordFilter(String[] words) {
                prefix = new Trie();
                suffix = new Trie();

                for(int i = 0; i < words.length; i++) {
                    prefix.insert(words[i], i);
                    suffix.insert(new StringBuilder(words[i]).reverse().toString(), i);
                }
            }

            // Time: O(k+n)
            public static int f(String pre, String suf) {
                List<Integer> pList = prefix.startsWith(pre);
                List<Integer> sList = suffix.startsWith(new StringBuilder(suf).reverse().toString());

                int i = pList.size()-1, j = sList.size()-1;
                while (i>=0 && j>=0) {
                    if(Objects.equals(pList.get(i), sList.get(j))) return pList.get(i);
                    else if(pList.get(i) > sList.get(j)) i--;
                    else j--;
                }

                return -1;
            }
        }
    }
}
