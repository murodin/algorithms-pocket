public class BalancedBinaryTree {
    /*
           Given a binary tree, determine if it is height-balanced.
           For this problem, a height-balanced binary tree is defined as:
           a binary tree in which the left and right subtrees of every node differ in height by no more than 1.

            Example 1.
            Input: root = [3,9,20,null,null,15,7]
            Output: true
            Example 2.
            Input: root = [1,2,2,3,3,null,null,4,4]
            Output: false
            Example 3:

            Input: root = []
            Output: true


            Constraints:

            The number of nodes in the tree is in the range [0, 5000].
            -104 <= Node.val <= 104
     */

    public static void main(String[] args) {
        System.out.println("Done...");
    }

    /**
     * Definition for a binary tree node.
     * */
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

    // Time: O(N^2)
    // Space: O(1)
    static class Solution_I {
        public static boolean isBalanced(TreeNode root) {
            if(root == null) return true;
            if(Math.abs(height(root.right) - height(root.left)) > 1) return false;
            return isBalanced(root.left) && isBalanced(root.right);
        }

        private static int height(TreeNode node) {
            if(node == null) return 0;
            return Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    // Time: O(N)
    // Space: O(1)
    static class Solution_II {
        public static boolean isBalanced(TreeNode root) {
            if(root == null) return true;
            return height(root) != -1;
        }

        private static int height(TreeNode node) {
            if(node == null) return 0;
            int left = height(node.left);
            int right = height(node.right);
            int bf = Math.abs(left - right);

            if(bf > 1 || left == -1 || right == -1) return -1;
            return Math.max(left, right) + 1;
        }
    }
}
