import java.util.Arrays;

public class LongestPathWithDifferentAdjacentCharacters {
    /*
        You are given a tree (i.e. a connected, undirected graph that has no cycles) rooted at node 0 consisting of n nodes numbered from 0 to n - 1.
        The tree is represented by a 0-indexed array parent of size n, where parent[i] is the parent of node i. Since node 0 is the root, parent[0] == -1.
        You are also given a string s of length n, where s[i] is the character assigned to node i.
        Return the length of the longest path in the tree such that no pair of adjacent nodes on the path have the same character assigned to them.

        Example 1.
        Input: parent = [-1,0,0,1,1,2], s = "abacbe"
        Output: 3
        Explanation: The longest path where each two adjacent nodes have different characters in the tree is the path: 0 -> 1 -> 3. The length of this path is 3, so 3 is returned.
        It can be proven that there is no longer path that satisfies the conditions.

        Example 2.
        Input: parent = [-1,0,0,0], s = "aabc"
        Output: 3
        Explanation: The longest path where each two adjacent nodes have different characters is the path: 2 -> 0 -> 3. The length of this path is 3, so 3 is returned.


        Constraints:

        n == parent.length == s.length
        1 <= n <= 105
        0 <= parent[i] <= n - 1 for all i >= 1
        parent[0] == -1
        parent represents a valid tree.
        s consists of only lowercase English letters.
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Solution.longestPath(new int[]{-1,0,0,1,1,2}, "abacbe"));
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int longestPath(int[] parents, String s) {
            char[] a = s.toCharArray();
            int n = a.length, res = 1;
            int[] degree = new int[n];
            for (int i = 1; i < n; i++) {
                degree[parents[i]]++;
            }
            int[] q = new int[n];
            int qIdx = -1;
            for (int i = 1; i < n; i++) {
                if (degree[i] == 0) q[++qIdx] = i;
            }

            int[] path = new int[n];
            Arrays.fill(path, 1);

            while (qIdx >= 0) {
                int c = q[qIdx--];
                int p = parents[c];
                if (--degree[p] == 0 && p != 0) q[++qIdx] = p;
                if (a[p] == a[c]) continue;
                res = Math.max(res, path[p] + path[c]);
                path[p] = Math.max(path[p], path[c] + 1);
            }
            return res;
        }
    }
}
