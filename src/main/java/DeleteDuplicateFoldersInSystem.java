import java.util.*;

public class DeleteDuplicateFoldersInSystem {
    /*
        Due to a bug, there are many duplicate folders in a file system.
        You are given a 2D array paths, where paths[i] is an array representing an absolute path to the ith folder in the file system.
        For example, ["one", "two", "three"] represents the path "/one/two/three".
        Two folders (not necessarily on the same level) are identical
        if they contain the same non-empty set of identical subfolders and underlying subfolder structure.
        The folders do not need to be at the root level to be identical. If two or more folders are identical, then mark the folders as well as all their subfolders.
        For example, folders "/a" and "/b" in the file structure below are identical. They (as well as their subfolders) should all be marked:
        /a
        /a/x
        /a/x/y
        /a/z
        /b
        /b/x
        /b/x/y
        /b/z
        However, if the file structure also included the path "/b/w", then the folders "/a" and "/b" would not be identical. Note that "/a/x" and "/b/x" would still be considered identical even with the added folder.
        Once all the identical folders and their subfolders have been marked, the file system will delete all of them. The file system only runs the deletion once, so any folders that become identical after the initial deletion are not deleted.

        Return the 2D array ans containing the paths of the remaining folders after deleting all the marked folders. The paths may be returned in any order.

        Example 1.
        Input: paths = [["a"],["c"],["d"],["a","b"],["c","b"],["d","a"]]
        Output: [["d"],["d","a"]]
        Explanation: The file structure is as shown.
        Folders "/a" and "/c" (and their subfolders) are marked for deletion because they both contain an empty
        folder named "b".
        Example 2.
        Input: paths = [["a"],["c"],["a","b"],["c","b"],["a","b","x"],["a","b","x","y"],["w"],["w","y"]]
        Output: [["c"],["c","b"],["a"],["a","b"]]
        Explanation: The file structure is as shown.
        Folders "/a/b/x" and "/w" (and their subfolders) are marked for deletion because they both contain an empty folder named "y".
        Note that folders "/a" and "/c" are identical after the deletion, but they are not deleted because they were not marked beforehand.
        Example 3.
        Input: paths = [["a","b"],["c","d"],["c"],["a"]]
        Output: [["c"],["c","d"],["a"],["a","b"]]
        Explanation: All folders are unique in the file system.
        Note that the returned array can be in a different order as the order does not matter.


        Constraints:

        1 <= paths.length <= 2 * 104
        1 <= paths[i].length <= 500
        1 <= paths[i][j].length <= 10
        1 <= sum(paths[i][j].length) <= 2 * 105
        path[i][j] consists of lowercase English letters.
        No two paths lead to the same folder.
        For any folder not at the root level, its parent folder will also be in the input.
     */
    public static void main(String[] args) {
        System.out.println("Done..");
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        static class TrieNode {
            // Use TreeMap so keys stay in the same order for any 2 nodes, so when we
            // serialize 2 nodes with identical structures, we will get the same result
            TreeMap<String, TrieNode> next;
            List<Integer> indices;
            String contents;

            TrieNode() {
                next = new TreeMap();
                indices = new ArrayList();
            }

            TrieNode addChild(String dir) {
                if (next.containsKey(dir))
                    return next.get(dir);

                next.put(dir, new TrieNode());
                return next.get(dir);
            }

            void addIndex(int index) {
                indices.add(index);
            }

            String serialize() {
                if (contents != null)
                    return contents;

                StringBuilder sb = new StringBuilder();
                next.forEach((k, v) -> {
                    sb.append(k);
                    sb.append(":");
                    sb.append(v.serialize());
                    // Need to use separate delimiter for same level
                    sb.append(",");
                });

                return contents = sb.toString();
            }
        }

        public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
            TrieNode root = buildTrie(paths);
            // Store the indicies of the paths that correspond to duplicate directories
            Set<Integer> duplicates = new HashSet();
            findDuplicates(root, new HashMap<String, List<Integer>>(), duplicates);
            List<List<String>> res = new ArrayList();
            for (int i = 0; i < paths.size(); ++i) {
                if (!duplicates.contains(i))
                    res.add(paths.get(i));
            }

            return res;
        }

        private String findDuplicates(TrieNode node, Map<String, List<Integer>> directories, Set<Integer> dups) {
            for (TrieNode child : node.next.values()) {
                String key = findDuplicates(child, directories, dups);
                if (key.isEmpty())
                    continue;

                if (directories.containsKey(key)) {
                    dups.addAll(child.indices);
                    dups.addAll(directories.get(key));
                } else
                    directories.put(key, child.indices);
            }

            return node.serialize();
        }

        private TrieNode buildTrie(List<List<String>> paths) {
            TrieNode root = new TrieNode();
            for (int i = 0; i < paths.size(); ++i) {
                TrieNode node = root;
                for (String dir : paths.get(i)) {
                    node = node.addChild(dir);
                    // Add the index so we know if this directory ends up being a
                    // duplicate, we will know which paths were associated with it
                    node.addIndex(i);
                }
            }
            return root;
        }
    }
}
