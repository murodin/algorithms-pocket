import java.util.ArrayList;
import java.util.Arrays;

public class MaximumGeneticDifferenceQuery {
    /*
        There is a rooted tree consisting of n nodes numbered 0 to n - 1.
        Each node's number denotes its unique genetic value (i.e. the genetic value of node x is x). The genetic difference between two genetic values is defined as the bitwise-XOR of their values. You are given the integer array parents,
        where parents[i] is the parent for node i. If node x is the root of the tree, then parents[x] == -1.
        You are also given the array queries where queries[i] = [nodei, vali]. For each query i, find the maximum genetic difference between vali and pi,
        where pi is the genetic value of any node that is on the path between nodei and the root (including nodei and the root).
        More formally, you want to maximize vali XOR pi.

        Return an array ans where ans[i] is the answer to the ith query.

        Example 1.
        Input: parents = [-1,0,1,1], queries = [[0,2],[3,2],[2,5]]
        Output: [2,3,7]
        Explanation: The queries are processed as follows:
        - [0,2]: The node with the maximum genetic difference is 0, with a difference of 2 XOR 0 = 2.
        - [3,2]: The node with the maximum genetic difference is 1, with a difference of 2 XOR 1 = 3.
        - [2,5]: The node with the maximum genetic difference is 2, with a difference of 5 XOR 2 = 7.
        Example 2.
        Input: parents = [3,7,-1,2,0,7,0,2], queries = [[4,6],[1,15],[0,5]]
        Output: [6,14,7]
        Explanation: The queries are processed as follows:
        - [4,6]: The node with the maximum genetic difference is 0, with a difference of 6 XOR 0 = 6.
        - [1,15]: The node with the maximum genetic difference is 1, with a difference of 15 XOR 1 = 14.
        - [0,5]: The node with the maximum genetic difference is 2, with a difference of 5 XOR 2 = 7.


        Constraints:

        2 <= parents.length <= 105
        0 <= parents[i] <= parents.length - 1 for every node i that is not the root.
        parents[root] == -1
        1 <= queries.length <= 3 * 104
        0 <= nodei <= parents.length - 1
        0 <= vali <= 2 * 105
     */
    public static void main(String[] args) {
        System.out.println("Solution: " + Arrays.toString(Solution.maxGeneticDifference(new int[]{-1, 0, 1, 1}, new int[][]{{0, 2}, {3, 2}, {2, 5}})));
    }

    // Time:
    // Space:
    static class TrieNode {
        TrieNode[] child = new TrieNode[2];
        int go = 0; // Number of elements goes through this node
        public void increase(int number, int d) {
            TrieNode cur = this;
            for (int i = 17; i >= 0; --i) {
                int bit = (number >> i) & 1;
                if (cur.child[bit] == null) cur.child[bit] = new TrieNode();
                cur = cur.child[bit];
                cur.go += d;
            }
        }
        public int findMax(int number) {
            TrieNode cur = this;
            int ans = 0;
            for (int i = 17; i >= 0; --i) {
                int bit = (number >> i) & 1;
                if (cur.child[1 - bit] != null && cur.child[1 - bit].go > 0) {
                    cur = cur.child[1 - bit];
                    ans |= (1 << i);
                } else cur = cur.child[bit];
            }
            return ans;
        }
    }

    static class Solution { // 215 ms, faster than 100.00%
        static TrieNode trieRoot = new TrieNode();
        public static int[] maxGeneticDifference(int[] parents, int[][] qs) {
            int n = parents.length, m = qs.length, root = -1;
            ArrayList<Integer>[] graph = new ArrayList[n];
            for (int i = 0; i < n; ++i) graph[i] = new ArrayList<>();
            for (int i = 0; i < n; ++i)
                if (parents[i] == -1) root = i;
                else graph[parents[i]].add(i);

            ArrayList<int[]>[] queryByNode = new ArrayList[n];
            for (int i = 0; i < n; ++i) queryByNode[i] = new ArrayList<>();
            for (int i = 0; i < m; ++i)
                queryByNode[qs[i][0]].add(new int[]{qs[i][1], i}); // node -> list of pairs (val, idx)

            int[] ans = new int[m];
            dfs(root, graph, queryByNode, ans);
            return ans;
        }
        static void dfs(int u, ArrayList<Integer>[] graph, ArrayList<int[]>[] queryByNode, int[] ans) {
            trieRoot.increase(u, 1);
            for (int[] p : queryByNode[u])
                ans[p[1]] = trieRoot.findMax(p[0]);
            for (int v: graph[u])
                dfs(v, graph, queryByNode, ans);
            trieRoot.increase(u, -1);
        }
    }
}
