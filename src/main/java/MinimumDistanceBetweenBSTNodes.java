import java.util.ArrayList;
import java.util.List;

public class MinimumDistanceBetweenBSTNodes {
    /*
        Given the root of a Binary Search Tree (BST), return the minimum difference between the values of any two different nodes in the tree.

        Example 1.
        Input: root = [4,2,6,1,3]
        Output: 1
        Example 2.
        Input: root = [1,0,48,null,null,12,49]
        Output: 1

        Constraints:

        The number of nodes in the tree is in the range [2, 100].
        0 <= Node.val <= 105


        Note: This question is the same as 530: https://leetcode.com/problems/minimum-absolute-difference-in-bst/
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
             this.right = right;}
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public static int minDiffInBST(TreeNode root) {
            List<Integer> list= new ArrayList<>();
            inOrderTraversal(root, list);
            int min=Integer.MAX_VALUE;
            for(int i=0; i<list.size()-1; i++){
                min= Math.min(min, list.get(i+1)-list.get(i));
            }
            return min;

        }
        public static void inOrderTraversal(TreeNode root, List<Integer> list){
            if(root==null) return;
            inOrderTraversal(root.left, list);
            list.add(root.val);
            inOrderTraversal(root.right, list);
        }
    }
}
