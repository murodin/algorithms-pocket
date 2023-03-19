import java.util.HashMap;
import java.util.Map;

public class DesignAddAndSearchWordsDataStructure {
    /*
        Design a data structure that supports adding new words and finding if a string matches any previously added string.
        Implement the WordDictionary class:
        WordDictionary() Initializes the object.
        void addWord(word) Adds word to the data structure, it can be matched later.
        bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.'
        where dots can be matched with any letter.

        Example.
        Input
        ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
        [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
        Output
        [null,null,null,null,false,true,true,true]

        Explanation
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        wordDictionary.search("pad"); // return False
        wordDictionary.search("bad"); // return True
        wordDictionary.search(".ad"); // return True
        wordDictionary.search("b.."); // return True


        Constraints:

        1 <= word.length <= 25
        word in addWord consists of lowercase English letters.
        word in search consist of '.' or lowercase English letters.
        There will be at most 3 dots in word for search queries.
        At most 104 calls will be made to addWord and search.
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;
        public TrieNode() {
            children = new HashMap<>();
            isWord = false;
        }
    }

    class WordDictionary {
        private TrieNode root;
        public WordDictionary() {
            root = new TrieNode();
        }
        public void addWord(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }
                node = node.children.get(c);
            }
            node.isWord = true;
        }

        public boolean search(String word) {
            return searchHelper(root, word, 0);
        }

        private boolean searchHelper(TrieNode node, String word, int index) {
            if (index == word.length()) {
                return node.isWord;
            }
            char c = word.charAt(index);
            if (c == '.') {
                for (TrieNode child : node.children.values()) {
                    if (searchHelper(child, word, index + 1)) {
                        return true;
                    }
                }
                return false;
            } else {
                TrieNode child = node.children.get(c);
                if (child == null) {
                    return false;
                }
                return searchHelper(child, word, index + 1);
            }
        }
    }
}
