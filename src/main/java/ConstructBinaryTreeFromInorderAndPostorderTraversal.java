public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    /*
        Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder is the postorder traversal of the same tree,
        construct and return the binary tree.

        Example 1.
        Input: inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
        Output: [3,9,20,null,null,15,7]
        Example 2.
        Input: inorder = [-1], postorder = [-1]
        Output: [-1]


        Constraints:

        1 <= inorder.length <= 3000
        postorder.length == inorder.length
        -3000 <= inorder[i], postorder[i] <= 3000
        inorder and postorder consist of unique values.
        Each value of postorder also appears in inorder.
        inorder is guaranteed to be the inorder traversal of the tree.
        postorder is guaranteed to be the postorder traversal of the tree.
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    /**
     * Definition for a binary tree node.
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
        public TreeNode buildTree(int[] inorder, int[] postorder) {
            return buildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
        }

        private TreeNode buildTree(int[] inorder, int inStart, int inEnd, int[] postorder, int postStart, int postEnd) {
            if (inStart > inEnd || postStart > postEnd) {
                return null;
            }
            int rootVal = postorder[postEnd];
            TreeNode root = new TreeNode(rootVal);
            int rootIndex = 0;
            for (int i = inStart; i <= inEnd; i++) {
                if (inorder[i] == rootVal) {
                    rootIndex = i;
                    break;
                }
            }
            int leftSize = rootIndex - inStart;
            int rightSize = inEnd - rootIndex;
            root.left = buildTree(inorder, inStart, rootIndex - 1, postorder, postStart, postStart + leftSize - 1);
            root.right = buildTree(inorder, rootIndex + 1, inEnd, postorder, postEnd - rightSize, postEnd - 1);
            return root;
        }
    }
}
