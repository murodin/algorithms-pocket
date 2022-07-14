import java.util.HashMap;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    /*
        Given two integer arrays preorder and inorder where
        preorder is the preorder traversal of a binary tree and inorder is the inorder traversal of the same tree, construct and return the binary tree.

        Example 1.
        Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
        Output: [3,9,20,null,null,15,7]
        Example 2.
        Input: preorder = [-1], inorder = [-1]
        Output: [-1]


        Constraints:

        1 <= preorder.length <= 3000
        inorder.length == preorder.length
        -3000 <= preorder[i], inorder[i] <= 3000
        preorder and inorder consist of unique values.
        Each value of inorder also appears in preorder.
        preorder is guaranteed to be the preorder traversal of the tree.
        inorder is guaranteed to be the inorder traversal of the tree.
     */

    public static void main(String[] args) {
        System.out.println("Done");
    }

    // Time: O(N)
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

    static class Solution {
        static HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        static int pos = 0;

        static TreeNode build(int[] pre, int[] ino, int low, int hig){
            int index = map.get(pre[pos]);
            TreeNode node = new TreeNode(pre[pos]);
            if(low < index){
                pos++;
                node.left = build(pre, ino, low,index-1);
            }
            if(index < hig){
                pos++;
                node.right = build(pre, ino,index+1, hig);
            }
            return node;
        }

        public static TreeNode buildTree(int[] preorder, int[] inorder) {
            int c = 0;
            for(int values : inorder) map.put(values, c++);
            return build(preorder, inorder, 0, inorder.length-1);
        }
    }
}
