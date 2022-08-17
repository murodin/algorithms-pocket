import java.util.*;

public class WordLadderII {
    /*
        A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words beginWord -> s1 -> s2 -> ... -> sk such that:

        Every adjacent pair of words differs by a single letter.
        Every si for 1 <= i <= k is in wordList. Note that beginWord does not need to be in wordList.
        sk == endWord
        Given two words, beginWord and endWord, and a dictionary wordList,
        return all the shortest transformation sequences from beginWord to endWord, or an empty list if no such sequence exists.
         Each sequence should be returned as a list of the words [beginWord, s1, s2, ..., sk].

        Example 1.
        Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
        Output: [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
        Explanation: There are 2 shortest transformation sequences:
        "hit" -> "hot" -> "dot" -> "dog" -> "cog"
        "hit" -> "hot" -> "lot" -> "log" -> "cog"
        Example 2.
        Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
        Output: []
        Explanation: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.


        Constraints:

        1 <= beginWord.length <= 5
        endWord.length == beginWord.length
        1 <= wordList.length <= 500
        wordList[i].length == beginWord.length
        beginWord, endWord, and wordList[i] consist of lowercase English letters.
        beginWord != endWord
        All the words in wordList are unique.
        The code is super messy, but I hope you can follow it.

        The idea is rather simple:

        Find all shortest paths with BFS from beginWord to endWord, and while doing so build an adjacency Map<String,
         List<String>> - edges of the graph. This is a little bit tricky, because of the possible cycles,
          and we need to finish the whole BFS "level" even when the endWord is found somewhere in the middle - we need ALL possible shortest paths.
          Cycles are controlled by keeping a record of every element ever put into BFS queue.
        Traverse every possible path to endWord with DFS using pre-order traversal - this is the most straightforward part of the algorithm.
         We already know that the shortest path(-s) is somewhere there (thanks to BFS), we need to find it(them).
         The trickiest part is to control cycles, again, but with Set it's almost trivial at this point.
        Time complexity: all the heavy lifting is done during step 1. So, yeah, it's O( M * M * N), where M is the length of the word, and N is the size of the wordList.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.findLadders("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
    }

    // Time: O(NxM)
    // Space: O(NxM)
    static class Solution {
        private static final List<List<String>> result = new ArrayList<>();
        private static final List<String> runningDFSPath = new LinkedList<>();
        private static Set<String> visitedDuringDFS = new HashSet<>();

        public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
            Set<String> dictionary = new HashSet<>(wordList);
            if (!dictionary.contains(endWord)) {
                return List.of();
            }

            Map<String, List<String>> adjacency = buildAdjacency(beginWord, endWord, dictionary);
            findAllPaths(beginWord, endWord, adjacency);
            return result;
        }

        private static void findAllPaths(String beginWord, String endWord, Map<String, List<String>> adjacencies) {
            if (visitedDuringDFS.contains(beginWord)) {
                return;
            }

            if (beginWord.equals(endWord)) {
                runningDFSPath.add(endWord);
                result.add(new ArrayList<>(runningDFSPath));
                runningDFSPath.remove(endWord);
                visitedDuringDFS = new HashSet<>();
                return;
            }
            List<String> adj = adjacencies.get(beginWord);
            if (adj == null || adj.isEmpty()) {
                return;
            }
            visitedDuringDFS.add(beginWord);
            for (String adjWord : adj) {
                runningDFSPath.add(beginWord);
                findAllPaths(adjWord, endWord, adjacencies);
                runningDFSPath.remove(beginWord);
            }
        }

        private static Map<String, List<String>> buildAdjacency(String beginWord, String endWord, Set<String> dictionary) {
            Queue<String> bfsQueue = new LinkedList<>();
            bfsQueue.add(beginWord);
            dictionary.remove(beginWord);
            Map<String, List<String>> adjacencies = new HashMap<>();
            Set<String> wasInTheQueue = new HashSet<>();
            boolean endWordFound = false;
            while (!bfsQueue.isEmpty()) {
                int size = bfsQueue.size();
                Set<String> visited = new HashSet<>();
                for (int i = 0; i < size; i++) {
                    List<String> adj = new ArrayList<>();
                    String word = bfsQueue.poll();

                    for (int j = 0; j < word.length(); j++) {
                        for (char k = 'a'; k <= 'z'; k++) {
                            char[] arr = word.toCharArray();
                            if (arr[j] == k) {
                                continue;
                            }
                            arr[j] = k;

                            String str = new String(arr);
                            if (dictionary.contains(str)) {
                                adj.add(str);
                                if (!wasInTheQueue.contains(str)) {
                                    bfsQueue.add(str);
                                    wasInTheQueue.add(str);
                                }
                                visited.add(str);

                                if (str.equals(endWord)) {
                                    endWordFound = true;
                                }
                            }
                        }
                    }
                    adjacencies.put(word, adj);
                }
                if (endWordFound) {
                    break;
                }
                dictionary.removeAll(visited);
            }
            return adjacencies;
        }
    }
}
