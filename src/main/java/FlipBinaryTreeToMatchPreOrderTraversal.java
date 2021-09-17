import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlipBinaryTreeToMatchPreOrderTraversal {

    /*
        You are given the root of a binary tree with n nodes, where each node is uniquely assigned a value from 1 to n.
        You are also given a sequence of n values voyage, which is the desired pre-order traversal of the binary tree.
        Any node in the binary tree can be flipped by swapping its left and right subtrees. For example, flipping node 1 will have the following effect:
        Flip the smallest number of nodes so that the pre-order traversal of the tree matches voyage.
        Return a list of the values of all flipped nodes. You may return the answer in any order.
        If it is impossible to flip the nodes in the tree to make the pre-order traversal match voyage, return the list [-1].

        Example 1.
        Input: root = [1,2], voyage = [2,1]
        Output: [-1]
        Explanation: It is impossible to flip the nodes such that the pre-order traversal matches voyage.
        Example 2.
        Input: root = [1,2,3], voyage = [1,3,2]
        Output: [1]
        Explanation: Flipping node 1 swaps nodes 2 and 3, so the pre-order traversal matches voyage.
        Example 3.
        Input: root = [1,2,3], voyage = [1,2,3]
        Output: []
        Explanation: The tree's pre-order traversal already matches voyage, so no nodes need to be flipped.
     */

    public static void main(String[] args) {
        TreeNode l = new TreeNode(2);
        TreeNode r = new TreeNode(3);
        TreeNode root = new TreeNode(1, l, r);

        int[] testVoyage = {1,3,2};
        System.out.println("Result :" + Solution.flipMatchVoyage(root, testVoyage));
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // Time: O(N)
    // Space: O(N)
    static class Solution {
        static int i;
        static List<Integer> res;
        static int[] v;
        static public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
            i = 0;
            res = new ArrayList<>();
            v=voyage;
            return dfs(root) ? res : Arrays.asList(-1);
        }

        static public boolean dfs(TreeNode node) {
            if(node==null) return true;
            if(node.val != v[i++]) return false;
            if(node.left != null && node.left.val != v[i]) {
                res.add(node.val);
                return dfs(node.right) && dfs(node.left);
            }
            else {
                return dfs(node.left) && dfs(node.right);
            }
        }
    }
}
