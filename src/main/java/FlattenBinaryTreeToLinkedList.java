public class FlattenBinaryTreeToLinkedList {

    /*
        Given the root of a binary tree, flatten the tree into a "linked list":
        The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
        The "linked list" should be in the same order as a pre-order traversal of the binary tree.
        Example:
        Input: root = [1,2,5,3,4,null,6]
        Output: [1,null,2,null,3,null,4,null,5,null,6]

         Can you flatten the tree in-place (with O(1) extra space)?
     */

    public static void main(String[] args) {
        TreeNode testNode = new TreeNode(
                1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(5, null, new TreeNode(6))
        );

        Solution.flatten(testNode);
        System.out.println("Flatten Linked List:" +testNode.toString());
    }

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

    static class Solution {

        // Time: O(N)
        // Space: O(Height of Tree)
        public static void flatten(TreeNode root) {
            if(root == null) return;
            TreeNode tempLeft = root.left;
            TreeNode tempRight = root.right;

            root.left = null;
            flatten(tempLeft);
            flatten(tempRight);

            root.right = tempLeft;
            TreeNode current = root;
            while (current.right != null) current = current.right;
            current.right = tempRight;
        }

        // Time: O(N)
        // Space: O(1)
        // Morris Traversal
        public static void flattenMorris(TreeNode root) {
            if (root == null) return;
            while (root != null) {
                if (root.left != null) {
                    TreeNode left = root.left;
                    TreeNode current = left;
                    while (current.right != null) current = current.right;
                    current.right = root.right; // Morris Traversal main step(!!!)
                    root.left = null;
                    root.right = left;
                }
                root = root.right;
            }
        }
    }
}
