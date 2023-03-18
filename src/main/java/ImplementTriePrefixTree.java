import java.util.HashMap;
import java.util.Map;

public class ImplementTriePrefixTree {
    /*
        A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings.
        There are various applications of this data structure, such as autocomplete and spellchecker.
        Implement the Trie class:
        Trie() Initializes the trie object.
        void insert(String word) Inserts the string word into the trie.
        boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
        boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.

        Example 1.
        Input
        ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
        [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
        Output
        [null, null, true, false, true, null, true]

        Explanation
        Trie trie = new Trie();
        trie.insert("apple");
        trie.search("apple");   // return True
        trie.search("app");     // return False
        trie.startsWith("app"); // return True
        trie.insert("app");
        trie.search("app");     // return True


        Constraints:

        1 <= word.length, prefix.length <= 2000
        word and prefix consist only of lowercase English letters.
        At most 3 * 104 calls in total will be made to insert, search, and startsWith.
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    /**
     * Your Trie object will be instantiated and called as such:
     * Trie obj = new Trie();
     * obj.insert(word);
     * boolean param_2 = obj.search(word);
     * boolean param_3 = obj.startsWith(prefix);
     */

    class Trie {
        private TriesNode rootNode;
        class TriesNode {
            private char key;
            private boolean isEnd;
            private Map<Character,TriesNode> preFixMap;
            public TriesNode(char key){
                this.key = key;
                preFixMap = new HashMap<>();
            }
        }

        public Trie() {
            rootNode = new TriesNode('/');
        }
        public void insert(String word) {
            TriesNode tempNode = rootNode;
            for(int i = 0 ; i <word.length();i++){
                char c = word.charAt(i);
                TriesNode findNode = tempNode.preFixMap.get(c);
                if(findNode == null){
                    findNode=new TriesNode(c);
                    tempNode.preFixMap.put(c,findNode);
                }
                tempNode = findNode;
            }
            tempNode.isEnd = true;

        }
        public boolean search(String word) {
            TriesNode searchNode = searchHelper(word);
            return searchNode != null && searchNode.isEnd;
        }

        public boolean startsWith(String prefix) {
            return searchHelper(prefix) != null;
        }

        public TriesNode searchHelper(String word){
            TriesNode tempNode = rootNode;
            for(int i = 0 ; i <word.length();i++){
                char c = word.charAt(i);
                TriesNode findNode = tempNode.preFixMap.get(c);
                if(findNode == null){
                    return null;
                }
                tempNode = findNode;
            }
            return tempNode;
        }
    }
}
