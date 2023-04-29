public class LongestZigZagPathInABinaryTree {
    /*
        You are given the root of a binary tree.
        A ZigZag path for a binary tree is defined as follow:
        Choose any node in the binary tree and a direction (right or left).
        If the current direction is right, move to the right child of the current node; otherwise, move to the left child.
        Change the direction from right to left or from left to right.
        Repeat the second and third steps until you can't move in the tree.
        Zigzag length is defined as the number of nodes visited - 1. (A single node has a length of 0).

        Return the longest ZigZag path contained in that tree.

        Example 1.
        Input: root = [1,null,1,1,1,null,null,1,1,null,1,null,null,null,1,null,1]
        Output: 3
        Explanation: Longest ZigZag path in blue nodes (right -> left -> right).
        Example 2.
        Input: root = [1,1,1,null,1,null,null,1,1,null,1]
        Output: 4
        Explanation: Longest ZigZag path in blue nodes (left -> right -> left -> right).
        Example 3.
        Input: root = [1]
        Output: 0


        Constraints:

        The number of nodes in the tree is in the range [1, 5 * 104].
        1 <= Node.val <= 100
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    // Time: O(NLogN)
    // Space: O(N)
    /**
     * Definition for a binary tree node.
     **/
    public static class TreeNode {
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

    static public class Solution {
        public static int longestZigZag(TreeNode root) {
            int[] res = dfs(root);
            return res[2];
        }

        private static int[] dfs(TreeNode node) {
            if (node == null) {
                return new int[]{-1, -1, -1};
            }

            int[] leftSubtree = dfs(node.left);
            int[] rightSubtree = dfs(node.right);

            int leftLength = leftSubtree[1] + 1;
            int rightLength = rightSubtree[0] + 1;
            int maxLen = Math.max(Math.max(leftLength, rightLength), Math.max(leftSubtree[2], rightSubtree[2]));

            return new int[]{leftLength, rightLength, maxLen};
        }
    }
}
