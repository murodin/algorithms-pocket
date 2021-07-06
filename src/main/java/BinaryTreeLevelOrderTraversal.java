
import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    /*
        Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
     */

    public static void main(String[] args) {
        TreeNode lr = new TreeNode(15);
        TreeNode ll = new TreeNode(7);
        TreeNode l = new TreeNode(20, ll, lr);
        TreeNode r = new TreeNode(9);
        TreeNode root = new TreeNode(3, l, r);

        System.out.println("Level Order:" + Solution.levelOrder(root));
        System.out.println("Level Order:" + Solution.levelOrderRecursive(root));
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

    static class Solution {
        public static List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> result = new ArrayList<>();
            if(root == null) return result;

            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> curLevel = new ArrayList<>();
                while (size-- > 0) {
                    TreeNode node = queue.poll();
                    curLevel.add(node.val);
                    if(node.left != null) queue.offer(node.left);
                    if(node.right != null) queue.offer(node.right);
                }
                result.add(curLevel);
            }
            return result;
        }

        public static List<List<Integer>> res;
        public static List<List<Integer>> levelOrderRecursive(TreeNode root) {
            res = new ArrayList<>();
            if(root == null) return res;
            traverse(root, 0);
            return res;
        }

        private static void traverse(TreeNode node, int level) {
            if (node == null) return;
            if(res.size() == level) res.add(new ArrayList<>());

            res.get(level).add(node.val);
            traverse(node.left, level+1);
            traverse(node.right, level+1);
        }
    }
}
