public class MinimumAbsoluteDifferenceInBST {
    /*
        Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.

        Example 1:


        Input: root = [4,2,6,1,3]
        Output: 1
        Example 2:


        Input: root = [1,0,48,null,null,12,49]
        Output: 1


        Constraints:

        The number of nodes in the tree is in the range [2, 104].
        0 <= Node.val <= 105


        Note: This question is the same as 783: https://leetcode.com/problems/minimum-distance-between-bst-nodes/
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
        int prev = Integer.MAX_VALUE;
        int ans = Integer.MAX_VALUE;
        public int getMinimumDifference(TreeNode root) {
            inOrder(root);
            return ans;
        }

        public void inOrder(TreeNode root){
            if(root.left!=null) inOrder(root.left);
            ans = Math.min(ans,Math.abs(root.val-prev));
            prev = root.val;
            if(root.right!=null) inOrder(root.right);
        }
    }
}
