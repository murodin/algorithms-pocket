import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ShortEncodingOfWords {

    /*
        A valid encoding of an array of words is any reference string s and array of indices indices such that:
        words.length == indices.length
        The reference string s ends with the '#' character.
        For each index indices[i], the substring of s starting from indices[i] and up to (but not including) the next '#' character is equal to words[i].
        Given an array of words, return the length of the shortest reference string s possible of any valid encoding of words.

        Example 1.
        Input: words = ["time", "me", "bell"]
        Output: 10
        Explanation: A valid encoding would be s = "time#bell#" and indices = [0, 2, 5].
        words[0] = "time", the substring of s starting from indices[0] = 0 to the next '#' is underlined in "time#bell#"
        words[1] = "me", the substring of s starting from indices[1] = 2 to the next '#' is underlined in "time#bell#"
        words[2] = "bell", the substring of s starting from indices[2] = 5 to the next '#' is underlined in "time#bell#"
        Example 2.
        Input: words = ["t"]
        Output: 2
        Explanation: A valid encoding would be s = "t#" and indices = [0].
     */

    public static void main(String[] args) {
        String[] words = {"time", "me", "bell"};
        System.out.println("Solution I: " + Solution.minimumLengthEncoding(words));
        System.out.println("Solution II: " + Solution_II.minimumLengthEncoding(words));
        System.out.println("Solution III: " + Solution_III.minimumLengthEncoding(words));
    }

    // Time: O(NLogN)
    // Space: O(Len(Words))
    static class Solution {
        public static int minimumLengthEncoding(String[] words) {
            Arrays.sort(words,(a, b) -> b.length()-a.length());
            StringBuilder encoding=new StringBuilder();
            for(String s:words){
                if(encoding.indexOf(s+"#")==-1){
                    encoding.append(s+"#");
                }
            }

            return encoding.length();
        }
    }

    // Time: O(Len(Words)*Len(Word))
    // Space: O(Len(Words))
    static class Solution_II {
        public static int minimumLengthEncoding(String[] words) {
            Set<String> wordSet=new HashSet<>(Arrays.asList(words));

            for(String s:words){
                for(int i=1;i<s.length();i++){
                    wordSet.remove(s.substring(i));
                }
            }

            int length=0;
            for(String s:wordSet){
                length+=s.length()+1;
            }

            return length;
        }
    }

    // Time: O(Len(Words))
    // Space: O(Len(Words))
    static class Solution_III {
        public static int minimumLengthEncoding(String[] words) {
            Trie root=new Trie();
            root.next=new Trie[26];

            int length=0;
            for(String s:words){
                length += helper(s,root);
            }

            return length;
        }

        private static int helper(String s,Trie node){
            boolean newBranch=false;
            int create=0;
            for(int i=s.length()-1;i>=0;i--){
                boolean newLevel = false;
                int id = s.charAt(i)- 'a';

                if(node.next==null){
                    newLevel =true;
                    node.next = new Trie[26];
                }

                if(node.next[id] == null){
                    if(newLevel==false) newBranch=true;
                    node.next[id]=new Trie();
                    create++;
                }

                node=node.next[id];
            }

            return newBranch?s.length()+1 : create;
        }

        static class Trie{
            Trie[] next=null;
        }
    }
}
