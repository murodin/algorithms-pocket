import java.util.ArrayList;
import java.util.List;

public class TwoSumIVInputIsABST {
    /*
        Given the root of a Binary Search Tree and a target number k,
        return true if there exist two elements in the BST such that their sum is equal to the given target.

        Example 1.
        Input: root = [5,3,6,2,4,null,7], k = 9
        Output: true
        Example 2.
        Input: root = [5,3,6,2,4,null,7], k = 28
        Output: false


        Constraints:

        The number of nodes in the tree is in the range [1, 104].
        -104 <= Node.val <= 104
        root is guaranteed to be a valid binary search tree.
        -105 <= k <= 105
     */
    public static void main(String[] args) {
        System.out.println("Done.");
    }

    /**
     *Definition for a binary tree node.
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

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        public boolean findTarget(TreeNode root, int k) {
            if (root == null) return false;
            List<Integer> list = new ArrayList<>();
            inorder(root, list);
            int i = 0, j = list.size() - 1;
            while (i < j) {
                int sum = list.get(i) + list.get(j);
                if (sum == k) return true;
                else if (sum < k) i++;
                else j--;
            }
            return false;
        }

        private void inorder(TreeNode root, List<Integer> list) {
            if (root == null) return;
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }
    }
}
