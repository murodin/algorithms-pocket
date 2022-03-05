public class MaximumDepthOfBinaryTree {

    /*
        Given the root of a binary tree, return its maximum depth.
        A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

        Example 1.
        Input: root = [3,9,20,null,null,15,7]
        Output: 3
        Example 2.
        Input: root = [1,null,2]
        Output: 2


        Constraints:

        The number of nodes in the tree is in the range [0, 104].
        -100 <= Node.val <= 100
     */

    public static void main(String[] args) {
        System.out.println("Done.");
    }

    /*
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

    // Time: O(H), where H is height of the tree
    // Space: O(1)
    static class Solution {
        public static int maxDepth(TreeNode root) {
            if (root == null) return 0;
            int l = maxDepth(root.left);
            int r = maxDepth(root.right);
            return 1 + Math.max(l, r);
        }
    }
}
