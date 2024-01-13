public class MaximumSumBSTInBinaryTree {
    /*
        Given a binary tree root, return the maximum sum of all keys of any sub-tree which is also a Binary Search Tree (BST).
        Assume a BST is defined as follows:
        The left subtree of a node contains only nodes with keys less than the node's key.
        The right subtree of a node contains only nodes with keys greater than the node's key.
        Both the left and right subtrees must also be binary search trees.

        Example 1:
        Input: root = [1,4,3,2,4,2,5,null,null,null,null,null,null,4,6]
        Output: 20
        Explanation: Maximum sum in a valid Binary search tree is obtained in root node with key equal to 3.
        Example 2:
        Input: root = [4,3,null,1,2]
        Output: 2
        Explanation: Maximum sum in a valid Binary search tree is obtained in a single root node with key equal to 2.
        Example 3:
        Input: root = [-4,-2,-5]
        Output: 0
        Explanation: All values are negatives. Return an empty BST.


        Constraints:

        The number of nodes in the tree is in the range [1, 4 * 104].
        -4 * 104 <= Node.val <= 4 * 104
     */
    public static void main(String[] args) {
        System.out.println("Done...");
    }

    // Time: O(N)
    // Space: O(1)
    /**
        Definition for a binary tree node.
    **/
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

    class Solution {
        private int maxSum = 0;
        public int maxSumBST(TreeNode root) {
            postOrderTraverse(root);
            return maxSum;
        }
        private int[] postOrderTraverse(TreeNode root) {
            if (root == null) return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
            int[] left = postOrderTraverse(root.left);
            int[] right = postOrderTraverse(root.right);
            if (!(left != null
                    && right != null
                    && root.val > left[1]
                    && root.val < right[0]))
                return null;
            int sum = root.val + left[2] + right[2];
            maxSum = Math.max(maxSum, sum);
            int min = Math.min(root.val, left[0]);
            int max = Math.max(root.val, right[1]);
            return new int[]{min, max, sum};
        }
    }
}
