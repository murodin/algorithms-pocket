import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordLadder {

    /*
        A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:
        Every adjacent pair of words differs by a single letter.
        Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
        sk == endWord
        Given two words, beginWord and endWord, and a dictionary wordList,
        return the number of words in the shortest transformation sequence from beginWord to endWord, or 0 if no such sequence exists.

        Example 1.
        Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
        Output: 5
        Explanation: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
        Example 2.
        Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
        Output: 0
        Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.

        Constraints.
        1 <= beginWord.length <= 10
        endWord.length == beginWord.length
        1 <= wordList.length <= 5000
        wordList[i].length == beginWord.length
        beginWord, endWord, and wordList[i] consist of lowercase English letters.
        beginWord != endWord
        All the words in wordList are unique.
     */

    public static void main(String[] args) {
        System.out.println("Word Ladder I: "+Solution_I.ladderLength("hit", "cog", List.of("hot","dot","dog","lot","log","cog")));
        System.out.println("Word Ladder II: "+Solution_II.ladderLength("hit", "cog", List.of("hot","dot","dog","lot","log","cog")));
    }

    // Time: O(M^2*N), where M:len(word), N: number pof wordList
    // Space: O(M^2*N)
    static class Solution_I {
        public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if(!wordList.contains(endWord)) return 0;

            Set<String> beginSet = new HashSet<>();
            beginSet.add(beginWord);

            Set<String> wordSet = new HashSet<>(wordList);

            return bfs(beginSet, endWord, wordSet,1);
        }

        static int bfs(Set<String> beginSet, String endWord, Set<String> wordSet, int distance){
            Set<String> reachableSet =new HashSet<>();
            wordSet.removeAll(beginSet);

            for(String word:beginSet){
                for(int pos=0;pos<word.length();pos++){
                    char[] charArray=word.toCharArray();
                    for(char c='a';c<='z';c++){
                        charArray[pos]=c;
                        String newWord=new String(charArray);
                        if(wordSet.contains(newWord)){
                            if(newWord.equals(endWord)){
                                return distance + 1;
                            }
                            reachableSet.add(newWord);
                        }
                    }
                }
            }
            distance++;
            if(reachableSet.isEmpty()){
                return 0;
            }

            return bfs(reachableSet, endWord, wordSet, distance);
        }
    }

    // Bi-direction BFS 100%TC
    // Time: O(M^2*N), where M:len(word), N: number pof wordList
    // Space: O(M^2*N)
    static class Solution_II {
        public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if(!wordList.contains(endWord)) return 0;

            Set<String> beginSet=new HashSet<>();
            Set<String> endSet=new HashSet<>();
            Set<String> wordSet=new HashSet<>(wordList);

            beginSet.add(beginWord);
            endSet.add(endWord);

            return bfs(beginSet, endSet, wordSet,1);
        }

        static int bfs(Set<String> beginSet, Set<String> endSet, Set<String> wordSet, int distance){
            if(beginSet.size()>endSet.size()){
                return bfs(endSet, beginSet, wordSet, distance);
            }

            Set<String> reachableWords=new HashSet<>();
            wordSet.removeAll(beginSet);
            for(String word:beginSet){
                for(int pos=0;pos<word.length();pos++){
                    char[] charArray=word.toCharArray();
                    for(char c='a';c<='z';c++){
                        charArray[pos]=c;
                        String newWord=new String(charArray);
                        if(wordSet.contains(newWord)){
                            if(endSet.contains(newWord)){
                                return distance + 1;
                            }
                            reachableWords.add(newWord);
                            //wordSet.remove(newWord);
                        }
                    }
                }
            }
            distance++;
            if(reachableWords.size()==0){
                return 0;
            }
            else{
                return bfs(reachableWords, endSet, wordSet, distance);
            }
        }
    }
}
