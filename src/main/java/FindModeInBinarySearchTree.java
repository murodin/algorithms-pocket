import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindModeInBinarySearchTree {
    /*
        Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.
        If the tree has more than one mode, return them in any order.
        Assume a BST is defined as follows:
        The left subtree of a node contains only nodes with keys less than or equal to the node's key.
        The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
        Both the left and right subtrees must also be binary search trees.


        Example 1:
        Input: root = [1,null,2,2]
        Output: [2]
        Example 2:
        Input: root = [0]
        Output: [0]


        Constraints:

        The number of nodes in the tree is in the range [1, 104].
        -105 <= Node.val <= 105


        Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
     */
    public static void main(String[] args) {
        System.out.println("Done...");
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
        private Map<Integer, Integer> mp = new HashMap<>();
        private void isValid(TreeNode root) {
            if (root == null) return;
            isValid(root.left);
            mp.put(root.val, mp.getOrDefault(root.val, 0) + 1);
            isValid(root.right);
        }

        public int[] findMode(TreeNode root) {
            isValid(root);
            int maxi = 0;
            for (int value : mp.values()) {
                maxi = Math.max(maxi, value);
            }
            List<Integer> ans = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
                if (entry.getValue() == maxi) {
                    ans.add(entry.getKey());
                }
            }
            int[] result = new int[ans.size()];
            for (int i = 0; i < ans.size(); i++) {
                result[i] = ans.get(i);
            }
            return result;
        }
    }
}
