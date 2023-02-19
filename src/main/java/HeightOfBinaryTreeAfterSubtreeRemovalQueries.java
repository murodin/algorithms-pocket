import java.util.HashMap;
import java.util.Map;

public class HeightOfBinaryTreeAfterSubtreeRemovalQueries {
    /*
        You are given the root of a binary tree with n nodes. Each node is assigned a unique value from 1 to n. You are also given an array queries of size m.
        You have to perform m independent queries on the tree where in the ith query you do the following:
        Remove the subtree rooted at the node with the value queries[i] from the tree. It is guaranteed that queries[i] will not be equal to the value of the root.
        Return an array answer of size m where answer[i] is the height of the tree after performing the ith query.

        Note:
        The queries are independent, so the tree returns to its initial state after each query.
        The height of a tree is the number of edges in the longest simple path from the root to some node in the tree.

        Example 1.
        Input: root = [1,3,4,2,null,6,5,null,null,null,null,null,7], queries = [4]
        Output: [2]
        Explanation: The diagram above shows the tree after removing the subtree rooted at node with value 4.
        The height of the tree is 2 (The path 1 -> 3 -> 2).
        Example 2.
        Input: root = [5,8,9,2,1,3,7,4,6], queries = [3,2,4,8]
        Output: [3,2,3,2]
        Explanation: We have the following queries:
        - Removing the subtree rooted at node with value 3. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 4).
        - Removing the subtree rooted at node with value 2. The height of the tree becomes 2 (The path 5 -> 8 -> 1).
        - Removing the subtree rooted at node with value 4. The height of the tree becomes 3 (The path 5 -> 8 -> 2 -> 6).
        - Removing the subtree rooted at node with value 8. The height of the tree becomes 2 (The path 5 -> 9 -> 3).


        Constraints:

        The number of nodes in the tree is n.
        2 <= n <= 105
        1 <= Node.val <= n
        All the values in the tree are unique.
        m == queries.length
        1 <= m <= min(n, 104)
        1 <= queries[i] <= n
        queries[i] != root.val
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    /**
     * Definition for a binary tree node.
     */
     public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
     }

    // Time: O(MLogN), where M is queries and N is the number of nodes.
    // Space: O(MxN)
    static class Solution {
        static Map<Integer, CustomTreeNode> valToCustomNode = new HashMap<>();
        public int[] treeQueries(TreeNode root, int[] queries) {
            CustomTreeNode customRoot = buildCustomTree(null, root);
            setNodeHeights(customRoot);
            int m = queries.length;
            int[] res = new int[m];

            for (int i = 0; i < m; i++) {
                int query = queries[i];

                CustomTreeNode impactedNode = valToCustomNode.get(query);
                int deltaOfDeletion = impactedNode.treeHeight();

                while (impactedNode.parent != null) {
                    CustomTreeNode parent = impactedNode.parent;
                    int curNewMaxHeight = 0;
                    if (parent.right != null && parent.right.val == impactedNode.val) {
                        int newRightHeight = parent.rightTreeHeight - deltaOfDeletion;
                        curNewMaxHeight = Math.max(parent.leftTreeHeight, newRightHeight);
                    } else {
                        int newLeftHeight = parent.leftTreeHeight - deltaOfDeletion;
                        curNewMaxHeight = Math.max(newLeftHeight, parent.rightTreeHeight);
                    }

                    deltaOfDeletion = parent.treeHeight() - curNewMaxHeight;
                    impactedNode = parent;

                    if (deltaOfDeletion == 0) break; // Optimization
                }
                res[i] = customRoot.treeHeight() - deltaOfDeletion - 1;

            }

            return res;
        }

       static private CustomTreeNode buildCustomTree(CustomTreeNode parent, TreeNode root) {
            if (root == null) return null;

            CustomTreeNode node = new CustomTreeNode(root.val, parent);
            node.setRightTree(buildCustomTree(node, root.right));
            node.setLeftTree(buildCustomTree(node, root.left));
            valToCustomNode.put(node.val, node);

            return node;
        }

        private int setNodeHeights(CustomTreeNode node) {
            if (node == null) return 0;

            int leftNodeHeight = setNodeHeights(node.left);
            int rightNodeHeight = setNodeHeights(node.right);
            node.leftTreeHeight = 1 + leftNodeHeight;
            node.rightTreeHeight = 1 + rightNodeHeight;
            return node.treeHeight();

        }

        static class CustomTreeNode {
            public int val;
            public CustomTreeNode parent;
            public CustomTreeNode left;
            public CustomTreeNode right;
            public int leftTreeHeight;
            public int rightTreeHeight;

            public CustomTreeNode(int val, CustomTreeNode parent) {
                this.val = val;
                this.parent = parent;
            }

            public void setRightTree(CustomTreeNode rightNode) {
                this.right = rightNode;
            }

            public void setLeftTree(CustomTreeNode leftNode) {
                this.left = leftNode;
            }

            public int treeHeight() {
                return Math.max(leftTreeHeight, rightTreeHeight);
            }

            @Override
            public String toString() {
                return "CustomTreeNode{" +
                        "val=" + val +
                        ", leftTreeHeight=" + leftTreeHeight +
                        ", rightTreeHeight=" + rightTreeHeight +
                        '}';
            }
        }
    }
}
